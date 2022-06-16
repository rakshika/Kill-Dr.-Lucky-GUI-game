package controller;

/**
 * The actual controller interface of the game. Contains the actions of the
 * controller that edits the controller based on the model.
 *
 */
public interface GameController {

  /**
   * The actual starting of the game.
   */
  void playGame();

  /**
   * Configures the button listeners.
   */
  void configureButtonListeners();

  /**
   * Configures the keyboard listeners.
   */
  void configureKeyListeners();

  /**
   * Configures the menu listeners.
   */
  void configureMenuListeners();

  /**
   * Configures the mouse listeners.
   * 
   * @param string represents the command that calls the listener.
   * @throws IllegalArgumentException if string is null or empty.
   */
  void configureMouseListeners(String string);

}
