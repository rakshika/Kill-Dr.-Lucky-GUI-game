package model;

/**
 * Generates the required random inputs.
 */
public interface RandomGenerator {
  
  /**
   * Generates random number where the max limit is mentioned.
   * 
   * @param max represents the max random number.
   * @return the random number generated
   * @throws IllegalArgumentException if max is invalid.
   */
  public int generateSequantialInput(int max);
}
