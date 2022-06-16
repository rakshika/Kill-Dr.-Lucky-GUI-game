package controller;

/**
 * This interface represents a set of features implemented in the controller.
 * Each method does several operations depending on the parameters provided.
 * 
 * <p>Each method takes in necessary data depending on which the respective
 * computation is made.
 */
public interface Features {

  /**
   * Handles the click operation of the menu items.
   * 
   * @param actionCommand the command of the menu item clicked.
   * @throws IllegalArgumentException if actionCommand is null or empty.
   */
  void handleMenuClick(String actionCommand);

  /**
   * Handles the click operation of the buttons.
   * 
   * @param string the name of the button clicked with inputs taken, if any.
   * @throws IllegalArgumentException if string is null or empty.
   */
  void handleButtonClick(String string);

  /**
   * Handles the keyboard operations.
   * 
   * @param c the character of the keyboard button pressed.
   * @throws IllegalArgumentException if c is empty.
   */
  void handleKeyClick(char c);

  /**
   * Handles the mouse click operations based on the command that calls it.
   * 
   * @param x      the x coordinate of the mouse click.
   * @param y      the y coordinate of the mouse click.
   * @param string the command that requires the mouse click.
   * @throws IllegalArgumentException if x, y or string are invalid.
   */
  void handleMouseClick(int x, int y, String string);

}
