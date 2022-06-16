package model;

import java.util.List;

/**
 * The methods in the world that is visible to the view.
 */
public interface ReadOnlyWorld {

  /**
   * Gets the names of all the spaces in the world.
   * 
   * @return a list of names of spaces.
   */
  public List<String> getSpaceNames();
  
  /**
   * Displays the information about a specific player which includes name of the
   * player, where the player currently is and what weapons are being carried.
   * 
   * @param playerName represents name of the player whose info is required.
   * @return a string containing the details of the operation performed.
   * @throws IllegalArgumentException if playerName is null or empty.
   */
  public String displayPlayerInfo(String playerName);
  
  /**
   * Gets the name of the player whose turn it is.
   * 
   * @return the name of the player who has to play
   */
  public String getTurn();

  /**
   * Returns the string of space names.
   * 
   * @return string of space names
   */
  public String[] spaceToString();

  /**
   * Returns the names of weapons in space.
   * 
   * @return string array of names of weapons in space.
   */
  public String[] weaponsInSpaceToString();
  
  /**
   * Displays the information about a particular space which includes name of the
   * space, neighbors of the space, weapons in the space and players in the space
   * at the moment.
   * 
   * @return a string array containing the details of the operation performed.
   */
  public String displaySpaceInfo();

  /**
   * Returns the names of weapons possessed by a player.
   * 
   * @return string array of names of weapons with player.
   */
  String[] weaponsWithPlayerToString();
  
  /**
   * Gets the coordinates of the space.
   * 
   * @param spaceNum the index of the space whose coordinates are needed.
   * @return an array containing the coordinates of the space.
   * @throws IllegalArgumentException if spaceNum is invalid.
   */
  int[] getSpaceCoordinates(int spaceNum);

  /**
   * Gets the rows, columns, number of spaces and number of players in the world.
   * 
   * @return an array containing the rows, columns, number of spaces and number of players.
   */
  int[] getWorldSize();
  
  /**
   * Gets the names of the players in a particular space.
   * 
   * @param spaceNum the index of the space whose players are needed.
   * @return a list of player names.
   * @throws IllegalArgumentException if spaceNum is invalid.
   */
  List<String> getPlayersInSpace(int spaceNum);
  
  /**
   * Gets the winner of the game.
   * 
   * @return the name of the winner.
   */
  String getWinner();
  
  /**
   * Plays the turn of the computer controlled player.
   * 
   * @return a string containing the details of the operation performed.
   */
  public String computerTurn();


}
