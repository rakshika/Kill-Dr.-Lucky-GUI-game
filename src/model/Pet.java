package model;

/**
 * Pet interface declares all the operations possible on the pet.
 */
public interface Pet {

  /**
   * Gets the index of the space in which the pet currently resides.
   * 
   * @return the current index of the pet.
   */
  public int getLocation();
  
  /**
   * Gets the name of the pet.
   * 
   * @return the name of the pet.
   */
  public String getPetName();

  /**
   * Moves the pet to the index provided.
   * 
   * @param index the index of the space to which the pet should move.
   * @throws IllegalArgumentException if index is invalid.
   */
  public void updateLocation(int index);

}
