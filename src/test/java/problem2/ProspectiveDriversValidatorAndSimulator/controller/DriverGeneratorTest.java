package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.model.Driver;

class DriverGeneratorTest {
  Map<String, String> map;
  DriverGenerator driverGenerator;
  @BeforeEach
  void setUp() {
    String input = "{lastName=Doe, stateOfIssue=CA, countryOfIssue=US, vehicleViolation=MOVING, " +
        "offenderFirstName=Bob, driverViolation=MOVING, vehiclePlateNumber=ABC123, vehicleYear=2010, " +
        "vehicleModel=Camry, vehicleViolationDate=2020-01-01, licenseNumber=123456789, vehicleColor=Black, " +
        "dateOfExpiry=2030-01-01, address=123 Main St, vehicleCrash=WITHOUT_BODILY_INJURIES, " +
        "vehicleCrashDate=2020-01-01, dateOfBirth=1990-01-01, dateOfIssue=2020-01-01, vehicleMake=Toyota, " +
        "driverViolationDate=2020-01-01, offenderLastName=Smith, firstName=John, ownerOrInsured=owner, " +
        "driverViolationDetail=DISTRACTED_DRIVING, insuranceDateOfExpiry=2024-01-01}";

    map = new HashMap<>();
    String[] keyValuePairs = input.substring(1, input.length() - 1).split(", ");

    for (String pair : keyValuePairs) {
      String[] keyValue = pair.split("=");
      map.put(keyValue[0], keyValue[1]);
    }
  }

  @Test
  void testMovingViolation() {
    DriverGenerator.processDriver(map);
  }

  @Test
  void testNonMoving() {
    map.put("vehicleViolation", "NON-MOVING");
    DriverGenerator.processDriver(map);
  }

  @Test
  void testNull() {
    map.put("vehicleViolation", "NULL");
    DriverGenerator.processDriver(map);
  }

  @Test
  void testDriverViolation() {
    map.put("driverViolation", "NON-MOVING");
    map.put("driverViolationDetail", "PAPERWORK_ISSUES");
    DriverGenerator.processDriver(map);
  }

  public static void main(String[] args) {

  }
}