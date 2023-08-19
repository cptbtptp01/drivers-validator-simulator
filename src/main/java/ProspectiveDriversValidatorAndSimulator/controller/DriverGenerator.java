package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.CrashType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.NonMovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.PropertyEnum;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Crash;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverHistory;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Insurance;
import problem2.ProspectiveDriversValidatorAndSimulator.model.License;
import problem2.ProspectiveDriversValidatorAndSimulator.model.MovingViolation;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Name;
import problem2.ProspectiveDriversValidatorAndSimulator.model.NonMovingViolation;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Vehicle;
import problem2.ProspectiveDriversValidatorAndSimulator.model.VehicleBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.model.VehicleHistory;

/**
 * This class represents a DriverGenerator responsible for generating a Driver object from driver
 * data.
 */
public class DriverGenerator {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final Name PLACEHOLDER_NAME = new Name("placeholder", "placeholder");

  /**
   * Processes the driver data and returns a Driver object
   *
   * @param driverData - driver data
   * @return - Driver object
   */
  public static Driver processDriver(Map<String, String> driverData) {
    Name name = processDriverName(driverData);
    LocalDate dateOfBirth = formatDate(driverData.get(PropertyEnum.DATE_OF_BIRTH.getProperty()));
    License license = processDriverLicense(driverData);
    Vehicle vehicle = processVehicle(driverData);
    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(vehicle);
    DriverHistory driverHistory = processDriverHistory(driverData);
    Driver driver = new DriverBuilder(name).withDateOfBirth(dateOfBirth).withLicense(license)
        .withVehicles(vehicles).withDriverHistories(driverHistory).build();
    return driver;
  }

  /**
   * Processes the driver name
   *
   * @param driverData - driver data
   * @return - Name object
   */
  private static Name processDriverName(Map<String, String> driverData) {
    Name name = new Name(driverData.get(PropertyEnum.FIRST_NAME.getProperty()),
        driverData.get(PropertyEnum.LAST_NAME.getProperty()));
    return name;
  }

  /**
   * Processes the driver license
   *
   * @param driverData - driver data
   * @return - License object
   */
  private static License processDriverLicense(Map<String, String> driverData) {
    Name name = processDriverName(driverData);
    LocalDate dateOfBirth = formatDate(driverData.get(PropertyEnum.DATE_OF_BIRTH.getProperty()));
    LocalDate dateOfIssue = formatDate(driverData.get(PropertyEnum.DATE_OF_ISSUE.getProperty()));
    LocalDate dateOfExpiry = formatDate(driverData.get(PropertyEnum.DATE_OF_EXPIRY.getProperty()));
    License license = new License(driverData.get(PropertyEnum.LICENSE_NUMBER.getProperty()), name,
        driverData.get(PropertyEnum.ADDRESS.getProperty()),
        dateOfBirth, driverData.get(PropertyEnum.COUNTRY_OF_ISSUE.getProperty()),
        driverData.get(PropertyEnum.STATE_OF_ISSUE.getProperty()), dateOfIssue,
        dateOfExpiry);
    return license;
  }

  /**
   * Processes the driver insurance
   *
   * @param driverData - driver data
   * @return - Insurance object
   */
  private static Insurance processDriverInsurance(Map<String, String> driverData) {
    Insurance insurance;
    Name name = processDriverName(driverData);
    LocalDate insuranceDateOfExpiry = formatDate(
        driverData.get(PropertyEnum.INSURANCE_DATE_OF_EXPIRY.getProperty()));
    String ownerOrInsured = driverData.get(PropertyEnum.OWNER_OR_INSURED.getProperty());
    if (ownerOrInsured.equals(PropertyEnum.OWNER.getProperty())) {
      insurance = new Insurance(insuranceDateOfExpiry, name, null);
    } else {
      insurance = new Insurance(insuranceDateOfExpiry, PLACEHOLDER_NAME, List.of(name));
    }
    return insurance;
  }

  /**
   * Formats the date
   *
   * @param date - date
   * @return - LocalDate object
   */
  private static LocalDate formatDate(String date) {
    return LocalDate.parse(date, formatter);
  }

  /**
   * Processes the vehicle history
   *
   * @param driverData - driver data
   * @return - VehicleHistory object
   */
  private static VehicleHistory processVehicleHistory(Map<String, String> driverData) {
    VehicleHistory vehicleHistory = new VehicleHistory();
    String movingOrNonMoving = driverData.get(PropertyEnum.VEHICLE_VIOLATION.getProperty());
    String hasCrash = driverData.get(PropertyEnum.VEHICLE_CRASH.getProperty());
    if (!hasCrash.equals(PropertyEnum.NULL.getProperty())) {
      Crash crash = processVehicleCrash(driverData);
      vehicleHistory.addCrash(crash);
    }
    if (movingOrNonMoving.equals(PropertyEnum.NULL.getProperty())) {
      return vehicleHistory;
    }
    if (movingOrNonMoving.equals(PropertyEnum.MOVING.getProperty())) {
      MovingViolation mv = processMovingViolation(
          formatDate(driverData.get(PropertyEnum.VEHICLE_VIOLATION_DATE.getProperty())),
          PropertyEnum.OTHER.getProperty());
      vehicleHistory.addViolation(mv);
    } else {
      NonMovingViolation nmv = processNonMovingViolation(
          formatDate(driverData.get(PropertyEnum.VEHICLE_VIOLATION_DATE.getProperty())),
          PropertyEnum.OTHER.getProperty());
      vehicleHistory.addViolation(nmv);
    }
    return vehicleHistory;
  }

  /**
   * Processes the moving violation
   *
   * @param date   - date
   * @param detail - detail
   * @return - MovingViolation object
   */
  private static MovingViolation processMovingViolation(LocalDate date, String detail) {
    return new MovingViolation(date, ViolationType.MOVING, MovingViolationType.valueOf(detail));
  }

  /**
   * Processes the non-moving violation
   *
   * @param date   - date
   * @param detail - detail
   * @return - NonMovingViolation object
   */
  private static NonMovingViolation processNonMovingViolation(LocalDate date, String detail) {
    return new NonMovingViolation(date, ViolationType.NON_MOVING,
        NonMovingViolationType.valueOf(detail));
  }

  /**
   * Processes the vehicle crash
   *
   * @param driverData - driver data
   * @return - Crash object
   */
  private static Crash processVehicleCrash(Map<String, String> driverData) {
    Name offender = new Name(driverData.get(PropertyEnum.OFFENDER_FIRST_NAME.getProperty()),
        driverData.get(PropertyEnum.OFFENDER_LAST_NAME.getProperty()));
    Crash crash = new Crash(
        formatDate(driverData.get(PropertyEnum.VEHICLE_CRASH_DATE.getProperty())),
        CrashType.valueOf(driverData.get(PropertyEnum.VEHICLE_CRASH.getProperty())), offender);
    return crash;
  }

  /**
   * Processes the driver history
   *
   * @param driverData - driver data
   * @return - DriverHistory object
   */
  private static DriverHistory processDriverHistory(Map<String, String> driverData) {
    DriverHistory driverHistory = new DriverHistory();
    if (driverData.get(PropertyEnum.DRIVER_VIOLATION_DATE.getProperty())
        .equals(PropertyEnum.NULL.getProperty())) {
      return driverHistory;
    }
    LocalDate violationDate = formatDate(
        driverData.get(PropertyEnum.DRIVER_VIOLATION_DATE.getProperty()));
    String movingOrNonMoving = driverData.get(PropertyEnum.DRIVER_VIOLATION.getProperty());
    String violationDetail = driverData.get(PropertyEnum.DRIVER_VIOLATION_DETAIL.getProperty());
    if (movingOrNonMoving.equals(PropertyEnum.MOVING.getProperty())) {
      MovingViolation mv = processMovingViolation(violationDate, violationDetail);
      driverHistory.addViolation(mv);
    } else if (movingOrNonMoving.equals(PropertyEnum.NON_MOVING.getProperty())) {
      NonMovingViolation nmv = processNonMovingViolation(violationDate, violationDetail);
      driverHistory.addViolation(nmv);
    }
    return driverHistory;
  }

  /**
   * Processes the vehicle
   *
   * @param driverData - driver data
   * @return - Vehicle object
   */
  private static Vehicle processVehicle(Map<String, String> driverData) {
    Vehicle vehicle;
    Insurance insurance = processDriverInsurance(driverData);
    VehicleHistory vehicleHistory = processVehicleHistory(driverData);
    String ownerOrInsured = driverData.get(PropertyEnum.OWNER_OR_INSURED.getProperty());
    vehicle = new VehicleBuilder(driverData.get(PropertyEnum.VEHICLE_MAKE.getProperty()),
        driverData.get(PropertyEnum.VEHICLE_MODEL.getProperty()),
        Integer.valueOf(driverData.get(PropertyEnum.VEHICLE_YEAR.getProperty()))).withVehicleColor(
            driverData.get(PropertyEnum.VEHICLE_COLOR.getProperty()))
        .withVehicleId(driverData.get(PropertyEnum.VEHICLE_PLATE_NUMBER.getProperty()))
        .withInsurance(insurance).withOwnerName(PLACEHOLDER_NAME)
        .withVehicleHistories(vehicleHistory).build();
    if (ownerOrInsured.equals(PropertyEnum.OWNER.getProperty())) {
      vehicle.setOwnerName(processDriverName(driverData));
    }
    return vehicle;
  }
}
