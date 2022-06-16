package controller;

import controller.commandcontroller.AttackTarget;
import controller.commandcontroller.CommandController;
import controller.commandcontroller.LookAround;
import controller.commandcontroller.MovePet;
import controller.commandcontroller.MovePlayer;
import controller.commandcontroller.PickWeapon;
import controller.commandcontroller.QuitGame;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import model.World;
import view.FileTypeFilter;
import view.GameView;

/**
 * The controller implements the Features and the GameController interfaces. All
 * the operations of the interfaces are defined here.
 */
public class GameControllerImpl implements GameController, Features {

  private World model;
  private GameView view;
  private File file;

  /**
   * Constructor.
   * 
   * @param model the model of the MVC design.
   * @param view  the view of the MVC design.
   * @throws IllegalArgumentException if model or view is null.
   */
  public GameControllerImpl(World model, GameView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model and view cannot be null");
    }
    this.model = model;
    this.view = view;
    this.file = null;
  }

  @Override
  public void playGame() {
    view.makeVisible();
    this.configureMenuListeners();
    this.configureButtonListeners();
  }

  @Override
  public void configureButtonListeners() {
    view.addButtonListeners(this);

  }

  @Override
  public void configureKeyListeners() {
    view.addKeyListeners(this);
  }

  @Override
  public void configureMenuListeners() {
    view.addMenuListeners(this);

  }

  @Override
  public void configureMouseListeners(String string) {
    if (string == null || "".equals(string)) {
      throw new IllegalArgumentException("The command should not be null");
    }
    view.addMouseListeners(string, this);
  }

  @Override
  public void handleMenuClick(String actionCommand) {
    if (actionCommand == null || "".equals(actionCommand)) {
      throw new IllegalArgumentException("Action command should not be null");
    }
    if ("New World".equals(actionCommand)) {
      JFileChooser worldChooser = new JFileChooser();
      worldChooser.setDialogTitle("Choose a world specification file");
      worldChooser.setFileFilter(new FileTypeFilter(".txt", "Text File"));
      int result = worldChooser.showDialog(worldChooser, "Choose");
      if (result == JFileChooser.APPROVE_OPTION) {
        file = worldChooser.getSelectedFile();
        model.startGame(file);
        this.playGame();
        view.createDialog("createPlayer", this);
      }
    } else if ("Same World".equals(actionCommand)) {
      model.startGame(file);
      this.playGame();
      view.createDialog("createPlayer", this);
    } else if ("Exit".equals(actionCommand)) {
      System.exit(0);
    }
  }

  @Override
  public void handleButtonClick(String buttonName) {
    if (buttonName == null || "".equals(buttonName)) {
      throw new IllegalArgumentException("Button cannot be null");
    }
    try {
      if (buttonName.contains("CreatePlayers")) {
        model.startGame(file);
        view.createDialog("createPlayer", this);
      } else if (buttonName.contains("StartGameWithPlayers")) {
        String[] playerArray = buttonName.split("\n");
        String playerName = playerArray[1];
        String spaceName = playerArray[2];
        boolean isComputer = playerArray[3].equals("true");
        model.createPlayer(playerName, spaceName, isComputer);
        view.addPanel(this);
        this.configureKeyListeners();
      } else if (buttonName.contains("PlayerDetails")) {
        String[] playerArray = buttonName.split("\n");
        String playerName = playerArray[1];
        String spaceName = playerArray[2];
        boolean isComputer = playerArray[3].equals("true");
        model.createPlayer(playerName, spaceName, isComputer);
        view.createDialog("createPlayer", this);
      } else if (buttonName.contains("AttackTargetButton")) {
        String weaponName = buttonName.split("\n")[1];
        String returValue = model.attackTarget(weaponName);
        view.createDialog(String.format("attackTargetDone\n%s", returValue), this);
      } else if (buttonName.contains("pickWeaponDone")) {
        String weaponName = buttonName.split("\n")[1];
        String returnValue = model.pickWeapon(weaponName);
        view.createDialog(String.format("pickWeaponDone\n%s", returnValue), this);
      } else if (buttonName.contains("TargetInfoButton")) {
        String targetInfo = model.displayPlayerInfo("-1");
        view.createDialog(String.format("TargetInfoButton!*,%s", targetInfo), this);
        view.setFocus();
      }
      if (model.isGameOver()) {
        view.createDialog("gameOverScreen", this);
      }
    } catch (IllegalArgumentException iae) {
      view.createDialog(String.format("exceptionDialog\n%s", iae.getMessage()), this);
    }
    if (model.isGameOver()) {
      view.createDialog("gameOverScreen", this);
    } else if (model.getTurn().startsWith("Computer Player ")) {
      view.createDialog("RefreshNow", this);
    }
    view.refresh();

  }

  @Override
  public void handleMouseClick(int x, int y, String string) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    if (string == null || "".equals(string)) {
      throw new IllegalArgumentException("Mouse click command cannot be null");
    }
    try {
      if ("movePlayer".equals(string)) {
        int index = this.getSpaceIndex(x, y);
        model.movePlayer(index);
        view.disbleMouseListener();
      } else if ("movePet".equals(string)) {
        int index = this.getSpaceIndex(x, y);
        String movePetReturn = model.movePet(index);
        view.disbleMouseListener();
        view.createDialog(String.format("MovePetDone\n%s", movePetReturn), this);
      }
    } catch (IllegalArgumentException iae) {
      view.createDialog(String.format("exceptionDialog\n%s", iae.getMessage()), this);
      view.disbleMouseListener();
    }
    if (model.isGameOver()) {
      view.createDialog("gameOverScreen", this);
    } else if (model.getTurn().startsWith("Computer Player ")) {
      view.createDialog("RefreshNow", this);
    }
    view.refresh();
  }

  @Override
  public void handleKeyClick(char key) {
    if (key == '\u0000') {
      throw new IllegalArgumentException("Empty key is passed");
    }
    Map<Character, CommandController> gameCommands = new HashMap<>();
    gameCommands.put('m', new MovePlayer(view, this));
    gameCommands.put('a', new AttackTarget(view, this));
    gameCommands.put('p', new PickWeapon(view, this));
    gameCommands.put('x', new MovePet(view, this));
    gameCommands.put('l', new LookAround(view, this, model));
    gameCommands.put('q', new QuitGame(view, this));

    CommandController cmd = null;
    cmd = gameCommands.getOrDefault(Character.toLowerCase(Character.valueOf(key)), null);
    if (cmd == null) {
      view.createDialog("InvalidKey", this);
    } else {
      cmd.go();
      cmd = null;
    }
    if (model.isGameOver()) {
      view.createDialog("gameOverScreen", this);
    } else if (model.getTurn().startsWith("Computer Player ")) {
      view.createDialog("RefreshNow", this);
    }
    view.refresh();
  }

  /**
   * This method is used to get the space to which the coordinates belong.
   * 
   * @param x represents the x coordinate.
   * @param y represents the y coordinate.
   * @return the index of the space corresponding to the coordinates.
   */
  private int getSpaceIndex(int x, int y) {
    int index = -1;
    for (int i = 0; i < model.getSpaceNames().size(); i++) {
      if (x >= model.getSpaceCoordinates(i)[0] && x <= model.getSpaceCoordinates(i)[2]
          && y >= model.getSpaceCoordinates(i)[1] && y <= model.getSpaceCoordinates(i)[3]) {
        index = i;
      }
    }
    return index;
  }
}
