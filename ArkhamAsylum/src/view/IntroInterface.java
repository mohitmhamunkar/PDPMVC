package view;

import controller.Features;

/**
 * This interface represents the intro screen of the game.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 *
 */
public interface IntroInterface {
  /**
   * This method displays the intro frame with all the components.
   */
  void show();
  
  /**
   * This method creates a call back with listners to the controller.
   * 
   * @param feature Controller
   */
  void setFeatures(Features feature);
  
  /**
   * This method hides the frame on invoke of actions.
   */
  void hide();
}
