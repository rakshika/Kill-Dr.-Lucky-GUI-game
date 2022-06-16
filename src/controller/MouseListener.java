package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The mouse listener used in the program.
 */
public class MouseListener extends MouseAdapter {

  private String string;
  private Features listener;

  /**
   * Constructor. 
   * 
   * @param string the command that calls the mouse click.
   * @param listener the listener that listens to the event.
   * @throws IllegalArgumentException if string or features is null.
   */
  public MouseListener(String string, Features listener) {
    if (string == null || "".equals(string)) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.string = string;
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("Mouse event cannot be null");
    }
    int x = (((e.getY() - 25) / 25) + 1);
    int y = (((e.getX() - 25) / 25) + 1);
    listener.handleMouseClick(x, y, string);
    super.mouseClicked(e);
  }

}
