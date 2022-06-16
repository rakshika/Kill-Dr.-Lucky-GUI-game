package controllertest;

/**
 * Mock of the computer-specific operations for testing.
 */
public class MockHumanWorld extends MockWorld {
  
  private StringBuilder log;
  private String uniqueCode;

  /**
   * Initializing variables required for testing.
   * 
   * @param log        represents the logs generated
   * @param uniqueCode represents the unique identifier specific for the model
   */
  public MockHumanWorld(StringBuilder log, String uniqueCode) {
    super(log, uniqueCode);
  }

  @Override
  public String getTurn() {
    log.append(String.format("In human getTurn(). There is no input.\n"));
    return uniqueCode;
  }



}
