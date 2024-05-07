package utils;

/**
 * Custom data structure class to store values in pairs.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 *
 * @param <E1> contains the first value
 * @param <E2> contains the second value
 */
public class Pair<E1, E2> {

  private final E1 firstElement;
  private final E2 secondElement;

  /**
   * Constructor to initialise the Pair object class.
   *
   * @param firstElement  of the Pair object
   * @param secondElement of the Pair object class
   */
  public Pair(E1 firstElement, E2 secondElement) {
    if (firstElement == null || secondElement == null) {
      throw  new IllegalArgumentException("Elements cannot be null");
    }
    this.firstElement = firstElement;
    this.secondElement = secondElement;
  }

  /**
   * This function is used to get the first element of the two values.
   * @return First element of two value.
   */
  public E1 getFirstElement() {
    return firstElement;
  }

  /**
   * This function is used to get the second element of the two values.
   * @return Second element of two value.
   */
  public E2 getSecondElement() {
    return secondElement;
  }

}
