package controllertest;

import static org.junit.Assert.assertEquals;

import controller.GameController;
import controller.GameControllerImpl;
import model.World;
import org.junit.Before;
import org.junit.Test;
import view.GameView;

/**
 * Tests the exceptional cases of the controller.
 */
public class ControllerExceptionsTest {

  private GameController controller;
  private StringBuilder log;
  private World model;
  private GameView view;

  /**
   * Initialization of required fields.
   */
  @Before
  public void setup() {
    log = new StringBuilder();
    model = new MockExceptionsModel(log, "noooooo");
    view = new MockExceptionsView(log);
    controller = new GameControllerImpl(model, view);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullView() {
    controller = new GameControllerImpl(model, null);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    controller = new GameControllerImpl(null, view);
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

  @Test (expected = IllegalArgumentException.class)
  public void testConfigureMouseListenersNullString() {
    controller.configureMouseListeners("");
    assertEquals("In addMouseListeners(). string: mouse string, listener is given as the input.\n",
        log.toString());
  }
}