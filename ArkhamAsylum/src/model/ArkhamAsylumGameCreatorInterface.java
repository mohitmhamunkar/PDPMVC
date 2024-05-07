package model;

import model.world.ArkhamAsylumWorld;

/**
 * Interface of the DrLuckyGameInterface.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface ArkhamAsylumGameCreatorInterface {

  /**
   * This function returns the world of Dr Lucky.
   *
   * @return DrLuckyWorldImpl world's class object.
   */
  ArkhamAsylumWorld getArkhamAsylumWorld();
}
