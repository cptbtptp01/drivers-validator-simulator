package problem2.ProspectiveDriversValidatorAndSimulator;

import problem2.ProspectiveDriversValidatorAndSimulator.controller.DriverService;

/**
 * Represents the entry of the Rideshare Driver Validator program.
 */
public class RideshareDriverValidator {

  public static void main(String[] args) {
    String path = System.getProperty("user.dir") + "/src/main/resources/drivers.csv";
    DriverService driverService = new DriverService(path);
    driverService.run();
  }
}
