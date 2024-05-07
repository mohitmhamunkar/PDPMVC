package utils;

import java.util.Random;

/**
 * Helper class to generate predictable Random numbers.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class RandomClassGenerator implements RandomClassInterface {

  private final int[] randomNumber;
  private final Random random;
  private int counter;

  /**
   * Default constructor to initialise Random class generator.
   */
  public RandomClassGenerator() {
    randomNumber = null;
    random = new Random();
  }

  /**
   * Contructor to set the randomNumber array.
   *
   * @param randomNumber an array of random number to make it predictable.
   */
  public RandomClassGenerator(int... randomNumber) {
    this.randomNumber = randomNumber;
    random = new Random();
    counter = 0;
  }

  @Override
  public int getRandomNumber(int endingRange) {
    endingRange++;
    if (randomNumber != null) {
      int r = randomNumber[counter];
      counter++;
      return r;
    } else {
      return random.nextInt(endingRange);
    }
  }


}
