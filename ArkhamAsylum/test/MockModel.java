import java.util.List;
import model.target.Target;
import model.world.ArkhamAsylumWorld;

/**
 * This class is used to mock the main ArkhamAsylum model.
 * This class represents check if the function is being called properly.
 */
public class MockModel implements ArkhamAsylumWorld {

  private StringBuilder log;
  private final int uniqueCode;
  private int maxTurn;

  /**
   * Constructor of the Mock Model class.
   * @param log StringBuilder logs the input values.
   * @param uniqueCode is used to identify the unique values.
   * @param maxTurn maintains maximum turn of the players.
   */
  public MockModel(StringBuilder log, int uniqueCode, int maxTurn) {
    this.log = log;
    this.uniqueCode = uniqueCode;
    this.maxTurn = maxTurn;
  }

  @Override
  public List<String> getItemsInTheSpace(int spaceIdspaceId) {
    log.append("getItemsInTheSpace! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public List<String> getNeighbors(int spaceId) {
    log.append("getNeighbors invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public List<String> getNeighborsWhoCanSee(int spaceId) {
    log.append("getNeighborsWhoCanSee invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public int getTotalNumberOfSpaces() {
    log.append("getTotalNumberOfSpacesinvoked! Uniquecode: " + uniqueCode);
    return 0;
  }

  @Override
  public void createWorldMap() {
    log.append("UniqueCode: " + uniqueCode + " Create world map invoked!\n");
  }

  @Override
  public String getWorldName() {
    log.append("getWorldName Character invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public List<String> getListOfSpacesInWorld() {
    log.append("getListOfSpacesInWorld invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public List<String> getListOfItems() {
    log.append("getListOfItems invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public Target getTargetCharacter() {
    log.append("Get Target Character invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public String getTargetLocation() {
    log.append("Uniquecode: " + uniqueCode);
    return String.valueOf(uniqueCode);
  }

  @Override
  public boolean pickAnItemByPlayer(String weapon) {
    maxTurn--;
    log.append("Uniquecode: " + uniqueCode + " Input: " + weapon + "\n");
    return true;
  }

  @Override
  public void movePlayerToNeighboringRoom(String roomName) {
    if (roomName.isEmpty()) {
      throw new IllegalArgumentException("Room name should not be empty");
    }
    maxTurn--;
    log.append("Uniquecode: " + uniqueCode + " Input: " + roomName + "\n");
  }

  @Override
  public String lookAround() {
    log.append("UniqueCode: " + uniqueCode + " LookAround method invoked!\n");
    maxTurn--;
    return String.format("LookAround method invoked! %s", uniqueCode);
  }

  @Override
  public void addPlayer(String playerName,
                        String roomLocationName,
                        int maximumItemCount,
                        boolean isComputerPlayer) {
    if (playerName.isEmpty() || roomLocationName.isEmpty() || maximumItemCount < 0) {
      throw new IllegalArgumentException("Please check for valid arguments.");
    }
    log.append("UniqueCode: "
            + uniqueCode + " Input: " + playerName
            + " " + roomLocationName + " "
            + maximumItemCount + " "
            + isComputerPlayer + "\n");
  }

  @Override
  public String getTurn(boolean isPickUpWeapon, boolean isMovePlayer) {
    log.append("UniqueCode: " + uniqueCode + " getTurn function invoked.\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String getWorldInformation() {
    log.append("UniqueCode: " + uniqueCode + " Display Info of world invoked!\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String displayInfoOfCurrentPlayer() {
    log.append("UniqueCode: " + uniqueCode + " Display Info Current player invoked!\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String getSpaceInformation(String spaceName) {
    log.append("Input: " + spaceName + " Uniquecode: " + uniqueCode);
    return String.valueOf(uniqueCode);
  }

  @Override
  public String displayInfoOfSpecifiedPlayer(String name) {
    log.append("Uniquecode: " + uniqueCode + " Input: " + name + "\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public int getNumberOfHumanPlayer() {
    return 10;
  }

  @Override
  public boolean isComputerPlayer() {
    return false;
  }

  @Override
  public void movePetToSpecifiedSpace(String roomName, boolean isCalledByPlayer) {
    log.append("Uniquecode: "
            + uniqueCode
            + " Input: "
            + roomName
            + "isCalledByPlayer:"
            + isCalledByPlayer);
  }

  @Override
  public String getHint() {
    log.append("UniqueCode: " + uniqueCode + "Get information function invoked!\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public boolean attackTarget(String itemName) {
    log.append("UniqueCode: " + uniqueCode + "attack Target function invoked!\n");
    return true;
  }

  @Override
  public boolean isGameOver() {
    log.append("UniqueCode: " + uniqueCode + "isGameOver function invoked!\n");
    return false;
  }

  @Override
  public int getTargetHealth() {
    log.append("UniqueCode: " + uniqueCode + "getTargetHealth function invoked!\n");
    return uniqueCode;
  }

  @Override
  public String getWinner() {
    log.append("UniqueCode: " + uniqueCode + "getWinner function invoked!\n");
    return String.valueOf(uniqueCode);
  }

  @Override
  public boolean isPlayerCanSeeEachOther(String playerA, String playerB) {
    log.append("UniqueCode: " + uniqueCode + "isPlayerCanSeeEachOther function invoked!\n");
    return false;
  }

  @Override
  public List<String> getItemsInCurrentSpace() {
    log.append("UniqueCode: " + uniqueCode + "getItemsInCurrentSpace() function invoked!\n");
    return null;
  }

  @Override
  public List<String> getItemsWithCurrentPlayer() {
    log.append("UniqueCode: " + uniqueCode + "getItemsWithCurrentPlayer() function invoked!\n");
    return null;
  }

  @Override
  public String processMouseClick(int x, int y) {
    log.append(
            "UniqueCode: "
                    + uniqueCode
                    + "processMouseClick() function invoked! input x\n"
                    + x
                    + " y:"
                    + y
    );

    return String.valueOf(uniqueCode);
  }

  @Override
  public String getCurrentPlayerLocation() {
    log.append("UniqueCode: " + uniqueCode + "getCurrentPlayerLocation() function invoked!\n");

    return String.valueOf(uniqueCode);
  }
}
