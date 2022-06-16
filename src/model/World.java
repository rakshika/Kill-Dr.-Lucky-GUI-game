package model;

import java.io.File;

/**
 * The world contains spaces, a target character, weapons and players that play
 * the game.
 */
public interface World extends ReadOnlyWorld {

  /**
   * The world is drawn graphically, one rectangle representing one space.
   * 
   * @return a buffered image which contains the graphical representation of the world.
   */
  public String drawWorld();
  
  /**
   * A player can look around from his room and information such as the space in
   * which he is and which spaces he can see from there.
   * 
   * @return a string containing the details of the operation performed.
   */
  public String lookAround();
  
  /**
   * Creates a new player at the beginning of the game.
   * 
   * @param playerName   represents name given by the player.
   * @param currentIndex represents the index of the space in which the player
   *                     wants to start the game.
   * @param isComputer   represents whether the player is a computer player or
   *                     human player
   * @return a string containing the details of the operation performed.
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public String createPlayer(String playerName, String currentSpaceName, boolean isComputer);
  
  /**
   * Finds if the game is over.
   * 
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();
  
  /**
   * Attacks the target with the weapon specified. Pokes in the eye if the player
   * does not have the weapon.
   * 
   * @param weaponIndex represents the index of the weapon with which the attack
   *                    is to be made.
   * @return string containing the details of the operation.
   * @throws IllegalArgumentException if weaponName is null or empty.
   */
  String attackTarget(String weaponName);
  
  /**
   * Picks weapon from the space after checking if it is actually in the space.
   * 
   * @param weaponIndex represents the index of the weapon to be picked
   * @return a string containing the details of the operation performed.
   * @throws IllegalArgumentException if weaponName is null or empty.
   */
  public String pickWeapon(String weaponName);
  
  /**
   * Starts the game.
   * 
   * @param file the file to be parsed.
   * @throws IllegalArgumentException if file is null.
   */
  void startGame(File file);
  
  /**
   * Moves the pet to the space whose index is provided.
   * 
   * @param index the index of the space to which the pet should be moved.
   * @return the String after successful movement.
   * @throws IllegalArgumentException if index is invalid.
   */
  String movePet(int index);

  /**
   * Moves the player to the desired index after checking if it is a neighboring
   * space.
   * 
   * @param index represents the index of the space where the player wants to move
   * @return a string containing the details of the operation performed.
   * @throws IllegalArgumentException if index is invalid.
   */
  String movePlayer(int index);

}
