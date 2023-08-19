package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.CrashType;

class CrashTest {

  private Crash crash1;
  private Crash crash2;

  @BeforeEach
  void setUp() {
    crash1 = new Crash(LocalDate.MIN, CrashType.FENDER_BENDER, new Name("s", "n"));
    crash2 = new Crash(LocalDate.MIN, CrashType.FENDER_BENDER, new Name("s", "m"));
  }

  @Test
  void getDate() {
    assertEquals(LocalDate.MIN, crash1.getDate());
  }

  @Test
  void getCrashType() {
    assertEquals(CrashType.FENDER_BENDER, crash1.getCrashType());
  }

  @Test
  void getOffenderName() {
    assertEquals(new Name("s", "n"), crash1.getOffenderName());
  }

  @Test
  void testEquals() {
    assertEquals(crash1, crash1);
    assertNotEquals(null, crash1);
    assertNotEquals(crash1, new Object());
    assertNotEquals(crash1, crash2);
  }

  @Test
  void testHashCode() {
    assertEquals(crash1.hashCode(), crash1.hashCode());
    assertNotEquals(crash1.hashCode(), crash2.hashCode());
  }

  @Test
  void testToString() {
    String e = "Fender Bender on -999999999-01-01 involving n, s";
    assertEquals(e, crash1.toString());
  }
}