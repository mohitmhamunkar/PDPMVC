package utils;

import java.util.List;

/**
 * Interface class of DFS traversal.
 * This class is used to expose necessary functions.
 * Which will be used to move the pet to the spaces.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface DepthFirstSearch {

  /**
   * This function finds the path of the pet in DFS manner.
   * It takes the starting id of the pet location.
   * Maps the path and return the path of the pet in the world.
   * @param id starting room id from where pet will start.
   * @return List of spaces' id where the pet will move next,
   */
  List<Integer> findPath(int id);
}
