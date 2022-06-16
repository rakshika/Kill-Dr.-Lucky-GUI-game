package model;

import java.util.Objects;

/**
 * Implementation of the Weapon interface defines all the operations that can be
 * done on a weapon.
 */
public class WeaponImpl implements Weapon {

  private final int weaponIndex;
  private final int damage;
  private final String weaponName;

  /**
   * Constructor.
   * 
   * @param weaponIndex represents the space in which the weapon is.
   * @param damage      represents the damage that the weapon can cause to the
   *                    target.
   * @param weaponName  represents the name of the weapon.
   * @throws IllegalArgumentException if any of the arguments are invalid
   */
  public WeaponImpl(int weaponIndex, int damage, String weaponName) {
    if (weaponIndex < 0) {
      throw new IllegalArgumentException("Weapon index cannot be negative");
    }
    if (damage < 0) {
      throw new IllegalArgumentException("Damage cannot be negative");
    }
    if (weaponName == null || "".equals(weaponName)) {
      throw new IllegalArgumentException("Weapon name cannot be empty");
    }
    this.weaponIndex = weaponIndex;
    this.damage = damage;
    this.weaponName = weaponName;
  }

  @Override
  public String getWeaponName() {
    return weaponName;
  }

  @Override
  public int getWeaponIndex() {
    return weaponIndex;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(damage, weaponIndex, weaponName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof WeaponImpl)) {
      return false;
    }
    WeaponImpl other = (WeaponImpl) obj;
    return damage == other.damage && weaponIndex == other.weaponIndex
        && weaponName.equals(other.weaponName);
  }

  @Override
  public String toString() {
    return String.format("Weapon [weaponIndex=%s, damage=%s, weaponName=%s]", weaponIndex, damage,
        weaponName);
  }

}
