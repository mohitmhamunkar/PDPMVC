import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumConsoleController;
import controller.ArkhamAsylumController;
import java.io.Reader;
import java.io.StringReader;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * This test class tests the complete controller functionality.
 * Created different test function to test all the input and output mechanism.
 */
public class ControllerTest {

  @Test
  public void testAddPlayerController() {
    // Test if the controller is asking for command adding one player.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller =
            new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    StringBuilder expectedString = new StringBuilder();
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\n");
    expectedString.append("Press 3. to start game.\n");
    expectedString.append("Is this a computer player (Y/N):\n");
    expectedString.append("Please enter the name of the player: \n");
    expectedString.append("Please enter the room location: \n");
    expectedString.append("Specify the maximum number of the items: \n");
    expectedString.append("Player Added!\n");
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\nPress 3. to start game.\n");

    assertEquals(expectedString.toString(), out.toString());
    assertEquals("UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10");

    ArkhamAsylumWorld model = null;
    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);
  }

  @Test
  public void testCreateWorldMap() {
    // Test if the controller is asking for command after creating the map.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    StringBuilder expectedString = new StringBuilder();
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\n");
    expectedString.append("Press 3. to start game.\n");
    expectedString.append("World map created!\n");
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\n");
    expectedString.append("Press 3. to start game.\n");


    assertEquals(expectedString.toString(), out.toString());
    assertEquals("UniqueCode: 1234321 Create world map invoked!\n", log.toString());
  }

  @Test
  public void testStartGame() {
    // Test if the controller is asking for command after starting the game.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testMovePlayer() {
    // Test if the controller is asking for command after Move player.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n1\nKitchen\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the neighbouring space name: \n"
            + "Player successfully moved\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "Uniquecode: 1234321 Input: Kitchen\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testLookAround() {
    // Test if the controller is asking for command after looking around command.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n3\nKitchen\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the item to be picked: \n"
            + "Item picked by the player successfully.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "Uniquecode: 1234321 Input: Kitchen\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";


    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testPickItem() {
    // Test if the controller is asking for command after picking up item command.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n3\nGun\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the item to be picked: \n"
            + "Item picked by the player successfully.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "Uniquecode: 1234321 Input: Gun\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";


    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testDisplayWorldInformation() {
    // Test if the controller is asking for command after picking up item command.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n6\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "UniqueCode: 1234321 Display Info of world invoked!\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testDisplayPlayerInformation() {
    // Test if the controller is asking for command after Display player information command.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n7\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "UniqueCode: 1234321 Display Info Current player invoked!\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testInvalidAddPlayer() {
    // Test if the controller is accepting inputs after
    // providing an invalid arguments for add player.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nS\nKitchen\n-1\nn\n1\nN\nS\nKitchen\n1\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    StringBuilder expectedString = new StringBuilder();
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\n");
    expectedString.append("Press 3. to start game.\n");
    expectedString.append("Is this a computer player (Y/N):\n");
    expectedString.append("Please enter the name of the player: \n");
    expectedString.append("Please enter the room location: \n");
    expectedString.append("Specify the maximum number of the items: \n");
    expectedString.append("Unable to add a player. Please check parameters.\n");
    expectedString.append("Is this a computer player (Y/N):\n");
    expectedString.append("Please enter the name of the player: \n");
    expectedString.append("Please enter the room location: \n");
    expectedString.append("Specify the maximum number of the items: \n");
    expectedString.append("Please enter a valid format limit: S\n");
    expectedString.append("Please enter a valid format limit: Kitchen\n");
    expectedString.append("Player Added!\n");
    expectedString.append("Please enter from one of the following commands:\n");
    expectedString.append("Press 1. to add player.\n");
    expectedString.append("Press 2. to create world map.\n");
    expectedString.append("Press 3. to start game.\n");

    assertEquals(expectedString.toString(), out.toString());
    assertEquals("UniqueCode: 1234321 Input: 1 N 1 false\n", log.toString());
  }

  @Test
  public void testFailedToPickItem() {
    // Test if the controller is accepting inputs after the player fails to pick an item.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\nBROOM\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new InvalidMockModel(log, 1234321);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testStartGameWith0Player() {
    // Test if the controller is starting if there are 0 players.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("3\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new InvalidMockModel(log, 1234321);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    assertEquals(expectedString, out.toString());
  }

  @Test
  public void testMaxTurn() {
    // Test for 0 max turn
    // Test if the controller is starting if there are 0 players.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 0);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 0);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    assertEquals(expectedString, out.toString());

    // Test if game is getting over after 1 max turn.
    StringBuffer out2 = new StringBuffer();
    Reader in2 = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n2\nq\n");

    StringBuilder log2 = new StringBuilder();
    ArkhamAsylumWorld model2 = new MockModel(log2, 1234321, 1);

    ArkhamAsylumController controller2 = new ArkhamAsylumConsoleController(in2, out2, 1);
    controller2.playArkhamGame(model2);

    String expectedString2 = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "LookAround method invoked! 1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    assertEquals(expectedString2, out2.toString());

    // Test if game is getting over after 2 max turn.
    StringBuffer out3 = new StringBuffer();
    Reader in3 = new StringReader("1\nN\nS\nKitchen\n10\n1\nN\nB\nArmory\n10\n3\n2\n2\nq\n");

    StringBuilder log3 = new StringBuilder();
    ArkhamAsylumWorld model3 = new MockModel(log3, 1234321, 2);

    ArkhamAsylumController controller3 = new ArkhamAsylumConsoleController(in3, out3, 2);
    controller3.playArkhamGame(model3);

    String expectedString3 = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "LookAround method invoked! 1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "LookAround method invoked! 1234321\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    assertEquals(expectedString3, out3.toString());
  }

  @Test
  public void testMovePetLocationSuccess() {
    // Test if the controller is asking for command after moving the pet:
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n4\ncell\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the space name: \n"
            + "Pet successfully moved\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "Uniquecode: 1234321 Input: cell\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testMovePetLocationFailure() {
    // Test if the controller is asking for command after moving the pet
    // an invalid room:
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n4\n!@#$\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the space name: \n"
            + "Pet successfully moved\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "Uniquecode: 1234321 Input: !@#$\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testSuccessfulAttackTarget() {
    // Test if the controller is asking for command after attacking the
    // player using POKE.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n5\nPOKE\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 10);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the item to attack: \n"
            + "Attack was successful.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "UniqueCode: 1234321attack Target function invoked!\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }

  @Test
  public void testUnSuccessfulAttackTarget() {
    // Test if the controller is asking for command after attacking the
    // player using POKE.
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1\nN\nSubhankar\nKitchen\n10\n3\n5\nPOKE\nq\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new InvalidMockModel(log, 1234321);

    ArkhamAsylumController controller = new ArkhamAsylumConsoleController(in, out, 10);
    controller.playArkhamGame(model);

    String expectedString = "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "Is this a computer player (Y/N):\n"
            + "Please enter the name of the player: \n"
            + "Please enter the room location: \n"
            + "Specify the maximum number of the items: \n"
            + "Player Added!\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to add player.\n"
            + "Press 2. to create world map.\n"
            + "Press 3. to start game.\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n"
            + "Please enter the item to attack: \n"
            + "Move failed. Someone is watching you! -__-\n"
            + "------------- HINT ------------\n"
            + "1234321-------------------------------\n"
            + "Please enter from one of the following commands:\n"
            + "Press 1. to move player.[TURN]\n"
            + "Press 2. to look around. [TURN]\n"
            + "Press 3. to pick an item. [TURN]\n"
            + "Press 4. to move Pet to another location. [TURN]\n"
            + "Press 5. to ATTACK target. [TURN]\n"
            + "Press 6. to display world information.\n"
            + "Press 7. to display player information.\n"
            + "Press 8. to display specific space information\n"
            + "1234321\n";

    String logStringBuilder = "UniqueCode: 1234321 Input: Subhankar Kitchen 10 false\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n"
            + "UniqueCode: 1234321attack Target function invoked!\n"
            + "UniqueCode: 1234321Get information function invoked!\n"
            + "UniqueCode: 1234321isGameOver function invoked!\n"
            + "UniqueCode: 1234321 getTurn function invoked.\n";

    assertEquals(expectedString, out.toString());
    assertEquals(logStringBuilder, log.toString());
  }


}
