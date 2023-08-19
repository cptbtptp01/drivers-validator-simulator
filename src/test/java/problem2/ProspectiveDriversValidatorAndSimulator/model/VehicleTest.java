package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {

  private Insurance insurance;
  private License license;
  private LocalDate dob;
  private Name name;
  private Vehicle vehicle;
  private Vehicle vehicle2;
  private Vehicle vehicle3;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
    dob = LocalDate.of(1990, 1, 1);
    LocalDate issueDate = LocalDate.of(2010, 1, 1);
    LocalDate expiryDate = LocalDate.of(2020, 1, 1);
    license = new License("123456789", name, "123 Main St", dob, "USA", "NY", issueDate,
        expiryDate);
    insurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);

    vehicle = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(insurance)
        .build();

    vehicle2 = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(insurance)
        .build();

    vehicle3 = new VehicleBuilder("Honda", "Civic", 2016)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(insurance)
        .build();

  }

  @Test
  void getMake() {
    assertEquals("Honda", vehicle.getMake());
  }

  @Test
  void getModel() {
    assertEquals("Civic", vehicle.getModel());
  }

  @Test
  void getYear() {
    assertEquals(2010, vehicle.getYear());
  }

  @Test
  void getVehicleColor() {
    assertEquals("Black", vehicle.getVehicleColor());
  }

  @Test
  void getVehicleId() {
    assertEquals("123456789", vehicle.getVehicleId());
  }

  @Test
  void getOwnerName() {
    assertEquals(name, vehicle.getOwnerName());
  }

  @Test
  void getInsurance() {
    assertEquals(insurance, vehicle.getInsurance());
  }

  @Test
  void getVehicleHistories() {
    assertEquals(new VehicleHistory(), vehicle.getVehicleHistories());
  }

  @Test
  void setOwnerName() {
    Name newName = new Name("Jane", "Doe");
    vehicle.setOwnerName(newName);
    assertEquals(newName, vehicle.getOwnerName());
  }

  @Test
  void testEquals() {
    assertEquals(vehicle, vehicle2);
    assertNotEquals(vehicle, vehicle3);
    assertNotEquals(vehicle, null);
    assertNotEquals(vehicle, "d");
    assertNotEquals(vehicle, new Object());
  }

  @Test
  void testHashCode() {
    assertEquals(vehicle.hashCode(), vehicle2.hashCode());
    assertNotEquals(vehicle.hashCode(), vehicle3.hashCode());
  }

  @Test
  void testToString() {
    String e = "2010 Black Honda Civic, 123456789";
    assertEquals(e, vehicle.toString());
  }
}