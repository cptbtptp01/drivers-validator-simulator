package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Insurance;
import problem2.ProspectiveDriversValidatorAndSimulator.model.License;
import problem2.ProspectiveDriversValidatorAndSimulator.model.MovingViolation;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Name;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Vehicle;
import problem2.ProspectiveDriversValidatorAndSimulator.model.VehicleBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.view.ErrorLogger;

class RegistrationValidatorTest {

  private License license;
  private LocalDate dob;
  private Name name;
  private Insurance insurance;
  private Driver driver;
  private Vehicle vehicle;
  private ErrorLogger logger;

  @BeforeEach
  void setUp() {
    logger = new ErrorLogger();
    name = new Name("John", "Doe");
    dob = LocalDate.of(1990, 1, 1);
    LocalDate issueDate = LocalDate.of(2010, 1, 1);
    LocalDate expiryDate = LocalDate.of(2024, 1, 1);
    license = new License("123456789", name, "123 Main St", dob, "US", "NY", issueDate, expiryDate);
    insurance = new Insurance(LocalDate.of(2024, 1, 1), name, null);
    // build driver
    driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    // build vehicle
    vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(insurance)
        .build();

  }

  @Test
  void isDriverValid1() {
    // driver with valid license, no vehicles, no violations
    //DriverService.registerDriver(driver);
    assertTrue(RegistrationValidator.isDriverValid(driver, logger));
  }

  @Test
  void isDriverValid2() {
    // driver with valid license, with vehicles, insurance, no violations
    driver.getVehicles().add(vehicle);
    //DriverService.registerDriver(driver);
    assertTrue(RegistrationValidator.isDriverValid(driver, logger));
  }

  @Test
  void isDriverValid3() {
    // driver with valid license, with vehicles, expired insurance, no violations
    Insurance expiredInsurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
    Vehicle vehicle2 = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(expiredInsurance)
        .build();
    driver.getVehicles().add(vehicle);
    driver.getVehicles().add(vehicle2);
    assertFalse(RegistrationValidator.isDriverValid(driver, logger));
  }

  @Test
  void isDriverValid4() {
    // driver with valid license, with old vehicles, expired insurance, no violations
    Insurance expiredInsurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
    Vehicle vehicle2 = new VehicleBuilder("Honda", "Civic", 2000)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(expiredInsurance)
        .build();
    driver.getVehicles().add(vehicle2);
    assertFalse(RegistrationValidator.isDriverValid(driver, logger));
  }

  @Test
  void isDriverValid5() {
    // driver with valid license, with vehicles, insurance, with moving violation - speeding
    MovingViolation movingViolation = new MovingViolation(LocalDate.of(2019, 1, 1),
        ViolationType.MOVING, MovingViolationType.SPEEDING);
    driver.getVehicles().add(vehicle);
    driver.getDriverHistories().addViolation(movingViolation);
    assertFalse(RegistrationValidator.isDriverValid(driver, logger));
  }

  @Test
  void isDriverValid6() {
    // driver with valid license, with vehicles, insurance, with vehicle history - moving violation - speeding - within 6 months
    MovingViolation movingViolation = new MovingViolation(LocalDate.of(2023, 7, 1),
        ViolationType.MOVING, MovingViolationType.SPEEDING);
    driver.getVehicles().add(vehicle);
    vehicle.getVehicleHistories().addViolation(movingViolation);
    assertFalse(RegistrationValidator.isDriverValid(driver, logger));
  }
}