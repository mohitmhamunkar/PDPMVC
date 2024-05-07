package controller.command;

import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to command the model to Move to the neighbouring space.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class MovePlayerCommand implements CommandInterface {
  private final String spaceLocation;

  /**
   * Constructor of MovePlayerCommand to initialise the space location.
   * 
   * @param spaceLocation location where the player will be in the world.
   */
  public MovePlayerCommand(String spaceLocation) {
    if  (spaceLocation == null || spaceLocation.isEmpty()) {
      throw new IllegalArgumentException("SpaceLocation is not valid");
    }

    this.spaceLocation = spaceLocation;
  }

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {

    try {
      model.movePlayerToNeighboringRoom(spaceLocation);
      view.showDialogMessage("Player moved successfully");
    } catch (IllegalArgumentException io) {
      view.showError("Unable To Move Player");
    }
  }
}
