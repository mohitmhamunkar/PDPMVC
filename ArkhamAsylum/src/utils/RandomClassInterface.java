package utils;

/**
 * Helper class to generate predictable Random numbers.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface RandomClassInterface {

  /**
   * Get random number.
   *
   * @param endingRange set the range with the ending value.
   * @return a random value within the range.
   */
  int getRandomNumber(int endingRange);
}
