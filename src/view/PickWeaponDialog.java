package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.ReadOnlyWorld;

/**
 * The dialog that appears when weapon is picked.
 */
public class PickWeaponDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @param listener      the listener to be added to the dialogs.
   * @throws IllegalArgumentException if invalid parameters are passed.
   */
  public PickWeaponDialog(ReadOnlyWorld readOnlyModel, Features listener) {
    if (readOnlyModel == null || listener == null) {
      throw new IllegalArgumentException("Model or listener were not valid.");
    }
    this.contentPanel = new JPanel();
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
    getContentPane().add(buttonPane, BorderLayout.SOUTH);

    if (readOnlyModel.weaponsInSpaceToString()[0].equals("")) {
      JLabel noWeaponLabel = new JLabel("There are no weapons in this room");
      noWeaponLabel.setBounds(36, 37, 269, 16);
      contentPanel.add(noWeaponLabel);

      JButton okButton = new JButton("OK");
      okButton.setActionCommand("pickWeaponDone");
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          dispose();
        }
      });
      buttonPane.add(okButton);
      getRootPane().setDefaultButton(okButton);
    } else {
      JLabel lblNewLabel = new JLabel("Which Weapon do you want to pick?");
      lblNewLabel.setBounds(36, 37, 269, 16);
      contentPanel.add(lblNewLabel);

      JComboBox<String> comboBox = new JComboBox<String>();
      for (int i = 0; i < readOnlyModel.weaponsInSpaceToString().length; i++) {
        comboBox.addItem(readOnlyModel.weaponsInSpaceToString()[i]);
      }
      comboBox.setBounds(36, 80, 227, 27);
      contentPanel.add(comboBox);

      JButton okButton = new JButton("Pick");
      okButton.setActionCommand("pickWeaponDone");
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          listener.handleButtonClick(
              String.format("%s\n%s", okButton.getActionCommand(), comboBox.getSelectedItem()));
          dispose();
        }
      });
      buttonPane.add(okButton);
      getRootPane().setDefaultButton(okButton);
    }

  }
}
