package controllertest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.World;

/**
 * Mock Model class to mock the model for Controller testing.
 */
public abstract class MockWorld implements World {

  private StringBuilder log;
  private final String uniqueCode;

  /**
   * Initializing variables required for testing.
   * 
   * @param log        represents the logs generated
   * @param uniqueCode represents the unique identifier specific for the model
   */
  public MockWorld(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String drawWorld() {
    log.append("In drawWorld(). There is no input.\n");
    return uniqueCode;
  }

  @Override
  public String createPlayer(String playerName, String spaceName, boolean isComputer) {
    log.append(
        String.format("In createPlayer(). PlayerName: %s, spaceName: %s, isComputer: %s\n",
            playerName, spaceName, isComputer));
    return uniqueCode;
  }

  @Override
  public String displaySpaceInfo() {
    log.append(String.format("In displaySpaceInfo(). There is no input.\n"));
    return uniqueCode;
  }
  
  @Override
  public String pickWeapon(String weaponName) {
    log.append(String.format("In pickWeapon(). weaponName: %s\n", weaponName));
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
    return uniqueCode;
  }
  
  @Override
  public String[] spaceToString() {
    log.append(String.format("In spaceToString(). There is no input.\n"));
    String[] returnString = {uniqueCode};
    return returnString;
  }
  
  @Override
  public String attackTarget(String weaponName) {
    log.append(String.format("In attackTarget(). weaponName: %s\n", weaponName));
    return uniqueCode;
  }

  @Override
  public String computerTurn() {
    log.append(String.format("In computerTurn(). There is no input.\n"));
    return uniqueCode;
  }
  
  @Override
  public boolean isGameOver() {
    log.append(String.format("In isGameOver(). There is no input.\n"));
    return false;
  }

  @Override
  public String movePlayer(int index) {
    log.append(String.format("In movePlayer(). index: %s\n", index));
    return uniqueCode;
  }

  @Override
  public String movePet(int index) {
    log.append(String.format("In movePet(). index: %s\n", index));
    return uniqueCode;
  }

  @Override
  public void startGame(File file) {
    log.append(String.format("In startGame(). file: %s", file));
  }

  @Override
  public List<String> getSpaceNames() {
    log.append(String.format("In getSpaceNames(). There is no input.\n"));
    List<String> returnList = new ArrayList<>();
    returnList.add(uniqueCode);
    return returnList;
  }

  @Override
  public String[] weaponsInSpaceToString() {
    log.append(String.format("In weaponsInSpaceToString(). There is no input.\n"));
    String[] returnString = {uniqueCode};
    return returnString;
  }

  @Override
  public String[] weaponsWithPlayerToString() {
    log.append(String.format("In weaponsWithPlayerToString(). There is no input.\n"));
    String[] returnString = {uniqueCode};
    return returnString;
  }

  @Override
  public int[] getSpaceCoordinates(int spaceNum) {
    log.append(String.format("In getSpaceCoordinates(). spaceNum: %s\n", spaceNum));
    return null;
  }

  @Override
  public int[] getWorldSize() {
    log.append(String.format("In getWorldSize(). There is no input.\n"));
    return null;
  }

  @Override
  public List<String> getPlayersInSpace(int spaceNum) {
    log.append(String.format("In getPlayersInSpace(). spaceNum: %s\n", spaceNum));
    List<String> returnList = new ArrayList<>();
    returnList.add(uniqueCode);
    return returnList;
  }

  @Override
  public String getWinner() {
    log.append(String.format("In getWinner(). There is no input.\n"));
    return uniqueCode;
  }

}
