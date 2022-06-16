package view;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.ReadOnlyWorld;

/**
 * The panel for when the game ends.
 */
public class GameOverScreen extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @throws IllegalArgumentException if readOnlyModel is null.
   */
  public GameOverScreen(ReadOnlyWorld readOnlyModel) {
    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Empty read only model provided");
    }
    this.setSize(500, 500);
    setLayout(null);
    JLabel lblNewLabel;
    if (readOnlyModel.getWinner().equals("")) {
      lblNewLabel = new JLabel(
          "<html>Game Over!<br>Max number of turns reached.<br>The target escaped :(</html>");
    } else {
      String output = String.format("<html>Game over!<br>Target died!<br>%s wins!",
          readOnlyModel.getWinner());
      lblNewLabel = new JLabel(output);
    }
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
    lblNewLabel.setSize(500, 500);
    lblNewLabel.setLocation(200, 50);
    this.add(lblNewLabel);
    this.setVisible(true);
  }
}
