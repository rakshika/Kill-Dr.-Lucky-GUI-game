package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player implementation that defines all the operations that are possible on a
 * player.
 */
public class PlayerImpl implements Player {

  private final String playerName;
  private int currentIndex;
  private final int maxWeapons;
  private List<Weapon> weaponsPossessed;
  private final boolean isComputer;

  /**
   * Constructor.
   * 
   * @param playerName   represents the name of the player
   * @param currentIndex represents the current position of the player
   * @param isComputer   represents whether the player is human or computer
   * @throws IllegalArgumentException if player name is null or empty
   */
  public PlayerImpl(String playerName, int currentIndex, boolean isComputer) {
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Player name should not be null");
    }
    if (currentIndex < 0) {
      throw new IllegalArgumentException("Current Index cannot be negative"); 
    }
    this.playerName = playerName;
    this.currentIndex = currentIndex;
    this.maxWeapons = 3;
    weaponsPossessed = new ArrayList<Weapon>();
    this.isComputer = isComputer;
  }

  @Override
  public void movePlayer(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Invalid argument for space index");
    }
    this.currentIndex = index;
  }

  @Override
  public String getPlayerName() {
    return playerName;
  }

  @Override
  public int getCurrentIndex() {
    return currentIndex;
  }

  @Override
  public int getMaxWeapons() {
    return maxWeapons;
  }

  @Override
  public String getWeaponsPossessed() {
    StringBuilder weapons = new StringBuilder();
    for (int i = 0; i < this.weaponsPossessed.size(); i++) {
      weapons.append(String.format("\n%s", this.weaponsPossessed.get(i).getWeaponName()));
    }
    return weapons.toString();
  }

  @Override
  public boolean isComputer() {
    return isComputer;
  }

  @Override
  public void addWeapon(Weapon weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("Weapon is null");
    }
    this.weaponsPossessed.add(weapon);
  }

  @Override
  public String displayPlayerInfo() {
    StringBuilder playerInfo = new StringBuilder();
    playerInfo.append(String.format("%s, you can have a maximum of %s weapons\n", this.playerName,
        this.maxWeapons));
    if ("".equals(this.getWeaponsPossessed())) {
      playerInfo.append("You have no weapons at the moment\n");
    } else {
      playerInfo.append(
          String.format("Weapons with you at the moment are:%s\n", this.getWeaponsPossessed()));
    }
    return playerInfo.toString();
  }

  @Override
  public String toString() {
    return String.format(
        "Player [playerName = %s, currentIndex = %s, maxWeapons = %s, weaponsPossessed = %s]",
        playerName, currentIndex, maxWeapons, weaponsPossessed);
  }

  @Override
  public void removeWeapon(Weapon weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("Weapon name cannot be null");
    }
    this.weaponsPossessed.remove(weapon);
  }

  @Override
  public Weapon getWeaponForComputer() {
    Weapon weapon = null;
    int max = 0;

    for (Weapon each : this.weaponsPossessed) {
      if (each.getDamage() > max) {
        max = each.getDamage();
        weapon = each;
      }
    }
    return weapon;
  }

}
