package controller.command;

import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * Command class to add different players in the game.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class AddPlayer implements CommandInterface {

  private String playerName;
  private boolean isComputerPlayer;
  private String roomName;
  private int totalNumberItems;

  /**
   * Constructor of the Add Player class.
   * @param playerName Name of the player.
   * @param isComputerPlayer It is used to identify if the player is a computer.
   * @param roomName location of the player.
   * @param totalNumberItems Maximum number of items a player can carry.
   */
  public AddPlayer(String playerName,
                   boolean isComputerPlayer,
                   String roomName,
                   int totalNumberItems) {
    if (playerName == null || playerName.isEmpty()
        || roomName == null || roomName.isEmpty()
        || totalNumberItems < 0 || totalNumberItems == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Add players parameters are not valid");
    }

    this.playerName = playerName;
    this.isComputerPlayer = isComputerPlayer;
    this.roomName = roomName;
    this.totalNumberItems = totalNumberItems;
  }

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {

    try {
      model.addPlayer(
              playerName,
              roomName,
              totalNumberItems,
              isComputerPlayer
      );
      view.showDialogMessage("Player added successfully");
    } catch (IllegalArgumentException is) {
      view.showError("Unable to add player");
    }
  }
}
