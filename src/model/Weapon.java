package model;

/**
 * Weapon interface declares all the operations that can be done on a weapon.
 */
public interface Weapon {
  
  /**
   * Gets the name of the weapon.
   * 
   * @return the name of the weapon
   */
  public String getWeaponName();
  
  /**
   * Gets the space index in which the weapon is present.
   * 
   * @return the index of the weapon
   */
  public int getWeaponIndex();

  /**
   * Gets the damage that the weapon can make.
   * 
   * @return integer that represents the damage the weapon can make.
   */
  public int getDamage();

}
