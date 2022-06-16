package controller.commandcontroller;

import controller.Features;
import view.GameView;

/**
 * Command to move the player.
 */
public class MovePlayer implements CommandController {

  private Features listener;
  private GameView view;
  
  /**
   * Controller.
   * 
   * @param view the view of the game.
   * @param listener the listener of the event.
   * @throws IllegalArgumentException if view or listener are null.
   */
  public MovePlayer(GameView view, Features listener) {
    if (view == null || listener == null) {
      throw new IllegalArgumentException("View and Listener cannot be null.");
    }
    this.listener = listener;
    this.view = view;
  }
  
  @Override
  public void go() {
    view.addMouseListeners("movePlayer", listener);
  }

}
