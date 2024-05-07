package model.spaces;

import java.util.List;

/**
 * Specifies the method used to get the details of the space.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Space {

  /**
   * Function is used to get the spaceId.
   *
   * @return the spaceId of the space
   */
  int getSpaceId();

  /**
   * Function is used to get the name.
   *
   * @return the name of the space
   */
  String getSpaceName();

  /**
   * Function is used to get the location coordinates.
   *
   * @return the name of the space
   */
  List<Integer> getSpaceLocation();
}
