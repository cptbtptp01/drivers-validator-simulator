package problem2.ProspectiveDriversValidatorAndSimulator.view;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.model.License;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Name;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Vehicle;
import problem2.ProspectiveDriversValidatorAndSimulator.model.VehicleBuilder;

class DriverViewTest {
  private String n;

  @BeforeEach
  void setUp() {
    Name name = new Name("John", "Doe");
    LocalDate dob = LocalDate.of(1990, 1, 1);
    LocalDate dateOfIssue = LocalDate.of(2010, 1, 1);
    LocalDate dateOfExpiry = LocalDate.of(2024, 1, 1);
    License license = new License("123456789", name, "123 Main St", dob, "US", "NY", dateOfIssue,
        dateOfExpiry);
    Driver d = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    n = "John Doe";
  }

  @Test
  void getInvalidAgeMessage() {
    String e = "Driver John Doe is not at least 21 years old";
    assertEquals(e, DriverView.getInvalidAgeMessage(n));
  }

  @Test
  void getInvalidLicenseMessage() {
    String e = "Driver John Doe does not have a valid license";
    assertEquals(e, DriverView.getInvalidLicenseMessage(n));
  }

  @Test
  void getInvalidInsuranceMessage() {
    String e = "Driver John Doe does not have a valid insurance";
    assertEquals(e, DriverView.getInvalidInsuranceMessage(n));
  }

  @Test
  void getInvalidVehicleHistoryMessage() {
    String e = "Driver John Doe has a moving violation or crash in the last 6 months";
    assertEquals(e, DriverView.getInvalidVehicleHistoryMessage(n));

  }

  @Test
  void getInvalidDriverHistoryMessage() {
    String e = "Driver John Doe has a moving violation";
    assertEquals(e, DriverView.getInvalidDriverHistoryMessage(n));
  }

  @Test
  void getInvalidVehicleMessage() {
    String e = "Driver John Doe has a vehicle older than 15 years";
    assertEquals(e, DriverView.getInvalidVehicleMessage(n));
  }

  @Test
  void getDriverNotApprovedMessage() {
    String e = "Driver John Doe is not approved";
    assertEquals(e, DriverView.getDriverNotApprovedMessage(n));

  }

  @Test
  void getDriverNotFoundMessage() {
    String e = "Driver not found";
    assertEquals(e, DriverView.getDriverNotFoundMessage());
  }
}