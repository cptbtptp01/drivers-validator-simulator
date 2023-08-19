package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverBuilderTest {

  private License license;
  private LocalDate dob;
  private Name name;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
    dob = LocalDate.of(1990, 1, 1);
    LocalDate issueDate = LocalDate.of(2010, 1, 1);
    LocalDate expiryDate = LocalDate.of(2020, 1, 1);
    license = new License("123456789", name, "123 Main St", dob, "USA", "NY", issueDate,
        expiryDate);
  }

  @Test
  void build() {
    // create a set to store drivers
    Set<Driver> drivers = new HashSet<>();
    // build a driver without vehicles, insurance
    Driver driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    // add driver to set
    drivers.add(driver);
    assertEquals(1, drivers.size());
  }

  @Test
  void build1() {
    // create a set to store drivers
    Set<Driver> drivers = new HashSet<>();
    // build a driver without vehicles, insurance
    Driver driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    // build a vehicle and add driver as official owner
    Vehicle vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .build();
    // add insurance to vehicle
    Insurance insurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
    // add vehicle to driver
    driver.getVehicles().add(vehicle);
    // add insurance to vehicle
    vehicle.insurance = insurance;
    drivers.add(driver);
    assertEquals(1, drivers.size());
  }

  @Test
  void build2() {
    // create a set to store drivers
    Set<Driver> drivers = new HashSet<>();
    // build a driver without vehicles, insurance
    Driver driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    // build a vehicle and add driver as official owner
    Vehicle vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .build();
    // build a insured driver
    Driver insuredDriver = new DriverBuilder(new Name("Jane", "Doe"))
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    // add insurance to vehicle
    Insurance insurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
    // add vehicle to driver
    driver.getVehicles().add(vehicle);
    // add insurance to vehicle
    vehicle.insurance = insurance;
    // add vehicle to insured driver
    insuredDriver.getVehicles().add(vehicle);
    drivers.add(driver);
    drivers.add(insuredDriver);
    assertEquals(2, drivers.size());
  }
}