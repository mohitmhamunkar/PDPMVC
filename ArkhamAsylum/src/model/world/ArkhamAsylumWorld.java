package model.world;

import java.util.List;
import model.target.Target;

/**
 * This java file contains implementation of complete DrLuckyWorld.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface ArkhamAsylumWorld extends ReadOnlyInterface {

  /**
   * This function can be used to get the list of items in the room.
   *
   * @param spaceId denoting the unique space in the world
   * @return an arraylist of string stating the items
   */
  List<String> getItemsInTheSpace(int spaceId);

  /**
   * Get the neighbors of the specified room.
   *
   * @param spaceId is a unique room id
   * @return a list of string denoting the name of the room
   */
  List<String> getNeighbors(int spaceId);

  /**
   * Neighbors who share the walls can see on the other room.
   * This function is used to who can see from where.
   *
   * @param spaceId is unique integer to identify who is looked at
   * @return list of different players who can see the attack.
   */
  List<String> getNeighborsWhoCanSee(int spaceId);

  /**
   * There are only a finite number of spaces in the world.
   * This function is used to get the total number of sapces.
   *
   * @return an integer value stating the total number of spaces
   */
  int getTotalNumberOfSpaces();

  /**
   * This function is used to create the map of Dr Lucky game.
   */
  void createWorldMap();

  /**
   * This function returns the world name.
   *
   * @return String the name of the world.
   */
  String getWorldName();

  /**
   * Get the list of items in the world.
   *
   * @return list of string denoting the items in the world.
   */
  List<String> getListOfItems();

  /**
   * Get the target character of the world.
   * Each world Has a target character,
   * Our task is to kill the target character.
   *
   * @return Target object. Which contains the data of the target character.
   */
  Target getTargetCharacter();

  /**
   * Function returns the location of target.
   *
   * @return String which denotes the location of target.
   */
  String getTargetLocation();

  /**
   * This function is used to pick an item by player.
   * The item is being added to the player and removed
   * from the world.
   *
   * @param weapon that needs to be added to the player.
   * @return true value if the item is picked up successfully.
   */
  boolean pickAnItemByPlayer(String weapon);

  /**
   * This function is used to move the player.
   * The player is moves only to the neighboring room.
   *
   * @param roomName Accepts a unique room identifier.
   */
  void movePlayerToNeighboringRoom(String roomName);

  /**
   * This function is used to move the player to the specifed space.
   * Throws IllegalArgument Exception if the room name is not valid.
   * @param roomName Accepts a unique room identifier.
   * @param isCalledByPlayer is used to identify if called by a player or not
   */
  void movePetToSpecifiedSpace(String roomName, boolean isCalledByPlayer);

  /**
   * This function is used to get the information around the space.
   * If it is player's turn then the player can
   * look around the neighboring spaces.
   *
   * @return String that represents the neighbours and items in the room.
   */
  String lookAround();

  /**
   * This function is used to add player in the world.
   * A player has to have a valid name, room location
   * and maximum count.
   *
   * @param playerName       Each player is identified by their name.
   * @param roomLocationId   Each player has to be in room in the world.
   * @param maximumItemCount It denotes the maximum number of items a player can carry.
   * @param isComputerPlayer true if the current player is computer.
   */
  void addPlayer(String playerName,
                 String roomLocationId,
                 int maximumItemCount,
                 boolean isComputerPlayer);

  /**
   * Get the name of the current player.
   *
   * @param isMovePlayer is move player boolean
   * @param isPickUpWeapon pickUp weapon boolean
   * @return player name which identifies the player.
   */
  String getTurn(boolean isPickUpWeapon, boolean isMovePlayer);

  /**
   * Get the whole information of the world.
   * It returns the name of the world, number of players,
   * location of the target character and the items available in the world.
   *
   * @return the complete information of the world.
   */
  String getWorldInformation();

  /**
   * Gets the information of the current player.
   * The information includes location, neighbours and
   * the items available in the location.
   *
   * @return the complete information of the current player.
   */
  String displayInfoOfCurrentPlayer();

  /**
   * Gets the information of the current player.
   * The information includes location, neighbours and
   * the items available in the location.
   *
   * @param name which identifies the unique player
   * @return the complete information of the current player.
   */
  String displayInfoOfSpecifiedPlayer(String name);

  /**
   * Returns if the current player is a computer player or not.
   *
   * @return true if the current player is computer.
   */
  boolean isComputerPlayer();

  /**
   * Get information about a particular space.
   *
   * @param spaceName is the unique space name to get info.
   * @return String which
   */
  String getSpaceInformation(String spaceName);

  /**
   * This function is used to attack target character with a weapon.
   * @param itemName is the weapon using with which the attack will be done.
   * @return true if the attack was successful or else false.
   */
  boolean attackTarget(String itemName);

  /**
   * Check if the game is over or not.
   * A game can be over if the there are no turns left.
   * Or the Target character is killed.
   * @return true if either of the above condition is true
   */
  boolean isGameOver();

  /**
   * Get the health of the Target.
   * The health of the target is continuously decreasing based on the attacks.
   *
   * @return int which denotes the current health of the target.
   */
  int getTargetHealth();

  /**
   * Get winner of the current game.
   * This will return the name of the winner.
   * If the string is empty then the game is empty.
   *
   * @return the name of the player who won.
   */
  String getWinner();

  /**
   * This function determines if player A can see Player B.
   * Player A can only see player B if they are in the same room.
   * Or they are in the neighboring space.
   *
   * @param playerA Name of the first person.
   * @param playerB Name of the second person.
   * @return true if the players can see each other.
   */
  boolean isPlayerCanSeeEachOther(String playerA, String playerB);

  /**
   * This is a function that gets the list of items in the current space.
   *
   * @return List of items
   */
  List<String> getItemsInCurrentSpace();

  /**
   * This is a function that gets the list of items with the player in turn.
   *
   * @return List of items with the player
   */
  List<String> getItemsWithCurrentPlayer();

  /**
   * This method gets the (X,Y) coordinates of the mouse click on a image and
   * then processes it to give the space where it was clicked.
   *
   * @param x The x Axis coordinate
   * @param y The y Axis coordinate
   * @return Name of Space
   */
  String processMouseClick(int x, int y);

  /**
   * This method gets the space of the current player in turn.
   *
   * @return name of Space
   */
  String getCurrentPlayerLocation();
}
