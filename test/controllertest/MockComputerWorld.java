package controllertest;

/**
 * Mock of the computer-specific operations for testing.
 */
public class MockComputerWorld extends MockWorld {

  private StringBuilder log;
  private String uniqueCode;

  /**
   * Initializing variables required for testing.
   * 
   * @param log        represents the logs generated
   * @param uniqueCode represents the unique identifier specific for the model
   */
  public MockComputerWorld(StringBuilder log, String uniqueCode) {
    super(log, uniqueCode);
  }

  @Override
  public String getTurn() {
    log.append(String.format("In computer getTurn(). There is no input.\n"));
    return uniqueCode;
  }

}
