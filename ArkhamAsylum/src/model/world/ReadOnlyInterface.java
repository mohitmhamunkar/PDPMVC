package model.world;

import java.util.List;

/**
 * This java file contains read only methods of DrLuckyWorld.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 */
public interface ReadOnlyInterface {
  /**
   * Get list of spaces in the world.
   *
   * @return list of string denoting the spaces in the room
   */
  List<String> getListOfSpacesInWorld();
  
  /**
   * This function is used to get the number of current player.
   *
   * @return the total number of current player.
   */
  int getNumberOfHumanPlayer();

  /**
   * This function is used to get the hint for the current player.
   * With this hint the player will know the location of the target.
   * Location of the pet.
   * Items in the room.
   * @return String with the complete hint.
   */
  String getHint();
  
}
