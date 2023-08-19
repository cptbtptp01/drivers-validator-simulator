package problem2.ProspectiveDriversValidatorAndSimulator.view;

/**
 * This class represents the DriverView responsible for providing the registration information to
 * the console.
 */
public class DriverView {

  /**
   * Returns the start message to the console
   *
   * @param errorLogger
   */
  public static void displayErrors(ErrorLogger errorLogger) {
    System.out.println(errorLogger.toString());
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidAgeMessage(String driver) {
    return "Driver " + driver + " is not at least 21 years old";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidLicenseMessage(String driver) {
    return "Driver " + driver + " does not have a valid license";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidInsuranceMessage(String driver) {
    return "Driver " + driver + " does not have a valid insurance";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidVehicleHistoryMessage(String driver) {
    return "Driver " + driver + " has a moving violation or crash in the last 6 months";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidDriverHistoryMessage(String driver) {
    return "Driver " + driver + " has a moving violation";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getInvalidVehicleMessage(String driver) {
    return "Driver " + driver + " has a vehicle older than 15 years";
  }

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getDriverNotApprovedMessage(String driver) {
    return "Driver " + driver + " is not approved";
  }

  public static String getDriverNotFoundMessage() {
    return "Driver not found";
  }
}
