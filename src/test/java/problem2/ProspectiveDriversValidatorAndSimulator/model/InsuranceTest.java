package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceTest {

  private Insurance insurance;
  private Insurance insurance2;

  @BeforeEach
  void setUp() {
    Name name = new Name("John", "Doe");
    Name name2 = new Name("Jane", "Doe");
    insurance = new Insurance(LocalDate.of(2019, 1, 1), name, null);
    insurance2 = new Insurance(LocalDate.of(2019, 1, 1), name2, null);
  }

  @Test
  void getDateOfExpiry() {
    assertEquals(LocalDate.of(2019, 1, 1), insurance.getDateOfExpiry());
  }

  @Test
  void getOwnerName() {
    assertEquals(new Name("John", "Doe"), insurance.getOwnerName());
  }

  @Test
  void getInsuredDrivers() {
    assertEquals(0, insurance.getInsuredDrivers().size());
  }

  @Test
  void getInsuredDrivers2() {
    insurance.getInsuredDrivers().add(new Name("Jane", "Doe"));
    assertEquals(1, insurance.getInsuredDrivers().size());
  }

  @Test
  void testEquals() {
    Insurance insurance3 = new Insurance(LocalDate.of(2019, 1, 1), new Name("Jane", "Doe"), null);
    assertEquals(insurance2, insurance3);
    assertNotEquals(insurance, null);
    assertNotEquals(insurance, new Object());
    assertNotEquals(insurance, insurance2);
  }

  @Test
  void testHashCode() {
    assertEquals(insurance.hashCode(), insurance.hashCode());
    assertNotEquals(insurance.hashCode(), insurance2.hashCode());
  }

  @Test
  void testToString() {
    String e = "Insurance{dateOfExpiry=2019-01-01, ownerName=Doe, John, insuredDrivers=[]}";
    assertEquals(e, insurance.toString());
  }
}