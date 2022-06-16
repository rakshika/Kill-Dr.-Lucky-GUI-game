package model;

/**
 * Space interface declares all operations that can be done on a space.
 */
public interface Space {

  /**
   * Gets the top left x-coordinate.
   * 
   * @return the topLeftX coordinate
   */
  public int getTopLeftX();

  /**
   * Gets the top left y-coordinate.
   * 
   * @return the topLeftY coordinate
   */
  public int getTopLeftY();

  /**
   * Gets the bottom right x-coordinate.
   * 
   * @return the bottomRightX coordinate
   */
  public int getBottomRightX();

  /**
   * Gets the bottom right y-coordinate.
   * 
   * @return the bottomRightY coordinate
   */
  public int getBottomRightY();

  /**
   * Gets the name of the space.
   * 
   * @return the name of the space
   */
  public String getName();

  /**
   * Gets the list of weapons in the space.
   * 
   * @return a string of names of weapons in a space.
   */
  public String getWeaponsInSpace();

  /**
   * Gets the neighbors of the space.
   * @param petLocation represents the index of the space in which the pet is present.
   * @return a string of names of neighboring spaces of a space.
   * @throws IllegalArgumentException if petLocation is invalid.
   */
  public String getNeighbor(int petLocation);

  /**
   * Gets the players currently in the space.
   * 
   * @return a string of names of players in the space.
   */
  public String getPlayersInSpace();

  /**
   * Adds weapons to the specified space.
   * 
   * @param weapon specifies the weapon to be added to the space.
   * @throws IllegalArgumentException if weapon is null
   */
  public void addWeaponsToSpace(Weapon weapon);

  /**
   * Removes weapons from the space.
   * 
   * @param weapon represents the weapon to be removed from the space
   * @throws IllegalArgumentException if weapon is null
   */
  public void removeWeaponFromSpace(Weapon weapon);

  /**
   * Adds a neighbor to the specified space.
   * 
   * @param space specifies the space that is to be added.
   * @throws IllegalArgumentException if space is null
   */
  public void addNeighbor(Space space);

  /**
   * Adds a player to a particular space.
   * 
   * @param player specifies the player that is to be added.
   * @throws IllegalArgumentException if player is null
   */
  public void addPlayerToSpace(Player player);

  /**
   * Removes the player mentioned from the space.
   * 
   * @param player who needs to be removed from the space
   * @throws IllegalArgumentException id player is null
   */
  public void removePlayerFromSpace(Player player);

  /**
   * Gets the index of the space.
   * 
   * @return space index.
   */
  public int getIndex();

}
