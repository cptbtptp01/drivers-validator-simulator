package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LicenseTest {
  private License license;

  @BeforeEach
  void setUp() {
    license = new License("1234567890", new Name("John", "Doe"), "1234 Main St, Seattle, WA 98101",
        LocalDate.MIN, "USA", "WA", LocalDate.MIN, LocalDate.MIN);
  }

  @Test
  void getNumber() {
    assertEquals("1234567890", license.getNumber());
  }

  @Test
  void getName() {
    assertEquals(new Name("John", "Doe"), license.getName());
  }

  @Test
  void getAddress() {
    assertEquals("1234 Main St, Seattle, WA 98101", license.getAddress());
  }

  @Test
  void getDateOfBirth() {
    assertEquals(LocalDate.MIN, license.getDateOfBirth());
  }

  @Test
  void getCountryOfIssue() {
    assertEquals("USA", license.getCountryOfIssue());
  }

  @Test
  void getStateOfIssue() {
    assertEquals("WA", license.getStateOfIssue());
  }

  @Test
  void getDateOfIssue() {
    assertEquals(LocalDate.MIN, license.getDateOfIssue());
  }

  @Test
  void getDateOfExpiry() {
    assertEquals(LocalDate.MIN, license.getDateOfExpiry());
  }

  @Test
  void testEquals() {
    License l = new License("1234567890", new Name("Joh", "Doe"), "1234 Main St, Seattle, WA 98101",
        LocalDate.MIN, "USA", "WA", LocalDate.MIN, LocalDate.MIN);
    License l1 = new License("1234567890", new Name("John", "Doe"), "1234 Main St, Seattle, WA 98101",
        LocalDate.MIN, "USA", "WA", LocalDate.MIN, LocalDate.MIN);
    assertNotEquals(l, license);
    assertEquals(l1, license);
    assertNotEquals(l, null);
    assertNotEquals(l, new Object());
  }

  @Test
  void testHashCode() {
    License l = new License("1234567890", new Name("Joh", "Doe"), "1234 Main St, Seattle, WA 98101",
        LocalDate.MIN, "USA", "WA", LocalDate.MIN, LocalDate.MIN);
    assertNotEquals(l.hashCode(), license.hashCode());
  }

  @Test
  void testToString() {
    String e = "1234567890, Doe, John, 1234 Main St, Seattle, WA 98101, -999999999-01-01, USA, WA, -999999999-01-01, -999999999-01-01";
    assertEquals(e, license.toString());
  }
}