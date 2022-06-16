package view;

import controller.Features;
import controller.MouseListener;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.ReadOnlyWorld;

/**
 * The main frame of the program.
 */
public class GameViewImpl extends JFrame implements GameView {

  private static final long serialVersionUID = 1L;
  private JMenuItem newWorld;
  private JMenuItem sameWorld;
  private JMenuItem exit;
  private PlayGame playGamePanel;
  private CardLayout cardLayout;
  private WelcomeScreen welcomeScreen;
  private JButton createPlayersButton;
  private GameOverScreen gameOverScreen;
  private ReadOnlyWorld readOnlyModel;
  private JMenuBar gameMenuBar;
  private JPanel gamePanel;
  private MouseListener mouseListener;
  private JScrollPane scrollPane;

  /**
   * Constructor.
   * 
   * @param readOnlyModel the read-only model that contains required information
   *                      used in the view.
   * @throws IllegalArgumentException if readOnlyModel is null.
   */
  public GameViewImpl(ReadOnlyWorld readOnlyModel) {
    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.readOnlyModel = readOnlyModel;
    createPlayersButton = new JButton("Create players to start the game");
    this.gameMenuBar = new JMenuBar();

    this.addMenuBar();
    this.setSize(new Dimension(1000, 800));
    this.setMinimumSize(new Dimension(300, 300));
    this.setBounds(0, 0, this.getWidth(), this.getHeight());
    gamePanel = new JPanel();
    gamePanel.setSize(new Dimension(1000, 800));
    gamePanel.setMinimumSize(new Dimension(300, 300));
    gamePanel.setLocation(this.getLocation());
    cardLayout = new CardLayout();
    gamePanel.setLayout(cardLayout);
    welcomeScreen = new WelcomeScreen(createPlayersButton);
    gamePanel.add(welcomeScreen, "1");
    welcomeScreen.setVisible(true);
    scrollPane = new JScrollPane(gamePanel);
    getContentPane().add(scrollPane);
    scrollPane.setMaximumSize(new Dimension(1000, 800));
    scrollPane.setMinimumSize(new Dimension(300, 300));
    scrollPane.setPreferredSize(new Dimension(1000, 800));
    
    this.pack();
    this.makeVisible();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

  }

  @Override
  public void addPanel(Features listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    welcomeScreen.setVisible(false);
    playGamePanel = new PlayGame(readOnlyModel, listener);
    this.gameMenuBar.setVisible(false);
    gamePanel.add(playGamePanel, "2");
    playGamePanel.setVisible(true);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Adds Menu bar to the frame.
   */
  private void addMenuBar() {

    JMenu file = new JMenu("File");
    gameMenuBar.add(file);

    JMenu newGame = new JMenu("New Game");
    newWorld = new JMenuItem("New World");
    newGame.add(newWorld);
    sameWorld = new JMenuItem("Same World");
    newGame.add(sameWorld);

    file.add(newGame);
    file.addSeparator();

    exit = new JMenuItem("Exit");

    file.add(exit);
    this.setJMenuBar(gameMenuBar);
    gameMenuBar.repaint();
  }

  @Override
  public void addMenuListeners(Features listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.setFocusable(true);
    this.requestFocusInWindow();
    sameWorld.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listener.handleMenuClick(e.getActionCommand());
      }

    });
    newWorld.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listener.handleMenuClick(e.getActionCommand());
      }

    });
    exit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listener.handleMenuClick(e.getActionCommand());
      }

    });

  }

  @Override
  public void addButtonListeners(Features listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.createPlayersButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listener.handleButtonClick(e.getActionCommand());
      }
    });
  }

  @Override
  public void addKeyListeners(Features listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e == null) {
          throw new IllegalArgumentException("Key press cannot be null");
        }
        listener.handleKeyClick(e.getKeyChar());

      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Empty because it is not needed
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Empty because it is not needed
      }
    });
  }

  @Override
  public void addMouseListeners(String string, Features listener) {
    if (listener == null || string == null || "".equals(string)) {
      throw new IllegalArgumentException("Listener and string cannot be null");
    }
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.mouseListener = new MouseListener(string, listener);
    gamePanel.addMouseListener(mouseListener);
  }

  @Override
  public void createDialog(String string, Features listener) {
    if (listener == null || string == null || "".equals(string)) {
      throw new IllegalArgumentException("Listener and string cannot be null");
    }
    if (string.contains("attackTargetDone")) {
      JOptionPane.showMessageDialog(null, string.split("\n")[1]);
    } else if (string.contains("attackTarget")) {
      new AttackTargetDialog(this.readOnlyModel, listener).setVisible(true);
    } else if (string.contains("pickWeaponDone")) {
      JOptionPane.showMessageDialog(null, string.split("\n")[1]);
    } else if (string.contains("pickWeapon")) {
      new PickWeaponDialog(this.readOnlyModel, listener).setVisible(true);
    } else if (string.contains("lookAround")) {
      JTextArea textArea = new JTextArea(40, 30);
      textArea.setText(string.split("!*,")[1]);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      JOptionPane.showMessageDialog(null, scrollPane);
    } else if (string.contains("createPlayer")) {
      new PlayerDetailsDialog(this.readOnlyModel, listener);
    } else if (string.contains("exceptionDialog")) {
      JOptionPane.showMessageDialog(null, string.split("\n")[1]);
    } else if (string.contains("TargetInfoButton")) {
      String[] targetInfo = string.split("!*,")[1].split("\n");
      JOptionPane.showMessageDialog(null,
          String.format("Target Name: %s\nTarget Health: %s\nTarget's space: %s", targetInfo[0],
              targetInfo[1], targetInfo[2]));
    } else if (string.contains("gameOverScreen")) {
      playGamePanel.setVisible(false);
      gameOverScreen = new GameOverScreen(readOnlyModel);
      gamePanel.add(gameOverScreen, "3");
      gameOverScreen.setVisible(true);
    } else if (string.contains("RefreshNow")) {
      JOptionPane.showMessageDialog(null, readOnlyModel.computerTurn());
    } else if (string.contains("InvalidKey")) {
      JOptionPane.showMessageDialog(null,
          "See the options and give the proper input.\n\nWhat do you want to do?\n\n"
          + "m -> Move to a neighboring space<\np -> Pick a weapon\nl -> Look around "
          + "your space\na -> Attack Target\nx -> Move Pet\nq -> Quit");
    } else if (string.contains("MovePetDone")) {
      JOptionPane.showMessageDialog(null, string.split("\n")[1]);
    } else if (string.contains("QuitGame")) {
      this.dispose();
      System.exit(0);
    }

  }

  @Override
  public void setFocus() {
    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  @Override
  public void refresh() {
    this.repaint();

  }

  @Override
  public void disbleMouseListener() {
    gamePanel.removeMouseListener(mouseListener);
  }

}
