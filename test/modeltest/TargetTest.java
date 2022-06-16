package modeltest;

import static org.junit.Assert.assertEquals;

import model.TargetImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Target class.
 */
public class TargetTest {

  private TargetImpl targetCharacter;

  /**
   * Creates all the objects required for testing this class.
   */
  @Before
  public void setUp() {
    targetCharacter = target(50, "Oswald");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHealth() {
    targetCharacter = target(-2, "Oswald");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    targetCharacter = target(2, "");
  }

  @Test
  public void testMoveTargetFrom0to1() {
    targetCharacter.moveTarget(24);
    int actual = targetCharacter.getCurrentIndex();
    assertEquals(1, actual);
  }

  @Test
  public void testMoveTargetFrom1toLast() {
    for (int i = 0; i < 23; i++) {
      targetCharacter.moveTarget(24);
      assertEquals(i + 1, targetCharacter.getCurrentIndex());
    }
  }

  @Test
  public void testMoveTargetBackToZero() {
    for (int i = 0; i < 24; i++) {
      targetCharacter.moveTarget(24);
    }
    assertEquals(0, targetCharacter.getCurrentIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateNegative() {
    targetCharacter.updateHealth(-2);
  }

  @Test
  public void testUpdateValidHealth() {
    targetCharacter.updateHealth(2);
    assertEquals(48, targetCharacter.getHealth());
  }

  @Test
  public void testToString() {
    assertEquals("Target [name=Oswald, health=50, currentIndex=0]", targetCharacter.toString());
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param health of the target.
   * @param name of the target.
   * @return the target object created.
   */
  protected TargetImpl target(int health, String name) {
    return new TargetImpl(health, name);
  }

}
