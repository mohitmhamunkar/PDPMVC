package model.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.item.Item;
import model.item.Weapon;
import model.pet.Pet;
import model.player.Player;
import model.player.PlayerImpl;
import model.spaces.Space;
import model.spaces.SpaceImpl;
import model.target.Target;
import model.target.TargetImpl;
import utils.Constants;
import utils.DepthFirstSearchTraversal;
import utils.Pair;
import utils.RandomClassInterface;

/**
 * This is the world implementation of DrLuckyWorld's game.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class ArkhamAsylumWorldImpl implements ArkhamAsylumWorld {

  private final int rowCount;
  private final int colCount;
  private final List<Space> arrayListSpace;
  private final List<Item> arrayListItems;
  private final List<Item> mainArrayListItems;
  private final List<Player> arrayListPlayer;
  private final String worldName;
  private final Target targetCharacter;
  private final Pet pet;
  private int currentPlayerIndex;
  private int maxTurn;
  private final RandomClassInterface randomClassGenerator;
  private List<String> petTraversalString;
  private int petCurrentLocationIndex;

  /**
   * The constructor initialises all the important details of the game.
   *
   * @param rowCount             number of rows in the map
   * @param colCount             number of cols in the map
   * @param worldName            name of the world
   * @param arrayListSpace       list of all the spaces in the world
   * @param weaponsArrayList     list of all the weapons in the world
   * @param targetCharacter      details of the target character
   * @param pet                  details of the pet class
   * @param maxTurn              which keeps a count of the maximum number of turn.
   * @param arrayListPlayer      keeps the track of the current arrayListOfPlayers
   * @param randomClassGenerator is a helper function to get the random function.
   */
  public ArkhamAsylumWorldImpl(
          int rowCount,
          int colCount,
          String worldName,
          List<Space> arrayListSpace,
          List<Item> weaponsArrayList,
          List<Player> arrayListPlayer,
          Target targetCharacter,
          Pet pet,
          int maxTurn,
          RandomClassInterface randomClassGenerator) {

    if (rowCount < 0
            || rowCount == Integer.MAX_VALUE
            || colCount < 0
            || colCount == Integer.MAX_VALUE
            || worldName == null
            || worldName.isBlank()
            || worldName.isEmpty()
            || !isValidSpace(arrayListSpace)
            || !isValidItem(weaponsArrayList)
            || !isValidTarget(targetCharacter)
            || !isValidPlayerList(arrayListPlayer)
            || !isValidPet(pet)
            || maxTurn <= 0
            || randomClassGenerator == null) {
      throw new IllegalArgumentException("Please validate your parameters");
    }

    this.rowCount = rowCount;
    this.colCount = colCount;
    this.arrayListItems = getArrayListItemCopy(weaponsArrayList);
    this.mainArrayListItems = getArrayListItemCopy(weaponsArrayList);
    this.arrayListSpace = getArrayListSpaceCopy(arrayListSpace);
    this.arrayListPlayer = getArraylistPlayerCopy(arrayListPlayer);
    this.worldName = worldName;
    this.targetCharacter = targetCharacter;
    this.pet = pet;
    this.currentPlayerIndex = 0;
    this.maxTurn = maxTurn;
    this.randomClassGenerator = randomClassGenerator;
    this.petCurrentLocationIndex = 0;

    if (isOverLappingSpaces()) {
      throw new IllegalArgumentException("Spaces Should not overlap each other");
    }

    petDfsTraversal(0);
  }

  private void petDfsTraversal(int startingIndex) {
    if (startingIndex < 0 || startingIndex >= arrayListSpace.size()) {
      throw new IllegalArgumentException();
    }
    DepthFirstSearchTraversal dfs = new DepthFirstSearchTraversal(getAdjacencyListFromNeighbors());
    List<Integer> petTraversal = dfs.findPath(startingIndex);

    petTraversalString = new ArrayList<>();

    for (Integer m : petTraversal) {
      petTraversalString.add(getRoomInformationFromRoomName(m));
    }
  }

  private boolean isValidPlayerList(List<Player> arrayListPlayer) {
    if (arrayListPlayer == null) {
      return false;
    }

    for (Player player : arrayListPlayer) {
      if (player.getPlayerName() == null
              || player.getPlayerName().isEmpty()
              || player.getRoomName() == null
              || player.getRoomName().isEmpty()
              || player.getMaximumItemsWithPlayerCount() < 0) {
        return false;
      }
    }
    return true;
  }

  private List<Player> getArraylistPlayerCopy(List<Player> arrayListPlayer) {
    List<Player> playerListNew = new ArrayList<>();

    for (Player player : arrayListPlayer) {
      playerListNew.add(
              new PlayerImpl(
                      player.getPlayerName(),
                      player.getRoomName(),
                      player.getMaximumItemsWithPlayerCount(),
                      player.isComputerPlayer(),
                      player.getPlayerIconPath()
              )
      );
    }

    return playerListNew;
  }

  private boolean isValidPlayerName(String playerName) {
    if (playerName.isEmpty()) {
      return false;
    }

    for (Player player : arrayListPlayer) {
      if (player.getPlayerName().trim().equalsIgnoreCase(playerName.trim())) {
        return true;
      }
    }

    return false;
  }

  private boolean isValidTarget(Target target) {
    return target != null
            && target.getTargetName() != null
            && !target.getTargetName().isEmpty()
            && target.getTargetHealth() != Integer.MAX_VALUE
            && target.getTargetHealth() >= 0
            && target.getTargetLocation() >= 0;
  }

  private boolean isValidPet(Pet pet) {

    return pet != null
            && pet.getPetLocation() == 0
            && pet.getPetName() != null
            && !pet.getPetName().isBlank();
  }

  private boolean isValidItem(List<Item> arrayListItems) {
    if (arrayListItems == null) {
      return false;
    }

    for (Item item : arrayListItems) {
      if (item == null
              || item.getItemName() == null
              || item.getItemName().isEmpty()
              || item.getDamageValue() < 0
              || item.getDamageValue() == Integer.MAX_VALUE
              || item.getRoomId() < 0
              || item.getRoomId() == Integer.MAX_VALUE) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidSpace(List<Space> arrayListSpace) {
    if (arrayListSpace == null) {
      return false;
    }

    for (Space space : arrayListSpace) {
      if (space == null
              || space.getSpaceId() < 0
              || space.getSpaceId() == Integer.MAX_VALUE
              || space.getSpaceName() == null
              || space.getSpaceName().isEmpty()
              || space.getSpaceLocation() == null
              || space.getSpaceLocation().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  private List<Space> getArrayListSpaceCopy(List<Space> spaceArrayList) {
    List<Space> spaceListNew = new ArrayList<>();

    for (Space space : spaceArrayList) {
      spaceListNew.add(new SpaceImpl(space.getSpaceId(),
              space.getSpaceName(),
              space.getSpaceLocation()));
    }

    return spaceListNew;
  }

  private List<Item> getArrayListItemCopy(List<Item> weaponsArrayList) {
    List<Item> itemListNew = new ArrayList<>();

    for (Item item : weaponsArrayList) {
      itemListNew.add(new Weapon(item.getRoomId(),
              item.getDamageValue(),
              item.getItemName()));
    }

    return itemListNew;
  }

  @Override
  public List<String> getItemsInCurrentSpace() {

    List<String> itemsNameInSpace = new ArrayList<>();

    for (Item weapons : arrayListItems) {
      if (getRoomInformationFromRoomName(weapons.getRoomId())
              .trim()
              .equalsIgnoreCase(
                      arrayListPlayer.get(currentPlayerIndex).getRoomName().trim()
              )
      ) {
        itemsNameInSpace.add(weapons.getItemName());
      }
    }
    return itemsNameInSpace;
  }

  @Override
  public List<String> getItemsWithCurrentPlayer() {
    List<String> itemsWithCurrentPlayer = new ArrayList<>();

    for (String item : arrayListPlayer.get(currentPlayerIndex).getItemsWithPlayer()) {
      itemsWithCurrentPlayer.add(item);
    }

    itemsWithCurrentPlayer.add("Poke");

    return itemsWithCurrentPlayer;
  }

  @Override
  public String processMouseClick(int x, int y) {
    // no-op
    int xcoordinate = x / 32;
    int ycoordinate = y / 32;

    return getSpaceNameFromCoordinates(xcoordinate + 1, ycoordinate + 1);
  }

  @Override
  public String getCurrentPlayerLocation() {
    return arrayListPlayer.get(currentPlayerIndex).getPlayerRoomLocation();
  }

  @Override
  public List<String> getItemsInTheSpace(int roomId) {

    if (roomId >= getTotalNumberOfSpaces() || roomId < 0) {
      throw new IllegalArgumentException("RoomId should not be more than the available room");
    }
    List<String> itemsNameInSpace = new ArrayList<>();

    for (Item weapons : arrayListItems) {
      if (weapons.getRoomId() == roomId) {
        itemsNameInSpace.add(weapons.getItemName());
      }
    }
    return itemsNameInSpace;
  }

  private String getSpaceNameFromCoordinates(int x, int y) {
    if (x < 0
            || x == Integer.MAX_VALUE
            || y < 0
            || y == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("coordinates are not valid");
    }

    for (Space space : arrayListSpace) {
      if (space.getSpaceLocation().get(0) <= y
              && space.getSpaceLocation().get(1) <= x
              && space.getSpaceLocation().get(2) >= y
              && space.getSpaceLocation().get(3) >= x) {
        return space.getSpaceName();
      }
    }

    return "";
  }

  private List<Space> getNeighborsSpace(int spaceId) {
    Pair<Integer, Integer> xaxisCoordinates = getXaxisCoordinates(spaceId);
    Pair<Integer, Integer> yaxisCoordinates = getYaxisCoordinates(spaceId);
    List<Space> neighboringSpaces = new ArrayList<>();

    for (Space space : arrayListSpace) {
      if (space.getSpaceId() != spaceId) {
        int x1 = space.getSpaceLocation().get(Constants.ONE);
        int x2 = space.getSpaceLocation().get(Constants.THREE);
        int y1 = space.getSpaceLocation().get(Constants.ZERO);
        int y2 = space.getSpaceLocation().get(Constants.TWO);

        if (checkAxisForNeighbors(xaxisCoordinates, yaxisCoordinates, x1, x2, y1, y2)) {
          neighboringSpaces.add(space);
        }
      }
    }

    return neighboringSpaces;
  }

  private List<Integer> getNeighborsSpaceId(int spaceId) {
    Pair<Integer, Integer> xaxisCoordinates = getXaxisCoordinates(spaceId);
    Pair<Integer, Integer> yaxisCoordinates = getYaxisCoordinates(spaceId);
    List<Integer> neighboringSpaces = new ArrayList<>();

    for (Space space : arrayListSpace) {
      if (space.getSpaceId() != spaceId) {
        int x1 = space.getSpaceLocation().get(Constants.ONE);
        int x2 = space.getSpaceLocation().get(Constants.THREE);
        int y1 = space.getSpaceLocation().get(Constants.ZERO);
        int y2 = space.getSpaceLocation().get(Constants.TWO);

        if (checkAxisForNeighbors(xaxisCoordinates, yaxisCoordinates, x1, x2, y1, y2)) {
          neighboringSpaces.add(space.getSpaceId());
        }
      }
    }

    return neighboringSpaces;
  }

  private List<Integer>[] getAdjacencyListFromNeighbors() {

    List<Integer>[] adjLinkedList = new LinkedList[arrayListSpace.size()];

    for (int i = 0; i < arrayListSpace.size(); i++) {
      adjLinkedList[i] = new LinkedList<Integer>();
    }

    for (Space space : arrayListSpace) {
      List<Integer> neighborsSpace = getNeighborsSpaceId(space.getSpaceId());
      adjLinkedList[space.getSpaceId()].addAll(neighborsSpace);
    }

    return adjLinkedList;
  }

  @Override
  public List<String> getNeighbors(int spaceId) {
    Pair<Integer, Integer> xaxisCoordinates = getXaxisCoordinates(spaceId);
    Pair<Integer, Integer> yaxisCoordinates = getYaxisCoordinates(spaceId);
    List<String> neighboringSpaces = new ArrayList<>();

    for (Space space : arrayListSpace) {
      if (space.getSpaceId() != spaceId) {
        int x1 = space.getSpaceLocation().get(Constants.ONE);
        int x2 = space.getSpaceLocation().get(Constants.THREE);
        int y1 = space.getSpaceLocation().get(Constants.ZERO);
        int y2 = space.getSpaceLocation().get(Constants.TWO);

        if (checkAxisForNeighbors(xaxisCoordinates, yaxisCoordinates, x1, x2, y1, y2)) {
          neighboringSpaces.add(space.getSpaceName());
        }
      }
    }

    return neighboringSpaces;
  }

  @Override
  public List<String> getNeighborsWhoCanSee(int spaceId) {
    return getNeighbors(spaceId);
  }

  @Override
  public int getTotalNumberOfSpaces() {
    return arrayListSpace.size();
  }

  @Override
  public void createWorldMap() {
    CreateWorldImage world = new CreateWorldImage(
            getArrayListSpaceCopy(arrayListSpace),
            getArraylistPlayerCopy(arrayListPlayer),
            targetCharacter.getTargetLocation(),
            colCount,
            rowCount,
            pet.getPetLocation(),
            getCurrentPlayerLocation()
    );

    world.createWorldMap();
  }

  /**
   * Helper function to check if the coordinates in the X axis is in range.
   *
   * @param xaxisCoordinates Contains the coordinates of X axis of my current space.
   * @param x1               contain x1 coordinate of testing space.
   * @param x2               contain x2 coordinate of testing space.
   * @return boolean if X coordinates of current and testing space is in the range.
   */
  private Boolean checkXaxisInRange(Pair<Integer, Integer> xaxisCoordinates,
                                    int x1,
                                    int x2) {
    return ((x1 >= xaxisCoordinates.getFirstElement()
            && x1 <= xaxisCoordinates.getSecondElement())
            || (x2 >= xaxisCoordinates.getFirstElement()
            && x2 <= xaxisCoordinates.getSecondElement()));
  }

  /**
   * Check if all the coordinates of the two spaces are in range to considered as neighbors.
   *
   * @param xaxisCoordinates x-axis coordinates of the current chosen space.
   * @param yaxisCoordinates y-axis coordinates of the current chosen space.
   * @param x1               x1-axis coordinates of the testing space.
   * @param x2               x2-axis coordinates of the testing space.
   * @param y1               y1-axis coordinates of the testing space.
   * @param y2               y2-axis coordinates of the testing space.
   * @return boolean : true if the two spaces are neighbors.
   */
  private Boolean checkAxisForNeighbors(Pair<Integer, Integer> xaxisCoordinates,
                                        Pair<Integer, Integer> yaxisCoordinates,
                                        int x1,
                                        int x2,
                                        int y1,
                                        int y2) {

    if ((Math.abs(xaxisCoordinates.getFirstElement() - x2) == 1)
            && checkYaxisForNeighbors(yaxisCoordinates, y1, y2)) {
      return true;
    } else if ((Math.abs(xaxisCoordinates.getSecondElement() - x1) == 1)
            && checkYaxisForNeighbors(yaxisCoordinates, y1, y2)) {
      return true;
    } else if ((Math.abs(yaxisCoordinates.getFirstElement() - y2) == 1)
            && checkXaxisInRange(xaxisCoordinates, x1, x2)) {
      return true;
    } else if ((Math.abs(yaxisCoordinates.getSecondElement() - y1) == 1)
            && checkXaxisInRange(xaxisCoordinates, x1, x2)) {
      return true;
    }
    return false;
  }

  /**
   * Helper function to check if the coordinates in the Y axis is in range.
   *
   * @param yaxisCoordinates Contains the coordinates of X axis of my current space.
   * @param y1               contain y1 coordinate of testing space.
   * @param y2               contain y2 coordinate of testing space.
   * @return boolean if Y coordinates of current and testing space is in the range.
   */
  private Boolean checkYaxisForNeighbors(Pair<Integer, Integer> yaxisCoordinates,
                                         int y1,
                                         int y2) {
    return ((y1 >= yaxisCoordinates.getFirstElement()
            && y1 <= yaxisCoordinates.getSecondElement())
            || (y2 >= yaxisCoordinates.getFirstElement()
            && y2 <= yaxisCoordinates.getSecondElement()));
  }

  /**
   * Helper function to create and store coordinates.
   *
   * @param spaceId of the unique space.
   * @return Pair data structure which contains the X axis values.
   */
  private Pair<Integer, Integer> getXaxisCoordinates(int spaceId) {
    int x1 = -1;
    int x2 = -1;
    for (Space space : arrayListSpace) {
      if (spaceId == space.getSpaceId()) {
        x1 = space.getSpaceLocation().get(Constants.ONE);
        x2 = space.getSpaceLocation().get(Constants.THREE);
      }
    }

    return new Pair<Integer, Integer>(x1, x2);
  }

  /**
   * Helper function to create and store coordinates.
   *
   * @param spaceId of the unique space.
   * @return Pair data structure which contains the Y axis values.
   */
  private Pair<Integer, Integer> getYaxisCoordinates(int spaceId) {
    int y1 = -1;
    int y2 = -1;
    for (Space space : arrayListSpace) {
      if (spaceId == space.getSpaceId()) {
        y1 = space.getSpaceLocation().get(Constants.ZERO);
        y2 = space.getSpaceLocation().get(Constants.TWO);
      }
    }

    return new Pair<Integer, Integer>(y1, y2);
  }

  /**
   * Function to check if there are any overlapping spaces exists.
   *
   * @return true if there is any space that overlaps with each other.
   */
  private boolean isOverLappingSpaces() {

    boolean flag = false;
    for (Space currentSpace : arrayListSpace) {
      for (Space nextSpace : arrayListSpace) {
        if (currentSpace.getSpaceId() != nextSpace.getSpaceId()) {
          flag = isOverLappingSpacesHelper(currentSpace, nextSpace);
          if (flag) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Helper function to check if the two spaces overlapping.
   * This function checks if the coordinates are overlapping.
   *
   * @param space1 Contain information of 1st space.
   * @param space2 Contain information of 2nd space.
   * @return true if the two spaces overlap each other.
   */
  private boolean isOverLappingSpacesHelper(Space space1, Space space2) {
    int space1Y1 = space1.getSpaceLocation().get(0);
    int space1X1 = space1.getSpaceLocation().get(1);
    int space1Y2 = space1.getSpaceLocation().get(2);
    int space1X2 = space1.getSpaceLocation().get(3);

    int space2Y1 = space2.getSpaceLocation().get(0);
    int space2X1 = space2.getSpaceLocation().get(1);
    int space2Y2 = space2.getSpaceLocation().get(2);
    int space2X2 = space2.getSpaceLocation().get(3);

    return (space1X1 < space2X2
            && space1X2 > space2X1
            && space1Y1 < space2Y2
            && space1Y2 > space2Y1);
  }

  @Override
  public String getWorldName() {
    return worldName;
  }

  @Override
  public List<String> getListOfSpacesInWorld() {
    List<String> listOfSpaces = new ArrayList<>();

    for (Space space : arrayListSpace) {
      listOfSpaces.add(space.getSpaceName());
    }
    return listOfSpaces;
  }

  @Override
  public List<String> getListOfItems() {
    List<String> listOfItems = new ArrayList<>();

    for (Item weapon : arrayListItems) {
      listOfItems.add(weapon.getItemName());
    }
    return listOfItems;
  }

  @Override
  public Target getTargetCharacter() {

    return new TargetImpl.TargetBuilder()
            .targetName(targetCharacter.getTargetName())
            .targetHealth(targetCharacter.getTargetHealth())
            .totalNumberOfSpaces(getTotalNumberOfSpaces())
            .build();
  }

  @Override
  public String toString() {
    StringBuilder drLuckyWorldInformation = new StringBuilder();

    drLuckyWorldInformation.append("Name of the world: ");
    drLuckyWorldInformation.append(getWorldName());
    drLuckyWorldInformation.append(Constants.FULL_STOP);
    drLuckyWorldInformation.append(Constants.SPACE);

    drLuckyWorldInformation.append("Total Number of spaces: ");
    drLuckyWorldInformation.append(getTotalNumberOfSpaces());
    drLuckyWorldInformation.append(Constants.FULL_STOP);
    drLuckyWorldInformation.append(Constants.SPACE);

    drLuckyWorldInformation.append("Total number of Items: ");
    drLuckyWorldInformation.append(arrayListItems.size());
    drLuckyWorldInformation.append(Constants.FULL_STOP);
    drLuckyWorldInformation.append(Constants.SPACE);

    drLuckyWorldInformation.append("Name of the target Character: ");
    drLuckyWorldInformation.append(targetCharacter.getTargetName());
    drLuckyWorldInformation.append(Constants.FULL_STOP);

    return drLuckyWorldInformation.toString();
  }

  @Override
  public String getTargetLocation() {
    int targetLocation = targetCharacter.getTargetLocation();
    return arrayListSpace.get(targetLocation).getSpaceName();
  }

  private Item getValidItemsRemaining(String weaponName) {
    Item weapon = null;
    boolean isValidFlag = true;

    if (weaponName == null) {
      throw new IllegalArgumentException("No weapon found");
    }

    for (Item item : arrayListItems) {
      if (item.getItemName().trim().equalsIgnoreCase(weaponName.trim())) {
        weapon = new Weapon(
                item.getRoomId(),
                item.getDamageValue(),
                item.getItemName()
        );
      }
    }

    return weapon;
  }

  private Item getValidItems(String weaponName) {
    Item weapon = null;
    boolean isValidFlag = true;

    if (weaponName == null) {
      throw new IllegalArgumentException("No weapon found");
    }

    for (Item item : mainArrayListItems) {
      if (item.getItemName().trim().equalsIgnoreCase(weaponName.trim())) {
        weapon = new Weapon(
                item.getRoomId(),
                item.getDamageValue(),
                item.getItemName()
        );
      }
    }

    return weapon;
  }

  @Override
  public boolean pickAnItemByPlayer(String weaponName) {

    Item weapon = null;
    //Check if valid item name:
    try {
      weapon = getValidItemsRemaining(weaponName);
    } catch (IllegalArgumentException io) {
      return false;
    }

    if (weapon == null) {
      return false;
    }

    for (Player player : arrayListPlayer) {
      if (arrayListPlayer
              .get(currentPlayerIndex)
              .getPlayerName()
              .equalsIgnoreCase(player.getPlayerName())
              && player.pickAnItem(weapon)) {
        arrayListItems.remove(weapon);
        nextTurn(true);
        return true;
      }
    }
    return false;
  }

  @Override
  public String getTurn(boolean isPickUpWeapon, boolean isMovePlayer) {
    StringBuilder turnStringBuilder = new StringBuilder();

    if (arrayListPlayer.size() == 0) {
      throw new IllegalArgumentException("Please add player.");
    }

    // Check if the player is a computer player or not:
    if (arrayListPlayer.get(currentPlayerIndex).isComputerPlayer()) {
      turnStringBuilder.append("Current Player Turn: \n");
      turnStringBuilder.append(arrayListPlayer.get(currentPlayerIndex).getPlayerName());
      computerControlledPlayerTurn(isPickUpWeapon, isMovePlayer);
    } else {
      turnStringBuilder.append("Current player turn: ");
      turnStringBuilder.append(arrayListPlayer.get(currentPlayerIndex).getPlayerName());
    }

    return turnStringBuilder.toString();
  }

  private String computerControlledPlayerTurn(boolean isPickUpWeapon, boolean isMovePlayer) {
    StringBuilder turnStringBuilder = new StringBuilder();

    if (arrayListPlayer.get(currentPlayerIndex).getRoomName()
            .trim()
            .equalsIgnoreCase(getRoomInformationFromRoomName(
                    targetCharacter.getTargetLocation()).trim()
            )
    ) {
      attackTargetByComputerPlayer(turnStringBuilder);
      return turnStringBuilder.toString();
    }

    if (isPickUpWeapon) {
      pickUpItemByComputerPlayer(turnStringBuilder);
      return turnStringBuilder.toString();
    } else if (isMovePlayer) {
      movePlayerByComputerPlayer(turnStringBuilder);
      return turnStringBuilder.toString();
    }

    int turn = getRandomIntegerWithinaRange(4);

    if (turn == 0) {
      turn++;
    }

    switch (turn) {
      case 1:
        turnStringBuilder.append(lookAround());
        break;
      case 2:
        pickUpItemByComputerPlayer(turnStringBuilder);
        break;
      case 3:
        // Move the computer to neighboring place:
        movePlayerByComputerPlayer(turnStringBuilder);
        break;
      default:
        break;
    }

    return turnStringBuilder.toString();
  }

  private void movePlayerByComputerPlayer(StringBuilder turnStringBuilder) {
    Space currentSpace = getRoomInformationFromRoomName(
            arrayListPlayer
                    .get(currentPlayerIndex)
                    .getRoomName());

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    List<Space> availableRoomsToMove = getNeighborsSpace(currentSpace.getSpaceId());
    int index = getRandomIntegerWithinaRange(availableRoomsToMove.size());
    if (index == availableRoomsToMove.size()) {
      index--;
    }
    Space movedPlayerToRoom = availableRoomsToMove
            .get(index);
    movePlayerToNeighboringRoom(movedPlayerToRoom.getSpaceName());
    turnStringBuilder
            .append(
                    String.format("Moved the player to the room %s",
                            movedPlayerToRoom.getSpaceName()));
  }

  private void pickUpItemByComputerPlayer(StringBuilder turnStringBuilder) {
    String pickedUpItemByComputer = pickaRandomItemByPlayer();

    boolean isItemPickedUp = pickAnItemByPlayer(pickedUpItemByComputer);

    if (isItemPickedUp) {
      turnStringBuilder
              .append(
                      String.format("Item picked up by the computer %s\n", pickedUpItemByComputer
                      ));
    } else {
      turnStringBuilder.append("Failed to pick up the item");
    }
  }

  private void attackTargetByComputerPlayer(StringBuilder turnStringBuilder) {
    // Check for items with the computer player.
    List<String> computerPlayerItems = arrayListPlayer.get(currentPlayerIndex).getItemsWithPlayer();

    String weaponToUse = "";

    // Get the maximum power items from the list of items:
    if (computerPlayerItems.size() > 1) {
      weaponToUse = getMaximumPowerItem(computerPlayerItems);
    } else if (computerPlayerItems.size() == 1) {
      weaponToUse = computerPlayerItems.get(0);
    }

    if (weaponToUse.isEmpty()) {
      weaponToUse = "POKE";
    }

    turnStringBuilder
            .append(
                    String.format("Computer player attacks the target using %s\n", weaponToUse));
    attackTarget(weaponToUse);
  }

  @Override
  public String lookAround() {
    StringBuilder neighboursInformation = new StringBuilder();

    String currentSpaceName = arrayListPlayer.get(currentPlayerIndex).getRoomName();
    neighboursInformation.append("Current player location: ");

    Space currentSpace = null;
    for (Space space : arrayListSpace) {
      if (space.getSpaceName().trim().equalsIgnoreCase(currentSpaceName.trim())) {
        currentSpace = space;
        neighboursInformation.append(space.getSpaceName());
        neighboursInformation.append("\n");
      }
    }

    if (currentSpace == null) {
      throw new IllegalArgumentException("Please enter valid space");
    }

    // Get Items in the room:
    appendItemsInTheRoom(neighboursInformation, currentSpace);

    List<String> neighborsStringList = getNeighbors(currentSpace.getSpaceId());

    // Players in the current room:
    neighboursInformation.append(String.format(
                    "Players in the current: %s\n",
                    getPlayersInTheRoom(currentSpace.getSpaceName()
                    )
            )
    );

    // Remove neighboring space which is occupied by the pet.
    neighborsStringList.removeIf(neighboringSpace ->
            getRoomInformationFromRoomName(
                    pet.getPetLocation()).trim().equalsIgnoreCase(neighboringSpace.trim()
            )
    );

    neighboursInformation.append("Neighbors of the space :");
    neighboursInformation.append(neighborsStringList);
    neighboursInformation.append("\n");

    for (String neighborSpaceName : neighborsStringList) {
      Space neighboringSpace = null;
      for (Space space : arrayListSpace) {
        if (space.getSpaceName().trim().equalsIgnoreCase(neighborSpaceName.trim())) {
          neighboringSpace = space;
        }
      }

      if (neighboringSpace == null) {
        throw new IllegalArgumentException("Please enter valid space");
      }
      neighboursInformation.append(
              String.format("Info of neighboring space %s: \n", neighborSpaceName)
      );
      appendItemsInTheRoom(neighboursInformation, neighboringSpace);
      neighboursInformation.append("Player in the room:");
      neighboursInformation.append(getPlayersInTheRoom(neighboringSpace.getSpaceName()));
      neighboursInformation.append("\n");
    }

    nextTurn(true);

    return neighboursInformation.toString();
  }

  private String getMaximumPowerItem(List<String> itemList) {

    int max = -1;
    String weaponName = "";
    for (String item : itemList) {
      for (Item weaponItem : mainArrayListItems) {
        if (item.trim().equalsIgnoreCase(weaponItem.getItemName().trim())) {
          if (weaponItem.getDamageValue() > max) {
            weaponName = weaponItem.getItemName();
            max = weaponItem.getDamageValue();
          }
        }
      }
    }

    return weaponName;
  }

  /**
   * Helper function to get the room id from room name.
   *
   * @param roomName which is unique in the world.
   * @return Space object after identifying it.
   */
  private Space getRoomInformationFromRoomName(String roomName) {
    for (Space space : arrayListSpace) {
      if (space.getSpaceName().trim().equalsIgnoreCase(roomName.trim())) {
        return space;
      }
    }
    return null;
  }

  /**
   * Helper function to get the room id from room name.
   *
   * @param roomId which is unique in the world.
   * @return Space name after identifying it.
   */
  private String getRoomInformationFromRoomName(int roomId) {
    if (roomId < 0 || roomId > getTotalNumberOfSpaces()) {
      throw new IllegalArgumentException("Room Id is not valid");
    }

    return arrayListSpace.get(roomId).getSpaceName();
  }

  /**
   * Helper function to check the available items in the room.
   *
   * @return list of Items in the room
   */
  private List<Item> getAvailableItemsInRoom() {
    List<Item> availableItemsInRoom = new ArrayList<>();

    Space currentSpace = getRoomInformationFromRoomName(arrayListPlayer
            .get(currentPlayerIndex)
            .getRoomName());

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    int currentSpaceId = currentSpace.getSpaceId();
    for (Item item : arrayListItems) {
      if (item.getRoomId() == currentSpaceId) {
        availableItemsInRoom.add(item);
      }
    }

    return availableItemsInRoom;
  }

  /**
   * Helper function to pick a random item by the player.
   *
   * @return String gives output of the computer player picked up the item.
   */
  private String pickaRandomItemByPlayer() {
    List<Item> availableItemsInRoom = getAvailableItemsInRoom();

    if (availableItemsInRoom.size() == 0) {
      return null;
    }

    int max = -1;
    String pickaRandomItem = "";
    for (Item item : availableItemsInRoom) {
      if (item.getDamageValue() > max) {
        pickaRandomItem = item.getItemName();
        max = item.getDamageValue();
      }
    }

    if (pickaRandomItem.isEmpty()) {
      pickaRandomItem =
              availableItemsInRoom
                      .get(getRandomIntegerWithinaRange(availableItemsInRoom.size()))
                      .getItemName();
    }

    return pickaRandomItem;
  }


  private int getRandomIntegerWithinaRange(int endingRange) {
    return randomClassGenerator.getRandomNumber(endingRange - 1);
  }

  @Override
  public String getWorldInformation() {
    // World name:

    StringBuilder worldInfo = new StringBuilder();

    worldInfo.append("Name of the world: Arkham Asylum\n");

    worldInfo.append("Number of rows:\n");
    worldInfo.append(rowCount);
    worldInfo.append("\nNumber of cols:\n");
    worldInfo.append(colCount);
    worldInfo.append("\nTarget Character name:\n");
    worldInfo.append(targetCharacter.getTargetName());
    worldInfo.append("\nTarget character location:\n");
    worldInfo.append(getLocationFromSpaceId(targetCharacter.getTargetLocation()));
    worldInfo.append("\n");
    worldInfo.append("List of items in the world:\n");
    worldInfo.append(getListOfItems());

    return worldInfo.toString();
  }

  @Override
  public String displayInfoOfCurrentPlayer() {
    StringBuilder playerInfo = new StringBuilder();

    Space currentSpace = getRoomInformationFromRoomName(arrayListPlayer
            .get(currentPlayerIndex)
            .getRoomName());

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    playerInfo.append("Current player name: \n");
    playerInfo.append(arrayListPlayer.get(currentPlayerIndex).getPlayerName());
    playerInfo.append("\nCurrent player location:\n");
    playerInfo.append(currentSpace.getSpaceName());
    playerInfo.append("\nCurrent items with the player:\n");
    playerInfo.append(
            arrayListPlayer.get(currentPlayerIndex).getItemsWithPlayer()
    );

    return playerInfo.toString();
  }

  private Player getPlayerByName(String name) {
    if (name.isEmpty()) {
      return null;
    } else {
      for (Player player : arrayListPlayer) {
        if (player.getPlayerName()
                .trim().equalsIgnoreCase(name.trim())) {
          return player;
        }
      }

    }
    return null;
  }

  @Override
  public String displayInfoOfSpecifiedPlayer(String name) {

    Player currentPlayer = getPlayerByName(name);

    if (currentPlayer == null) {
      throw new IllegalArgumentException("Player name is not valid");
    }

    StringBuilder playerInfo = new StringBuilder();

    Space currentSpace = getRoomInformationFromRoomName(arrayListPlayer
            .get(currentPlayerIndex)
            .getRoomName());

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    playerInfo.append("Current player name: \n");
    playerInfo.append(currentPlayer.getPlayerName());
    playerInfo.append("\nCurrent player location:\n");
    playerInfo.append(currentPlayer.getRoomName());
    playerInfo.append("\nCurrent items with the player:\n");
    playerInfo.append(currentPlayer.getItemsWithPlayer());

    return playerInfo.toString();
  }

  @Override
  public int getNumberOfHumanPlayer() {
    return arrayListPlayer.size();
  }

  @Override
  public boolean isComputerPlayer() {
    if (currentPlayerIndex >= arrayListPlayer.size()) {
      return false;
    } else {
      return arrayListPlayer.get(currentPlayerIndex).isComputerPlayer();
    }
  }

  @Override
  public boolean isGameOver() {
    return maxTurn <= 0 || targetCharacter.getTargetHealth() <= 0;
  }

  @Override
  public int getTargetHealth() {
    return targetCharacter.getTargetHealth();
  }

  @Override
  public String getWinner() {
    if (targetCharacter.getTargetHealth() <= 0) {
      return arrayListPlayer.get(currentPlayerIndex).getPlayerName();
    } else {
      return "";
    }
  }

  @Override
  public boolean isPlayerCanSeeEachOther(String playerA, String playerB) {
    if (!isValidPlayerName(playerA) || !isValidPlayerName(playerB)) {
      throw new IllegalArgumentException("Player name is invalid");
    }

    Player player = null;

    for (Player individualPlayer : arrayListPlayer) {
      if (playerA.trim().equalsIgnoreCase(individualPlayer.getPlayerName().trim())) {
        player = individualPlayer;
      }
    }

    if (player == null) {
      throw new IllegalArgumentException("Player name is not valid");
    }

    Space currentSpace = getRoomInformationFromRoomName(
            player.getRoomName()
    );

    if (currentSpace == null) {
      throw new IllegalArgumentException("Current Player is in invalid space");
    }

    // Check if the attack is being watched in the current room;
    List<String> playerThatCanWatchTheAttack = new ArrayList<>(
            getPlayersInTheRoom(currentSpace.getSpaceName()
            )
    );

    // Check if there are any players in the neighbours
    List<String> listOfNeighbors = getNeighbors(currentSpace.getSpaceId());

    // Check if there are any players in the neighbors:
    for (String spaceName : listOfNeighbors) {
      playerThatCanWatchTheAttack.addAll(getPlayersInTheRoom(spaceName));
    }

    for (String eachPlayer : playerThatCanWatchTheAttack) {
      if (eachPlayer.trim().equalsIgnoreCase(playerB.trim())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getSpaceInformation(String spaceName) {
    Space currentSpace = getRoomInformationFromRoomName(spaceName);

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    List<String> items = getItemsInTheSpace(currentSpace.getSpaceId());
    StringBuilder string = new StringBuilder();

    string.append("Items in the room:\n");
    string.append(items.toString());
    string.append("\n");
    string.append("Neighbours:\n");
    string.append(getNeighbors(currentSpace.getSpaceId()));
    string.append("\n");

    List<String> playerList = getPlayersInTheRoom(spaceName);

    string.append("Players in the room:\n");
    string.append(playerList.toString());

    return string.toString();
  }

  private List<String> getPlayersInTheRoom(String spaceName) {
    List<String> playerList = new ArrayList<String>();

    for (Player player : arrayListPlayer) {
      if (player.getRoomName().trim().equalsIgnoreCase(spaceName.trim())) {
        playerList.add(player.getPlayerName());
      }
    }
    return playerList;
  }

  @Override
  public String getHint() {
    StringBuilder hintString = new StringBuilder();

    String petLocationName = getLocationFromSpaceId(pet.getPetLocation());

    hintString.append("<html> Target Name: ");
    hintString.append(targetCharacter.getTargetName());
    hintString.append("<br>");
    hintString.append("Target Health: ");
    hintString.append(targetCharacter.getTargetHealth());
    hintString.append("<br>");
    hintString.append("Pet Location: ");
    hintString.append(petLocationName);
    hintString.append("<br>");
    hintString.append("Current Room Items: ");
    hintString.append("<br>");
    appendItemsInTheRoom(hintString,
        getRoomInformationFromRoomName(
                arrayListPlayer
                        .get(currentPlayerIndex)
                        .getRoomName()
        )
    );
    hintString.append("<br><br><b>");

    return hintString.toString();
  }

  @Override
  public boolean attackTarget(String weaponName) {

    if (!getRoomInformationFromRoomName(
            targetCharacter.getTargetLocation())
            .trim()
            .equalsIgnoreCase(
                    arrayListPlayer
                            .get(currentPlayerIndex)
                            .getRoomName())) {
      return false;
    }

    //Check if the item is being valid or not.
    Item weapon = null;
    //Check if valid item name:
    try {
      weapon = getValidItems(weaponName);
    } catch (IllegalArgumentException io) {
      return false;
    }

    if (!weaponName.trim().equalsIgnoreCase("poke") && weapon == null) {
      throw new IllegalArgumentException("Please enter valid item name. ");
    }

    Space currentSpace = getRoomInformationFromRoomName(
            arrayListPlayer.get(currentPlayerIndex).getRoomName()
    );

    if (currentSpace == null) {
      throw new IllegalArgumentException("Current Player is in invalid space");
    }

    if (!weaponName.trim().equalsIgnoreCase("poke")) {
      arrayListPlayer.get(currentPlayerIndex).useAnItem(weapon.getItemName());
      // Remove the item from the arrayList of Items:
      arrayListItems.remove(weapon);
    }

    // Check if the attack is being watched in the current room;
    List<String> playerThatCanWatchTheAttack = new ArrayList<>(
            getPlayersInTheRoom(currentSpace.getSpaceName()
            )
    );

    // Check if there are any players in the neighbours
    List<String> listOfNeighbors = getNeighbors(currentSpace.getSpaceId());

    // Check if there are any players in the neighbors:
    for (String spaceName : listOfNeighbors) {
      playerThatCanWatchTheAttack.addAll(getPlayersInTheRoom(spaceName));
    }

    if (playerThatCanWatchTheAttack.size() > 1
            && pet.getPetLocation() != currentSpace.getSpaceId()) {
      return false;
    }

    if (weaponName.trim().equalsIgnoreCase("poke")) {
      targetCharacter.decreaseTargetHealth(5);
      nextTurn(true);
      return true;
    }

    targetCharacter.decreaseTargetHealth(weapon.getDamageValue());

    nextTurn(true);
    return true;
  }

  private String getLocationFromSpaceId(int spaceId) {
    for (Space space : arrayListSpace) {
      if (space.getSpaceId() == spaceId) {
        return space.getSpaceName();
      }
    }
    return Constants.EMPTY_STRING;
  }

  /**
   * Helper function to check if the neighbors are valid or not.
   *
   * @param roomName unique name which identifies the space.
   * @return true if the neighbours are valid.
   */
  private boolean isValidNeighbors(String roomName) {

    Space currentSpace = getRoomInformationFromRoomName(arrayListPlayer
            .get(currentPlayerIndex)
            .getRoomName());

    if (currentSpace == null) {
      throw new IllegalArgumentException("Invalid space location for computer");
    }

    List<String> neighborOfCurrentPlayerSpace =
            getNeighbors(currentSpace.getSpaceId());

    String name = "";
    for (Space space : arrayListSpace) {
      if (space.getSpaceName().trim().equalsIgnoreCase(roomName.trim())) {
        name = space.getSpaceName();
      }
    }

    return neighborOfCurrentPlayerSpace.contains(name);
  }

  @Override
  public void movePetToSpecifiedSpace(String roomName, boolean isCalledByPlayer) {
    if (!isValidRoomSpaceSpecified(roomName)) {
      throw new IllegalArgumentException("Please specify correct room location");
    }

    Space movingToSpace = getRoomInformationFromRoomName(roomName);

    if (movingToSpace == null) {
      throw new IllegalArgumentException("Room Location is not valid");
    }

    pet.movePet(movingToSpace.getSpaceId());

    if (isCalledByPlayer) {
      nextTurn(false);
      petDfsTraversal(movingToSpace.getSpaceId());
      petCurrentLocationIndex = 0;
    }

  }

  @Override
  public void movePlayerToNeighboringRoom(String roomName) {

    // Check if the roomId provided is neighboring room:
    if (!isValidNeighbors(roomName)) {
      throw new IllegalArgumentException("Invalid room id");
    }

    for (Space space : arrayListSpace) {
      if (space.getSpaceName().trim().equalsIgnoreCase(roomName.trim())) {
        arrayListPlayer.get(currentPlayerIndex).moveCharacterToSpace(roomName);
        nextTurn(true);
        return;
      }
    }
    throw new IllegalArgumentException("Invalid room id");
  }

  private void appendItemsInTheRoom(StringBuilder neighboursInformation, Space currentSpace) {
    List<String> arrayListOfItems = new ArrayList<>();

    for (Item item : arrayListItems) {
      if (item.getRoomId() == currentSpace.getSpaceId()) {
        arrayListOfItems.add(item.getItemName());
      }
    }

    if (arrayListOfItems.size() != 0) {
      neighboursInformation.append("Items in the space: ");
      neighboursInformation.append(arrayListOfItems);
      neighboursInformation.append("\n");
    } else {
      neighboursInformation.append("No items present in the room.\n");
    }
  }

  /**
   * Helper function to switch player to the next turn.
   */
  private void nextTurn(boolean isCallMovePet) {

    if (isGameOver()) {
      return;
    }

    maxTurn--;
    if (currentPlayerIndex + 1 >= arrayListPlayer.size()) {
      currentPlayerIndex = 0;
    } else {
      currentPlayerIndex++;
    }

    targetCharacter.moveCharacter();

    if (isCallMovePet) {
      petCurrentLocationIndex++;
      if (petCurrentLocationIndex >= arrayListSpace.size()) {
        petCurrentLocationIndex = 0;
      }
      movePetToSpecifiedSpace(petTraversalString.get(petCurrentLocationIndex), false);
    }
  }

  /**
   * Helper funciton to validate to space.
   *
   * @param roomName unique name which identifies the space.
   * @return true if the room is available in the world
   */
  private boolean isValidRoomSpaceSpecified(String roomName) {

    if (roomName == null || roomName.isEmpty()) {
      return false;
    }

    for (Space room : arrayListSpace) {
      if (room.getSpaceName().trim().equalsIgnoreCase(roomName.trim())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void addPlayer(String playerName,
                        String roomLocationName,
                        int maximumItemCount,
                        boolean isComputerPlayer) {
    if (playerName == null
            || playerName.isEmpty()
            || roomLocationName == null
            || !isValidRoomSpaceSpecified(roomLocationName)
            || maximumItemCount < 1
            || maximumItemCount == Integer.MAX_VALUE
    ) {
      throw new IllegalArgumentException("Please enter valid arguments for Player");
    }

    int currentTotalNumberOfPlayers = arrayListPlayer.size();

    String playerIconPath = String.format("icons/player%d.png", currentTotalNumberOfPlayers + 1);

    arrayListPlayer.add(
            new PlayerImpl(
                    playerName,
                    roomLocationName,
                    maximumItemCount,
                    isComputerPlayer,
                    playerIconPath
            )
    );
  }
}
