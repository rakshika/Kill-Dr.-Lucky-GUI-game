package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The panel that appears at the beginning of the screen.
 */
public class WelcomeScreen extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param createPlayersButton the button to be added to the panel.
   * @throws IllegalArgumentException if invalid parameter is passed for JButton.
   */
  public WelcomeScreen(JButton createPlayersButton) {
    if (createPlayersButton == null) {
      throw new IllegalArgumentException("Button does not exist");
    }
    setLayout(new BorderLayout(0, 0));

    JLabel welcomeLabel = new JLabel("KILL HIM!");
    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 200));
    add(welcomeLabel, BorderLayout.NORTH);

    JLabel creditsLabel = new JLabel(
        "<html>Credits:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>Rakshika Raju"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>Soundarya Srikanta&nbsp;&nbsp;"
        + "&nbsp;&nbsp;&nbsp;</html>");
    add(creditsLabel, BorderLayout.EAST);
    welcomeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 100));

    createPlayersButton.setActionCommand("CreatePlayers");
    add(createPlayersButton, BorderLayout.SOUTH);
  }
}