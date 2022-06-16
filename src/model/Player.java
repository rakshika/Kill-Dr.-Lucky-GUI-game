package model;

/**
 * Player interface declares all the operations on a player.
 */
public interface Player {

  /**
   * Moves the player to the specified index.
   * 
   * @param index of the space the player wants to move to
   * @throws IllegalArgumentException if index is invalid
   */
  public void movePlayer(int index);

  /**
   * Gets the name of the player.
   * 
   * @return the name of the player.
   */
  public String getPlayerName();

  /**
   * Gets the index of the space in which the player currently is in.
   * 
   * @return the index of space.
   */
  public int getCurrentIndex();

  /**
   * Gets the max number of weapons that the player can possess.
   * 
   * @return the max number of weapons
   */
  public int getMaxWeapons();

  /**
   * Gets the weapons possessed by the player.
   * 
   * @return a string of names of weapons possessed by the player
   */
  public String getWeaponsPossessed();

  /**
   * Gets if the player is a computer player or a human player.
   * 
   * @return true for computer player and false for human player
   */
  public boolean isComputer();

  /**
   * Adds the weapon to the player who is playing.
   * 
   * @param weapon that is to be added to the player
   * @throws IllegalArgumentException when the weapon is null
   */
  public void addWeapon(Weapon weapon);

  /**
   * Displays the information about the particular player.
   * 
   * @return String containing info about the space
   */
  public String displayPlayerInfo();

  /**
   * Removes the weapon from the player.
   * 
   * @param weaponName represents the name of the weapon to be removed.
   * @throws IllegalArgumentException if weapon is null or empty.
   */
  public void removeWeapon(Weapon weapon);

  /**
   * Gets the weapon with maximum damage.
   * 
   * @return weapon that has max damage.
   */
  public Weapon getWeaponForComputer();

}
