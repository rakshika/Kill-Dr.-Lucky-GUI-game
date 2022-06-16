package model;

/**
 * Target implementation that defines all the operations possible on the target
 * character.
 */
public class TargetImpl implements Target {

  private String name;
  private int health;
  private int currentIndex;

  /**
   * Constructor.
   * 
   * @param name   representing the name of the target.
   * @param health representing the health of the target.
   * @throws IllegalArgumentException if health is invalid or name is null or
   *                                  empty
   */
  public TargetImpl(int health, String name) {
    if (health < 0) {
      throw new IllegalArgumentException("Target health cannot be negative");
    }
    if (name == null || "".equals(name)) {
      throw new IllegalArgumentException("Target name cannot be empty");
    }
    this.health = health;
    this.name = name;
    this.currentIndex = 0;
  }

  @Override
  public void moveTarget(int numberOfSpaces) {
    currentIndex++;
    if (currentIndex == numberOfSpaces) {
      currentIndex = 0;
    }
  }
  
  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCurrentIndex() {
    return currentIndex;
  }

  @Override
  public void updateHealth(int decrease) {
    if (decrease < 0) {
      throw new IllegalArgumentException("Health cannot be increased");
    }
    this.health -= decrease;
  }

  @Override
  public int getHealth() {
    return this.health;
  }
  
  @Override
  public String toString() {
    return String.format("Target [name=%s, health=%s, currentIndex=%s]", name, health,
        currentIndex);
  }

}
