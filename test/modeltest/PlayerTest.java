package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.PlayerImpl;
import model.Weapon;
import model.WeaponImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Player class.
 */
public class PlayerTest {

  private PlayerImpl player;

  /**
   * Creates all the objects required for testing this class.
   */
  @Before
  public void setUp() {
    player = player("Soundarya", 0, false);
  }

  @Test
  public void testPlayerCreation() {
    String expected = "Player [playerName = Soundarya, currentIndex = 0, maxWeapons = 3, "
        + "weaponsPossessed = []]";
    String actual = player.toString();
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    player = player("", 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIndex() {
    player = player("", -2, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIndex() {
    player = player("", 29, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeIndexMovePlayer() {
    player.movePlayer(-2);
  }

  @Test
  public void testGetPlayerName() {
    assertEquals("Soundarya", player.getPlayerName());
  }

  @Test
  public void testGetCurrentIndex() {
    assertEquals(0, player.getCurrentIndex());
  }
  
  @Test
  public void testGetMaxWeapons() {
    assertEquals(3, player.getMaxWeapons());
  }

  @Test
  public void testMovePlayer() {
    String expected1 = "Soundarya, you can have a maximum of 3 weapons\n"
        + "You have no weapons at the moment\n";
    String actual1 = player.displayPlayerInfo();

    player.movePlayer(3);
    String expected2 = "Soundarya, you can have a maximum of 3 weapons\n"
        + "You have no weapons at the moment\n";
    String actual2 = player.displayPlayerInfo();
    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullWeapon() {
    player.addWeapon(null);
  }

  @Test
  public void testAddValidWeapon() {
    Weapon weapon = new WeaponImpl(2, 3, "Poison");
    player.addWeapon(weapon);
    String expected1 = "\nPoison";
    String actual1 = player.getWeaponsPossessed();
    String expected2 = "Soundarya, you can have a maximum of 3 weapons\n"
        + "Weapons with you at the moment are:\nPoison\n";
    String actual2 = player.displayPlayerInfo();
    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullWeapon() {
    player.removeWeapon(null);
  }

  @Test
  public void testRemoveValidWeapon() {
    Weapon weapon = new WeaponImpl(2, 3, "Poison");
    player.addWeapon(weapon);
    player.removeWeapon(weapon);
    String expected1 = "";
    String actual1 = player.getWeaponsPossessed();
    String expected2 = "Soundarya, you can have a maximum of 3 weapons\n"
        + "You have no weapons at the moment\n";
    String actual2 = player.displayPlayerInfo();
    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
  }
  
  @Test
  public void testIsComputer() {
    assertFalse(player.isComputer());
    player = player("comp", 2, true);
    assertTrue(player.isComputer());
  }
  
  @Test
  public void testGetWeaponForComputer() {
    Weapon weapon1 = new WeaponImpl(2, 3, "Poison");
    player.addWeapon(weapon1);
    Weapon weapon2 = new WeaponImpl(2, 2, "Poisonous insect");
    player.addWeapon(weapon2);
    Weapon weapon = player.getWeaponForComputer();
    assertEquals(weapon1, weapon);
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param name represents the name of the player
   * @param currentIndex represents the starting index of the player
   * @param isComputer represents if the player is a human or a computer.
   * @return PlayerImpl object.
   */
  protected PlayerImpl player(String name, int currentIndex, boolean isComputer) {
    return new PlayerImpl(name, currentIndex, isComputer);
  }
}
