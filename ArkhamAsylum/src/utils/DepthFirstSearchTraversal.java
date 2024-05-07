package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This class is used for DFS traversal.
 * This class is used to expose necessary functions.
 * Which will be used to move the pet to the spaces.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class DepthFirstSearchTraversal implements DepthFirstSearch {

  private final List<Integer>[] adjacencyList;

  /**
   * Constructor of the DFS class.
   * This constructor is used to initialise the adjacency list.
   * @param adjacencyList is used to know the neighbors of each space.
   */
  public DepthFirstSearchTraversal(List<Integer>[] adjacencyList) {

    if (adjacencyList == null || adjacencyList.length <= 0) {
      throw new IllegalArgumentException("Adjacency list is empty");
    }

    this.adjacencyList = getCopyOfLinkedList(adjacencyList);
  }

  /**
   * Helper function to get the copy of the LinkedList.
   *
   * @param adjacencyList List of nodes and its neighbors.
   * @return Copy of the same list
   */
  private List<Integer>[] getCopyOfLinkedList(List<Integer>[] adjacencyList) {

    List<Integer>[] adjLinkedListCopy = new LinkedList[adjacencyList.length];

    for (int i = 0; i < adjacencyList.length; i++) {
      adjLinkedListCopy[i] = new LinkedList<Integer>();
    }

    for (int i = 0; i < adjacencyList.length; i++) {
      List<Integer> list = adjacencyList[i];
      for (Integer id : list) {
        adjLinkedListCopy[i].add(id);
      }
    }

    return adjLinkedListCopy;
  }

  @Override
  public List<Integer> findPath(int id) {
    List<Integer> traversal = new ArrayList<>();
    boolean[] visited = new boolean[this.adjacencyList.length];

    // Initialize visited array to false:
    for (int i = 0; i < this.adjacencyList.length; i++) {
      visited[i] = false;
    }

    Stack<Integer> stack = new Stack<>();

    stack.push(id);

    while (!stack.isEmpty()) {
      int temp = stack.peek();

      traversal.add(temp);

      if (!visited[temp]) {
        visited[temp] = true;
      }

      // Check if all the neighboring nodes are visited.

      List<Integer> neighboringList = adjacencyList[temp];

      boolean isAllNeighborsVisited = true;

      for (Integer n : neighboringList) {
        if (!visited[n]) {
          isAllNeighborsVisited = false;
          break;
        }
      }

      if (isAllNeighborsVisited) {
        stack.pop();
      } else {
        for (Integer n : neighboringList) {
          if (!visited[n]) {
            isAllNeighborsVisited = false;
            stack.push(n);
            break;
          }
        }
      }
    }
    return traversal;
  }

}
