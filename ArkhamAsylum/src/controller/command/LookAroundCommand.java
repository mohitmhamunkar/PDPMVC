package controller.command;

import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to command the model to look around the current player.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class LookAroundCommand implements CommandInterface {

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {
    try {
      view.lookAroundView(model.lookAround());
    } catch (IllegalArgumentException io) {
      view.showError("Unable to execute the command");
    }
  }
}
