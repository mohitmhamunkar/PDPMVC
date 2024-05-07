package controller;

import model.world.ArkhamAsylumWorld;

/**
 * This represents a controller for ArkhamAsylum game.
 * Its functionality is to handle the move given by the user.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface ArkhamAsylumController {

  /**
   * This function is used to execute the Arkham Asylum game.
   * It accepts the ArkhamAsylum model.
   * After the game is over this function
   *
   * @param model a non-null ArkhamAsylumWorld Model
   */
  void playArkhamGame(ArkhamAsylumWorld model);
}
