package model;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Implementation of the world.
 */
public class WorldImpl implements World {

  private Readable fileReader;
  private Target target;
  private Pet pet;
  private int row;
  private int column;
  private int numberOfSpaces;
  private List<String> spaceNames;
  private List<String> playerNames;
  private List<String> weaponNames;
  private Map<String, Space> spaces;
  private Map<String, Weapon> weapons;
  private Map<String, Player> players;
  private Map<Integer, List<Integer>> spaceToNeighbor;
  private List<Integer> visitedList;
  private int visitedIndex;
  private int turn;
  private RandomGenerator random;
  private int maxMoves;
  private String winner;

  /**
   * Constructor.
   * 
   * @param fileReader the file reader to read the file.
   * @param random     the random number generator.
   * @param maxMoves   the maximum number of moves after which the game ends.
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public WorldImpl(Readable fileReader, RandomGenerator random, int maxMoves) {
    if (fileReader == null) {
      throw new IllegalArgumentException("File is empty");
    }
    if (random == null) {
      throw new IllegalArgumentException("Random number generator not setup");
    }
    if (maxMoves <= 0) {
      throw new IllegalArgumentException("Maximum moves cannot be negative or zero.");
    }
    this.fileReader = fileReader;
    this.spaceNames = new ArrayList<String>();
    this.playerNames = new ArrayList<String>();
    this.weaponNames = new ArrayList<String>();
    this.spaces = new HashMap<>();
    this.weapons = new HashMap<>();
    this.players = new HashMap<>();
    this.spaceToNeighbor = new HashMap<>();
    this.visitedList = new ArrayList<>();
    this.turn = 0;
    this.visitedIndex = 1;
    this.random = random;
    this.maxMoves = maxMoves;
    this.winner = "";

  }

  @Override
  public String drawWorld() {
    BufferedImage image = new BufferedImage(row * 80, column * 80, BufferedImage.TYPE_INT_RGB);

    java.awt.Graphics g = image.getGraphics();
    for (int i = 0; i < spaceNames.size(); i++) {
      int x1 = spaces.get(spaceNames.get(i)).getTopLeftX();
      int y1 = spaces.get(spaceNames.get(i)).getTopLeftY();
      int x2 = spaces.get(spaceNames.get(i)).getBottomRightX();
      int y2 = spaces.get(spaceNames.get(i)).getBottomRightY();

      int distanceX = x2 - x1;
      int distanceY = y2 - y1;
      g.drawRect(y1 * 40, x1 * 40, (distanceY * 40) + 40, (distanceX * 40) + 40);
      g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 18));
      g.drawString(spaceNames.get(i), y1 * 40 + 20, x1 * 40 + 20);
    }
    try {
      ImageIO.write(image, "png", new File("res/imageTrial.png"));
      return "The file is saved in res/imageTrial.png";
    } catch (IOException ioe) {
      throw new IllegalArgumentException("File path not found.");
    }
  }

  @Override
  public String displaySpaceInfo() {
    StringBuilder spaceInfo = new StringBuilder();
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    Player player = this.players.get(playerNames.get(this.turn));
    spaceInfo.append(String.format("%s, this is the info about the space you are in:\n",
        player.getPlayerName()));
    spaceInfo.append(displaySpaceInfo(player.getCurrentIndex()));
    spaceInfo.append(String.format("The target %s is in %s\n", this.target.getName(),
        this.spaceNames.get(target.getCurrentIndex())));
    return spaceInfo.toString();
  }

  /**
   * Displays the information of the space whose index is given.
   * 
   * @param index index of the space whose information is needed.
   * @return the String containing the information of the space
   */
  private String displaySpaceInfo(int index) {
    Space space = spaces.get(spaceNames.get(index));
    StringBuilder spaceInfo = new StringBuilder();
    spaceInfo.append(String.format("\nThe name of the space is:%s\n\n", space.getName()));
    String weapons = space.getWeaponsInSpace();
    if ("".equals(weapons)) {
      spaceInfo.append("There are no wepaons in this space\n\n");
    } else {
      spaceInfo.append(String.format("Weapons in space are:\n%s\n", weapons));
    }
    String neighbors = space.getNeighbor(this.pet.getLocation());
    if ("".equals(neighbors)) {
      spaceInfo.append("This space has no neighbors\n\n");
    } else {
      spaceInfo.append(String.format("Neighbors of space are:\n%s\n", neighbors));
    }
    String players = space.getPlayersInSpace();
    if ("".equals(players)) {
      spaceInfo.append("This space has no players\n\n");
    } else {
      spaceInfo.append(String.format("Players in the space are:\n%s\n", players));
    }
    if (pet.getLocation() == space.getIndex()) {
      spaceInfo.append(String.format("Target's pet %s is in this space\n", this.pet.getPetName()));
    }
    return spaceInfo.toString();
  }

  @Override
  public String createPlayer(String playerName, String currentSpaceName, boolean isComputer) {
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Player name cannot be null\n");
    }
    if (currentSpaceName == null || "".equals(currentSpaceName) 
        || !this.spaceNames.contains(currentSpaceName)) {
      throw new IllegalArgumentException("Space does not exist\n");
    }
    int index = this.spaces.get(currentSpaceName).getIndex();
    PlayerImpl player = new PlayerImpl(playerName, index, isComputer);
    playerNames.add(playerName);
    players.put(playerName, player);
    this.spaces.get(currentSpaceName).addPlayerToSpace(player);
    return String.format("Player %s created at %s\n", playerName, currentSpaceName);
  }

  @Override
  public String pickWeapon(String weaponName) {
    if (weaponName == null || "".equals(weaponName)) {
      throw new IllegalArgumentException("Weapon name is null");
    }
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    Player player = this.players.get(playerNames.get(this.turn));
    updateTurn();
    if (player.getWeaponsPossessed().split("\n").length > player.getMaxWeapons()) {
      throw new IllegalArgumentException(
          "You already have the maximum number of weapons that you can have. "
              + "You cannot pick another one.\n");
    }
    Weapon weapon = this.weapons.get(weaponName);
    Space space = this.spaces.get(spaceNames.get(player.getCurrentIndex()));
    if (space.getWeaponsInSpace().contains(weaponName)) {
      space.removeWeaponFromSpace(weapon);
      player.addWeapon(weapon);
    } else {
      throw new IllegalArgumentException("Weapon is not in space\n");
    }
    target.moveTarget(numberOfSpaces);
    return String.format("Weapon %s picked by %s", weaponName, player.getPlayerName());
  }

  @Override
  public String displayPlayerInfo(String playerName) {
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Create atleast one player to start the game\n");
    }
    if ("-1".equals(playerName)) {
      return String.format("%s\n%s\n%s", target.getName(), target.getHealth(),
          this.spaceNames.get(target.getCurrentIndex()));
    }
    Player player = this.players.get(playerName);
    if (player == null) {
      throw new IllegalArgumentException("Player does not exist\n");
    }
    return player.displayPlayerInfo();
  }

  @Override
  public String lookAround() {
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    String playerName = playerNames.get(this.turn);
    Player player = this.players.get(playerName);
    int currentIndex = player.getCurrentIndex();
    StringBuilder returnValue = new StringBuilder();
    returnValue.append(String.format("\nInfo about %s's space\n", playerName));
    returnValue.append(this.displaySpaceInfo(currentIndex));
    returnValue.append("\nInfo about neighboring spaces:\n");
    String visibleSpaces = this.spaces.get(spaceNames.get(currentIndex))
        .getNeighbor(this.pet.getLocation());
    String[] visibleArray = visibleSpaces.split("\n");
    for (int i = 1; i < visibleArray.length; i++) {
      Space neighborSpace = spaces.get(visibleArray[i]);
      int spaceIndex = neighborSpace.getIndex();
      returnValue.append(this.displaySpaceInfo(spaceIndex));
    }
    updateTurn();
    target.moveTarget(numberOfSpaces);
    return returnValue.toString();
  }

  @Override
  public String attackTarget(String weaponName) {
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }

    int weaponIndex;
    if ("Poke in the eye".equals(weaponName)) {
      weaponIndex = this.weaponNames.size();
    } else if (!this.weaponNames.contains(weaponName)) {
      throw new IllegalArgumentException("Weapon does not exist.");
    } else {
      weaponIndex = this.weapons.get(weaponName).getWeaponIndex();
    }

    String playerName = playerNames.get(this.turn);
    Player player = this.players.get(playerName);
    if (player.getCurrentIndex() != target.getCurrentIndex()) {
      if (weaponIndex != this.weaponNames.size()) {
        Weapon weapon = weapons.get(weaponNames.get(weaponIndex));
        this.weapons.remove(weapon.getWeaponName());
        this.weaponNames.remove(weapon.getWeaponIndex());
        player.removeWeapon(weapon);
      }
      this.updateTurn();
      throw new IllegalArgumentException(
          "Target should be in the same space to be able to attack\n");
    }
    if (weaponIndex == this.weaponNames.size()) {
      if (checkVisibility(playerName)) {
        updateTurn();
        throw new IllegalArgumentException(
            "Oops! Players are watching you! You cannot attack the target now!\n");
      }
      updateTurn();
      target.updateHealth(1);
    } else {
      if (player.getWeaponsPossessed().contains(weaponName)) {
        Weapon weapon = weapons.get(weaponNames.get(weaponIndex));
        this.weapons.remove(weapon.getWeaponName());
        this.weaponNames.remove(weaponName);
        player.removeWeapon(weapon);
        if (checkVisibility(playerName)) {
          updateTurn();
          throw new IllegalArgumentException(
              "Oops! Players are watching you! You cannot attack the target now!\n");
        }
        int weaponDamage = weapon.getDamage();
        updateTurn();
        target.updateHealth(weaponDamage);
      } else {
        throw new IllegalArgumentException("You do not have the weapon\n");
      }
    }
    if (target.getHealth() <= 0) {
      this.winner = playerName;
      return String.format("Target %s died!", this.target.getName());
    }
    target.moveTarget(numberOfSpaces);
    return String.format("Target %s attacked! His health is now %s", this.target.getName(),
        this.target.getHealth());
  }

  @Override
  public String getTurn() {
    if (playerNames.size() != 0) {
      if (this.players.get(playerNames.get(this.turn)).isComputer()) {
        return "Computer Player " + playerNames.get(this.turn);
      }
      return playerNames.get(this.turn);
    }
    return "";
  }

  @Override
  public String[] spaceToString() {
    String[] spaceList = new String[spaces.size()];
    for (int i = 0; i < spaces.size(); i++) {
      spaceList[i] = spaces.get(spaceNames.get(i)).getName();
    }
    return spaceList;
  }

  @Override
  public String[] weaponsInSpaceToString() {
    String[] weaponsInSpaceList = new String[weaponNames.size()];
    String playerName = playerNames.get(this.turn);
    Player player = this.players.get(playerName);
    Space space = this.spaces.get(this.spaceNames.get(player.getCurrentIndex()));
    weaponsInSpaceList = space.getWeaponsInSpace().split("\n");
    return weaponsInSpaceList;
  }

  @Override
  public String[] weaponsWithPlayerToString() {
    String playerName = playerNames.get(this.turn);
    Player player = this.players.get(playerName);
    String[] weaponsWithPlayerList = new String[player.getWeaponsPossessed().length()];
    weaponsWithPlayerList = player.getWeaponsPossessed().split("\n");
    return weaponsWithPlayerList;
  }

  @Override
  public void startGame(File file) {
    if (file == null) {
      this.parseMethod();
      dfsTraversal(0);
    } else {
      try {
        fileReader = new FileReader(file);
        this.parseMethod();
        dfsTraversal(0);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File does not exist");
      }
    }
  }

  @Override
  public boolean isGameOver() {
    if (this.target.getHealth() <= 0 || this.maxMoves <= 0) {
      return true;
    }
    return false;
  }

  @Override
  public String movePlayer(int index) {
    if (index < 0 || index >= this.numberOfSpaces) {
      throw new IllegalArgumentException("Invalid index provided");
    }
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    Player player = this.players.get(playerNames.get(this.turn));

    int currentIndex = player.getCurrentIndex();
    if (this.spaces.get(spaceNames.get(currentIndex)).getNeighbor(this.pet.getLocation())
        .contains(this.spaces.get(spaceNames.get(index)).getName())
        || this.spaces.get(spaceNames.get(this.pet.getLocation()))
            .getNeighbor(this.pet.getLocation()).contains(spaceNames.get(currentIndex))) {
      player.movePlayer(index);
      this.spaces.get(spaceNames.get(currentIndex)).removePlayerFromSpace(player);
      this.spaces.get(spaceNames.get(index)).addPlayerToSpace(player);
    } else {
      updateTurn();
      throw new IllegalArgumentException("Space mentioned is not a neighbor\n");
    }
    target.moveTarget(numberOfSpaces);
    updateTurn();
    return String.format("Player %s moved to %s\n", player.getPlayerName(),
        this.spaces.get(spaceNames.get(index)).getName());
  }

  @Override
  public int[] getSpaceCoordinates(int spaceNum) {
    if (spaceNum < 0 || spaceNum >= this.numberOfSpaces) {
      throw new IllegalArgumentException("Invalid space index provided");
    }
    int[] coordinates = new int[4];
    coordinates[0] = this.spaces.get(spaceNames.get(spaceNum)).getTopLeftX();
    coordinates[1] = this.spaces.get(spaceNames.get(spaceNum)).getTopLeftY();
    coordinates[2] = this.spaces.get(spaceNames.get(spaceNum)).getBottomRightX();
    coordinates[3] = this.spaces.get(spaceNames.get(spaceNum)).getBottomRightY();
    return coordinates;
  }

  @Override
  public int[] getWorldSize() {
    int[] worldSize = new int[4];
    worldSize[0] = this.row;
    worldSize[1] = this.column;
    worldSize[2] = this.numberOfSpaces;
    worldSize[3] = this.playerNames.size();
    return worldSize;
  }

  @Override
  public List<String> getSpaceNames() {
    List<String> spaceNamesCopy = new ArrayList<String>();
    for (int i = 0; i < this.numberOfSpaces; i++) {
      spaceNamesCopy.add(this.spaceNames.get(i));
    }
    return spaceNamesCopy;
  }

  @Override
  public List<String> getPlayersInSpace(int spaceNum) {
    if (spaceNum < 0 || spaceNum >= this.numberOfSpaces) {
      throw new IllegalArgumentException("Invalid space index provided");
    }
    List<String> playersInSpace = new ArrayList<String>();
    if (target.getCurrentIndex() == spaceNum) {
      playersInSpace.add(String.format("*%s", target.getName()));
    }
    if (pet.getLocation() == spaceNum) {
      playersInSpace.add(String.format("#%s", pet.getPetName()));
    }
    for (String player : this.spaces.get(spaceNames.get(spaceNum)).getPlayersInSpace()
        .split("\n")) {
      playersInSpace.add(player);
    }
    return playersInSpace;
  }

  @Override
  public String movePet(int index) {
    if (index < 0 || index >= this.numberOfSpaces) {
      throw new IllegalArgumentException("Invalid space index given.\n");
    }
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    updateTurn();
    this.pet.updateLocation(index);
    dfsTraversal(pet.getLocation());
    target.moveTarget(numberOfSpaces);
    return String.format("Target's pet %s is moved to %s\n", this.pet.getPetName(),
        this.spaceNames.get(pet.getLocation()));
  }

  @Override
  public String getWinner() {
    return winner;
  }

  @Override
  public String computerTurn() {
    if ("".equals(this.getTurn())) {
      throw new IllegalArgumentException("Create atleast one player to start the game");
    }
    String playerName = playerNames.get(this.turn);
    Player player = this.players.get(playerName);
    Space space = spaces.get(spaceNames.get(player.getCurrentIndex()));
    String returnValue = "";
    if (this.target.getCurrentIndex() == player.getCurrentIndex()
        && !this.checkVisibility(playerName)) {
      if ("".equals(player.getWeaponsPossessed())) {
        returnValue = this.attackTarget("Poke in the eye");
      } else {
        Weapon weapon = player.getWeaponForComputer();
        returnValue = this.attackTarget(weapon.getWeaponName());
      }
    } else if ((!("".equals(space.getWeaponsInSpace())))
        && player.getWeaponsPossessed().split("\n").length < player.getMaxWeapons()) {
      List<String> weaponsToChooseFrom = new ArrayList<String>();
      String[] weaponNames = space.getWeaponsInSpace().split("\n");
      for (int i = 0; i < weaponNames.length; i++) {
        String index = this.weapons.get(weaponNames[i]).getWeaponName();
        weaponsToChooseFrom.add(index);
      }
      int randomIndex = random.generateSequantialInput(weaponsToChooseFrom.size());
      returnValue = this.pickWeapon(weaponsToChooseFrom.get(randomIndex));
    } else if (space.getNeighbor(this.pet.getLocation()).split("\n").length > 0) {
      List<Integer> spacesToChooseFrom = new ArrayList<Integer>();
      String[] spaceNames = space.getNeighbor(this.pet.getLocation()).split("\n");
      for (int i = 0; i < spaceNames.length; i++) {
        int index = spaces.get(spaceNames[i]).getIndex();
        spacesToChooseFrom.add(index);
      }
      int randomIndex = random.generateSequantialInput(spacesToChooseFrom.size());
      returnValue = this.movePlayer(spacesToChooseFrom.get(randomIndex));
    } else {
      returnValue = lookAround();
    }
    return returnValue;
  }

  /**
   * Implemented the DFS traversal for spaces.
   * 
   * @param spaceIndex the index of the space from which the traversal starts.
   */
  private void dfsTraversal(int spaceIndex) {
    boolean[] visited = new boolean[this.numberOfSpaces];
    visitedList.clear();
    dfsHelper(spaceIndex, visited);
  }

  /**
   * DFS traversal helper method that finds the visited list of spaces.
   * 
   * @param spaceIndex the index of the space from which the traversal starts.
   * @param visited    the boolean array which represents if a particular space is
   *                   visited or not.
   */
  private void dfsHelper(int spaceIndex, boolean[] visited) {
    visited[spaceIndex] = true;
    visitedList.add(spaceIndex);
    Iterator<Integer> i = this.spaceToNeighbor.get(spaceIndex).listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited[n]) {
        dfsHelper(n, visited);
        visitedList.add(spaceIndex);
      }
    }
  }

  /**
   * The input from the file is parsed line by line to make a sensible list of
   * values out of it.
   */
  private void parseMethod() {
    this.spaceNames = new ArrayList<String>();
    this.playerNames = new ArrayList<String>();
    this.weaponNames = new ArrayList<String>();
    this.spaces = new HashMap<>();
    this.weapons = new HashMap<>();
    this.players = new HashMap<>();
    this.spaceToNeighbor = new HashMap<>();
    this.visitedList = new ArrayList<>();
    this.turn = 0;
    this.visitedIndex = 1;
    this.numberOfSpaces = 0;
    String line;
    int i = 0;
    try (Scanner scanner = new Scanner(fileReader)) {
      while (scanner.hasNext()) {
        line = scanner.nextLine();
        line = line.trim();
        String[] lineArray = line.split("\\s+");
        if (i == 0) {
          this.row = Integer.parseInt(lineArray[0]);
          this.column = Integer.parseInt(lineArray[1]);
        }
        if (i == 1) {
          int health = Integer.parseInt(lineArray[0]);
          String[] array = Arrays.copyOfRange(lineArray, 1, lineArray.length);
          String targetName = String.join(" ", array);
          this.target = new TargetImpl(health, targetName);
        }
        if (i == 2) {
          String petName = lineArray[0];
          this.pet = new PetImpl(petName);
        }
        if (i == 3) {
          numberOfSpaces = Integer.parseInt(lineArray[0]);
        }
        if (i >= 4 && i < 4 + numberOfSpaces) {
          String[] array = Arrays.copyOfRange(lineArray, 4, lineArray.length);
          int topLeftX = Integer.parseInt(lineArray[0]);
          int topLeftY = Integer.parseInt(lineArray[1]);
          int bottomRightX = Integer.parseInt(lineArray[2]);
          int bottomRightY = Integer.parseInt(lineArray[3]);
          String nameOfSpace = String.join(" ", array);
          Space space = new SpaceImpl(i - 4, topLeftX, topLeftY, bottomRightX, bottomRightY,
              nameOfSpace);
          spaceNames.add(nameOfSpace);
          spaces.put(nameOfSpace, space);
          this.spaceToNeighbor.put(space.getIndex(), new ArrayList<Integer>());
        }
        if (i == 4 + numberOfSpaces) {
          Integer.parseInt(lineArray[0]);
        }
        if (i >= 5 + numberOfSpaces) {
          String[] array = Arrays.copyOfRange(lineArray, 2, lineArray.length);
          int weaponIndex = Integer.parseInt(lineArray[0]);
          Space space = this.spaces.get(spaceNames.get(weaponIndex));
          int damage = Integer.parseInt(lineArray[1]);
          String weaponName = String.join(" ", array);
          Weapon weapon = new WeaponImpl(weaponIndex, damage, weaponName);
          weaponNames.add(weaponName);
          weapons.put(weaponName, weapon);
          space.addWeaponsToSpace(weapon);
        }
        i++;
      }
      overLappingNeighbors();
      spaceNeighbors();
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Not a valid number");
    }
  }

  /**
   * Updates the turn.
   */
  private void updateTurn() {
    this.turn++;
    this.maxMoves--;
    if (this.turn == playerNames.size()) {
      turn = 0;
    }
    pet.updateLocation(this.visitedList.get(visitedIndex));
    visitedIndex++;
    if (visitedIndex == this.numberOfSpaces) {
      visitedIndex = 0;
    }
  }

  /**
   * Finds the neighbors of a particular space and saves it as a list in the
   * particular space.
   */
  private void spaceNeighbors() {
    for (int index = 0; index < numberOfSpaces; index++) {
      int x11 = spaces.get(spaceNames.get(index)).getTopLeftX();
      int y11 = spaces.get(spaceNames.get(index)).getTopLeftY();
      int x12 = spaces.get(spaceNames.get(index)).getBottomRightX();
      int y12 = spaces.get(spaceNames.get(index)).getBottomRightY();
      Space parentSpace = this.spaces.get(spaceNames.get(index));

      for (int i = 0; i < numberOfSpaces; i++) {
        int x21 = spaces.get(spaceNames.get(i)).getTopLeftX();
        int y21 = spaces.get(spaceNames.get(i)).getTopLeftY();
        int x22 = spaces.get(spaceNames.get(i)).getBottomRightX();
        int y22 = spaces.get(spaceNames.get(i)).getBottomRightY();
        if (i != index) {
          if (x22 == x11 - 1) {
            if (y21 <= y11 && y22 <= y12 && y22 >= y11 || y21 >= y11 && y22 <= y12
                || y21 <= y11 && y22 >= y12 || y21 >= y11 && y22 >= y12 && y21 <= y12) {
              parentSpace.addNeighbor(spaces.get(spaceNames.get(i)));
              this.spaceToNeighbor.get(parentSpace.getIndex())
                  .add(spaces.get(spaceNames.get(i)).getIndex());
            }
          } else if (y22 == y11 - 1) {
            if (x21 <= x11 && x22 <= x12 && x22 >= x11 || x21 >= x11 && x22 <= x12
                || x21 <= x11 && x22 >= x12 || x21 >= x11 && x22 >= x12 && x21 <= x12) {
              parentSpace.addNeighbor(spaces.get(spaceNames.get(i)));
              this.spaceToNeighbor.get(parentSpace.getIndex())
                  .add(spaces.get(spaceNames.get(i)).getIndex());
            }
          } else if (x21 == x12 + 1) {
            if (y21 <= y11 && y22 <= y12 && y22 >= y11 || y21 >= y11 && y22 <= y12
                || y21 <= y11 && y22 >= y12 || y21 >= y11 && y22 >= y12 && y21 <= y12) {
              parentSpace.addNeighbor(spaces.get(spaceNames.get(i)));
              this.spaceToNeighbor.get(parentSpace.getIndex())
                  .add(spaces.get(spaceNames.get(i)).getIndex());
            }
          } else if (y21 == y12 + 1) {
            if (x21 <= x11 && x22 <= x12 && x22 >= x11 || x21 >= x11 && x22 <= x12
                || x21 <= x11 && x22 >= x12 || x21 >= x11 && x22 >= x12 && x21 <= x12) {
              parentSpace.addNeighbor(spaces.get(spaceNames.get(i)));
              this.spaceToNeighbor.get(parentSpace.getIndex())
                  .add(spaces.get(spaceNames.get(i)).getIndex());
            }
          }
        }
      }
    }
  }

  /**
   * Finds if any of the spaces in the world are overlapping.
   * 
   * @return whether any of the spaces are overlapping or not
   */
  private boolean overLappingNeighbors() {
    for (int index = 0; index < numberOfSpaces; index++) {
      int x11 = spaces.get(spaceNames.get(index)).getTopLeftX();
      int y11 = spaces.get(spaceNames.get(index)).getTopLeftY();
      int x12 = spaces.get(spaceNames.get(index)).getBottomRightX();
      int y12 = spaces.get(spaceNames.get(index)).getBottomRightY();
      for (int i = 0; i < numberOfSpaces; i++) {
        if (index != i) {
          int x21 = spaces.get(spaceNames.get(i)).getTopLeftX();
          int y21 = spaces.get(spaceNames.get(i)).getTopLeftY();
          int x22 = spaces.get(spaceNames.get(i)).getBottomRightX();
          int y22 = spaces.get(spaceNames.get(i)).getBottomRightY();
          boolean checkY = y21 <= y11 && y22 <= y12 && y22 >= y11 || y21 >= y11 && y22 <= y12
              || y21 <= y11 && y22 >= y12 || y21 >= y11 && y22 >= y12 && y21 <= y12;
          boolean checkX = x21 <= x11 && x22 <= x12 && x22 >= x11 || x21 >= x11 && x22 <= x12
              || x21 <= x11 && x22 >= x12 || x21 >= x11 && x22 >= x12 && x21 <= x12;
          if (checkX && checkY) {
            if ((y11 + 1) < y22 || (y12 + 1) < y21) {
              throw new IllegalArgumentException(String.format("Overlapping spaces: %s and %s",
                  spaces.get(spaceNames.get(index)).toString(),
                  spaces.get(spaceNames.get(i)).toString()));
            } else if ((x11 + 1) < x22 || (x12 + 1) < x21) {
              throw new IllegalArgumentException(String.format("Overlappping spaces: %s and %s",
                  spaces.get(spaceNames.get(index)).toString(),
                  spaces.get(spaceNames.get(i)).toString()));
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks if a player is being seen by any other player.
   * 
   * @param playerName represents the name of the player whose turn it is.
   */
  private boolean checkVisibility(String playerName) {
    Player player = this.players.get(playerName);
    int currentIndex = player.getCurrentIndex();
    Space space = this.spaces.get(this.spaceNames.get(player.getCurrentIndex()));
    if (pet.getLocation() == space.getIndex()) {
      if (space.getPlayersInSpace().split("\n").length > 1) {
        return true;
      }
    } else {
      String visibleSpaces = this.spaces.get(spaceNames.get(currentIndex))
          .getNeighbor(this.pet.getLocation());
      if (!"".equals(visibleSpaces)) {
        String[] visibleArray = visibleSpaces.split("\n");
        for (int i = 0; i < visibleArray.length; i++) {
          Space neighborSpace = spaces.get(visibleArray[i]);
          if (!("".equals(neighborSpace.getPlayersInSpace()))) {
            return true;
          }
        }
      }
      if ((spaces.get(spaceNames.get(pet.getLocation())).getNeighbor(pet.getLocation()))
          .contains(space.getName())
          && (!("".equals(spaces.get(spaceNames.get(pet.getLocation())).getPlayersInSpace())))) {
        return true;
      }
      if (space.getPlayersInSpace().split("\n").length > 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format(
        "WorldImpl [target=%s, pet=%s, row=%s, column=%s, numberOfSpaces=%s, spaces=%s, "
            + "weapons=%s, players=%s, maxMoves=%s]",
        target, pet, row, column, numberOfSpaces, spaces, weapons, players, maxMoves);
  }

}
