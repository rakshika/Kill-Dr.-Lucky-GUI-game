package model;

/**
 * Pet implementation which defines all the operations on the pet.
 */
public class PetImpl implements Pet {
  
  private String petName;
  private int currentLocation;
  
  /**
   * Constructor.
   * 
   * @param petName represents the name of the pet.
   * @throws IllegalArgumentException if petName is null or empty.
   */
  public PetImpl(String petName) {
    if (petName == null || "".equals(petName)) {
      throw new IllegalArgumentException("Pet name cannot be empty");
    }
    this.petName = petName;
    this.currentLocation = 0;
  }

  @Override
  public String getPetName() {
    return petName;
  }
  
  @Override
  public int getLocation() {
    return this.currentLocation;
  }

  @Override
  public void updateLocation(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index cannot be negative");
    }
    this.currentLocation = index;
  }

  @Override
  public String toString() {
    return String.format("PetImpl [petName=%s, currentLocation=%s]", petName, currentLocation);
  }
 
}
