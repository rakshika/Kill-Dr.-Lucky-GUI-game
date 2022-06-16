package controller.commandcontroller;

import controller.Features;
import model.World;
import view.GameView;

/**
 * Command to look around.
 */
public class LookAround implements CommandController {
  
  private Features listener;
  private GameView view;
  private World model;
  
  /**
   * Constructor.
   * 
   * @param view the view of the game.
   * @param listener the listener of the event.
   * @param model the model of the game.
   * @throws IllegalArgumentException when model, view or controller are null.
   */
  public LookAround(GameView view, Features listener, World model) {
    if (view == null || listener == null || model == null) {
      throw new IllegalArgumentException("View, Listener and model cannot be null.");
    }
    this.listener = listener;
    this.view = view;
    this.model = model;
  }
  
  @Override
  public void go() {
    String returnValue = model.lookAround();
    view.createDialog(String.format("lookAround!*,%s", returnValue), listener);
  }

}
