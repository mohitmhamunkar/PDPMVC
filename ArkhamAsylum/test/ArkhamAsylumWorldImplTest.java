import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ArkhamAsylumGameWorldCreator;
import model.target.Target;
import model.target.TargetImpl;
import model.world.ArkhamAsylumWorld;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class of DrLuckyWorldImpl object class.
 * Test different methods of the complete world implementation.
 */
public class ArkhamAsylumWorldImplTest {

  private ArkhamAsylumWorld gameWorld;

  @Before
  public void setUp() {
    gameWorld = new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();
  }

  /**
   * Test for spaces which has overlapping boundaries.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ArkhamAsylumWorld gameDriverLocal =
            new ArkhamAsylumGameWorldCreator("res/mansionInvalid.txt", 10).getArkhamAsylumWorld();
    gameDriverLocal.toString();
  }

  /**
   * Test for items in different spaces.
   */
  @Test
  public void testGetItemsInTheSpace() {

    // Items in room id : 0, Name: Armory
    List<String> arrayListOfSpaces = new ArrayList<String>(
            List.of("Revolver"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(0));

    // Items in room id : 1, Name: Billiard Room
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Gun"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(1));

    // Items in room id : 2, Name: Carriage House
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Sword", "Hammer", "Piece of Rope", "Loud Noise", "Bazzuca"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(2));

    // Items in room id : 3, Name: Dining Hall.
    // Empty item
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Pistol", "Poison"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(3));

    // Items in room id : 2, Name: Carriage House
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Boomerang"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(4));

    // Items in room id : 5, no items found
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(5));

    // Items in room id : 6
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Trowel", "Electric Fence"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(6));

    // Items in room id : 7
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(7));

    // Items in room id : 8
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Bottle"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(8));

    // Items in room id : 9
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Card"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(9));

    // Items in room id : 10. No items
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(10));

    // Items in room id : 11.
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Tight Hat"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(11));

    // Items in room id : 12.
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Knife"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(12));

    // Items in room id : 13.
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Box"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(13));

    // Items in room id : 14. No items.
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(14));

    // Items in room id : 15
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Poster"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(15));

    // Items in room id : 16
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Bat Knife"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(16));

    // Items in room id : 17, Empty list.
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(17));

    // Items in room id : 18
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Pan", "Monkey Hand"));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(18));

    // Items in room id : 19
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(19));

    // Items in room id : 20. No items
    arrayListOfSpaces = new ArrayList<String>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(20));
  }

  /**
   * Test case to test the list of all spaces in the list.
   */
  @Test
  public void testGetAllSpaces() {
    List<String> listOfSpaces = new ArrayList<>(
            Arrays.asList(
                    "Armory", "Gaming Room", "Gym ", "Dining Hall", "Cell",
                    "Intense Treatment Room", "Medical", "Maze",
                    "Kitchen ", "Pool ", "Library ",
                    "Batman Cave ", "Jailer Room ", "Watch Tower ",
                    "Guard Room ", "Pantry", "Servants' Quarters ",
                    "Gordan Room ", "CCTV Room", "Playing Area ", "Garden "
            )

    );
    assertEquals(listOfSpaces, gameWorld.getListOfSpacesInWorld());
  }

  /**
   * Test if items in invalid rooms.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetItemsInTheSpace() {
    // Items in room id : 21. No items
    List<String> arrayListOfSpaces = new ArrayList<>(
            List.of());

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(23));

    assertEquals(arrayListOfSpaces, gameWorld.getItemsInTheSpace(-1));
  }

  /**
   * Test to get all neighbors from the specified space.
   * The neighbor is defined when two spaces share a wall.
   */
  @Test
  public void testGetNeighbors() {
    // Neighbors of room id : 0, Name: Armory
    List<String> arrayListOfSpaces = new ArrayList<String>(
            List.of("Gaming Room", "Dining Hall", "Cell"));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(0));

    // Items in room id : 1, Name: Gaming Room
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Dining Hall", "CCTV Room"));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(1));

    // Items in room id : 2, Name: Gym
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Garden "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(2));

    // Items in room id : 3, Name: Dinning Hall
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory",
                    "Gaming Room",
                    "Cell",
                    "Kitchen ",
                    "Guard Room ",
                    "Gordan Room ",
                    "CCTV Room",
                    "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(3));

    // Items in room id : 4, Name: Cell
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Intense Treatment Room", "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(4));

    // Items in room id : 5, Name: Intense Treatment Room
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Intense Treatment Room", "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(4));
  }

  @Test
  public void testGetItems() {
    List<String> listOfItems = new ArrayList<>(
            Arrays.asList(
                    "Gun", "Boomerang", "Knife", "Bottle",
                    "Revolver", "Poster", "Sword", "Bat Knife", "Pistol",
                    "Poison", "Trowel", "Hammer", "Electric Fence", "Pan",
                    "Box", "Monkey Hand", "Tight Hat",
                    "Piece of Rope", "Card", "Loud Noise", "Bazzuca"
            )
    );

    assertEquals(listOfItems, gameWorld.getListOfItems());
  }

  /**
   * Test for all neighbors who can see in the adjacent room.
   * The neighbor is defined when two spaces are sharing the wall.
   * All neighbors can see the room.
   */
  @Test
  public void testGetNeighborsWhoCanSee() {
    // Neighbors of room id : 0 who can see, Name: Armory
    List<String> arrayListOfSpaces = new ArrayList<String>(
            List.of("Gaming Room", "Dining Hall", "Cell"));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(0));

    // Neighbors in room id : 1 who can see, Name: Gaming Room
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Dining Hall", "CCTV Room"));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(1));

    // Neighbors in room id : 2 who can see, Name: Gym
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Garden "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(2));

    // Neighbors in room id : 3 who can see, Name: Dinning Hall
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory",
                    "Gaming Room",
                    "Cell",
                    "Kitchen ",
                    "Guard Room ",
                    "Gordan Room ",
                    "CCTV Room",
                    "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(3));

    // Neighbors in room id : 4 who can see, Name: Cell
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Intense Treatment Room", "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(4));

    // Neighbors in room id : 5 who can see, Name: Intense Treatment Room
    arrayListOfSpaces = new ArrayList<String>(
            List.of("Armory", "Intense Treatment Room", "Playing Area "));

    assertEquals(arrayListOfSpaces, gameWorld.getNeighbors(4));
  }

  /**
   * Test for total number of spaces in the world.
   */
  @Test
  public void getTotalNumberOfSpaces() {
    assertEquals(21, gameWorld.getTotalNumberOfSpaces());
    assertNotEquals(22, gameWorld.getTotalNumberOfSpaces());
    assertNotEquals(0, gameWorld.getTotalNumberOfSpaces());
  }

  /**
   * Test of toString to check the necessary format.
   */
  @Test
  public void testToString() {
    //Check for correct values.
    StringBuilder expectedStringValue = new StringBuilder();
    expectedStringValue.append("Name of the world: Arkham Asylum Killing the Joker. ");
    expectedStringValue.append("Total Number of spaces: 21. ");
    expectedStringValue.append("Total number of Items: 21. ");
    expectedStringValue.append("Name of the target Character: Joker.");
    assertEquals(expectedStringValue.toString(), gameWorld.toString());

    //Check for incorrect values.
    StringBuilder notExpectedStringValue = new StringBuilder();
    notExpectedStringValue.append(
            "Name of the world: Subhankar's world. Total Number of spaces: 21. "
    );
    notExpectedStringValue.append("Total number of Items: 20. ");
    notExpectedStringValue.append("Name of the target Character: Doctor Lucky.");
    assertNotEquals(notExpectedStringValue.toString(), gameWorld.toString());
  }

  @Test
  public void testTargetCharacter() {
    Target target = new TargetImpl(50, "Joker", 21);
    assertEquals(target.toString(), gameWorld.getTargetCharacter().toString());
  }

  @Test
  public void testTargetCharacterLocation() {
    gameWorld.getTargetCharacter().moveCharacter();

    String expectedLocation = "Armory";
    assertEquals(expectedLocation, gameWorld.getTargetLocation());

    gameWorld.getTargetCharacter().moveCharacter();

    expectedLocation = "Armory";
    assertEquals(expectedLocation, gameWorld.getTargetLocation());

  }

  @Test
  public void testComputerPickUpItemByPlayer() {
    // Test when the Computer player tries to pick an item if it is not available in the space.
    // Here Dining hall does not contain any item.
    gameWorld.addPlayer(
            "Computer",
            "Dining hall",
            10,
            true);

    boolean isItemPicked = gameWorld.pickAnItemByPlayer("Leg");
    assertFalse(isItemPicked);

    // Test when the name of the item provided is not valid.

    isItemPicked = gameWorld.pickAnItemByPlayer("!@#$");
    assertFalse(isItemPicked);

    // Test if the player is able to pick an item from the room if available.

    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Computer1",
            "Kitchen",
            10,
            true);

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertTrue(isItemPicked);

    // Test if the player is able to pick an item which is already being picked up.
    // Here the revolver is already being picked up.

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);

    // Test if the player tries to pick an item which is already being picked up by other player.
    gameWorldLocal.addPlayer(
            "Computer2",
            "Kitchen",
            10,
            true);

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);

    // Test if multiple player can pick up different items from the same room.

    gameWorldLocal = new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Computer1",
            "CCTV ROOM",
            10,
            true);

    gameWorldLocal.addPlayer(
            "Computer2",
            "CCTV ROOM",
            10,
            true);

    // Testing computer controlled player using Random class generator.
    isItemPicked = gameWorld.pickAnItemByPlayer("Pan");
    assertTrue(isItemPicked);

    // Testing computer controlled player using Random class generator
    isItemPicked = gameWorld.pickAnItemByPlayer("Monkey Hand");
    assertTrue(isItemPicked);

    // Test if the computer controlled player tries
    // to pick up an item which is not present in the current room.
    // Here Revolver is not present in the present room.
    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);
  }

  @Test
  public void testPickUpItemByPlayer() {
    // Test when the player tries to pick an item if it is not available in the space.
    // Here Dining hall does not contain any item.
    gameWorld.addPlayer(
            "Subhankar",
            "Dining hall",
            10,
            false);

    boolean isItemPicked = gameWorld.pickAnItemByPlayer("Leg");
    assertFalse(isItemPicked);

    // Test when the name of the item provided is not valid.

    isItemPicked = gameWorld.pickAnItemByPlayer("!@#$");
    assertFalse(isItemPicked);

    // Test if the player is able to pick an item from the room if available.

    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Kitchen",
            10,
            false);

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertTrue(isItemPicked);

    // Test if the player is able to pick an item which is already being picked up.
    // Here the revolver is already being picked up.

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);

    // Test if the player tries to pick an item which is already being picked up by other player.
    gameWorldLocal.addPlayer(
            "Player2",
            "Kitchen",
            10,
            false);

    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);

    // Test if multiple player can pick up different items from the same room.

    gameWorldLocal = new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    isItemPicked = gameWorld.pickAnItemByPlayer("Pan");
    assertTrue(isItemPicked);

    isItemPicked = gameWorld.pickAnItemByPlayer("Monkey Hand");
    assertTrue(isItemPicked);

    // Test if the player tries to pick up an item which is not present in the current room.
    // Here Revolver is not present in the present room.
    isItemPicked = gameWorld.pickAnItemByPlayer("Revolver");
    assertFalse(isItemPicked);
  }

  @Test
  public void testIfItemIsRemovedAfterPickUp() {
    // Test when the player tries to pick an item if it is not available in the space.
    // Here Dining hall does not contain any item.
    gameWorld.addPlayer(
            "Subhankar",
            "Kitchen",
            10,
            false);

    String expectedString = "Current player location: Kitchen \n"
            + "Items in the space: [Bottle]\n"
            + "Players in the current: [Subhankar]\n"
            + "Neighbors of the space :[Dining Hall, Guard Room , Playing Area ]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Guard Room : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    assertEquals(expectedString, gameWorld.lookAround());

    // Item gun got removed:
    String expectedString2 = "Current player location: Kitchen \n"
            + "Items in the space: [Bottle]\n"
            + "Players in the current: [Subhankar]\n"
            + "Neighbors of the space :[Guard Room , Playing Area ]\n"
            + "Info of neighboring space Guard Room : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    boolean isItemPicked = gameWorld.pickAnItemByPlayer("Gun");
    assertTrue(isItemPicked);

    assertEquals(expectedString2, gameWorld.lookAround());
  }

  @Test
  public void testGetTurn() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));

    // Test for Look Around next turn:
    // Test if it is next player's turn after playing look around.
    gameWorldLocal.lookAround();
    assertEquals("Current player turn: Player2", gameWorldLocal.getTurn(false, false));

    // Test turn after picking an item.
    // Test if it is next player's turn after picking an item.
    gameWorldLocal.pickAnItemByPlayer("Pan");
    assertEquals("Current player turn: Player3", gameWorldLocal.getTurn(false, false));

    // Test turn after moving the player to another space.
    gameWorldLocal.movePlayerToNeighboringRoom("library");
    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));

    // Test if it is 1st player's turn after all the players have played their turn:
    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));


    // Test turn after moving the player to another space.
    gameWorldLocal.movePlayerToNeighboringRoom("Kitchen");
  }

  @Test
  public void testMovePlayer() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));


    // Test turn after moving the player to another space.
    gameWorldLocal.movePlayerToNeighboringRoom("library");

    String expectedString = "Current player location: Library \n"
            + "No items present in the room.\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Jailer Room , Watch Tower , CCTV Room]\n"
            + "Info of neighboring space Jailer Room : \n"
            + "Items in the space: [Knife]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Watch Tower : \n"
            + "Items in the space: [Box]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(expectedString, gameWorldLocal.lookAround());
  }

  @Test
  public void testMovePlayerAndVerifyNeighbours() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));


    // Test turn after moving the player to another space.
    gameWorldLocal.movePlayerToNeighboringRoom("Cell");

    String expectedValue = "Current player location: Cell\n"
            + "Items in the space: [Boomerang]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Armory, Intense Treatment Room, Playing Area ]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Intense Treatment Room: \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";
    assertEquals(expectedValue, gameWorldLocal.lookAround());

    // Verify nrighbors:
    List<String> neighbours = gameWorld.getNeighbors(0);

    assertTrue(neighbours.contains("Cell"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTurnAfterInvalidMove() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));

    // Test turn after moving the player to another space.
    // Move to an room which is not a neighbour.
    gameWorldLocal.movePlayerToNeighboringRoom("garden");
    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));
  }

  @Test
  public void testTurnAfterInvalidPickUp() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));

    // Test turn after picking up an invalid item.
    boolean isItemPicked = gameWorldLocal.pickAnItemByPlayer("Snow");
    assertFalse(isItemPicked);
    // The turn should not change.
    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));

    // Test turn after picking up an item which is not in the space.
    isItemPicked = gameWorldLocal.pickAnItemByPlayer("Boots");
    assertFalse(isItemPicked);
    // The turn should not change.
    assertEquals("Current player turn: Player1", gameWorldLocal.getTurn(false, false));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetTurn() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();
    gameWorldLocal.getTurn(false, false);
  }

  @Test
  public void testLookAroundInSameRoom() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player location: CCTV Room\n");
    stringBuilder.append("Items in the space: [Pan, Monkey Hand]\n");
    stringBuilder.append("Players in the current: [Player1, Player2, Player3]\n");
    stringBuilder.append("Neighbors of the space :");
    stringBuilder.append("[Gaming Room, Dining Hall, Library , Gordan Room ]\n");
    stringBuilder.append("Info of neighboring space Gaming Room: \n");
    stringBuilder.append("Items in the space: [Gun]\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Dining Hall: \n");
    stringBuilder.append("Items in the space: [Pistol, Poison]\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Library : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Gordan Room : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");

    assertEquals(stringBuilder.toString(), gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundInDifferentSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "cell",
            10,
            false);

    String stringBuilder = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[Player3]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String stringBuilder2 = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Armory, Dining Hall, CCTV Room]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());

    String stringBuilder3 = "Current player location: Cell\n"
            + "Items in the space: [Boomerang]\n"
            + "Players in the current: [Player3]\n"
            + "Neighbors of the space :[Armory, Intense Treatment Room, Playing Area ]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Intense Treatment Room: \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder3, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithTargetCharacterSameSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "cell",
            10,
            false);

    String targetCharacterString = gameWorldLocal.getHint();

    String expectedString = "Current Location: Armory\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, targetCharacterString);

    String stringBuilder = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[Player3]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String stringBuilder2 = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Armory, Dining Hall, CCTV Room]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());

    String stringBuilder3 = "Current player location: Cell\n"
            + "Items in the space: [Boomerang]\n"
            + "Players in the current: [Player3]\n"
            + "Neighbors of the space :[Armory, Intense Treatment Room, Playing Area ]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Intense Treatment Room: \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder3, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithTargetCharacterNeighboringSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "cell",
            10,
            false);

    String stringBuilder = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[Player3]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String targetCharacterString = gameWorldLocal.getHint();

    String expectedString = "Current Location: gaming room\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, targetCharacterString);

    String stringBuilder2 = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Armory, Dining Hall, CCTV Room]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithPetInSameSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "cell",
            10,
            false);

    String targetCharacterString = gameWorldLocal.getHint();

    String expectedString = "Current Location: Armory\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, targetCharacterString);

    String stringBuilder = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[Player3]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String stringBuilder2 = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Armory, Dining Hall, CCTV Room]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithPetInNeighboringSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    // All the players are in neighbors.
    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "cell",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "gaming room",
            10,
            false);

    String targetCharacterString = gameWorldLocal.getHint();

    String expectedString = "Current Location: Armory\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, targetCharacterString);

    gameWorldLocal.lookAround();

    String targetCharacterString2 = gameWorldLocal.getHint();

    String expectedString2 = "Current Location: cell\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n"
            + "Items in the space: [Boomerang]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString2, targetCharacterString2);

    String stringBuilder2 = "Current player location: Cell\n"
            + "Items in the space: [Boomerang]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Armory, Intense Treatment Room, Playing Area ]\n"
            + "Info of neighboring space Armory: \n"
            + "Items in the space: [Revolver]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Intense Treatment Room: \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Playing Area : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithTargetCharacterNotInNeighboringSpace() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Medical",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "Maze",
            10,
            false);

    String stringBuilder = "Current player location: Medical\n"
            + "Items in the space: [Trowel, Electric Fence]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Maze]\n"
            + "Info of neighboring space Maze: \n"
            + "No items present in the room.\n"
            + "Player in the room:[Player2]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String targetCharacterString = gameWorldLocal.getHint();

    String expectedString = "Current Location: Maze\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n"
            + "No items present in the room.\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, targetCharacterString);

    String stringBuilder2 = "Current player location: Maze\n"
            + "No items present in the room.\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Medical, Pantry]\n"
            + "Info of neighboring space Medical: \n"
            + "Items in the space: [Trowel, Electric Fence]\n"
            + "Player in the room:[Player1]\n"
            + "Info of neighboring space Pantry: \n"
            + "Items in the space: [Poster]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundNoPlayersInNeighbors() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "garden",
            10,
            false);

    String stringBuilder = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());

    String stringBuilder2 = "Current player location: Gym \n"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Garden ]\n"
            + "Info of neighboring space Garden : \n"
            + "No items present in the room.\n"
            + "Player in the room:[Player3]\n";

    assertEquals(stringBuilder2, gameWorldLocal.lookAround());

    String stringBuilder3 = "Current player location: Garden \n"
            + "No items present in the room.\n"
            + "Players in the current: [Player3]\n"
            + "Neighbors of the space :[Gym , Pantry]\n"
            + "Info of neighboring space Gym : \n"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space Pantry: \n"
            + "Items in the space: [Poster]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder3, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundNoItems() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Gordan Room",
            10,
            false);

    String stringBuilder = "Current player location: Gordan Room \n"
            + "No items present in the room.\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :"
            + "[Dining Hall, Batman Cave , Jailer Room , Guard Room , CCTV Room]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Batman Cave : \n"
            + "Items in the space: [Tight Hat]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Jailer Room : \n"
            + "Items in the space: [Knife]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Guard Room : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundNoItemsInNeighbors() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Batman Cave",
            10,
            false);

    String stringBuilder = "Current player location: Batman Cave \n"
            + "Items in the space: [Tight Hat]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Pool , Jailer Room , Servants' Quarters , Gordan Room ]\n"
            + "Info of neighboring space Pool : \n"
            + "Items in the space: [Card]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Jailer Room : \n"
            + "Items in the space: [Knife]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Servants' Quarters : \n"
            + "Items in the space: [Bat Knife]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Gordan Room : \n"
            + "No items present in the room.\n"
            + "Player in the room:[]\n";

    assertEquals(stringBuilder, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithTarget() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    String expectedValue = "Current player location: Armory\n"
            + "Items in the space: [Revolver]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Gaming Room, Dining Hall, Cell]\n"
            + "Info of neighboring space Gaming Room: \n"
            + "Items in the space: [Gun]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Cell: \n"
            + "Items in the space: [Boomerang]\n"
            + "Player in the room:[]\n";

    assertEquals(expectedValue, gameWorldLocal.lookAround());
  }

  @Test
  public void testPlayerInfoWithNoItem() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Armory",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player name: \n");
    stringBuilder.append("Player1\n");
    stringBuilder.append("Current player location:\n");
    stringBuilder.append("Armory\n");
    stringBuilder.append("Current items with the player:\n");
    stringBuilder.append("[]");

    assertEquals(stringBuilder.toString(), gameWorldLocal.displayInfoOfSpecifiedPlayer("Player1"));
  }

  @Test
  public void testPlayerInfoWithItems() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "Cell",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Boomerang");

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player name: \n");
    stringBuilder.append("Player1\n");
    stringBuilder.append("Current player location:\n");
    stringBuilder.append("Cell\n");
    stringBuilder.append("Current items with the player:\n");
    stringBuilder.append("[Boomerang]");

    assertEquals(stringBuilder.toString(), gameWorldLocal.displayInfoOfSpecifiedPlayer("Player1"));
  }


  @Test
  public void testGetWorldName() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();
    assertEquals("Arkham Asylum Killing the Joker", gameWorldLocal.getWorldName());
  }

  @Test
  public void testGetWorldInformation() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();
    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());
  }

  @Test
  public void testMoveTargetCharacter() {
    // Test if the target character moves to next index after every turn.
    // Current location of the target:

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    // Turn of the player: Look Around
    gameWorldLocal.lookAround();

    // Check if the target character moved to the incremented index room:
    stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nGaming Room\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    // Target character moved to Gaming Room:
    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());

    // Turn of the player: Move Around
    gameWorldLocal.movePlayerToNeighboringRoom("Gaming Room");

    // Check if the target character moved to the incremented index room:
    stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nGym \n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    // Target character moved to Gym:
    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());

    // Turn of the player: Pick Up Item:
    gameWorldLocal.pickAnItemByPlayer("Pan");

    // Check if the target character moved to the incremented index room:
    stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nDining Hall\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    // Target character moved to Dining Hall:
    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfTheTargetCharacterMoveAfterInvalidMove() {
    // Check if target character does not move after making an  invalid move:

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    // Turn of the player: Move Around
    gameWorldLocal.movePlayerToNeighboringRoom("Pool");

    stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());
  }

  @Test
  public void testIfTheTargetCharacterMoveAfterInvalidPickUp() {
    // Check if target character does not move after making an invalid move:

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    // Turn of the player: Move Around
    gameWorldLocal.pickAnItemByPlayer("Bat");

    stringBuilder = new StringBuilder();
    stringBuilder.append("Name of the world: Arkham Asylum\n");
    stringBuilder.append("Number of rows:\n36\n");
    stringBuilder.append("Number of cols:\n30\n");
    stringBuilder.append("Target Character name:\nJoker\n");
    stringBuilder.append("Target character location:\nArmory\n");
    stringBuilder.append("List of items in the world:\n");
    stringBuilder.append("[Gun, Boomerang, Knife, Bottle, Revolver,");
    stringBuilder.append(" Poster, Sword, Bat Knife, Pistol, Poison,");
    stringBuilder.append(" Trowel, Hammer, Electric Fence, Pan, Box,");
    stringBuilder.append(" Monkey Hand, Tight Hat, Piece of Rope, Card, Loud Noise, Bazzuca]");

    assertEquals(stringBuilder.toString(), gameWorldLocal.getWorldInformation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInformationAboutSpecifiedPlaceInvalid() {
    gameWorld.getSpaceInformation("!@#");
  }

  @Test
  public void testInformationAboutSpecifiedPlace() {
    StringBuilder string = new StringBuilder();
    string.append("Items in the room:\n");
    string.append("[Bottle]\n");
    string.append("Neighbours:\n");
    string.append("[Dining Hall, Guard Room , Playing Area ]\n");
    string.append("Players in the room:\n");
    string.append("[Subhankar]");

    gameWorld.addPlayer("Subhankar", "Kitchen", 10, false);

    assertEquals(string.toString(), gameWorld.getSpaceInformation("Kitchen"));

    gameWorld.addPlayer("Yash", "Kitchen", 10, false);

    string = new StringBuilder();
    string.append("Items in the room:\n");
    string.append("[Bottle]\n");
    string.append("Neighbours:\n");
    string.append("[Dining Hall, Guard Room , Playing Area ]\n");
    string.append("Players in the room:\n");
    string.append("[Subhankar, Yash]");

    assertEquals(string.toString(), gameWorld.getSpaceInformation("Kitchen"));
  }

  /**
   * There is only 1 player in the world.
   */
  @Test
  public void testTargetHealthAfterSuccessfulAttackWithOnePlayer() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.attackTarget("gun");

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 47);
  }

  /**
   * There is only 2 player in the world.
   */
  @Test
  public void testTargetHealthAfterSuccessfulAttackWithTwoPlayer() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("sword");

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("sword");

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 46);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItemAfterUsingTheItem() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("sword");

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("sword");

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 46);

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("sword");
  }

  @Test
  public void testAttackIfAttackIfPlayerInSameRoom() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.lookAround();

    gameWorldLocal.pickAnItemByPlayer("poison");

    gameWorldLocal.lookAround();

    assertFalse(gameWorldLocal.attackTarget("poison"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    // Target health did not decrease.
    assertEquals(targetHealth, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackIfTargetNotInSpace() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "DINING HALL",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.pickAnItemByPlayer("poison");

    gameWorldLocal.lookAround();

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("poison");

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    // Target health did not decrease.
    assertEquals(targetHealth, 50);
  }

  @Test
  public void testAttackIfAttackIsSeen() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "DINING HALL",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.pickAnItemByPlayer("poison");

    gameWorldLocal.lookAround();

    assertFalse(gameWorldLocal.attackTarget("poison"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    // Target health did not decrease.
    assertEquals(targetHealth, 50);
  }

  @Test
  public void testAttackIfAttackWithPetAndPlayerInSameNeighboringSpace() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "DINING HALL",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.pickAnItemByPlayer("poison");

    gameWorldLocal.movePetToSpecifiedSpace("gaming room", true);

    assertFalse(gameWorldLocal.attackTarget("poison"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    // Target health did not decrease.
    assertEquals(targetHealth, 50);
  }

  @Test
  public void testIfItemIsUsedAfterAttackForOneItem() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.attackTarget("gun");

    // No items available with the player:
    String expectedString = "Current player name: \n"
            + "Player1\n"
            + "Current player location:\n"
            + "gaming room\n"
            + "Current items with the player:\n"
            + "[]";

    assertEquals(gameWorldLocal.displayInfoOfSpecifiedPlayer("player1"), expectedString);
  }

  @Test
  public void testAttackWithoutWeaponUsingPoke() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.lookAround();

    gameWorldLocal.lookAround();

    assertTrue(gameWorldLocal.attackTarget("poke"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 45);
  }

  @Test
  public void testIfItemIsUsedAfterAttackForTwoItems() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("gun");

    gameWorldLocal.attackTarget("gun");

    // No items available with the player:
    String expectedString = "Current player name: \n"
            + "Player1\n"
            + "Current player location:\n"
            + "gaming room\n"
            + "Current items with the player:\n"
            + "[]";

    assertEquals(gameWorldLocal.displayInfoOfSpecifiedPlayer("player1"), expectedString);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackWithInvalidWeapon() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    // Player 1 turn
    gameWorldLocal.lookAround();

    // Player 2 turn
    gameWorldLocal.lookAround();

    // Player 1 turn
    gameWorldLocal.attackTarget("RPG");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackWrongWeapon() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    // Player 1 turn:
    gameWorldLocal.pickAnItemByPlayer("sword");

    // Player 2 turn:
    gameWorldLocal.lookAround();

    // Player 1 turn:
    // Player 1 attacks with the item he does not have.
    gameWorldLocal.attackTarget("RPG");
  }

  @Test
  public void testValidAttack() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Sword");

    gameWorldLocal.lookAround();

    assertTrue(gameWorldLocal.attackTarget("Sword"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 46);
  }

  @Test
  public void testValidAttackNoPlayersAround() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Sword");

    String expectedString = "Current player location: Pool \n"
            + "Items in the space: [Card]\n"
            + "Players in the current: [Player2]\n"
            + "Neighbors of the space :[Batman Cave , Servants' Quarters ]\n"
            + "Info of neighboring space Batman Cave : \n"
            + "Items in the space: [Tight Hat]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space Servants' Quarters : \n"
            + "Items in the space: [Bat Knife]\n"
            + "Player in the room:[]\n";

    assertEquals(expectedString, gameWorldLocal.lookAround());

    expectedString = "Current Location: gym\n"
            + "Items with the player: [Sword]\n"
            + "Location of the Target: Gym \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Dining Hall\n"
            + "Items in the space: [Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, gameWorldLocal.getHint());

    assertTrue(gameWorldLocal.attackTarget("Sword"));

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 46);
  }

  @Test
  public void testKillTarget() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Bazzuca");

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("Bazzuca");

    int targetHealth = gameWorldLocal.getTargetCharacter().getTargetHealth();

    assertEquals(targetHealth, 0);
  }

  @Test
  public void testGameOverAfterTargetDead() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "pool",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Bazzuca");

    gameWorldLocal.lookAround();

    gameWorldLocal.attackTarget("Bazzuca");

    int targetHealth = gameWorldLocal.getTargetHealth();

    assertEquals(targetHealth, 0);
    assertTrue(!gameWorld.isGameOver());
  }

  @Test
  public void testIfTheStartingPositionOfPetAndTarget() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    String expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n" //Starting location of the target.
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n" // Starting location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  @Test
  public void testStartingPositionOfPetOfPetWith0() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    String expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n" //Starting location of the target.
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n" // Pet is starting with 0th index.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  @Test
  public void testMovePetByPlayer() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.movePetToSpecifiedSpace("pool", true);

    // Starting location of the target.
    // Starting location of the pet.
    String expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>Pet "
            + "Location: Pool <br>Current Room "
            + "Items: <br>Items in the space: "
            + "[Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToTheSameSpace() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.movePetToSpecifiedSpace("Armory", true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToInvalidRoom() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    // invalid room.
    gameWorldLocal.movePetToSpecifiedSpace("Rock", true);
  }

  @Test
  public void testInvisibleRoomWherePetIs() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    // Actual Neighbors of the gaming room:
    assertEquals("[Armory, Dining Hall, CCTV Room]", gameWorldLocal.getNeighbors(1).toString());

    // ExpectedLookAround output:
    String expectedLookAround = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Dining Hall, CCTV Room]\n" // Armory room is not visible.
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[Player2]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(expectedLookAround, gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithPetAndPlayerInNeighbors() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    gameWorldLocal.addPlayer(
            "Player1",
            "gaming room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "Armory",
            10,
            false);

    // Actual Neighbors of the gaming room:
    assertEquals("[Armory, Dining Hall, CCTV Room]", gameWorldLocal.getNeighbors(1).toString());

    // ExpectedLookAround output:
    String expectedLookAround = "Current player location: Gaming Room\n"
            + "Items in the space: [Gun]\n"
            + "Players in the current: [Player1]\n"
            + "Neighbors of the space :[Dining Hall, CCTV Room]\n" // Armory room is not visible.
            + "Info of neighboring space Dining Hall: \n"
            + "Items in the space: [Pistol, Poison]\n"
            + "Player in the room:[]\n"
            + "Info of neighboring space CCTV Room: \n"
            + "Items in the space: [Pan, Monkey Hand]\n"
            + "Player in the room:[]\n";

    assertEquals(expectedLookAround, gameWorldLocal.lookAround());
  }


  @Test
  public void testComputerPlayerWiningTheGameAgainstHuman() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Player1",
            "cell",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Boomerang");
    gameWorldLocal.pickAnItemByPlayer("Poison");
    // Move the pet the current room.
    gameWorldLocal.movePetToSpecifiedSpace("dining hall", true);
    gameWorldLocal.attackTarget("poison");

    // Target is dead even though player is in the neighbor.
    assertEquals(0, gameWorldLocal.getTargetHealth());
  }

  @Test
  public void testComputerPlayerWiningTheGame() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Player1",
            "cell",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Boomerang");
    gameWorldLocal.pickAnItemByPlayer("Poison");
    // Move the pet the current room.
    gameWorldLocal.movePetToSpecifiedSpace("dining hall", true);
    gameWorldLocal.attackTarget("poison");

    // Target is dead even though player is in the neighbor.
    assertEquals(0, gameWorldLocal.getTargetHealth());
  }

  @Test
  public void testSuccessfulAttackInRoomWithTargetAndPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Player1",
            "cell",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Boomerang");
    gameWorldLocal.pickAnItemByPlayer("Poison");
    // Move the pet the current room.
    gameWorldLocal.movePetToSpecifiedSpace("dining hall", true);
    assertTrue(gameWorldLocal.attackTarget("poison"));

    // Target is dead even though player is in the neighbor.
    assertEquals(0, gameWorldLocal.getTargetHealth());
  }

  @Test
  public void testAttackByComputerWithPoke() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // There is 1 computer player
    gameWorldLocal.addPlayer(
            "Computer",
            "Armory",
            10,
            true);

    String computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    String expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Computer player attacks the target using POKE\n";

    assertEquals(expectedString, expectedString);
  }

  @Test
  public void testAttackByComputerWithOneWeapon() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // There is 1 computer player
    gameWorldLocal.addPlayer(
            "Computer",
            "Gaming Room",
            10,
            true);

    String computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    String expectedString = "Current Player Turn: \n"
            + "Computer";

    assertEquals(expectedString, computerPlayerTurn);

    // Attack by computer
    computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    expectedString = "Current Player Turn: \n"
            + "Computer";

    assertEquals(expectedString, computerPlayerTurn);
  }

  @Test
  public void testAttackByComputerWithTwoWeaponHighestPower() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Computer",
            "Gym",
            10,
            true);

    String computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    String expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Item picked up by the computer Bazzuca\n";

    String hintString = "Current Location: Gym\n"
            + "Items with the player: [Bazzuca]\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, computerPlayerTurn);
    assertEquals(hintString, gameWorldLocal.getHint());

    // computer player picks another item:
    computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Item picked up by the computer Piece of Rope\n";

    assertEquals(expectedString, computerPlayerTurn);

    // Bazzuca danage value: 50 and sword damage value: 4
    // Computer player attacks with Bazzuca:
    // Target and computer player is in the same location:

    computerPlayerTurn = gameWorldLocal.getTurn(false, false);

    expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Computer player attacks the target using Bazzuca\n";

    assertEquals(expectedString, computerPlayerTurn);
  }

  @Test
  public void testTargetCharacterLocationByUser() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Computer",
            "Gym",
            10,
            true);

    String computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    String expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Item picked up by the computer Bazzuca\n";

    String hintString = "Current Location: Gym\n"
            + "Items with the player: [Bazzuca]\n"
            + "Location of the Target: Gaming Room\n" // Location of the target
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(expectedString, computerPlayerTurn);
    assertEquals(hintString, gameWorldLocal.getHint());

    // computer player picks another item:
    computerPlayerTurn = gameWorldLocal.getTurn(true, false);

    expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Item picked up by the computer Piece of Rope\n";

    assertEquals(expectedString, computerPlayerTurn);

    hintString = "Current Location: Gym\n"
            + "Items with the player: [Bazzuca, Piece of Rope]\n"
            + "Location of the Target: Gym \n" // Location of the target
            + "Health of the Target: 50\n"
            + "Location of the Pet: Dining Hall\n"
            + "Items in the space: [Sword, Hammer, Loud Noise]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    assertEquals(hintString, gameWorldLocal.getHint());

    // Bazzuca danage value: 50 and sword damage value: 4
    // Computer player attacks with Bazzuca:
    // Target and computer player is in the same location:

    computerPlayerTurn = gameWorldLocal.getTurn(false, false);

    expectedString = "Current player turn: \n"
            + "Computer\n"
            + "Computer player attacks the target using Bazzuca\n";

    assertEquals(expectedString, computerPlayerTurn);
  }

  @Test
  public void testLookAroundWithNoPlayerInSameRoom() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "GYM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "gaming room",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player location: CCTV Room\n");
    stringBuilder.append("Items in the space: [Pan, Monkey Hand]\n");
    stringBuilder.append("Players in the current: [Player1]\n");
    stringBuilder.append("Neighbors of the space :");
    stringBuilder.append("[Gaming Room, Dining Hall, Library , Gordan Room ]\n");
    stringBuilder.append("Info of neighboring space Gaming Room: \n");
    stringBuilder.append("Items in the space: [Gun]\n");
    stringBuilder.append("Player in the room:[Player3]\n");
    stringBuilder.append("Info of neighboring space Dining Hall: \n");
    stringBuilder.append("Items in the space: [Pistol, Poison]\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Library : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Gordan Room : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");

    assertEquals(stringBuilder.toString(), gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundWithPlayerInSameRoom() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "CCTV ROOM",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player location: CCTV Room\n");
    stringBuilder.append("Items in the space: [Pan, Monkey Hand]\n");
    stringBuilder.append("Players in the current: [Player1, Player2, Player3]\n");
    stringBuilder.append("Neighbors of the space :");
    stringBuilder.append("[Gaming Room, Dining Hall, Library , Gordan Room ]\n");
    stringBuilder.append("Info of neighboring space Gaming Room: \n");
    stringBuilder.append("Items in the space: [Gun]\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Dining Hall: \n");
    stringBuilder.append("Items in the space: [Pistol, Poison]\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Library : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Gordan Room : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");

    assertEquals(stringBuilder.toString(), gameWorldLocal.lookAround());
  }

  @Test
  public void testGameOverWithPlayerOneWining() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Bazzuca");
    gameWorldLocal.pickAnItemByPlayer("PISTOL");
    gameWorldLocal.attackTarget("Bazzuca");

    assertTrue(gameWorldLocal.isGameOver());
    assertEquals("Player1", gameWorldLocal.getWinner());
  }

  @Test
  public void testGameOverWithAtie() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 4).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // They 2 players are in neighboring room.
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "cell",
            10,
            false);

    gameWorldLocal.pickAnItemByPlayer("Bazzuca");
    gameWorldLocal.pickAnItemByPlayer("PISTOL");
    gameWorldLocal.lookAround();
    gameWorldLocal.lookAround();

    assertTrue(gameWorldLocal.isGameOver());
    assertEquals("", gameWorldLocal.getWinner());
  }

  @Test
  public void testGameOverWithComputerWining() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);

    // Computer player:
    gameWorldLocal.addPlayer(
            "ComputerPlayer",
            "gym",
            10,
            true);

    // Human player:
    gameWorldLocal.addPlayer(
            "Player2",
            "dining hall",
            10,
            false);

    gameWorldLocal.getTurn(true, false);
    gameWorldLocal.pickAnItemByPlayer("PISTOL");
    gameWorldLocal.getTurn(false, false);

    assertTrue(gameWorldLocal.isGameOver());
    assertEquals("ComputerPlayer", gameWorldLocal.getWinner());
  }

  @Test
  public void testLookAroundItemsInTheNeighboringRoom() {
    // Test if the game starts with 1st player in order:
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "Gaming Room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "Dining Hall",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player location: CCTV Room\n");
    stringBuilder.append("Items in the space: [Pan, Monkey Hand]\n");
    stringBuilder.append("Players in the current: [Player1]\n");
    stringBuilder.append("Neighbors of the space :");
    stringBuilder.append("[Gaming Room, Dining Hall, Library , Gordan Room ]\n");
    stringBuilder.append("Info of neighboring space Gaming Room: \n");
    stringBuilder.append("Items in the space: [Gun]\n");
    stringBuilder.append("Player in the room:[Player2]\n");
    stringBuilder.append("Info of neighboring space Dining Hall: \n");
    stringBuilder.append("Items in the space: [Pistol, Poison]\n");
    stringBuilder.append("Player in the room:[Player3]\n");
    stringBuilder.append("Info of neighboring space Library : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Gordan Room : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");

    assertEquals(stringBuilder.toString(), gameWorldLocal.lookAround());
  }

  @Test
  public void testLookAroundPlayersInTheNeighboringRoom() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "CCTV ROOM",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "Gaming Room",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player3",
            "Dining Hall",
            10,
            false);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Current player location: CCTV Room\n");
    stringBuilder.append("Items in the space: [Pan, Monkey Hand]\n");
    stringBuilder.append("Players in the current: [Player1]\n");
    stringBuilder.append("Neighbors of the space :");
    stringBuilder.append("[Gaming Room, Dining Hall, Library , Gordan Room ]\n");
    stringBuilder.append("Info of neighboring space Gaming Room: \n");
    stringBuilder.append("Items in the space: [Gun]\n");
    stringBuilder.append("Player in the room:[Player2]\n");
    stringBuilder.append("Info of neighboring space Dining Hall: \n");
    stringBuilder.append("Items in the space: [Pistol, Poison]\n");
    stringBuilder.append("Player in the room:[Player3]\n");
    stringBuilder.append("Info of neighboring space Library : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");
    stringBuilder.append("Info of neighboring space Gordan Room : \n");
    stringBuilder.append("No items present in the room.\n");
    stringBuilder.append("Player in the room:[]\n");

    assertEquals(stringBuilder.toString(), gameWorldLocal.lookAround());
  }

  @Test
  public void testPlayerCanSeeEachOtherInNeighborsWithoutPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    // The two players are in neighbors:
    assertTrue(gameWorldLocal.isPlayerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerCannotSeeEachOtherInNeighborsWithPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    gameWorldLocal.movePetToSpecifiedSpace("gaming room", false);

    // The two players are in neighbors:
    assertFalse(gameWorldLocal.isPlayerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerCanSeeEachOtherInSameRoomWithPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "armory",
            10,
            false);

    String expectedString = "<html> Target Name: Joker<br>Target "
            + "Health: 50<br>Pet Location: Armory<br>"
            + "Current Room Items: <br>Items in the space: [Revolver]\n"
            + "<br><br><b>";

    assertEquals(expectedString, gameWorldLocal.getHint());

    // The two players are in the same room:
    assertTrue(gameWorldLocal.isPlayerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerCanSeeEachOtherInSameRoomWithoutPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "armory",
            10,
            false);

    gameWorldLocal.movePetToSpecifiedSpace("gym", false);

    String expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>Pet Location: Gym <br>"
            + "Current Room Items: <br>Items in the space: [Revolver]\n"
            + "<br><br><b>";

    assertEquals(expectedString, gameWorldLocal.getHint());

    // The two players are in the same room:
    assertTrue(gameWorldLocal.isPlayerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerCannotSeeEach() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    gameWorldLocal.addPlayer(
            "Player1",
            "armory",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "garden",
            10,
            false);

    // The two players cannot see each other:
    assertFalse(gameWorldLocal.isPlayerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testCurrentPlayerLocationStartOfTurn() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 10).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    gameWorldLocal.addPlayer(
            "Player2",
            "gaming room",
            10,
            false);

    // Current Player location in the start of the turn:
    String expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>Pet Location: Armory<br>"
            + "Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  @Test
  public void testMovePetDepthFirstSearchTraversal() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 21).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    String expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>Pet Location: Armory<br>"
            + "Current Room Items: <br>Items in the space: "
            + "[Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After first turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>"
            + "Pet Location: Gaming Room<br>Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After Second turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>Pet Location: Dining Hall<br>"
            + "Current Room Items: <br>Items in the space: "
            + "[Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After third turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Cell<br>"
            + "Current Room Items: <br>"
            + "Items in the space: "
            + "[Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After forth turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "<html> Target Name: Joker<br>"
            + "Target Health: 50<br>"
            + "Pet Location: Intense Treatment Room<br>"
            + "Current Room Items: <br>Items in the space: "
            + "[Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 5th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Cell<br>Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 6th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Playing Area <br>Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 7th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Kitchen <br>Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 8th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Guard Room <br>"
            + "Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 9th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "<html> Target Name: Joker<br>Target Health: 50<br>"
            + "Pet Location: Servants' Quarters <br>Current Room Items: <br>"
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "<br><br><b>";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 10th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Library \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Pool \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 11th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Batman Cave \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Batman Cave \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 12th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Jailer Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Jailer Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 13th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Watch Tower \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 14th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Guard Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Watch Tower \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 15th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Pantry\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 16th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Servants' Quarters \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: CCTV Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 17th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gordan Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gordan Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 18th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: CCTV Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: CCTV Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 19th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Playing Area \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 20th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Garden \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Jailer Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  /**
   * This test function is to test the location of the pet.
   * After all the location in the world has been visited then the pet will come
   * back the 0th id and start again.
   */
  @Test
  public void testPetLocationAfterAllSpacesVisited() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 21).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    String expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After first turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After Second turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gym \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Dining Hall\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After third turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Dining Hall\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Cell\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After forth turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Cell\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Intense Treatment Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 5th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Intense Treatment Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Cell\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 6th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Medical\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Playing Area \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 7th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Maze\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Kitchen \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 8th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Kitchen \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Guard Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 9th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Pool \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Servants' Quarters \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 10th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Library \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Pool \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 11th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Batman Cave \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Batman Cave \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 12th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Jailer Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Jailer Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 13th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Watch Tower \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 14th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Guard Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Watch Tower \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 15th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Pantry\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 16th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Servants' Quarters \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: CCTV Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 17th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gordan Room \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gordan Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 18th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: CCTV Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: CCTV Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 19th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Playing Area \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Library \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After 20th turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    // Here the Pet is coming back to cell as there are no neighbors of the Intense Treatment Room.
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Garden \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Jailer Room \n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  @Test
  public void testPetLocationStartOverAfterMovingPet() {
    ArkhamAsylumWorld gameWorldLocal =
            new ArkhamAsylumGameWorldCreator("res/mansion.txt", 21).getArkhamAsylumWorld();

    assertEquals(gameWorldLocal.getTargetCharacter().getTargetHealth(), 50);
    gameWorldLocal.addPlayer(
            "Player1",
            "gym",
            10,
            false);

    String expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Armory\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After first turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gaming Room\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After Second turn:
    gameWorldLocal.movePetToSpecifiedSpace("Armory", true);

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Gym \n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Armory\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());

    // After third turn:
    gameWorldLocal.lookAround();

    // Next Location of the Pet:
    expectedString = "Current Location: gym\n"
            + "Items with the player: []\n"
            + "Location of the Target: Dining Hall\n"
            + "Health of the Target: 50\n"
            + "Location of the Pet: Gaming Room\n" // Location of the pet.
            + "Items in the space: [Sword, Hammer, Piece of Rope, Loud Noise, Bazzuca]\n"
            + "You can always attack target by using POKE command. Damage value of 5\n";

    // Position of the pet.
    assertEquals(expectedString, gameWorldLocal.getHint());
  }

  /**
   * This method tests all the mouse clicks and its results.
   */
  @Test
  public void testMouseClick() {
    //Mouse click outside world
    String space = gameWorld.processMouseClick(0, 0);
    assertEquals("", space);
    space = gameWorld.processMouseClick(900, 200);
    assertEquals("", space);
    //Mouse click inside world
    space = gameWorld.processMouseClick(200, 200);
    assertEquals("Servants' Quarters ", space);
    space = gameWorld.processMouseClick(500, 700);
    assertEquals("Cell", space);
    space = gameWorld.processMouseClick(600, 700);
    assertEquals("Armory", space);
  }
}