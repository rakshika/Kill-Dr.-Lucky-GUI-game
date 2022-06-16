package controller.commandcontroller;

import controller.Features;
import view.GameView;

/**
 * Command to attack the target.
 */
public class PickWeapon implements CommandController {

  private Features listener;
  private GameView view;

  /**
   * Controller.
   * 
   * @param view the view of the game.
   * @param listener the listener of the event.
   * @throws IllegalArgumentException if the view or the listener are null.
   */
  public PickWeapon(GameView view, Features listener) {
    if (view == null || listener == null) {
      throw new IllegalArgumentException("View or Listener cannot be null.");
    }
    this.listener = listener;
    this.view = view;
  }

  @Override
  public void go() {
    view.createDialog("pickWeapon", listener);
  }

}
