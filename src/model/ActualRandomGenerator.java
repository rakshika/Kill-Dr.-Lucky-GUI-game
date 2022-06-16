package model;

import java.util.Random;

/**
 * Generates unpredictable random numbers.
 */
public class ActualRandomGenerator implements RandomGenerator {
  
  @Override
  public int generateSequantialInput(int max) {
    if (max < 0) {
      throw new IllegalArgumentException("Max value cannot be negative");
    }
    return new Random().ints(0, max).findAny().getAsInt();
  }
}