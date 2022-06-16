package controllertest;

import static org.junit.Assert.assertEquals;

import controller.GameController;
import controller.GameControllerImpl;
import model.World;
import org.junit.Before;
import org.junit.Test;
import view.GameView;

/**
 * Tests the functioning of the controller.
 */
public class ControllerTest {

  private GameController controller;
  private StringBuilder log;

  /**
   * Initialization of required fields.
   */
  @Before
  public void setup() {
    log = new StringBuilder();
    World model = new MockHumanWorld(log, "yayyy");
    GameView view = new MockView(log);
    controller = new GameControllerImpl(model, view);
  }

  @Test
  public void testPlayGame() {
    controller.playGame();
    assertEquals("In makeVisible(). There is no input.\n"
        + "In addMenuListeners(). listener is given as the input.\n"
        + "In addButtonListeners(). listener is given as the input.\n", log.toString());
  }

  @Test
  public void testConfigureButtonListeners() {
    controller.configureButtonListeners();
    assertEquals("In addButtonListeners(). listener is given as the input.\n", log.toString());

  }

  @Test
  public void testConfigureKeyListeners() {
    controller.configureKeyListeners();
    assertEquals("In addKeyListeners(). listener is given as the input.\n", log.toString());

  }

  @Test
  public void testConfigureMenuListeners() {
    controller.configureMenuListeners();
    assertEquals("In addMenuListeners(). listener is given as the input.\n", log.toString());

  }

  @Test
  public void testConfigureMouseListeners() {
    controller.configureMouseListeners("mouse string");
    assertEquals("In addMouseListeners(). string: mouse string, listener is given as the input.\n",
        log.toString());

  }

}