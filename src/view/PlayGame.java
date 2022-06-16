package view;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ReadOnlyWorld;

/**
 * The panel where the game is actually played.
 */
public class PlayGame extends JPanel {

  private static final long serialVersionUID = 1L;
  private ReadOnlyWorld readOnlyModel;
  private int totalRow;
  private int totalCol;
  private int totalSpaces;
  private int width;
  private int height;
  private JButton targetbtn;
  private JLabel petLabel;
  private Icon iconPet;
  private Icon iconPlayer;
  private Map<String, JButton> playerButtonList;
  private JLabel labelSpaceInfo;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @param listener the listener to be added to the panel.
   * @throws IllegalArgumentException if invalid parameters are passed.
   */
  public PlayGame(ReadOnlyWorld readOnlyModel, Features listener) {
    if (readOnlyModel == null || listener == null) {
      throw new IllegalArgumentException("Model or listener were not valid.");
    }
    this.readOnlyModel = readOnlyModel;
    this.labelSpaceInfo = new JLabel();
    
    totalRow = readOnlyModel.getWorldSize()[0];
    totalCol = readOnlyModel.getWorldSize()[1];
    totalSpaces = readOnlyModel.getWorldSize()[2];
    width = totalCol * 25 + 25;
    height = totalRow * 25 + 25;
    Icon iconTarget = new ImageIcon(new ImageIcon("res/target.png").getImage()
        .getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    targetbtn = new JButton(iconTarget);
    targetbtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.handleButtonClick("TargetInfoButton");
      }
    });
    iconPet = new ImageIcon(new ImageIcon("res/pet.png").getImage().getScaledInstance(15, 15,
        Image.SCALE_DEFAULT));
    petLabel = new JLabel(iconPet);
    iconPlayer = new ImageIcon(new ImageIcon("res/player.png").getImage()
        .getScaledInstance(15, 15, Image.SCALE_DEFAULT));

    playerButtonList = new HashMap<String, JButton>();
    // Create all the buttons needed for players:
    for (int i = 0; i < readOnlyModel.getSpaceNames().size(); i++) {
      if (readOnlyModel.getPlayersInSpace(i).size() != 0) {
        for (int j = 0; j < readOnlyModel.getPlayersInSpace(i).size(); j++) {
          if (!"".equals(readOnlyModel.getPlayersInSpace(i).get(j))) {
            if (readOnlyModel.getPlayersInSpace(i).get(j).charAt(0) != '*'
                && readOnlyModel.getPlayersInSpace(i).get(j).charAt(0) != '#') {
              JButton playerbtn = new JButton(iconPlayer);
              playerbtn.setFont(new Font("Arial", Font.PLAIN, 10));
              String playerName = readOnlyModel.getPlayersInSpace(i).get(j);
              playerbtn.setText(playerName);
              playerbtn.setOpaque(false);
              playerbtn.setContentAreaFilled(false);
              playerbtn.setBorderPainted(false);
              playerButtonList.put(playerName, playerbtn);
              playerButtonList.get(playerName).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  if (playerName.equals(readOnlyModel.getTurn())) {
                    JOptionPane.showMessageDialog(null,
                        readOnlyModel.displayPlayerInfo(playerName));
                  }
                }
              });
            }
          }
        }
      }
    }
    this.setMaximumSize(new Dimension(1000, 800));
    this.setMinimumSize(new Dimension(300, 300));
    this.setPreferredSize(new Dimension(1000, height));
    this.setLayout(null);
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException("Graphics object is not valid");
    }
    super.paintComponent(g);
    totalRow = readOnlyModel.getWorldSize()[0];
    totalCol = readOnlyModel.getWorldSize()[1];
    totalSpaces = readOnlyModel.getWorldSize()[2];
    width = totalCol * 25 + 25;
    height = totalRow * 25 + 25;

    String[] spaceInfoArray = readOnlyModel.displaySpaceInfo().split("\n");
    StringBuilder spaceInfo = new StringBuilder();
    spaceInfo.append(
        String.format("<html><b>%s, it is your turn!</b><br><br>", readOnlyModel.getTurn()));
    for (int i = 0; i < spaceInfoArray.length; i++) {
      spaceInfo.append(spaceInfoArray[i]);
      spaceInfo.append("<br>");
    }
    spaceInfo.append("<br><br>What do you want to do?<br><br>m -> Move to a neighboring space<br>"
        + "p -> Pick a weapon<br>l -> Look around your space<br>a -> Attack Target<br>"
        + "x -> Move Pet<br>q -> Quit<br></html>");
    labelSpaceInfo.setText(spaceInfo.toString());
    labelSpaceInfo.setLocation(width + 10, 30);
    labelSpaceInfo.setSize(new Dimension(500, 700));
    add(labelSpaceInfo);
    this.setVisible(true);
    
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillRect(0, 0, width, height);
    for (int i = 0; i < totalSpaces; i++) {
      g2d.setColor(Color.GRAY);
      int[] coordinates = new int[4];
      for (int k = 0; k < readOnlyModel.getSpaceCoordinates(i).length; k++) {
        coordinates[k] = readOnlyModel.getSpaceCoordinates(i)[k];
      }
      g2d.fillRect(coordinates[1] * 25, coordinates[0] * 25,
          (coordinates[3] - coordinates[1]) * 25 + 25,
          (((coordinates[2]) * 25 + 25) - ((coordinates[0]) * 25 + 25)) + 25);

      // to make pet's room partially invisible
      /*if (readOnlyModel.getPlayersInSpace(i).size() > 0) {
        for (int j = 0; j < readOnlyModel.getPlayersInSpace(i).size(); j++) {
          if (!"".equals(readOnlyModel.getPlayersInSpace(i).get(j))) {
            if (readOnlyModel.getPlayersInSpace(i).get(j).charAt(0) == '#') {
              g2d.setColor(Color.LIGHT_GRAY);
              g2d.fillRect(coordinates[1] * 25, coordinates[0] * 25,
                  (coordinates[3] - coordinates[1]) * 25 + 25,
                  (((coordinates[2]) * 25 + 25) - ((coordinates[0]) * 25 + 25)) + 25);
            }
          }
        }
      }*/
      //

      g2d.setColor(Color.black);
      g2d.drawRect(coordinates[1] * 25, coordinates[0] * 25,
          (coordinates[3] - coordinates[1]) * 25 + 25,
          (((coordinates[2]) * 25 + 25) - ((coordinates[0]) * 25 + 25)) + 25);
      g2d.drawString(readOnlyModel.spaceToString()[i], coordinates[1] * 25 + 6,
          coordinates[0] * 25 + 12);

      // Add players to the space
      if (readOnlyModel.getPlayersInSpace(i).size() != 0) {
        for (int j = 0; j < readOnlyModel.getPlayersInSpace(i).size(); j++) {
          if (!("".equals(readOnlyModel.getPlayersInSpace(i).get(j)))) {
            if (readOnlyModel.getPlayersInSpace(i).get(j).charAt(0) == '*') {
              // draw target
              targetbtn.setFont(new Font("Arial", Font.PLAIN, 10));
              targetbtn.setText(readOnlyModel.getPlayersInSpace(i).get(j).split("\\*")[1]);
              targetbtn.setForeground(Color.RED);
              targetbtn.setBounds(coordinates[1] * 25,
                  coordinates[0] * 25 + (10 * (j + 1)), 100, 20);
              targetbtn.setOpaque(false);
              targetbtn.setContentAreaFilled(false);
              targetbtn.setBorderPainted(false);
              add(targetbtn);
            } else if (readOnlyModel.getPlayersInSpace(i).get(j).charAt(0) == '#') {
              // draw pet only when the space has player with active turn.
              petLabel.setVisible(false);
              if (readOnlyModel.getPlayersInSpace(i).contains(readOnlyModel.getTurn())) {
                petLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                petLabel.setText(readOnlyModel.getPlayersInSpace(i).get(j).split("#")[1]);
                petLabel.setForeground(Color.BLUE);
                petLabel.setBounds(coordinates[1] * 25, coordinates[0] * 25 + (13 * (j + 1)),
                    100, 20);
                petLabel.setVisible(true);
                add(petLabel);
              }
            } else {
              // draw player
              String playerName = readOnlyModel.getPlayersInSpace(i).get(j);
              playerButtonList.get(playerName).setBounds(coordinates[1] * 25,
                  coordinates[0] * 25 + (15 * (j + 1)), 100, 20);
              add(playerButtonList.get(playerName));
            }
          }
        }
      }
    }
  }
}
