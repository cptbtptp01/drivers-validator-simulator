package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverPool;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Vehicle;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Violation;
import problem2.ProspectiveDriversValidatorAndSimulator.view.DriverView;
import problem2.ProspectiveDriversValidatorAndSimulator.view.ErrorLogger;
import problem2.ProspectiveDriversValidatorAndSimulator.view.QueryView;

/**
 * This class represents the DriverService responsible for registering drivers and providing
 * information to the console.
 */
public class DriverService {

  private static final String REGEX = "[a-zA-Z]+";
  private final DriverPool drivers;
  private final DriverDataProcessor processor;
  private final List<Map<String, String>> data;
  private final Scanner scanner;
  private final ErrorLogger errorLogger;

  /**
   * Constructs a new DriverService object.
   */
  public DriverService(String path) {
    this.processor = new DriverDataProcessor(path);
    this.drivers = DriverPool.getInstance();
    this.data = processor.getData();
    this.scanner = new Scanner(System.in);
    this.errorLogger = new ErrorLogger();
  }

  /**
   * Registers a driver to the system.
   *
   * @param driver - the driver
   */
  public void registerDriver(Driver driver) {
    if (RegistrationValidator.isDriverValid(driver, errorLogger)) {
      Driver existingDriver = drivers.getDriverByName(driver.getName());
      if (existingDriver == null) {
        drivers.addDriver(driver);
      } else {
        addNewVehicle(driver, existingDriver);
      }
    } else {
      DriverView.displayErrors(errorLogger);
    }
  }

  /**
   * Starts the registration process for all drivers in the data file.
   */
  public void startRegistration() {
    for (Map<String, String> driverData : data) {
      Driver driver = DriverGenerator.processDriver(driverData);
      registerDriver(driver);
    }
  }

  /**
   * Runs the program.
   */
  public void run() {
    startRegistration();
    System.out.println(QueryView.getStartMessage());
    String input;
    do {
      System.out.println(QueryView.getQueryPrompt());
      input = scanner.nextLine();
      try {
        if (!isValidInput(input)) {
          throw new IllegalArgumentException(QueryView.getErrorMessage());
        }
        Set<Driver> result = drivers.getDriversByLastName(input);
        if (result.isEmpty()) {
          throw new IllegalArgumentException(QueryView.getDriverNotFoundMessage(input));
        }
        QueryView.provideDriverInformation(result);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } while (!isValidInput(input));
  }

  /**
   * Checks if the input is valid.
   *
   * @param input - the input
   * @return - true if the input is valid, false otherwise
   */
  private boolean isValidInput(String input) {
    return input.matches(REGEX) && input.length() > 0;
  }

  /**
   * Adds a new vehicle to the existing driver.
   *
   * @param d  - the driver
   * @param ed - the existing driver
   */
  protected void addNewVehicle(Driver d, Driver ed) {
    Vehicle newVehicle = d.getVehicles().get(0);
    Violation newViolation = d.getDriverHistories().hasViolations()
        ? d.getDriverHistories().getViolations().get(0)
        : null;
    ed.addVehicle(newVehicle);
    if (newViolation != null) {
      ed.addViolation(newViolation);
    }
  }

  /**
   * Returns the processor.
   *
   * @return - the processor
   */
  public DriverDataProcessor getProcessor() {
    return processor;
  }

  /**
   * Returns the drivers.
   *
   * @return - the drivers
   */
  public DriverPool getDrivers() {
    return drivers;
  }

  /**
   * Returns the data.
   *
   * @return - the data
   */
  public List<Map<String, String>> getData() {
    return data;
  }

  /**
   * Returns the scanner.
   *
   * @return - the scanner
   */
  public Scanner getScanner() {
    return scanner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverService that = (DriverService) o;
    return Objects.equals(processor, that.processor) && Objects.equals(drivers,
        that.drivers) && Objects.equals(data, that.data) && Objects.equals(
        scanner, that.scanner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processor, drivers, data, scanner);
  }

  @Override
  public String toString() {
    return "DriverService{" +
        "processor=" + processor +
        ", drivers=" + drivers +
        ", data=" + data +
        '}';
  }
}
