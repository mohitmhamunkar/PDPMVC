import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumFeatures;
import controller.Features;
import model.world.ArkhamAsylumWorld;
import org.junit.Before;
import org.junit.Test;
import view.Iview;

/**
 * This is a test class for testing the newly designed controller.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class ArkhamAsylumControllerTest {
  private StringBuilder log;
  private StringBuilder log2;
  private ArkhamAsylumWorld model;
  private ArkhamAsylumWorld inModel;
  private Iview view;
  private Iview inView;
  private Features controller;
  private Features inController;
  
  /**
   * This method setups the variables for testing.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    log2 = new StringBuilder();
    model = new MockModel(log, 271020, 10);
    inModel = new InvalidMockModel(log2, 271020);
    view = new MockView(log, 271020);
    inView = new InvalidMockView(log2, 271020);
    controller = new ArkhamAsylumFeatures(model, view);
    inController = new ArkhamAsylumFeatures(inModel, inView);
  }
  
  /**
   * This method tests invoke of intro page.
   */
  @Test
  public void testShowFirstPage() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of add players command.
   */
  @Test
  public void testAddPlayers() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.addPlayer("Dummy", false, "Dummy", 5);
    expected.append("UniqueCode: 271020 Input: Dummy Dummy 5 false\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020");
    expected.append(" Message: Player added successfully");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of start game.
   */
  @Test
  public void testStartGame() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.startGame();
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("closeAddPlayerFrame invoked! Uniquecode: 271020");
    expected.append("showGameView invoked! Uniquecode: 271020");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020");
    expected.append("UniqueCode: 271020Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of add players page.
   */
  @Test
  public void testAddplayers() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.openAddPlayers();
    expected.append("closeIntro invoked! Uniquecode: 271020");
    expected.append("showAddPlayers invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of File Chooser.
   */
  @Test
  public void testFileChooser() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.openFileChooser();
    expected.append("chooseFile invoked! Uniquecode: 271020showError invoked! Uniquecode: 271020 ");
    expected.append("Error: Please Try Again or Choose Existing Map.");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of attack Target command.
   */
  @Test
  public void testAttackTarget() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.attackTarget();
    expected.append("UniqueCode: 271020getItemsWithCurrentPlayer() function invoked!\n");
    expected.append("chooseItemToAttack invoked! Uniquecode: 271020");
    expected.append("UniqueCode: 271020attack Target function invoked!\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020 Message: Attack was successful");
    expected.append("UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020");
    expected.append("Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of pick an item command.
   */
  @Test
  public void testPickAnItem() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.pickAnItem();
    expected.append("UniqueCode: 271020getItemsInCurrentSpace() function invoked!\n");
    expected.append("pickItemView invoked! Uniquecode: 271020Uniquecode: 271020 Input: null\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020 Message: ");
    expected.append("Item has been successfully picked upUniqueCode: 271020isGameOver "
        + "function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020");
    expected.append("Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of Look Around command.
   */
  @Test
  public void testLookAround() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.lookAround();
    expected.append("UniqueCode: 271020 LookAround method invoked!\n");
    expected.append("lookAroundView invoked! Uniquecode: 271020 Message: ");
    expected.append("LookAround method invoked! 271020UniqueCode: 271020isGameOver function "
        + "invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020");
    expected.append("Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invoke of move pet command.
   */
  @Test
  public void testMovePet() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.movePet();
    expected.append("getListOfSpacesInWorld invoked! Uniquecode: 271020"
        + "movePetView invoked! Uniquecode: 271020Uniquecode: 271020 Input: "
        + "nullisCalledByPlayer:trueshowDialogMessage invoked! Uniquecode: 271020 "
        + "Message: Pet Moved SucessfullyUniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020"
        + "Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
  
  /**
   * This method tests invalid set features.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidFeatures() {
    inView.setFeature(null);
  }
  
  /**
   * This method tests for invalid pick item view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidPickItem() {
    inView.pickItemView(null);
  }
  
  /**
   * This method tests for invalid show dialog view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShowDialog() {
    inView.showDialogMessage(null);
  }
  
  /**
   * This method tests for invalid look around view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidLookAround() {
    inView.lookAroundView(null);
  }
  
  /**
   * This method tests for invalid move pet view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMovePet() {
    inView.movePetView(null);
  }
  
  /**
   * This method tests for invalid item attack view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidItemAttack() {
    inView.chooseItemToAttack(null);
  }
  
  /**
   * This method tests for invalid show error view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShowError() {
    inView.showError(null);
  }
  
  /**
   * This method tests for invalid current Player turn view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCurrentPlayerTurn() {
    inView.showCurrentPlayerTurn(null);
  }
  
  /**
   * This method tests for invalid pick item command.
   */
  @Test
  public void testInvalidPickCommand() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    inController.pickAnItem();
    expected.append("UniqueCode: 271020getItemsInCurrentSpace() function invoked!\n");
    expected.append("showError invoked! Uniquecode: 271020 Error: Unable to pick the item");
    expected.append("UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020");
    expected.append("Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log2.toString());
  }
  
  /**
   * This method tests for invalid look around command.
   */
  @Test
  public void testInvalidLookAroundCommand() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    inController.lookAround();
    expected.append("UniqueCode: 271020 LookAround method invoked!\n");
    expected.append("lookAroundView invoked! Uniquecode: 271020 Message: LookAround "
        + "method invoked! 271020");
    expected.append("UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020Get "
        + "information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log2.toString());
  }
  
  /**
   * This method tests for invalid move pet command.
   */
  @Test
  public void testInvalidMovePetCommand() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    inController.movePet();
    expected.append("getListOfSpacesInWorld invoked! Uniquecode: 271020");
    expected.append("showError invoked! Uniquecode: 271020 Error: Unable to move the pet");
    expected.append("UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020"
        + "Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log2.toString());
  }
  
  /**
   * This method tests for invalid move player command.
   */
  @Test
  public void testInvalidMovePlayerCommand() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    inController.mouseClicked(0, 3000);
    expected.append("UniqueCode: 271020processMouseClick() function invoked! input x\n");
    expected.append("0 y:3000UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020 Display Info Current player invoked!\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020 Message: 271020");
    expected.append("UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020"
        + "Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log2.toString());
  }
  
  /**
   * This method tests for invalid add player command.
   */
  @Test
  public void testInvalidAddPlayerCommand() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    inController.addPlayer(null, false, null, 0);
    expected.append("showError invoked! Uniquecode: 271020 Error: Player addition parameters"
        + " not valid");
    assertEquals(expected.toString(), log2.toString());
  }

  /**
   * This method tests for a winner.
   */
  @Test
  public void testWinner() {
    StringBuilder expected = new StringBuilder();
    expected.append("271020");
    String result = model.getWinner();
    assertEquals(expected.toString(), result);
  }

  /**
   * This method tests invoke of quit game method.
   */
  @Test
  public void testQuitGame() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.quitGame();
    expected.append("quitGame invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }

  /**
   * This method test the invoke after click outside the world.
   */
  @Test
  public void testMouseClickOutsideWorld() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.mouseClicked(0, 0);
    expected.append("UniqueCode: 271020processMouseClick() function invoked! input x\n");
    expected.append("0 y:0UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020 Display Info Current player invoked!\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020 Message: 271020"
            + "UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020"
            + "Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }

  /**
   * This method test the invoke after click inside the world.
   */
  @Test
  public void testMouseClickInsideWorld() {
    StringBuilder expected = new StringBuilder();
    expected.append("setFeature invoked! Uniquecode: 271020");
    expected.append("showIntro invoked! Uniquecode: 271020");
    controller.mouseClicked(700, 400);
    expected.append("UniqueCode: 271020processMouseClick() function invoked! input x\n");
    expected.append("700 y:400UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020getCurrentPlayerLocation() function invoked!\n");
    expected.append("UniqueCode: 271020 Display Info Current player invoked!\n");
    expected.append("showDialogMessage invoked! Uniquecode: 271020 Message: 271020"
            + "UniqueCode: 271020isGameOver function invoked!\n");
    expected.append("UniqueCode: 271020 Create world map invoked!\n");
    expected.append("refresh invoked! Uniquecode: 271020UniqueCode: 271020"
            + "Get information function invoked!\n");
    expected.append("UniqueCode: 271020 getTurn function invoked.\n");
    expected.append("showCurrentPlayerTurn invoked! Uniquecode: 271020");
    assertEquals(expected.toString(), log.toString());
  }
}
