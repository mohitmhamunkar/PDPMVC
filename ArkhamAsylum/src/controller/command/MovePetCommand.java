package controller.command;

import java.util.List;
import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to command the model to Move pet to the specified space.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class MovePetCommand implements CommandInterface {

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {

    try {
      List<String> list = model.getListOfSpacesInWorld();
      String space = view.movePetView(list);
      model.movePetToSpecifiedSpace(space, true);
      view.showDialogMessage("Pet Moved Sucessfully");
    } catch (IllegalArgumentException io) {
      view.showError("Unable to move the pet");
    }
  }
}
