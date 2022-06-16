package view;

import controller.Features;

/**
 * The view interface.
 */
public interface GameView {

  /**
   * Sets the visibility of the view to true.
   */
  void makeVisible();

  /**
   * Adds the menu listeners to the view.
   * 
   * @param listener the listener to be added.
   * @throws IllegalArgumentException if listener is null.
   */
  void addMenuListeners(Features listener);

  /**
   * Adds the button listeners to the view.
   * 
   * @param listener the listener to be added.
   * @throws IllegalArgumentException if listener is null.
   */
  void addButtonListeners(Features listener);

  /**
   * Adds the key listeners to the view.
   * 
   * @param listener the listener to be added.
   * @throws IllegalArgumentException if listener is null.
   */
  void addKeyListeners(Features listener);

  /**
   * Adds the mouse listeners to the view.
   * 
   * @param string   the command that needs the mouse listener.
   * @param listener the listener to be added.
   * @throws IllegalArgumentException if listener or string is null.
   */
  void addMouseListeners(String string, Features listener);

  /**
   * Adds the panel to the view.
   * 
   * @param listener the listener added to the panel.
   * @throws IllegalArgumentException if listener is null.
   */
  void addPanel(Features listener);

  /**
   * Create the dialog boxes in the view.
   * 
   * @param string based on which the respective dialog is to be called.
   * @param listener the listener to be passed to the dialog, if required.
   * @throws IllegalArgumentException if listener or string is null.
   */
  void createDialog(String string, Features listener);

  /**
   * Sets the focus to the frame.
   */
  void setFocus();

  /**
   * Refreshes the view.
   */
  void refresh();

  /**
   * Disable the mouse listener.
   */
  void disbleMouseListener();

}
