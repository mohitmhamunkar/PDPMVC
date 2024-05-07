package model.target;

/**
 * Can be used to implement function to perform the actions of target.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Target {

  /**
   * This function gives the type of player.
   *
   * @return an enumerator to denote player or target
   */
  CharacterType getPlayerType();

  /**
   * This function is used to get the current location of the target in the world.
   *
   * @return an unique id which represents the space in the world.
   */
  int getTargetLocation();

  /**
   * Function is used to decrease the target health by the specified.
   *
   * @param decreaseHealth decreases the health of the target
   */
  void decreaseTargetHealth(int decreaseHealth);

  /**
   * The target health keeps decreasing.
   * This function returns the target health in a particular point.
   *
   * @return the health of the target
   */
  int getTargetHealth();

  /**
   * This function returns the name of the target.
   *
   * @return the name of the target
   */
  String getTargetName();

  /**
   * Move the character to the next room.
   */
  void moveCharacter();

}
