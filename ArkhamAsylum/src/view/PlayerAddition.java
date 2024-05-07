package view;

import controller.Features;

/**
 * This is an interface for Player Addition frame class.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public interface PlayerAddition {
  
  /**
   * This method displays the frame with all its components.
   */
  void show();

  /**
   * This method hides the frame after a call of listner.
   */
  void hide();

  /**
   * This method sets the featues of the class.
   * 
   * @param feature Controller
   */
  void setFeature(Features feature);
}
