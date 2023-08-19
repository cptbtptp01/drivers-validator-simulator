package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverBuilder;
import problem2.ProspectiveDriversValidatorAndSimulator.model.DriverPool;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Insurance;
import problem2.ProspectiveDriversValidatorAndSimulator.model.License;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Name;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Vehicle;
import problem2.ProspectiveDriversValidatorAndSimulator.model.VehicleBuilder;

class DriverServiceTest {

  private LocalDate dob;
  private LocalDate dateOfIssue;
  private LocalDate dateOfExpiry;
  private Name name;
  private Driver driver;
  private DriverService driverService;
  private Scanner scanner;
  private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  String path;
  Vehicle vehicle;
  License license;

  @BeforeEach
  void setUp() {
    path = System.getProperty("user.dir") + "/src/test/resources/test.csv";
    driverService = new DriverService(path);
    name = new Name("John", "Doe2");
    dob = LocalDate.of(1990, 1, 1);
    dateOfIssue = LocalDate.of(2010, 1, 1);
    dateOfExpiry = LocalDate.of(2024, 1, 1);
    license = new License("123456789", name, "123 Main St", dob, "US", "NY", dateOfIssue,
        dateOfExpiry);
    driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    vehicle = new VehicleBuilder("Honda", "Civic", 2023)
        .withVehicleColor("Black")
        .withVehicleId("1234567898")
        .withOwnerName(name)
        .build();
    scanner = new Scanner(System.in);
    System.setOut(new PrintStream(capturedOut));
  }

  @AfterEach
  void tearDown() {
    DriverPool.getInstance().reset();
  }

  @Test
  void testRegister() {
    Insurance insurance = new Insurance(LocalDate.of(2024, 1, 1), name, null);
    Vehicle vehicle2 = new VehicleBuilder("Honda", "Civic", 2010)
        .withVehicleColor("Black")
        .withVehicleId("123456789")
        .withOwnerName(name)
        .withInsurance(insurance)
        .build();
    driver.addVehicle(vehicle2);

    License license1 = new License("123456789", new Name("Jane", "Doe"), "123 Main St", dob, "US",
        "NY", dateOfIssue, dateOfExpiry);
    Driver driver1 = new DriverBuilder(new Name("Jane", "Doe"))
        .withDateOfBirth(dob)
        .withLicense(license1)
        .build();
    driverService.registerDriver(driver);
    driverService.registerDriver(driver1);
  }

  @Test
  void testAddVehicleToExisting() {
    List<Vehicle> v = new ArrayList<>();
    v.add(vehicle);
    Driver newDriver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .withVehicles(v)
        .build();
    driverService.addNewVehicle(newDriver, driver);
    assertEquals(1, driver.getVehicles().size());
  }

  @Test
  void startRegistration() {
    driverService.startRegistration();
    assertNotEquals(0, DriverPool.getInstance().getNumberOfDrivers());
  }

  @Test
  void getDrivers() {
    assertFalse(driverService.getDrivers().getAllDrivers().stream().anyMatch(driver ->
        driver.getName() == null));
  }

  @Test
  void testEquals() {
    String path2 = System.getProperty("user.dir") + "/src/test/resources/test1.csv";
    DriverService ds = new DriverService(path2);
    assertNotEquals(driverService, ds);
    assertNotEquals(driverService, null);
    assertNotEquals(driverService, new Object());
    assertEquals(driverService, driverService);
  }

  @Test
  void testHashCode() {
    assertNotEquals(driverService.hashCode(), new Object().hashCode());
  }

  @Test
  void testToString() {
    String e = "e";
    assertNotEquals(e, driverService.toString());
  }

  @Test
  void testRun() {
    String input = "Doe\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    InputStream originalIn = System.in;
    System.setIn(in);
    try {
      DriverService ds = new DriverService(path);
      ds.run();
    } finally {
      System.setIn(originalIn);
    }
  }

  @Test
  void testRun2() {
    String input = "D\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    InputStream originalIn = System.in;
    System.setIn(in);
    try {
      DriverService ds = new DriverService(path);
      ds.run();
    } finally {
      System.setIn(originalIn);
    }
  }
  @Test
  void testRun3() {
    String input = "9\nDoe\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    InputStream originalIn = System.in;
    System.setIn(in);
    try {
      DriverService ds = new DriverService(path);
      ds.run();
    } finally {
      System.setIn(originalIn);
    }
  }
  @Test
  void getData(){
    List<Map<String, String>> e = new ArrayList<>();
    assertNotEquals(e, driverService.getData());
  }

  @Test
  void getProcessor() {
    String path1 = System.getProperty("user.dir") + "/src/test/resources/test1.csv";
    DriverDataProcessor p1 = new DriverDataProcessor(path1);
    assertNotEquals(p1, driverService.getProcessor());
  }

  @Test
  void getScanner() {
    assertNotEquals(scanner, driverService.getScanner());
  }
}