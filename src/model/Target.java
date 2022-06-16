package model;

/**
 * Target interface declares all the operations possible on the Target
 * character.
 */
public interface Target {

  /**
   * The target character moves along the spaces to the space that has the next
   * index.
   * 
   * @param numberOfSpaces in the world to consider maximum possible index of the
   *                       target.
   * @throws IllegalArgumentException if numberOfSpaces is invalid.
   */
  public void moveTarget(int numberOfSpaces);

  /**
   * Gives the current index of the target character.
   * 
   * @return the current index of the target.
   */
  public int getCurrentIndex();

  /**
   * Updates the health of the target character.
   * 
   * @param decrease represents the health to be decreased.
   * @throws IllegalArgumentException if decrease is invalid.
   */
  public void updateHealth(int decrease);

  /**
   * Gets the health of the target character.
   * 
   * @return integer containing the health of the target character.
   */
  public int getHealth();

  /**
   * Gets the name of the target.
   * 
   * @return the name of the target.
   */
  public String getName();
}
