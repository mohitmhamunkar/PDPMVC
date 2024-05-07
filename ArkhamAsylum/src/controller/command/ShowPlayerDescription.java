package controller.command;

import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to display all the information of the current player.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class ShowPlayerDescription implements CommandInterface {

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {
    try {
      view.showDialogMessage(model.displayInfoOfCurrentPlayer());
    } catch (IllegalArgumentException io) {
      view.showError("Unable to show player description");
    }
  }
}
