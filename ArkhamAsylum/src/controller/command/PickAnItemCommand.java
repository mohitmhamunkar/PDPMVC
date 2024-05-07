package controller.command;

import java.util.List;
import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to command the model to Move to pick an item.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class PickAnItemCommand implements CommandInterface {

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {

    try {
      List<String> list = model.getItemsInCurrentSpace();
      String item = view.pickItemView(list);
      boolean isItemPicked = model.pickAnItemByPlayer(item);
      if (isItemPicked) {
        view.showDialogMessage("Item has been successfully picked up");
      } else {
        view.showDialogMessage("Unable to pick up an item.");
      }
    } catch (IllegalArgumentException io) {
      view.showError("Unable to pick the item");
    }
  }
}
