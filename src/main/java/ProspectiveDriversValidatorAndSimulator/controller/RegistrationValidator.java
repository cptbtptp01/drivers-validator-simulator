package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import java.time.LocalDate;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.MovingViolation;
import problem2.ProspectiveDriversValidatorAndSimulator.view.DriverView;
import problem2.ProspectiveDriversValidatorAndSimulator.view.ErrorLogger;

/**
 * This class represents a RegistrationValidator.
 */
public class RegistrationValidator {

  private static final Integer MINIMUM_AGE = 21;
  private static final Integer MINIMUM_ISSUED_MONTHS = 6;
  private static final Integer VIOLATION_OR_CRASH_MONTHS = 6;
  private static final Integer MAX_VEHICLE_AGE = 15;
  private static final String COUNTRY_US = "US";
  private static final String COUNTRY_CANADA = "CANADA";

  /**
   * Checks if the driver is at least 21 years old
   *
   * @param driver the driver
   * @return true if the driver is at least 21 years old, false otherwise
   */
  private static boolean isMinimumAgeMet(Driver driver) {
    return driver.getDateOfBirth().getYear() - MINIMUM_AGE >= 0;
  }

  /**
   * Checks if the driver's license is valid
   *
   * @param driver the driver
   * @return true if the driver's license is valid, false otherwise
   */
  private static boolean isValidLicense(Driver driver) {
    return driver.getName().equals(driver.getLicense().getName()) && driver.getDateOfBirth()
        .equals(driver.getLicense().getDateOfBirth())
        && (driver.getLicense().getCountryOfIssue().equalsIgnoreCase(COUNTRY_US)
        || driver.getLicense()
        .getCountryOfIssue().equals(COUNTRY_CANADA))
        && getMonthDifference(driver.getLicense().getDateOfIssue()) >= MINIMUM_ISSUED_MONTHS
        && driver.getLicense()
        .getDateOfExpiry().isAfter(LocalDate.now());
  }

  /**
   * Checks if the driver has any vehicle older than 15 years
   *
   * @param driver the driver
   * @return true if the driver has any vehicle older than 15 years, false otherwise
   */
  private static boolean isVehicleOld(Driver driver) {
    return driver.getVehicles().stream()
        .anyMatch(vehicle -> LocalDate.now().getYear() - vehicle.getYear() > MAX_VEHICLE_AGE);
  }

  /**
   * Checks if the driver's insurance is expired
   *
   * @param driver the driver
   * @return true if the driver's insurance is expired, false otherwise
   */
  private static boolean isInsuranceExpired(Driver driver) {
    return driver.getVehicles().stream().anyMatch(vehicle -> {
      if (vehicle.getOwnerName().equals(driver.getName()) || vehicle.getInsurance()
          .getInsuredDrivers()
          .contains(driver.getName())) {
        return vehicle.getInsurance().getDateOfExpiry().isBefore(LocalDate.now());
      }
      return false;
    });
  }

  /**
   * Checks if the driver has any moving violation
   *
   * @param driver the driver
   * @return true if the driver has any moving violation include DUI, speeding, reckless driving, or
   * without a valid license/insurance false otherwise
   */
  private static boolean validateDriverHistory(Driver driver) {
    return driver.getDriverHistories().getViolations().stream()
        .anyMatch(v -> {
          if (v.getType().equals(ViolationType.MOVING)) {
            MovingViolation mv = (MovingViolation) v;
            return !((mv.getMovingViolationType().equals(MovingViolationType.DISTRACTED_DRIVING)) ||
                mv.getMovingViolationType()
                    .equals(MovingViolationType.FAILURE_TO_RESPECT_TRAFFIC_SIGNS));
          }
          return false;
        });
  }

  /**
   * Checks if the driver has any vehicle with moving or crash violation in the last 6 months
   *
   * @param driver the driver
   * @return true if the driver has any vehicle with moving violation or crash in the last 6 months,
   * false otherwise
   */
  private static boolean validateVehicleHistory(Driver driver) {
    return driver.getVehicles().stream().anyMatch(v -> {
      if ((!v.getVehicleHistories().getCrashes().isEmpty()) || v.getVehicleHistories()
          .getViolations().stream().anyMatch(mv -> mv.getType().equals(ViolationType.MOVING))) {
        return getMonthDifference(v.getVehicleHistories().getMostRecentViolationOrCrash())
            <= VIOLATION_OR_CRASH_MONTHS;
      }
      return false;
    });
  }

  /**
   * Utility method to calculates the difference in months between the current date and the given
   * date
   *
   * @param date the given date
   * @return the difference in months between the current date and the given date
   */
  private static int getMonthDifference(LocalDate date) {
    LocalDate curr = LocalDate.now();
    int currYear = curr.getYear();
    int currMonth = curr.getMonthValue();
    int dateYear = date.getYear();
    int dateMonth = date.getMonthValue();
    return (currYear - dateYear) * 12 + (currMonth - dateMonth);
  }

  private static String getDriverName(Driver driver) {
    return driver.getName().getFirstName() + " " + driver.getName().getLastName();
  }

  /**
   * Checks if the driver is valid
   *
   * @param driver the driver
   * @return true if the driver is valid, false otherwise
   */
  public static boolean isDriverValid(Driver driver, ErrorLogger errorLogger) {
    boolean isValid = true;
    String driverName = getDriverName(driver);
    if (!isMinimumAgeMet(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidAgeMessage(driverName));
    }
    if (!isValidLicense(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidLicenseMessage(driverName));
    }
    if (isVehicleOld(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidVehicleMessage(driverName));
    }
    if (isInsuranceExpired(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidInsuranceMessage(driverName));
    }
    if (validateDriverHistory(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidDriverHistoryMessage(driverName));
    }
    if (validateVehicleHistory(driver)) {
      isValid = false;
      errorLogger.log(DriverView.getInvalidVehicleHistoryMessage(driverName));
    }
    return isValid;
  }
}
