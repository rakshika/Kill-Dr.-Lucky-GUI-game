package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Space implementations defines all the operations that can be done in the
 * space.
 */
public class SpaceImpl implements Space {

  private int index;
  private int topLeftX;
  private int topLeftY;
  private int bottomRightX;
  private int bottomRightY;
  private String nameOfSpace;
  private List<Weapon> weaponsInSpace;
  private List<Space> neighbor;
  private List<Player> playersInSpace;

  /**
   * Constructor.
   * 
   * @param index        representing the index of the space
   * @param topLeftX     representing the topLeft x-coordinate
   * @param topLeftY     representing the topLeft y-coordinate
   * @param bottomRightX representing the bottomRight x-coordinate
   * @param bottomRightY representing the bottomRight y-coordinate
   * @param nameOfSpace  representing the name of space
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public SpaceImpl(int index, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY,
      String nameOfSpace) {
    if (topLeftX < 0 || topLeftY < 0 || bottomRightX < 0 || bottomRightY < 0) {
      throw new IllegalArgumentException("Co-ordinates cannot be negative\n");
    }
    if (index < 0) {
      throw new IllegalArgumentException("Index cannot be negative\n");
    }
    if (nameOfSpace == null || "".equals(nameOfSpace)) {
      throw new IllegalArgumentException("Name of space cannot be empty\n");
    }
    this.index = index;
    this.topLeftX = topLeftX;
    this.topLeftY = topLeftY;
    this.bottomRightX = bottomRightX;
    this.bottomRightY = bottomRightY;
    this.nameOfSpace = nameOfSpace;
    this.weaponsInSpace = new ArrayList<Weapon>();
    this.neighbor = new ArrayList<Space>();
    this.playersInSpace = new ArrayList<Player>();
  }

  @Override
  public int getTopLeftX() {
    return topLeftX;
  }

  @Override
  public int getTopLeftY() {
    return topLeftY;
  }

  @Override
  public int getBottomRightX() {
    return bottomRightX;
  }

  @Override
  public int getBottomRightY() {
    return bottomRightY;
  }

  @Override
  public String getName() {
    return nameOfSpace;
  }

  @Override
  public String getWeaponsInSpace() {
    StringBuilder weapons = new StringBuilder();
    for (int i = 0; i < this.weaponsInSpace.size(); i++) {
      weapons.append(String.format("%s\n", this.weaponsInSpace.get(i).getWeaponName()));
    }
    return weapons.toString();
  }

  @Override
  public String getNeighbor(int petLocation) {
    if (petLocation < 0) {
      throw new IllegalArgumentException("Pet location cannot be negative");
    }
    StringBuilder neighbors = new StringBuilder();
    for (int i = 0; i < this.neighbor.size(); i++) {
      if (this.neighbor.get(i).getIndex() != petLocation) {
        neighbors.append(String.format("%s\n", this.neighbor.get(i).getName()));
      }
    }
    return neighbors.toString();
  }

  @Override
  public String getPlayersInSpace() {
    StringBuilder players = new StringBuilder();
    for (int i = 0; i < this.playersInSpace.size(); i++) {
      players.append(String.format("%s\n", this.playersInSpace.get(i).getPlayerName()));
    }
    return players.toString();
  }

  @Override
  public void addWeaponsToSpace(Weapon weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("Weapon is null\n");
    }
    this.weaponsInSpace.add(weapon);
  }

  @Override
  public void removeWeaponFromSpace(Weapon weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("Weapon is null\n");
    }
    this.weaponsInSpace.remove(weapon);
  }

  @Override
  public void addNeighbor(Space space) {
    if (space == null) {
      throw new IllegalArgumentException("Space is null\n");
    }
    if (!this.neighbor.contains(space)) {
      this.neighbor.add(space);
    }
  }

  @Override
  public void addPlayerToSpace(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player is empty\n");
    }
    if (!this.playersInSpace.contains(player)) {
      this.playersInSpace.add(player);
    }
  }

  @Override
  public void removePlayerFromSpace(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player should not be null\n");
    }
    this.playersInSpace.remove(player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bottomRightX, bottomRightY, nameOfSpace, topLeftX, topLeftY);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SpaceImpl)) {
      return false;
    }
    SpaceImpl other = (SpaceImpl) obj;
    return bottomRightX == other.bottomRightX && bottomRightY == other.bottomRightY
        && Objects.equals(nameOfSpace, other.nameOfSpace) && topLeftX == other.topLeftX
        && topLeftY == other.topLeftY;
  }

  @Override
  public String toString() {
    return String
        .format("Space [topLeftX = %s, topLeftY = %s, bottomRightX = %s, bottomRightY = %s, "
            + "nameOfSpace = %s]", topLeftX, topLeftY, bottomRightX, bottomRightY, nameOfSpace);
  }

  @Override
  public int getIndex() {
    return this.index;
  }
}
