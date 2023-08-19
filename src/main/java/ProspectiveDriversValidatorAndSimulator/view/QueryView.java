package problem2.ProspectiveDriversValidatorAndSimulator.view;

import java.util.Comparator;
import java.util.Set;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;

/**
 * This class represents the QueryView responsible for providing the query information to the
 * console.
 */
public class QueryView {

  /**
   * Returns the start message to the console
   *
   * @return - the start message
   */
  public static String getStartMessage() {
    return "\nWelcome to the Prospective Drivers Validator and Simulator!";
  }

  /**
   * Return the invalid input message to the console
   *
   * @return - the invalid input message
   */
  public static String getErrorMessage() {
    return "A valid last name must be provided";
  }

  /**
   * Return the query prompt to the console
   *
   * @return - the query prompt
   */
  public static String getQueryPrompt() {
    return "\nPlease enter a last name to search for:";
  }

  /**
   * Returns the driver not found message to the console
   *
   * @return - the driver not found message
   */
  public static String getDriverNotFoundMessage(String lastName) {
    return "No registered driver found with the last name " + lastName;
  }

  /**
   * Prints the driver information alphabetically by first name to the console
   *
   * @param driver - the list of drivers to be printed
   */
  public static void provideDriverInformation(Set<Driver> driver) {
    driver.stream()
        .sorted(Comparator.comparing(d -> d.getName().getFirstName()))
        .forEach(System.out::println);
  }
}
