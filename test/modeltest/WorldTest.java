package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import model.RandomGenerator;
import model.SequentialRandomGenerator;
import model.World;
import model.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the World interface.
 */
public class WorldTest {

  private World world;

  /**
   * Creates all the objects required for testing.
   */
  @Before
  public void setUp() {
    String file = "33 22 Vampire Mansion\n5 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
  }

  @Test
  public void testWorldCreationAndStartGame() {
    String actual = world.toString();
    String expected = "WorldImpl [target=Target [name=Oswald, health=5, currentIndex=0], "
        + "pet=PetImpl [petName=Jacob, currentLocation=0], row=33, column=22, numberOfSpaces=24, "
        + "spaces={Indoor Pool=Space [topLeftX = 27, topLeftY = 18, bottomRightX = 30, "
        + "bottomRightY = 22, nameOfSpace = Indoor Pool], Maids Room=Space "
        + "[topLeftX = 0, topLeftY = 13, bottomRightX = 4, bottomRightY = 17,"
        + " nameOfSpace = Maids Room], Kill Room=Space [topLeftX = 0, topLeftY = 0, "
        + "bottomRightX = 6, bottomRightY = 4, nameOfSpace = Kill Room], Store Room=Space "
        + "[topLeftX = 31, topLeftY = 14, bottomRightX = 32, bottomRightY = 18, nameOfSpace = "
        + "Store Room], Meal Room=Space [topLeftX = 0, topLeftY = 5, bottomRightX = 10, "
        + "bottomRightY = 8, nameOfSpace = Meal Room], Dressing Room=Space [topLeftX = 11, "
        + "topLeftY = 2, bottomRightX = 16, bottomRightY = 5, nameOfSpace = Dressing Room],"
        + " Balcony=Space [topLeftX = 27, topLeftY = 0, bottomRightX = 32, bottomRightY = 2, "
        + "nameOfSpace = Balcony], War Room=Space [topLeftX = 5, topLeftY = 14, "
        + "bottomRightX = 10, bottomRightY = 17, nameOfSpace = War Room], Blood Bank=Space "
        + "[topLeftX = 0, topLeftY = 9, bottomRightX = 4, bottomRightY = 12, nameOfSpace ="
        + " Blood Bank], Party Hall=Space [topLeftX = 17, topLeftY = 1, bottomRightX = 21, "
        + "bottomRightY = 7, nameOfSpace = Party Hall], Refreshment Room=Space "
        + "[topLeftX = 11, topLeftY = 14, bottomRightX = 11, bottomRightY = 22, "
        + "nameOfSpace = Refreshment Room], Trophy Room=Space [topLeftX = 31, "
        + "topLeftY = 19, bottomRightX = 32, bottomRightY = 22, nameOfSpace = Trophy Room],"
        + " Drawing room=Space [topLeftX = 23, topLeftY = 5, bottomRightX = 26, "
        + "bottomRightY = 9, nameOfSpace = Drawing room], Master Bedroom=Space "
        + "[topLeftX = 27, topLeftY = 3, bottomRightX = 32, bottomRightY = 9, nameOfSpace = "
        + "Master Bedroom], Living Room=Space [topLeftX = 5, topLeftY = 9, bottomRightX = 16,"
        + " bottomRightY = 13, nameOfSpace = Living Room], Booze Room=Space [topLeftX = 22, "
        + "topLeftY = 0, bottomRightX = 22, bottomRightY = 8, nameOfSpace = Booze Room], "
        + "Changing room=Space [topLeftX = 17, topLeftY = 17, bottomRightX = 22, "
        + "bottomRightY = 20, nameOfSpace = Changing room], Dead Room=Space [topLeftX = 0,"
        + " topLeftY = 18, bottomRightX = 6, bottomRightY = 22, nameOfSpace = Dead Room],"
        + " Sports Club=Space [topLeftX = 12, topLeftY = 15, bottomRightX = 16, "
        + "bottomRightY = 21, nameOfSpace = Sports Club], Library=Space [topLeftX = 17, "
        + "topLeftY = 9, bottomRightX = 22, bottomRightY = 13, nameOfSpace = Library], "
        + "Wine Cellar=Space [topLeftX = 33, topLeftY = 0, bottomRightX = 33, "
        + "bottomRightY = 11, nameOfSpace = Wine Cellar], Armory=Space [topLeftX = 33, "
        + "topLeftY = 12, bottomRightX = 33, bottomRightY = 22, nameOfSpace = Armory],"
        + " Parlor=Space [topLeftX = 23, topLeftY = 10, bottomRightX = 32,"
        + " bottomRightY = 13, nameOfSpace = Parlor], Game Room=Space [topLeftX = 23, "
        + "topLeftY = 14, bottomRightX = 30, bottomRightY = 17, nameOfSpace = Game Room]}, "
        + "weapons={Warewolf Traps=Weapon [weaponIndex=11, damage=2, weaponName=Warewolf Traps], "
        + "Poisoned Pool=Weapon [weaponIndex=8, damage=2, weaponName=Poisoned Pool], "
        + "Revolver=Weapon [weaponIndex=14, damage=2, weaponName=Revolver], Sharp Pencil="
        + "Weapon [weaponIndex=16, damage=2, weaponName=Sharp Pencil], Electric Racket="
        + "Weapon [weaponIndex=9, damage=2, weaponName=Electric Racket], Hybrid=Weapon "
        + "[weaponIndex=18, damage=5, weaponName=Hybrid], Sharp Sword=Weapon [weaponIndex=23, "
        + "damage=4, weaponName=Sharp Sword], Specific Weapon=Weapon [weaponIndex=13, "
        + "damage=4, weaponName=Specific Weapon], Wolf Siren=Weapon [weaponIndex=13, "
        + "damage=2, weaponName=Wolf Siren], Wolf Sword=Weapon [weaponIndex=0, damage=5, "
        + "weaponName=Wolf Sword], Wolf Bane=Weapon [weaponIndex=5, damage=5, weaponName="
        + "Wolf Bane], Invisible Robe=Weapon [weaponIndex=15, damage=3, weaponName="
        + "Invisible Robe], Chemical=Weapon [weaponIndex=19, damage=2, weaponName="
        + "Chemical], Pesticide=Weapon [weaponIndex=20, damage=3, weaponName=Pesticide], "
        + "Secret Weapon=Weapon [weaponIndex=17, damage=4, weaponName=Secret Weapon], Sharp "
        + "Knife=Weapon [weaponIndex=1, damage=2, weaponName=Sharp Knife], Wolf Poison=Weapon "
        + "[weaponIndex=3, damage=5, weaponName=Wolf Poison], Syringe=Weapon [weaponIndex=7, "
        + "damage=3, weaponName=Syringe], Poisonous Insect=Weapon [weaponIndex=2, damage=2, "
        + "weaponName=Poisonous Insect], Bane Rain=Weapon [weaponIndex=10, damage=4, weaponName="
        + "Bane Rain], Vampire Blood=Weapon [weaponIndex=4, damage=3, weaponName=Vampire Blood], "
        + "Dagger=Weapon [weaponIndex=6, damage=3, weaponName=Dagger], Meat Knife=Weapon "
        + "[weaponIndex=2, damage=2, weaponName=Meat Knife], Bomb=Weapon [weaponIndex=14,"
        + " damage=4, weaponName=Bomb]}, players={}, maxMoves=15]";
    assertEquals(expected, actual);
  }

  @Test
  public void testGraph() throws IOException {
    world.drawWorld();
    Path path = Files.createTempFile("imageTrial", ".png");
    assertTrue(Files.exists(path));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgument() {
    Reader fileReader = null;
    RandomGenerator random = new SequentialRandomGenerator();
    world = world(fileReader, random, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldCoordinates() {
    String file = "30.0 23 Doctor Lucky's Mansion\n50 Doctor Lucky Cooper the Husky";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDamage() {
    String file = "33 22 Vampire Mansion\n-5 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpace() {
    String file = "30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " -5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n25 20 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWeapon() {
    String file = "30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n25 20 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 -4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
  }

  @Test
  public void testOneNeighbor() {
    String file = "30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n25 20 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick";
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
    world.createPlayer("Rakshika", "Garage", false);
    assertEquals("Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:Garage\n\nWeapons in space are:\nBroom Stick\n"
        + "Trowel\n\nNeighbors of space are:\nPiazza\n\nPlayers in the space are:\nRakshika"
        + "\n\nThe target Doctor Lucky is in Trophy Room\n", world.displaySpaceInfo());
  }

  @Test
  public void testtwoNeighbor() {
    world.createPlayer("Rakshika", "Dressing Room", false);
    assertEquals("Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:Dressing Room\n\nWeapons in space are:\n"
        + "Syringe\n\nNeighbors of space are:\nMeal Room\n"
        + "Party Hall\n\nPlayers in the space are:\nRakshika\n\n"
        + "The target Oswald is in Living Room\n", world.displaySpaceInfo());
  }

  @Test
  public void testMultipleNeighbor() {
    world.createPlayer("Rakshika", "War Room", false);
    assertEquals("Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:War Room\n\nWeapons in space are:\n"
        + "Dagger\n\nNeighbors of space are:\nRefreshment Room\n"
        + "Maids Room\nDead Room\n\nPlayers in the space are:\nRakshika\n"
        + "\nThe target Oswald is in Living Room\n", world.displaySpaceInfo());
  }

  @Test
  public void testDisplaySpaceInfoWithoutWeapons() {
    world.createPlayer("Rakshika", "Library", false);
    String expected = "Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:Library\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nBooze Room\nDrawing room\nParlor\n\n"
        + "Players in the space are:\nRakshika\n\nThe target Oswald is in Living Room\n";
    String actual = world.displaySpaceInfo();
    assertEquals(expected, actual);
  }

  @Test
  public void testDisplaySpaceInfoWithOneWeapons() {
    world.createPlayer("Soundary", "Living Room", false);
    String expected = "Soundary, this is the info about the space you are in:\n"
        + "\nThe name of the space is:Living Room\n\nWeapons in space are:\n"
        + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\n"
        + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\n"
        + "Maids Room\n\nPlayers in the space are:\nSoundary\n\n"
        + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n";
    String actual = world.displaySpaceInfo();
    assertEquals(expected, actual);
  }

  @Test
  public void testDisplaySpaceInfoWithMultipleWeapons() {
    world.createPlayer("Raju", "Meal Room", false);
    String expected = "Raju, this is the info about the space you are in:\n"
        + "\nThe name of the space is:Meal Room\n\nWeapons in space are:\n"
        + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\nBlood Bank\n"
        + "Dressing Room\nKill Room\n\nPlayers in the space are:\n"
        + "Raju\n\nThe target Oswald is in Living Room\n";
    String actual = world.displaySpaceInfo();
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisplaySpaceInfoWithoutPlayer() {
    world.displaySpaceInfo();
  }

  @Test
  public void testCreatePlayer() {
    String expected = "Player Soundarya created at Refreshment Room\n";
    String actual = world.createPlayer("Soundarya", "Refreshment Room", true);
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePlayerEmptyName() {
    world.createPlayer("", "Refreshment Room", true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePlayerInvalidIndex() {
    world.createPlayer("Rakshika", "Bathroom", false);
  }

  @Test
  public void testTrackPlayer() {
    world.createPlayer("Srikanta", "War Room", false);
    assertEquals("Srikanta, this is the info about the space you are in:\n\n"
        + "The name of the space is:War Room\n\nWeapons in space are:\n"
        + "Dagger\n\nNeighbors of space are:\nRefreshment Room\nMaids Room\n"
        + "Dead Room\n\nPlayers in the space are:\nSrikanta\n\n"
        + "The target Oswald is in Living Room\n", world.displaySpaceInfo());
    world.movePlayer(21);
    assertEquals("Srikanta, this is the info about the space you are in:\n\n"
        + "The name of the space is:Dead Room\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nWar Room\nMaids Room\n\nPlayers in the space are:\n"
        + "Srikanta\n\nThe target Oswald is in Master Bedroom\n", world.displaySpaceInfo());
    world.movePlayer(18);
    assertEquals(
        "Srikanta, this is the info about the space you are in:\n\n"
            + "The name of the space is:Maids Room\n\nWeapons in space are:\nHybrid\n"
            + "\nNeighbors of space are:\nLiving Room\nWar Room\nDead Room\n\n"
            + "Players in the space are:\nSrikanta\n\nThe target Oswald is in Meal Room\n",
        world.displaySpaceInfo());
  }

  @Test
  public void testValidMovePlayerNotToPetSpace() {
    world.createPlayer("Soundarya", "Living Room", false);
    String expected1 = "Player Soundarya moved to Blood Bank\n";
    String actual1 = world.movePlayer(4);
    String expected2 = "Soundarya, this is the info about the space you are in:\n\n"
        + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
        + "Vampire Blood\n\nNeighbors of space are:\nLiving Room\nMaids Room\n"
        + "\nPlayers in the space are:\nSoundarya\n\nThe target Oswald is in Master Bedroom\n";
    String actual2 = world.displaySpaceInfo();
    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerNotNeighbor() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.movePlayer(13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerNegativeIndex() {
    world.movePlayer(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerInvalidIndex() {
    world.movePlayer(29);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerWithoutPlayer() {
    world.movePlayer(0);
  }

  @Test
  public void testValidPickWeapon() {
    world.createPlayer("Rakshika", "Living Room", false);
    String expected1 = "Weapon Wolf Sword picked by Rakshika";
    String actual1 = world.pickWeapon("Wolf Sword");
    assertEquals(expected1, actual1);
    String expected2 = "Rakshika, you can have a maximum of 3 weapons\n"
        + "Weapons with you at the moment are:\nWolf Sword\n";
    String actual2 = world.displayPlayerInfo("Rakshika");
    assertEquals(expected2, actual2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickWeaponAfterMax() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.pickWeapon("Poisonous Insect");
    world.pickWeapon("Meat Knife");
    world.movePlayer(13);
    world.pickWeapon("Specific Weapon");
    world.pickWeapon("Wolf Siren");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickWeaponNotInSpace() {
    world.createPlayer("Rakshika", "Living Room", false);
    world.pickWeapon("Sharp Knife");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickWeaponInvalidName() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.pickWeapon("Sour Cream");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickWeaponWithoutPlayer() {
    world.pickWeapon("Sharp Knife");
  }

  @Test
  public void testDisplayPlayerInfo() {
    world.createPlayer("Rakshika", "Living Room", false);
    String expected = "Rakshika, you can have a maximum of 3 weapons\n"
        + "You have no weapons at the moment\n";
    String actual = world.displayPlayerInfo("Rakshika");
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDisplayPlayerInfo() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.displayPlayerInfo("Rakshika");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisplayEmptyPlayerInfo() {
    world.createPlayer("Rakshika", "Living Room", false);
    world.displayPlayerInfo("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisplayNullPlayerInfo() {
    world.displayPlayerInfo(null);
  }

  @Test
  public void testLookAroundWithoutPetInNeighboringSpace() {
    world.createPlayer("Rakshika", "Living Room", false);
    String expected = "\nInfo about Rakshika's space\n\n"
        + "The name of the space is:Living Room\n\nWeapons in space are:\n"
        + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\nRefreshment Room\n"
        + "Blood Bank\nWar Room\nLibrary\nMaids Room\n\n"
        + "Players in the space are:\nRakshika\n\n"
        + "Target's pet Jacob is in this space\n\nInfo about neighboring spaces:\n\n"
        + "The name of the space is:Refreshment Room\n\nWeapons in space are:\n"
        + "Wolf Poison\n\nNeighbors of space are:\nWar Room\nSports Club\n"
        + "\nThis space has no players\n\n\n"
        + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
        + "Vampire Blood\n\nNeighbors of space are:\nMeal Room\nMaids Room\n"
        + "\nThis space has no players\n\n\nThe name of the space is:War Room\n"
        + "\nWeapons in space are:\nDagger\n\nNeighbors of space are:\n"
        + "Refreshment Room\nMaids Room\nDead Room\n\n"
        + "This space has no players\n\n\nThe name of the space is:Library\n\n"
        + "There are no wepaons in this space\n\nNeighbors of space are:\n"
        + "Booze Room\nDrawing room\nParlor\n\nThis space has no players\n"
        + "\n\nThe name of the space is:Maids Room\n\nWeapons in space are:\n"
        + "Hybrid\n\nNeighbors of space are:\nBlood Bank\nWar Room\n"
        + "Dead Room\n\nThis space has no players\n\n";
    String actual = world.lookAround();
    assertEquals(expected, actual);
  }

  @Test
  public void testLookAroundWithPetInNeighboringSpace() {
    world.createPlayer("Soundarya", "Meal Room", false);
    String expected = "\nInfo about Soundarya's space\n\n"
        + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
        + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
        + "Blood Bank\nDressing Room\nKill Room\n\nPlayers in the space are:\n"
        + "Soundarya\n\n\nInfo about neighboring spaces:\n\n"
        + "The name of the space is:Dressing Room\n\nWeapons in space are:\n"
        + "Syringe\n\nNeighbors of space are:\nMeal Room\nParty Hall\n\n"
        + "This space has no players\n\n\nThe name of the space is:Kill Room\n"
        + "\nWeapons in space are:\nSpecific Weapon\nWolf Siren\n\n"
        + "Neighbors of space are:\nMeal Room\n\nThis space has no players\n\n";
    String actual = world.lookAround(); // with pet in neighbor
    assertEquals(expected, actual);
    world.movePet(22); // moved to take it away from neighboring space
    world.movePet(13);
    String expected1 = "\nInfo about Soundarya's space\n\n"
        + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
        + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
        + "Living Room\nBlood Bank\nDressing Room\n\n"
        + "Players in the space are:\nSoundarya\n\n\n"
        + "Info about neighboring spaces:\n\nThe name of the space is:Blood Bank\n\n"
        + "Weapons in space are:\nVampire Blood\n\nNeighbors of space are:\n"
        + "Living Room\nMeal Room\nMaids Room\n\nThis space has no players\n"
        + "\n\nThe name of the space is:Dressing Room\n\n"
        + "Weapons in space are:\nSyringe\n\nNeighbors of space are:\n"
        + "Meal Room\nParty Hall\n\nThis space has no players\n\n";
    String actual1 = world.lookAround(); // without pet in neighbor
    assertEquals(expected1, actual1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundWithoutPlayer() {
    world.lookAround();
  }

  @Test
  public void testLookAroundWithNoItemsAfterPicked() {
    world.createPlayer("Soundarya", "Living Room", false);
    assertEquals("\nInfo about Soundarya's space\n\n"
        + "The name of the space is:Living Room\n\nWeapons in space are:\n"
        + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\nRefreshment Room\n"
        + "Blood Bank\nWar Room\nLibrary\nMaids Room\n\n"
        + "Players in the space are:\nSoundarya\n\n"
        + "Target's pet Jacob is in this space\n\nInfo about neighboring spaces:\n\n"
        + "The name of the space is:Refreshment Room\n\nWeapons in space are:\n"
        + "Wolf Poison\n\nNeighbors of space are:\nWar Room\nSports Club\n"
        + "\nThis space has no players\n\n\n"
        + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
        + "Vampire Blood\n\nNeighbors of space are:\nMeal Room\nMaids Room\n"
        + "\nThis space has no players\n\n\nThe name of the space is:War Room\n"
        + "\nWeapons in space are:\nDagger\n\nNeighbors of space are:\n"
        + "Refreshment Room\nMaids Room\nDead Room\n\n"
        + "This space has no players\n\n\nThe name of the space is:Library\n\n"
        + "There are no wepaons in this space\n\nNeighbors of space are:\n"
        + "Booze Room\nDrawing room\nParlor\n\nThis space has no players\n"
        + "\n\nThe name of the space is:Maids Room\n\nWeapons in space are:\n"
        + "Hybrid\n\nNeighbors of space are:\nBlood Bank\nWar Room\n"
        + "Dead Room\n\nThis space has no players\n\n", world.lookAround());
    world.pickWeapon("Wolf Sword");
    assertEquals("\nInfo about Soundarya's space\n\n"
        + "The name of the space is:Living Room\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nMeal Room\nRefreshment Room\nWar Room\n"
        + "Library\nMaids Room\n\nPlayers in the space are:\nSoundarya\n\n"
        + "\nInfo about neighboring spaces:\n\n"
        + "The name of the space is:Refreshment Room\n\nWeapons in space are:\n"
        + "Wolf Poison\n\nNeighbors of space are:\nLiving Room\nWar Room\n"
        + "Sports Club\n\nThis space has no players\n\n\n"
        + "The name of the space is:War Room\n\nWeapons in space are:\nDagger\n"
        + "\nNeighbors of space are:\nLiving Room\nRefreshment Room\n"
        + "Maids Room\nDead Room\n\nThis space has no players\n\n\n"
        + "The name of the space is:Library\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nLiving Room\nBooze Room\nDrawing room\n"
        + "Parlor\n\nThis space has no players\n\n\n"
        + "The name of the space is:Maids Room\n\nWeapons in space are:\nHybrid\n"
        + "\nNeighbors of space are:\nLiving Room\nWar Room\nDead Room\n\n"
        + "This space has no players\n\n", world.lookAround());
  }

  @Test
  public void testLookAroundWithTarget() {
    world.createPlayer("Rakshika", "Living Room", false);
    assertEquals(
        "Rakshika, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\nRefreshment Room\n"
            + "Blood Bank\nWar Room\nLibrary\nMaids Room\n\n"
            + "Players in the space are:\nRakshika\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n",
        world.displaySpaceInfo());
    assertEquals("\nInfo about Rakshika's space\n\nThe name of the space is:Living Room\n"
        + "\nWeapons in space are:\nWolf Sword\n\nNeighbors of space are:\n"
        + "Meal Room\nRefreshment Room\nBlood Bank\nWar Room\nLibrary\n"
        + "Maids Room\n\nPlayers in the space are:\nRakshika\n\n"
        + "Target's pet Jacob is in this space\n\nInfo about neighboring spaces:\n"
        + "\nThe name of the space is:Refreshment Room\n\n"
        + "Weapons in space are:\nWolf Poison\n\nNeighbors of space are:\n"
        + "War Room\nSports Club\n\nThis space has no players\n\n\n"
        + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
        + "Vampire Blood\n\nNeighbors of space are:\nMeal Room\n"
        + "Maids Room\n\nThis space has no players\n\n\n"
        + "The name of the space is:War Room\n\nWeapons in space are:\nDagger\n"
        + "\nNeighbors of space are:\nRefreshment Room\nMaids Room\n"
        + "Dead Room\n\nThis space has no players\n\n\n"
        + "The name of the space is:Library\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nBooze Room\nDrawing room\nParlor\n"
        + "\nThis space has no players\n\n\n"
        + "The name of the space is:Maids Room\n\nWeapons in space are:\n"
        + "Hybrid\n\nNeighbors of space are:\nBlood Bank\nWar Room\n"
        + "Dead Room\n\nThis space has no players\n\n", world.lookAround());
  }

  @Test
  public void testLookAroundPlayersInSpace() {
    world.createPlayer("Rakshika", "Meal Room", false);
    world.createPlayer("Soundarya", "Meal Room", false);
    assertEquals("\nInfo about Rakshika's space\n\nThe name of the space is:Meal Room\n"
        + "\nWeapons in space are:\nPoisonous Insect\nMeat Knife\n\n"
        + "Neighbors of space are:\nBlood Bank\nDressing Room\nKill Room\n"
        + "\nPlayers in the space are:\nRakshika\nSoundarya\n\n\n"
        + "Info about neighboring spaces:\n\nThe name of the space is:Dressing Room\n"
        + "\nWeapons in space are:\nSyringe\n\nNeighbors of space are:\n"
        + "Meal Room\nParty Hall\n\nThis space has no players\n\n\n"
        + "The name of the space is:Kill Room\n\nWeapons in space are:\n"
        + "Specific Weapon\nWolf Siren\n\nNeighbors of space are:\n"
        + "Meal Room\n\nThis space has no players\n\n", world.lookAround());
  }

  @Test
  public void testLookAroundPlayersInNeighboringSpace() {
    world.createPlayer("Rakshika", "Sports Club", false);
    world.createPlayer("Soundarya", "Changing room", false);
    assertEquals(
        "\nInfo about Rakshika's space\n\n"
            + "The name of the space is:Sports Club\n\nWeapons in space are:\n"
            + "Electric Racket\n\nNeighbors of space are:\nRefreshment Room\n"
            + "Changing room\n\nPlayers in the space are:\nRakshika\n\n\n"
            + "Info about neighboring spaces:\n\nThe name of the space is:Changing room\n"
            + "\nWeapons in space are:\nInvisible Robe\n\nNeighbors of space are:\n"
            + "Sports Club\nGame Room\n\nPlayers in the space are:\nSoundarya\n\n",
        world.lookAround());
  }

  @Test
  public void testLookAroundWithNoItems() {
    world.createPlayer("Rakshika", "Library", false);
    assertEquals("\nInfo about Rakshika's space\n\n"
        + "The name of the space is:Library\n\nThere are no wepaons in this space\n"
        + "\nNeighbors of space are:\nBooze Room\nDrawing room\nParlor\n\n"
        + "Players in the space are:\nRakshika\n\n\n"
        + "Info about neighboring spaces:\n\nThe name of the space is:Drawing room\n"
        + "\nWeapons in space are:\nSharp Pencil\n\nNeighbors of space are:\n"
        + "Master Bedroom\nBooze Room\nLibrary\nParlor\n\n"
        + "This space has no players\n\n\nThe name of the space is:Parlor\n\n"
        + "Weapons in space are:\nChemical\n\nNeighbors of space are:\n"
        + "Master Bedroom\nWine Cellar\nLibrary\nArmory\nDrawing room\n"
        + "Store Room\nGame Room\n\nThis space has no players\n\n", world.lookAround());
  }

  @Test
  public void testLookAroundWithTargetInNeighbor() {
    world.createPlayer("Rakshika", "Meal Room", false);
    assertEquals("\nInfo about Rakshika's space\n\nThe name of the space is:Meal Room\n"
        + "\nWeapons in space are:\nPoisonous Insect\nMeat Knife\n\n"
        + "Neighbors of space are:\nBlood Bank\nDressing Room\nKill Room\n"
        + "\nPlayers in the space are:\nRakshika\n\n\n"
        + "Info about neighboring spaces:\n\nThe name of the space is:Dressing Room\n"
        + "\nWeapons in space are:\nSyringe\n\nNeighbors of space are:\n"
        + "Meal Room\nParty Hall\n\nThis space has no players\n\n\n"
        + "The name of the space is:Kill Room\n\nWeapons in space are:\n"
        + "Specific Weapon\nWolf Siren\n\nNeighbors of space are:\n"
        + "Meal Room\n\nThis space has no players\n\n", world.lookAround());
  }

  @Test
  public void testMovePet() {
    world.createPlayer("Soundarya", "Living Room", false);
    String actual = world.movePet(3);
    String expected = "Target's pet Jacob is moved to Refreshment Room\n";
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetNegativeIndex() {
    world.createPlayer("Rakshika", "Living Room", false);
    world.movePet(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetInvalidIndex() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.movePet(40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetWithoutPlayer() {
    world.movePet(4);
  }

  @Test
  public void testPetStartLocation() {
    world.createPlayer("player1", "Living Room", false);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\n"
            + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\nMaids Room\n"
            + "\nPlayers in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n",
        world.displaySpaceInfo());
  }

  @Test
  public void testPetDfs() {
    world.createPlayer("player1", "Living Room", false);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\n"
            + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\nMaids Room\n"
            + "\nPlayers in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n",
        world.displaySpaceInfo());
    world.movePlayer(2);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
            + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
            + "Living Room\nBlood Bank\nDressing Room\nKill Room\n\n"
            + "Players in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Master Bedroom\n",
        world.displaySpaceInfo());
    world.movePlayer(4);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
            + "Vampire Blood\n\nNeighbors of space are:\nLiving Room\nMeal Room\n"
            + "Maids Room\n\nPlayers in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Meal Room\n",
        world.displaySpaceInfo());
  }

  @Test
  public void testPetDfsRestart() {
    world.createPlayer("player1", "Living Room", false);
    world.createPlayer("player2", "Meal Room", false);
    world.createPlayer("player3", "Blood Bank", false);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\n"
            + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\nMaids Room\n"
            + "\nPlayers in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n",
        world.displaySpaceInfo());
    world.lookAround();
    assertEquals(
        "player2, this is the info about the space you are in:\n\n"
            + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
            + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
            + "Living Room\nBlood Bank\nDressing Room\nKill Room\n\n"
            + "Players in the space are:\nplayer2\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Master Bedroom\n",
        world.displaySpaceInfo());
    world.lookAround();
    assertEquals(
        "player3, this is the info about the space you are in:\n\n"
            + "The name of the space is:Blood Bank\n\nWeapons in space are:\n"
            + "Vampire Blood\n\nNeighbors of space are:\nLiving Room\nMeal Room\n"
            + "Maids Room\n\nPlayers in the space are:\nplayer3\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Meal Room\n",
        world.displaySpaceInfo());
    world.movePet(0);
    assertEquals(
        "player1, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\nRefreshment Room\n"
            + "Blood Bank\nWar Room\nLibrary\nMaids Room\n\nPlayers in the space are:\nplayer1\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Refreshment Room\n",
        world.displaySpaceInfo());
  }

  @Test
  public void testGetTurn() {
    assertEquals("", world.getTurn());
    world.createPlayer("Soundarya", "Living Room", false);
    world.createPlayer("Rakshika", "Meal Room", true);
    world.createPlayer("Raju", "War Room", false);
    assertEquals("Soundarya", world.getTurn());
    world.lookAround();
    assertEquals("Computer Player Rakshika", world.getTurn());
    world.movePet(5);
    assertEquals("Raju", world.getTurn());
  }

  @Test
  public void testSpaceToString() {
    assertEquals("Blood Bank", world.spaceToString()[4]);
    assertEquals("Store Room", world.spaceToString()[20]);
    assertEquals("Sports Club", world.spaceToString()[9]);
  }

  @Test
  public void testWeaponsToString() {
    world.createPlayer("Rakshika", "Meal Room", false);
    assertEquals("Poisonous Insect", world.weaponsInSpaceToString()[0]);
    assertEquals("Meat Knife", world.weaponsInSpaceToString()[1]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithoutPlayer() {
    world.attackTarget("Poisonous Insect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetNonExistantWeapon() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.attackTarget("Sour Cream");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetSeenByOthers() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.createPlayer("Rakshika", "Living Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(5);
    world.attackTarget("Poisonous Insect");
  }

  @Test
  public void testAttackTargetWithPetAndWeaponDeletion() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.createPlayer("Rakshika", "Living Room", false);
    assertEquals("Soundarya, this is the info about the space you are in:\n\n"
        + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
        + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
        + "Blood Bank\nDressing Room\nKill Room\n\nPlayers in the space are:\n"
        + "Soundarya\n\nThe target Oswald is in Living Room\n", world.displaySpaceInfo());
    world.pickWeapon("Poisonous Insect");
    assertEquals(
        "Rakshika, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\n"
            + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\nMaids Room\n"
            + "\nPlayers in the space are:\nRakshika\n\nThe target Oswald is in Master Bedroom\n",
        world.displaySpaceInfo());
    assertEquals(
        "Soundarya, you can have a maximum of 3 weapons\n"
            + "Weapons with you at the moment are:\nPoisonous Insect\n",
        world.displayPlayerInfo("Soundarya"));
    world.movePet(2);
    assertEquals("Target Oswald attacked! His health is now 3",
        world.attackTarget("Poisonous Insect"));
    assertEquals("Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:Living Room\n\nWeapons in space are:\n"
        + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\nRefreshment Room\nBlood Bank\n"
        + "Library\nMaids Room\n\nPlayers in the space are:\n"
        + "Rakshika\n\nThe target Oswald is in Refreshment Room\n", world.displaySpaceInfo());
    assertEquals(
        "Soundarya, you can have a maximum of 3 weapons\nYou have no weapons at the moment\n",
        world.displayPlayerInfo("Soundarya"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithPlayerAndPet() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.createPlayer("Rakshika", "Meal Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(2);
    world.attackTarget("Poisonous Insect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithPlayerAndPetInNeighbor() {
    world.createPlayer("Rakshika", "Meal Room", false);
    world.createPlayer("Srikanta", "Living Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(0);
    world.attackTarget("Poisonous Insect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithPlayerInSpace() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.createPlayer("Rakshika", "Meal Room", false);
    world.pickWeapon("Poisonous Insect");
    world.lookAround();
    world.attackTarget("Poisonous Insect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetNotInSpace() {
    world.createPlayer("Soundarya", "Meal Room", false);
    world.createPlayer("Rakshika", "Living Room", false);
    world.pickWeapon("Poisonous Insect");
    world.attackTarget("Poisonous Insect");
  }

  @Test
  public void testPokeInTheEye() {
    world.createPlayer("Rakshika", "Meal Room", false);
    world.createPlayer("Srikanta", "Living Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(2);
    assertEquals("Target Oswald attacked! His health is now 4",
        world.attackTarget("Poke in the eye"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPokeInTheEyeSeen() {
    world.createPlayer("Raju", "Meal Room", false);
    world.createPlayer("Srikanta", "Meal Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(2);
    world.attackTarget("Poke in the eye");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetInvalidWeapon() {
    world.createPlayer("Raju", "Meal Room", false);
    world.createPlayer("Srikanta", "Meal Room", false);
    world.pickWeapon("Poisonous Insect");
    world.movePet(2);
    world.attackTarget("Sharp Sword");
  }

  @Test
  public void testTargetDies() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.createPlayer("Rakshika", "Living Room", false);
    world.pickWeapon("Wolf Sword");
    world.movePet(9);
    world.movePlayer(4);
    world.movePet(4);
    String actualValue = world.attackTarget("Wolf Sword");
    assertEquals("Target Oswald died!", actualValue);
    assertEquals("Soundarya", world.getWinner());
  }

  @Test
  public void testTargetEscaped() {
    String file = "33 22 Vampire Mansion\n20 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    World world = null;
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 5;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
    world.createPlayer("Soundarya", "Living Room", false);
    world.createPlayer("Rakshika", "War Room", false);
    assertEquals("Target Oswald attacked! His health is now 19",
        world.attackTarget("Poke in the eye"));
    world.movePet(5);
    assertEquals(false, world.isGameOver());
    world.movePlayer(12);
    world.lookAround();
    world.lookAround();
    assertEquals(true, world.isGameOver());
    assertEquals("", world.getWinner());
  }

  @Test
  public void testWinWithPokeInEye() {
    String file = "33 22 Vampire Mansion\n1 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    World world2 = null;
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world2 = new WorldImpl(fileReader, random, maxMoves);
      world2.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
    world2.createPlayer("Soundarya", "Living Room", false);
    assertEquals("Target Oswald died!", world2.attackTarget("Poke in the eye"));
  }

  @Test
  public void testVisibilityOfRoomWithPet() {
    world.createPlayer("Rakshika", "Meal Room", false);
    assertEquals("Rakshika, this is the info about the space you are in:\n\n"
        + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
        + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
        + "Blood Bank\nDressing Room\nKill Room\n\nPlayers in the space are:\n"
        + "Rakshika\n\nThe target Oswald is in Living Room\n", world.displaySpaceInfo());
    world.movePet(2);
    assertEquals(
        "Rakshika, this is the info about the space you are in:\n\n"
            + "The name of the space is:Meal Room\n\nWeapons in space are:\n"
            + "Poisonous Insect\nMeat Knife\n\nNeighbors of space are:\n"
            + "Living Room\nBlood Bank\nDressing Room\nKill Room\n\n"
            + "Players in the space are:\nRakshika\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Master Bedroom\n",
        world.displaySpaceInfo());
  }

  @Test
  public void testAttackSuccessfulWithPetInSameSpaceAndPlayerInNeighbor() {
    world.createPlayer("Soundarya", "Living Room", false);
    world.createPlayer("Rakshika", "Meal Room", false);
    assertEquals(
        "Soundarya, this is the info about the space you are in:\n\n"
            + "The name of the space is:Living Room\n\nWeapons in space are:\n"
            + "Wolf Sword\n\nNeighbors of space are:\nMeal Room\n"
            + "Refreshment Room\nBlood Bank\nWar Room\nLibrary\nMaids Room\n"
            + "\nPlayers in the space are:\nSoundarya\n\n"
            + "Target's pet Jacob is in this space\nThe target Oswald is in Living Room\n",
        world.displaySpaceInfo());
    assertEquals("Target Oswald attacked! His health is now 4",
        world.attackTarget("Poke in the eye"));
  }

  /**
   * Creates an object of the specified class.
   * 
   * @param fileReader the file containing world specification.
   * @param random     the random number generator.
   * @return the world object created.
   */
  protected World world(Reader fileReader, RandomGenerator random, int maxMoves) {
    return new WorldImpl(fileReader, random, maxMoves);
  }

  @Test
  public void testgetSpaceNames() {
    assertEquals(
        "[Living Room, Master Bedroom, Meal Room, Refreshment Room, Blood Bank, "
            + "Wine Cellar, War Room, Dressing Room, Indoor Pool, Sports Club, Party Hall, "
            + "Booze Room, Library, Kill Room, Armory, Changing room, Drawing room, Balcony, "
            + "Maids Room, Parlor, Store Room, Dead Room, Trophy Room, Game Room]",
        world.getSpaceNames().toString());
  }

  @Test
  public void testComputerPlayerTurnAttack() {
    world.createPlayer("Soundarya", "Living Room", true);
    assertEquals("Target Oswald attacked! His health is now 4", world.computerTurn());
    assertEquals("Weapon Wolf Sword picked by Soundarya", world.computerTurn());
  }

  @Test
  public void testComputerPlayerUsesMaxDamageItemToAttack() {
    String file = "33 22 Vampire Mansion\n10 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    World world = null;
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world = new WorldImpl(fileReader, random, maxMoves);
      world.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
    world.createPlayer("Rakshika", "Living Room", true);
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    world.computerTurn();
    assertEquals("Target Oswald attacked! His health is now 4", world.computerTurn());
  }

  @Test
  public void testComputerPlayerWins() {
    String file = "33 22 Vampire Mansion\n1 Oswald\nJacob \n24\n"
        + "5 9 16 13 Living Room\n27 3 32 9 Master Bedroom\n0 5 10 8 Meal Room\n"
        + "11 14 11 22 Refreshment Room\n0 9 4 12 Blood Bank\n33 0 33 11 Wine Cellar\n"
        + "5 14 10 17 War Room\n11 2 16 5 Dressing Room\n27 18 30 22 Indoor Pool\n"
        + "12 15 16 21 Sports Club\n17 1 21 7 Party Hall\n22 0 22 8 Booze Room\n"
        + "17 9 22 13 Library\n0 0 6 4 Kill Room\n33 12 33 22 Armory\n"
        + "17 17 22 20 Changing room\n23 5 26 9 Drawing room\n27 0 32 2 Balcony\n"
        + "0 13 4 17 Maids Room\n23 10 32 13 Parlor\n31 14 32 18 Store Room\n"
        + "0 18 6 22 Dead Room\n31 19 32 22 Trophy Room\n23 14 30 17 Game Room\n24\n"
        + "0 5 Wolf Sword\n1 2 Sharp Knife\n2 2 Poisonous Insect\n3 5 Wolf Poison\n"
        + "2 2 Meat Knife\n4 3 Vampire Blood\n5 5 Wolf Bane\n6 3 Dagger\n"
        + "7 3 Syringe\n8 2 Poisoned Pool\n9 2 Electric Racket\n10 4 Bane Rain\n"
        + "11 2 Warewolf Traps\n13 4 Specific Weapon\n14 4 Bomb\n14 2 Revolver\n"
        + "15 3 Invisible Robe\n16 2 Sharp Pencil\n17 4 Secret Weapon\n18 5 Hybrid\n"
        + "19 2 Chemical\n20 3 Pesticide\n23 4 Sharp Sword\n13 2 Wolf Siren";
    World world2 = null;
    try (Reader fileReader = new StringReader(file)) {
      RandomGenerator random = new SequentialRandomGenerator();
      int maxMoves = 15;
      world2 = new WorldImpl(fileReader, random, maxMoves);
      world2.startGame(null);
    } catch (IOException e1) {
      throw new IllegalArgumentException("File not found");
    }
    world2.createPlayer("Soundarya", "Living Room", true);
    assertEquals("Target Oswald died!", world2.computerTurn());
  }
}