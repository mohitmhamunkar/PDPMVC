package model.player;

import java.util.ArrayList;
import java.util.List;
import model.item.Item;

/**
 * Implementation of the Player class.
 * It implements Player interface and contains information of a particular player.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class PlayerImpl implements Player {

  private final String playerName;
  private final List<Item> listOfItems;
  private String roomLocationName;
  private final int maximumItemCount;
  private final boolean isComputerPlayer;
  private final String playerIconPath;

  /**
   * Constructor of the PlayerImpl class to initialize all the values.
   *
   * @param playerName       unique player name.
   * @param roomLocationName denotes where the player is in the world.
   * @param maximumItemCount maximumNumber of items player can pick up.
   * @param isComputerPlayer to check if the player is computer player or human.
   * @param playerIconPath the file path to a player's icon
   */
  public PlayerImpl(
          String playerName,
          String roomLocationName,
          int maximumItemCount,
          boolean isComputerPlayer,
          String playerIconPath
  ) {
    if (playerName == null
            || roomLocationName == null
            || playerIconPath == null
            || playerName.isEmpty()
            || roomLocationName.isEmpty()
            || maximumItemCount <= 0 || maximumItemCount == Integer.MAX_VALUE
            || playerIconPath.isEmpty()) {
      throw new IllegalArgumentException("Player parameters should be valid");
    }

    this.playerName = playerName;
    this.roomLocationName = roomLocationName;
    this.maximumItemCount = maximumItemCount;
    this.listOfItems = new ArrayList<>();
    this.isComputerPlayer = isComputerPlayer;
    this.playerIconPath = playerIconPath;
  }

  private boolean isValidItem(Item weapon) {
    return weapon.getDamageValue() > 0
            && weapon.getRoomId() >= 0
            && weapon.getRoomId() != Integer.MAX_VALUE
            && weapon.getDamageValue() != Integer.MAX_VALUE
            && !weapon.getItemName().isEmpty();
  }

  private Item isValidItemToBeUsed(String itemName) {

    for (Item weaponName : listOfItems) {
      if (weaponName.getItemName().trim().equalsIgnoreCase(itemName)) {
        return weaponName;
      }
    }
    return null;
  }

  @Override
  public String getRoomName() {
    return roomLocationName;
  }

  @Override
  public boolean pickAnItem(Item weapon) {

    if (maximumItemCount == listOfItems.size()) {
      return false;
    }

    if (weapon == null || !isValidItem(weapon)) {
      return false;
    }

    listOfItems.add(weapon);

    return true;
  }

  @Override
  public String getPlayerRoomLocation() {
    return this.roomLocationName;
  }

  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  @Override
  public List<String> getItemsWithPlayer() {
    List<String> itemListString = new ArrayList<>();

    for (Item item : listOfItems) {
      itemListString.add(item.getItemName());
    }

    return itemListString;
  }

  @Override
  public void moveCharacterToSpace(String roomLocationName) {
    this.roomLocationName = roomLocationName;
  }

  @Override
  public int getMaximumItemsWithPlayerCount() {
    return this.maximumItemCount;
  }

  @Override
  public boolean isComputerPlayer() {
    return isComputerPlayer;
  }

  @Override
  public void useAnItem(String itemName) {

    if (itemName == null) {
      throw new IllegalArgumentException("Item name should be valid");
    }

    Item selectItem = isValidItemToBeUsed(itemName);
    if (itemName.isEmpty() || selectItem == null) {
      throw new IllegalArgumentException("Item name is not valid");
    }

    listOfItems.remove(selectItem);
  }

  @Override
  public String getPlayerIconPath() {
    return playerIconPath;
  }

  @Override
  public String toString() {
    StringBuilder playerString = new StringBuilder();

    playerString.append("Name of the player :");
    playerString.append(getPlayerName());
    playerString.append("\n");
    playerString.append("Location id of the current player:");
    playerString.append(getPlayerRoomLocation());
    playerString.append("\n");
    playerString.append("Items with the player:");
    playerString.append(getItemsWithPlayer().toString());

    return playerString.toString();
  }
}
