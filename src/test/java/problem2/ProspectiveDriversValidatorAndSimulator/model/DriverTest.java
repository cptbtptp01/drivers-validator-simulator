package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.view.QueryView;

class DriverTest {

  private License license;
  private LocalDate dob;
  private LocalDate dateOfIssue;
  private LocalDate dateOfExpiry;
  private Name name;
  private Driver driver;
  private Vehicle vehicle;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
    dob = LocalDate.of(1990, 1, 1);
    dateOfIssue = LocalDate.of(2010, 1, 1);
    dateOfExpiry = LocalDate.of(2020, 1, 1);
    license = new License("123456789", name, "123 Main St", dob, "US", "NY", dateOfIssue,
        dateOfExpiry);
    driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .build();
  }

  @Test
  void getName() {
    assertEquals(new Name("John", "Doe"), driver.getName());
  }

  @Test
  void getDateOfBirth() {
    assertEquals(LocalDate.of(1990, 1, 1), driver.getDateOfBirth());
  }

  @Test
  void getLicense() {
    License expected = new License("123456789", name, "123 Main St", dob, "US", "NY", dateOfIssue,
        dateOfExpiry);
    assertEquals(expected, driver.getLicense());
  }

  @Test
  void getVehicles() {
    assertEquals(0, driver.getVehicles().size());
  }

  @Test
  void getDriverHistories() {
    assertEquals(new DriverHistory(), driver.getDriverHistories());
  }

  @Test
  void addVehicle() {
    driver.addVehicle(vehicle);
    assertEquals(1, driver.getVehicles().size());
  }

  @Test
  void testEquals() {
    assertNotEquals(null, driver);
  }

  @Test
  void testEquals1() {
    assertNotEquals("d", driver);
  }

  @Test
  void testEquals2() {
    Driver driver1 = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    assertEquals(driver1, driver);
  }

  @Test
  void testEquals3() {
    Driver driver1 = new DriverBuilder(new Name("Jane", "Doe"))
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    assertNotEquals(driver1, driver);
  }

  @Test
  void testEquals4() {
    driver.addVehicle(vehicle);
    Driver driver1 = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    assertNotEquals(driver1, driver);
  }

  @Test
  void testHashCode() {
    Driver driver1 = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    assertEquals(driver1.hashCode(), driver.hashCode());
  }

  @Test
  void testHashCode1() {
    Driver driver1 = new DriverBuilder(new Name("Jane", "Doe"))
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    assertNotEquals(driver1.hashCode(), driver.hashCode());
  }

  @Test
  void testToString() {
    String expected = "Doe, John" + "\n" + "No vehicles registered";
    assertEquals(expected, driver.toString());
  }

  @Test
  void testToString2() {
    driver.addVehicle(vehicle);
    String expected = "Doe, John" + "\n" + "\t2010 Black Honda Civic, 123456789";
    assertEquals(expected, driver.toString());
  }

  @Test
  void testToString3() {
    driver.addVehicle(vehicle);
    MovingViolation movingViolation = new MovingViolation(LocalDate.of(2019, 1, 1),
        ViolationType.MOVING, MovingViolationType.SPEEDING);
    driver.driverHistories.addViolation(movingViolation);
    String expected =
        "Doe, John" + "\n" + "\t2010 Black Honda Civic, 123456789" + "\n" + "\tDriving violations:"
            + "\n" + "\t\tSpeeding on 2019-01-01";
    assertEquals(expected, driver.toString());
  }

  @Test
  void testGetDriverInfo() {
    Set<Driver> d = Set.of(driver);
    QueryView.provideDriverInformation(d);
  }
}