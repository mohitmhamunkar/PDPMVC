package controller.command;

import java.util.List;
import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This command class is used to command the model to attack the target.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class AttackTargetCommand implements CommandInterface {

  @Override
  public void execute(ArkhamAsylumWorld model, Iview view) {

    try {
      List<String> list = model.getItemsWithCurrentPlayer();
      String item = view.chooseItemToAttack(list);
      boolean isAttackSuccessful = model.attackTarget(item);
      if (isAttackSuccessful) {
        view.showDialogMessage("Attack was successful");
      } else {
        view.showDialogMessage("Attack was not successful");
      }
    } catch (IllegalArgumentException io) {
      view.showError("Unable to attack the target");
    }
  }
}
