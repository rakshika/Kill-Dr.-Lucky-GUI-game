package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.ReadOnlyWorld;

/**
 * The dialog that appears when new player should be created.
 */
public class PlayerDetailsDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @param listener      the listener to be added to the dialog.
   * @throws IllegalArgumentException if invalid parameters are passed.
   */
  public PlayerDetailsDialog(ReadOnlyWorld readOnlyModel, Features listener) {
    if (readOnlyModel == null || listener == null) {
      throw new IllegalArgumentException("Model or listener were not valid.");
    }
    this.contentPanel = new JPanel();

    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    JLabel playerNameLabel = new JLabel("Player name");
    playerNameLabel.setBounds(83, 41, 77, 16);
    contentPanel.add(playerNameLabel);

    JTextField textField = new JTextField();
    textField.setBounds(172, 36, 130, 26);
    contentPanel.add(textField);
    textField.setColumns(10);

    JLabel playerSpaceLabel = new JLabel("Start space");
    playerSpaceLabel.setBounds(83, 96, 77, 16);
    contentPanel.add(playerSpaceLabel);

    JComboBox<String> comboBox = new JComboBox<String>();
    for (int i = 0; i < readOnlyModel.getSpaceNames().size(); i++) {
      comboBox.addItem(readOnlyModel.getSpaceNames().get(i));
    }
    comboBox.setBounds(172, 92, 130, 27);
    contentPanel.add(comboBox);

    JRadioButton humanRadio = new JRadioButton("Human");
    humanRadio.setBounds(83, 161, 77, 23);
    contentPanel.add(humanRadio);

    JRadioButton computerRadio = new JRadioButton("Computer");
    computerRadio.setBounds(219, 161, 94, 23);
    contentPanel.add(computerRadio);

    ButtonGroup radioButtons = new ButtonGroup();
    radioButtons.add(humanRadio);
    radioButtons.add(computerRadio);

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
    getContentPane().add(buttonPane, BorderLayout.SOUTH);

    JButton startGameButton = new JButton("Start game!");
    startGameButton.setActionCommand("StartGameWithPlayers");
    buttonPane.add(startGameButton);
    startGameButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        StringBuilder playerDetails = new StringBuilder();
        playerDetails.append(startGameButton.getActionCommand());
        playerDetails.append("\n");
        playerDetails.append(textField.getText());
        playerDetails.append("\n");
        playerDetails.append(String.valueOf(comboBox.getSelectedItem()));
        playerDetails.append("\n");
        playerDetails.append(computerRadio.isSelected());
        listener.handleButtonClick(playerDetails.toString());
        dispose();
      }
    });
    JButton createPlayerButton = new JButton("Create more");
    createPlayerButton.setActionCommand("PlayerDetails");
    buttonPane.add(createPlayerButton);
    getRootPane().setDefaultButton(startGameButton);
    createPlayerButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        StringBuilder playerDetails = new StringBuilder();
        playerDetails.append(createPlayerButton.getActionCommand());
        playerDetails.append("\n");
        playerDetails.append(textField.getText());
        playerDetails.append("\n");
        playerDetails.append(String.valueOf(comboBox.getSelectedItem()));
        playerDetails.append("\n");
        playerDetails.append(computerRadio.isSelected());
        listener.handleButtonClick(playerDetails.toString());
        dispose();
      }
    });
    this.setVisible(true);
  }
}
