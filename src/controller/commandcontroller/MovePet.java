package controller.commandcontroller;

import controller.Features;
import view.GameView;

/**
 * Command to move the pet.
 */
public class MovePet implements CommandController {

  private Features listener;
  private GameView view;

  /**
   * Controller. 
   * 
   * @param view the view of the game.
   * @param listener the listener of the event.
   * @throws IllegalArgumentException when view or listeners are null.
   */
  public MovePet(GameView view, Features listener) {
    if (view == null || listener == null) {
      throw new IllegalArgumentException("View and listener cannot be null.");
    }
    this.listener = listener;
    this.view = view;
  }

  @Override
  public void go() {
    view.addMouseListeners("movePet", listener);
  }

}
