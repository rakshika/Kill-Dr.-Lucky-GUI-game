package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Player;
import model.PlayerImpl;
import model.Space;
import model.SpaceImpl;
import model.Weapon;
import model.WeaponImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Space class.
 */
public class SpaceTest {

  private SpaceImpl space;
  private SpaceImpl space2;

  /**
   * Creates all the objects required for testing.
   */
  @Before
  public void setUp() {
    space = space(1, 5, 9, 16, 13, "Living Room");
  }

  @Test
  public void testSpaceCreation() {
    String actual = space.toString();
    String expected = "Space [topLeftX = 5, topLeftY = 9, bottomRightX = 16, "
        + "bottomRightY = 13, nameOfSpace = Living Room]";
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTopLeftX() {
    space = space(1, -1, 3, 3, 5, "Space");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegtiveTopLeftY() {
    space = space(1, 1, -3, 3, 5, "Space");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBottomRightX() {
    space = space(1, 1, 3, -3, 5, "Space");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBottomRightY() {
    space = space(1, 1, 3, 3, -5, "Space");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    space = space(1, 1, 3, 3, 5, "");
  }

  @Test
  public void testGetTopLeftX() {
    assertEquals(5, space.getTopLeftX());
  }

  @Test
  public void testGetTopLeftY() {
    assertEquals(9, space.getTopLeftY());
  }

  @Test
  public void testGetBottomRightX() {
    assertEquals(16, space.getBottomRightX());
  }

  @Test
  public void testGetBottomRightY() {
    assertEquals(13, space.getBottomRightY());
  }

  @Test
  public void testGetName() {
    assertEquals("Living Room", space.getName());
  }
  
  @Test
  public void testGetIndex() {
    assertEquals(1, space.getIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullPlayerToSpace() {
    space.addPlayerToSpace(null);
  }
  
  @Test
  public void testAddAndRemovePlayerToSpace() {
    Player player = new PlayerImpl("Soundarya", 2, false);
    space.addPlayerToSpace(player);
    assertEquals("Soundarya\n", space.getPlayersInSpace());
    space.removePlayerFromSpace(player);
    assertEquals("", space.getPlayersInSpace());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullSpaceNeighbor() {
    space.addNeighbor(null);
  }
  
  @Test
  public void testAddSpaceNeighbor() {
    Space spaceNeighbor = space(3, 2, 5, 7, 9, "Neighbor");
    space.addNeighbor(spaceNeighbor);
    assertEquals("Neighbor\n", space.getNeighbor(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullWeaponFromSpace() {
    space.removeWeaponFromSpace(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullPlayerFromSpace() {
    space.removePlayerFromSpace(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullWeaponToSpace() {
    space.addWeaponsToSpace(null);
  }
  
  @Test
  public void testAddAndRemoveWeaponToSpace() {
    Weapon weapon = weapon(2, 3, "Poison");
    space.addWeaponsToSpace(weapon);
    assertEquals("Poison\n", space.getWeaponsInSpace());
    space.removeWeaponFromSpace(weapon);
    assertEquals("", space.getWeaponsInSpace());
  }

  @Test
  public void testGetWeaponsInSpace() {
    String expected = "";
    String actual = space.getWeaponsInSpace();
    assertEquals(expected, actual);
  }

  @Test
  public void testNoNeighbor() {
    String actual = space.getNeighbor(0);
    String expected = "";
    assertEquals(expected, actual);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetNeighborIllegalPetLocation() {
    space.getNeighbor(-1);
  }

  @Test
  public void testHashCode() {
    space2 = space(1, 5, 9, 16, 13, "Living Room");
    assertEquals(space.hashCode(), space2.hashCode());
  }

  @Test
  public void testEquals() {
    space2 = space(1, 5, 9, 16, 13, "Living Room");
    assertTrue(space.equals(space2));
    space2 = space(1, 5, 5, 16, 13, "Living Room");
    assertFalse(space.equals(space2));
    space2 = space;
    assertTrue(space.equals(space2));
    space2 = null;
    assertFalse(space.equals(space2));
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param i represents the index of the weapon.
   * @param j represents the damage of the weapon.
   * @param string represents the name of the weapon.
   * @return the weapon object created.
   */
  protected WeaponImpl weapon(int i, int j, String string) {
    return new WeaponImpl(i, j, string);
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param index of the space.
   * @param topLeftX coordinate of the space.
   * @param topLeftY coordinate of the space.
   * @param bottomRightX coordinate of the space.
   * @param bottomRightY coordinate of the space.
   * @param nameOfSpace represents the name of the space.
   * @return the space object created.
   */
  protected SpaceImpl space(int index, int topLeftX, int topLeftY, int bottomRightX,
      int bottomRightY, String nameOfSpace) {
    return new SpaceImpl(index, topLeftX, topLeftY, bottomRightX, bottomRightY, nameOfSpace);
  }
}
