package controller.commandcontroller;

import controller.Features;
import view.GameView;

/**
 * Command to attack the target.
 */
public class AttackTarget implements CommandController {

  private Features listener;
  private GameView view;
  
  /**
   * Constructor.
   * 
   * @param view the view of the game.
   * @param listener the listener that listens to the event.
   * @throws IllegalArgumentException when view or listener are null.
   */
  public AttackTarget(GameView view, Features listener) {
    if (view == null || listener == null) {
      throw new IllegalArgumentException("View and Listener cannot be null.");
    }
    this.listener = listener;
    this.view = view;
  }
  
  @Override
  public void go() {
    view.createDialog("attackTarget", listener);
  }

}
