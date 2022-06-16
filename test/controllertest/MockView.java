package controllertest;

import controller.Features;
import view.GameView;

/**
 * View model to test controller.
 */
public class MockView implements GameView {

  private StringBuilder log;

  /**
   * Constructor.
   * 
   * @param string the string builder object.
   */
  public MockView(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("String builder object not created.");
    }
    this.log = log;
  }

  @Override
  public void makeVisible() {
    log.append("In makeVisible(). There is no input.\n");

  }

  @Override
  public void addMenuListeners(Features listener) {
    log.append(String.format("In addMenuListeners(). listener is given as the input.\n", listener));

  }

  @Override
  public void addButtonListeners(Features listener) {
    log.append(
        String.format("In addButtonListeners(). listener is given as the input.\n", listener));

  }

  @Override
  public void addKeyListeners(Features listener) {
    log.append(String.format("In addKeyListeners(). listener is given as the input.\n", listener));

  }

  @Override
  public void addMouseListeners(String string, Features listener) {
    log.append(String.format(
        "In addMouseListeners(). string: %s, listener is given as the input.\n", string, listener));

  }

  @Override
  public void addPanel(Features listener) {
    log.append(String.format("In addPanel(). listener is given as the input.\n", listener));

  }

  @Override
  public void createDialog(String string, Features listener) {
    log.append(String.format("In createDialog(). string: %s, listener is given as the input.\n",
        string, listener));

  }

  @Override
  public void setFocus() {
    log.append("In setFocus(). There is no input.\n");

  }

  @Override
  public void refresh() {
    log.append("In refresh(). There is no input.\n");

  }

  @Override
  public void disbleMouseListener() {
    log.append("In disbleMouseListener(). There is no input.\n");

  }

}
