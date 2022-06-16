package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.ActualRandomGenerator;
import model.RandomGenerator;
import model.World;
import model.WorldImpl;
import view.GameView;
import view.GameViewImpl;

/**
 * The driver class that contains the main method.
 */
public class Main {

  /**
   * The method that starts the execution of the program.
   * 
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    
    String fileName = args[0];
    int maxMoves = Integer.parseInt(args[1]);
    File file = new File(fileName);
    Readable fileReader = null;
    try {
      fileReader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }
    RandomGenerator random = new ActualRandomGenerator();
    
    World model = new WorldImpl(fileReader, random, maxMoves);
    GameView view = new GameViewImpl(model);
    GameController controller = new GameControllerImpl(model, view);
    controller.playGame();
  }
}
