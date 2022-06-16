package model;

import java.util.stream.IntStream;

/**
 * Generates predictable random numbers.
 */
public class SequentialRandomGenerator implements RandomGenerator {

  private int value;

  /**
   * Generates random numbers from the array in sequential order.
   */
  public SequentialRandomGenerator() {
    super();
    this.value = 0;
  }

  @Override
  public int generateSequantialInput(int max) {
    if (max < 0) {
      throw new IllegalArgumentException("Max cannot be negative");
    }
    int[] is = IntStream.range(0, max).toArray();
    int val = is[value];
    value = (value + 1) % is.length;
    return val;
  }
}
