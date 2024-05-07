package model.player;

import java.util.List;
import model.item.Item;

/**
 * This interface class contains the function used to get player information.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Player {

  /**
   * Every is associated in a particular room at any point of game.
   * This function is used to return the room id where
   * the player is currently in.
   *
   * @return an integer which denotes the roomId of the item
   */
  String getRoomName();

  /**
   * A player can pick an Item in the room.
   * This function allows the player to pick an item
   * which is available in the room. When a player
   * picks up an item from the room the item is removed
   * from the list.
   *
   * @param weapon which contains the weapon information,
   *               that needs to be picked up.
   * @return true or false based on if it is successful or not.
   */
  boolean pickAnItem(Item weapon);

  /**
   * Move the player to the neighboring room.
   * This move function is for player to move
   * the current character to the neighboring room.
   * Set the roomID to the appropriate room location.
   *
   * @return room Location name of the player
   */
  String getPlayerRoomLocation();

  /**
   * In the world each player is identified by its name.
   * The name is unique.
   * This function is used to get the player name.
   *
   * @return String which denoted the unique name of the player.
   */
  String getPlayerName();

  /**
   * This function returns the list of items available.
   * with the player. If the player does not have any
   * items then the list is empty.
   *
   * @return A list of Items, which contain the details of the weapon.
   */
  List<String> getItemsWithPlayer();

  /**
   * This function is used to move the player.
   * The player is moves only to the neighboring room.
   *
   * @param roomName Accepts a unique space object.
   */
  void moveCharacterToSpace(String roomName);

  /**
   * This function is used to get the maximum number of items.
   * Every player can carry only till the maximum number of items.
   *
   * @return int value that denoted the maximum number of items that a player can carry.
   */
  int getMaximumItemsWithPlayerCount();

  /**
   * This function is used to identify if the player is a computer player or not.
   * @return true if the current Player is a computer player or else false.
   */
  boolean isComputerPlayer();

  /**
   * This function is used to use an item by the player.
   * After the item has been used the item is removed from player.
   * @param itemName which uniquely identifies the item.
   */
  void useAnItem(String itemName);

  /**
   * This function is used to return the path of the player icon.
   * @return the path from where we can find the player icon.
   */
  String getPlayerIconPath();
}
