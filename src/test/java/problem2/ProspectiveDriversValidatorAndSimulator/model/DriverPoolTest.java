package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverPoolTest {
  private DriverPool driverPool;
  private LocalDate dob;
  private LocalDate dateOfIssue;
  private LocalDate dateOfExpiry;
  private Name name;
  private Driver driver;

  @BeforeEach
  void setUp() {
    driverPool = DriverPool.getInstance();

    name = new Name("John", "Doe");
    dob = LocalDate.of(1990, 1, 1);
    dateOfIssue = LocalDate.of(2010, 1, 1);
    dateOfExpiry = LocalDate.of(2024, 1, 1);
    License license = new License("123456789", name, "123 Main St", dob, "US", "NY", dateOfIssue,
        dateOfExpiry);
    driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    Vehicle vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .build();
    driver.addVehicle(vehicle);
  }

  @Test
  void getInstance() {
    assertEquals(driverPool, DriverPool.getInstance());
  }

  @Test
  void addDriver() {
    driverPool.addDriver(driver);
    assertTrue(driverPool.getAllDrivers().contains(driver));
  }

  @Test
  void removeDriver() {
    assertThrows(IllegalArgumentException.class, () -> driverPool.removeDriver(new Driver()));
  }

  @Test
  void getAllDrivers() {
    assertEquals(0, driverPool.getAllDrivers().size());
  }

  @Test
  void getDriversByLastName() {
    DriverPool dp = DriverPool.getInstance();
    dp.addDriver(driver);
    assertEquals(0, dp.getDriversByLastName("oe").size());
  }

  @Test
  void getDriverByName() {
    Name n = new Name("John", "Doe");
    Driver d = new DriverBuilder(n).withDateOfBirth(LocalDate.of(1990, 1, 1)).build();
    driverPool.addDriver(d);
    assertEquals(d, driverPool.getDriverByName(n));
  }

  @Test
  void getNumberOfDrivers() {
    assertEquals(0, driverPool.getNumberOfDrivers());
  }

  @Test
  void testEquals() {
    DriverPool dp = DriverPool.getInstance();
    assertEquals(driverPool, dp);
    assertNotEquals(driverPool, new Object());
    assertNotEquals(driverPool, null);
  }

  @Test
  void testHashCode() {
    DriverPool dp = DriverPool.getInstance();
    assertEquals(driverPool.hashCode(), dp.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("DriverPool{drivers=[]}", driverPool.toString());
  }
}