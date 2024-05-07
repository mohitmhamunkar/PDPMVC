package controller.command;

import model.world.ArkhamAsylumWorld;
import view.Iview;

/**
 * This interface represents command classes.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 *
 */
public interface CommandInterface {

  /**
   * This function is used to execute the Arkham Asylum game.
   * It accepts the ArkhamAsylum model.
   * After the game is over this function
   *
   * @param model a non-null ArkhamAsylumWorld Model
   * @param view a non-null IView view
   */
  void execute(ArkhamAsylumWorld model, Iview view);
}
