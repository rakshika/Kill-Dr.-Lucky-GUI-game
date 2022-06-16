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
 * The dialog that appears when target is attacked.
 */
public class AttackTargetDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @param listener The listener listening to this dialog.
   * @throws IllegalArgumentException if readOnlyModel or listener is null.
   */
  public AttackTargetDialog(ReadOnlyWorld readOnlyModel, Features listener) {
    this.contentPanel = new JPanel();
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);

    JLabel lblNewLabel = new JLabel("Which Weapon do you want to use?");
    lblNewLabel.setBounds(36, 37, 269, 16);
    contentPanel.add(lblNewLabel);

    JComboBox<String> comboBox = new JComboBox<String>();
    for (int i = 0; i < readOnlyModel.weaponsWithPlayerToString().length; i++) {
      comboBox.addItem(readOnlyModel.weaponsWithPlayerToString()[i]);
    }
    comboBox.addItem("Poke in the eye");
    comboBox.setBounds(36, 80, 227, 27);
    contentPanel.add(comboBox);

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
    getContentPane().add(buttonPane, BorderLayout.SOUTH);

    JButton okButton = new JButton("Attack");
    okButton.setActionCommand("AttackTargetButton");
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StringBuilder attackTargetDetails = new StringBuilder();
        attackTargetDetails.append(okButton.getActionCommand());
        attackTargetDetails.append("\n");
        attackTargetDetails.append(String.valueOf(comboBox.getSelectedItem()));
        listener.handleButtonClick(attackTargetDetails.toString());
        dispose();
      }
    });
    buttonPane.add(okButton);
    getRootPane().setDefaultButton(okButton);

  }
}
