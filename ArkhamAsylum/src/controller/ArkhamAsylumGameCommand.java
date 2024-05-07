package controller;

import model.world.ArkhamAsylumWorld;

/**
 * Interface of the ArkhamAsylumGameCommand used to execute the command.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface ArkhamAsylumGameCommand {

  /**
   * This function is used to execute the Arkham Asylum game.
   * It accepts the ArkhamAsylum model.
   * After the game is over this function
   *
   * @param model a non-null ArkhamAsylumWorld Model
   */
  void execute(ArkhamAsylumWorld model);
}
