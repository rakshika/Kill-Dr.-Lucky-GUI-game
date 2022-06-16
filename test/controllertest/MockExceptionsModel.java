package controllertest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.World;

/**
 * Mock Model class to mock the model for Controller testing.
 */
public class MockExceptionsModel implements World {

  private StringBuilder log;
  private final String uniqueCode;

  /**
   * Mock Model class to mock the model exceptions for Controller testing.
   * 
   * @param log        to append the return values
   * @param uniqueCode the unique code to test the class
   */
  public MockExceptionsModel(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String drawWorld() {
    log.append("In drawWorld(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public String lookAround() {
    log.append("In lookAround(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public String displayPlayerInfo(String playerName) {
    log.append(String.format("In displayPlayerInfo(). playerName: %s\n", playerName));
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Player name cannot be null");
    }
    return uniqueCode;
  }

  @Override
  public String getTurn() {
    log.append("In getTurn(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public String displaySpaceInfo() {
    log.append("In displaySpaceInfo(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public String computerTurn() {
    log.append("In computerTurn(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public boolean isGameOver() {
    log.append("In isGameOver(). There is no input.\n");
    return false;
  }

  @Override
  public List<String> getSpaceNames() {
    log.append("In getSpaceNames(). There is no input.\n");
    List<String> returnList = new ArrayList<>();
    returnList.add(uniqueCode);
    return returnList;
  }

  @Override
  public String[] spaceToString() {
    log.append("In spaceToString(). There is no input.\n");
    String[] returnArray = { uniqueCode };
    return returnArray;
  }

  @Override
  public String[] weaponsInSpaceToString() {
    log.append("In weaponsInSpaceToString(). There is no input.\n");
    String[] returnArray = { uniqueCode };
    return returnArray;
  }

  @Override
  public String[] weaponsWithPlayerToString() {
    log.append("In weaponsWithPlayerToString(). There is no input.\n");
    String[] returnArray = { uniqueCode };
    return returnArray;
  }

  @Override
  public int[] getSpaceCoordinates(int spaceNum) {
    log.append(String.format("In getSpaceCoordinates(). spaceNum: %s\n", spaceNum));
    if (spaceNum < 0) {
      throw new IllegalArgumentException("Space index cannot be negative");
    }
    return null;
  }

  @Override
  public int[] getWorldSize() {
    log.append(String.format("In getWorldSize().  There is no input.\n"));
    return null;
  }

  @Override
  public List<String> getPlayersInSpace(int spaceNum) {
    log.append(String.format("In getPlayersInSpace(). spaceNum: %s\n", spaceNum));
    return null;
  }

  @Override
  public String getWinner() {
    log.append(String.format("In getWinner().  There is no input.\n"));
    return uniqueCode;
  }

  @Override
  public String createPlayer(String playerName, String currentSpaceName, boolean isComputer) {
    log.append(
        String.format("In createPlayer(). playerName: %s, currentSpaceName: %s, isComputer: %s.\n",
            playerName, currentSpaceName, isComputer));
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Player name should not be null");
    }
    if (currentSpaceName == null || "".equals(currentSpaceName)) {
      throw new IllegalArgumentException("Space name should not be null");
    }
    return uniqueCode;
  }

  @Override
  public String movePlayer(int index) {
    log.append(
        String.format("In movePlayer(). index: %s.\n",
            index));
    if (index < 0) {
      throw new IllegalArgumentException("Illegal coordinates");
    }    
    return uniqueCode;
  }

  @Override
  public String attackTarget(String weaponName) {
    log.append(String.format("In attackTarget(). weaponName: %s\n", weaponName));
    if (weaponName == null || "".equals(weaponName)) {
      throw new IllegalArgumentException("Weapon name cannot be null");
    }
    return uniqueCode;
  }

  @Override
  public String pickWeapon(String weaponName) {
    log.append(String.format("In pickWeapon(). weaponName: %s\n", weaponName));
    if (weaponName == null || "".equals(weaponName)) {
      throw new IllegalArgumentException("Weapon name cannot be null");
    }
    return uniqueCode;
  }

  @Override
  public String movePet(int index) {
    log.append(
        String.format("In movePlayerCoordinates(). index: %s.\n",
            index));
    if (index < 0) {
      throw new IllegalArgumentException("Illegal coordinates");
    }    
    return uniqueCode;
  }

  @Override
  public void startGame(File file) {
    log.append(String.format("In startGame(). file: %s\n", file));
    if (file == null || "".equals(file)) {
      throw new IllegalArgumentException("Weapon name cannot be null");
    }

  }

}
