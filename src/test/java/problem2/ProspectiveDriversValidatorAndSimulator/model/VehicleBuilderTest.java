package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleBuilderTest {

  private Insurance insurance;
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
    insurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
  }

  @Test
  void build() {
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
    // add vehicle to driver
    driver.addVehicle(vehicle);
    assertEquals(1, driver.getVehicles().size());
    // add insurance to vehicle
    vehicle.insurance = insurance;
    assertTrue(driver.getVehicles().stream()
        .anyMatch(vehicle1 -> vehicle1.insurance.getOwnerName().equals(name)));
  }
}