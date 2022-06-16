package controller.commandcontroller;

import controller.Features;
import view.GameView;

/**
 * Command to quit the game.
 */
public class QuitGame implements CommandController {


  private Features listener;
  private GameView view;

  /**
   * Controller. 
   * 
   * @param view the view of the game.
   * @param listener the listener of the event.
   * @throws IllegalArgumentException when view or listeners are null.
   */
  public QuitGame(GameView view, Features listener) {
    if (view == null || listener == null) {
      throw new IllegalArgumentException("View and listener cannot be null.");
    }
    this.listener = listener;
    this.view = view;
  }
  
  @Override
  public void go() {
    view.createDialog("QuitGame", listener);

  }

}
