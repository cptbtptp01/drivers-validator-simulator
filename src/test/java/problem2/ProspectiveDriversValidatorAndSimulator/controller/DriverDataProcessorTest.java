package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverDataProcessorTest {

  // "John","Doe","1990-01-01","123456789","123 Main St","US","CA","2020-01-01","2030-01-01","Toyota","Camry",2010,"Black","ABC123","2024-01-01","owner","WITHOUT_BODILY_INJURIES","NON-MOVING","MOVING",DISTRACTED_DRIVING,"2020-01-01","2020-01-01","2020-01-01","Bob","Smith"
  private DriverDataProcessor proccessor1;
  private DriverDataProcessor proccessor2;
  private static final String PATH =
      System.getProperty("user.dir") + "/src/test/resources/test.csv";
  private static final String PATH1 =
      System.getProperty("user.dir") + "/src/test/resources/test1.csv";

  @BeforeEach
  void setUp() {
    proccessor1 = new DriverDataProcessor(PATH);
    proccessor2 = new DriverDataProcessor(PATH1);
  }

  @Test
  void getData() {
    assertEquals(1, proccessor1.getData().size());
  }

  @Test
  void testEquals1() {
    assertEquals(proccessor1, proccessor1);
  }

  @Test
  void testEquals2() {
    assertNotEquals(null, proccessor1);
  }

  @Test
  void testEquals3() {
    assertNotEquals("e", proccessor1);
  }

  @Test
  void testEquals4() {
    assertNotEquals(proccessor2, proccessor1);
  }

  @Test
  void testHashCode() {
    assertEquals(proccessor1.hashCode(), proccessor1.hashCode());
    assertNotEquals(proccessor1.hashCode(), proccessor2.hashCode());
  }

  @Test
  void testToString() {
    String expected = "DriverDataProcessor{data=[{lastName=Doe, stateOfIssue=CA, countryOfIssue=US, vehicleViolation=NON-MOVING, offenderFirstName=Bob, driverViolation=MOVING, vehiclePlateNumber=ABC123, vehicleYear=2010, vehicleModel=Camry, vehicleViolationDate=2020-01-01, licenseNumber=123456789, vehicleColor=Black, dateOfExpiry=2030-01-01, address=123 Main St, vehicleCrash=WITHOUT_BODILY_INJURIES, vehicleCrashDate=2020-01-01, dateOfBirth=1990-01-01, dateOfIssue=2020-01-01, vehicleMake=Toyota, driverViolationDate=2020-01-01, offenderLastName=Smith, firstName=John, ownerOrInsured=owner, driverViolationDetail=DISTRACTED_DRIVING, insuranceDateOfExpiry=2024-01-01}]}";
    assertEquals(expected, proccessor1.toString());
  }
}