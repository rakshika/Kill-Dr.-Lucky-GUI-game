package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Weapon;
import model.WeaponImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Weapon class.
 */
public class WeaponTest {

  private Weapon everyWeapon;
  private Weapon everyWeapon1;

  /**
   * Creates all the objects required for testing.
   */
  @Before
  public void setUp() {
    everyWeapon = weapon(7, 3, "Syringe");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIndex() {
    everyWeapon = weapon(-1, 3, "Syringe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDamage() {
    everyWeapon = weapon(1, -2, "Syringe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    everyWeapon = weapon(1, 3, null);
  }

  @Test
  public void testGetWeaponName() {
    String actual = everyWeapon.getWeaponName();
    String expected = "Syringe";
    assertEquals(expected, actual);
  }

  @Test
  public void testGetWeaponIndex() {
    int actual = everyWeapon.getWeaponIndex();
    int expected = 7;
    assertEquals(expected, actual);
  }

  @Test
  public void testGetWeaponDamage() {
    int actual = everyWeapon.getDamage();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  public void testHashCode() {
    everyWeapon1 = weapon(7, 3, "Syringe");
    assertEquals(everyWeapon.hashCode(), everyWeapon1.hashCode());
  }

  @Test
  public void testEquals() {
    everyWeapon1 = weapon(7, 3, "Syringe");
    assertTrue(everyWeapon.equals(everyWeapon1));
    everyWeapon1 = weapon(7, 4, "Syringe");
    assertFalse(everyWeapon.equals(everyWeapon1));
    everyWeapon1 = everyWeapon;
    assertTrue(everyWeapon.equals(everyWeapon1));
    everyWeapon1 = null;
    assertFalse(everyWeapon.equals(everyWeapon1));
  }

  @Test
  public void testToString() {
    assertEquals("Weapon [weaponIndex=7, damage=3, weaponName=Syringe]", everyWeapon.toString());
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param weaponIndex represents the index  of the weapon.
   * @param damage represents the damage of the weapon.
   * @param weaponName represents the name of the weapon.
   * @return the weapon object created.
   */
  protected WeaponImpl weapon(int weaponIndex, int damage, String weaponName) {
    return new WeaponImpl(weaponIndex, damage, weaponName);
  }
}
