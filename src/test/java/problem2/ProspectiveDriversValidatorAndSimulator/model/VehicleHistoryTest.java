package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.CrashType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.NonMovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

class VehicleHistoryTest {

  private VehicleHistory vh;
  private VehicleHistory vh2;
  private VehicleHistory vh3;

  @BeforeEach
  void setUp() {
    Crash c = new Crash(LocalDate.MIN, CrashType.FENDER_BENDER, new Name("s", "n"));
    NonMovingViolation nonMovingViolation = new NonMovingViolation(LocalDate.MIN,
        ViolationType.NON_MOVING,
        NonMovingViolationType.OTHER);
    vh = new VehicleHistory();
    vh.addViolation(nonMovingViolation);
    vh.addCrash(c);
    vh2 = new VehicleHistory();
    vh2.addViolation(nonMovingViolation);
    vh2.addCrash(c);
    vh3 = new VehicleHistory();
  }

  @Test
  void getViolations() {
    assertEquals(1, vh.getViolations().size());
  }

  @Test
  void getCrashes() {
    assertEquals(1, vh.getCrashes().size());
  }

  @Test
  void getMostRecentViolationOrCrash() {
    assertEquals(LocalDate.MIN, vh.getMostRecentViolationOrCrash());
  }

  @Test
  void addViolation() {
    vh3.addViolation(new NonMovingViolation(LocalDate.MIN, ViolationType.NON_MOVING,
        NonMovingViolationType.OTHER));
    assertEquals(1, vh3.getViolations().size());
  }

  @Test
  void addCrash() {
    vh3.addCrash(new Crash(LocalDate.MIN, CrashType.FENDER_BENDER, new Name("s", "n")));
    assertEquals(1, vh3.getCrashes().size());
  }

  @Test
  void testEquals() {
    assertEquals(vh, vh);
    assertNotEquals(null, vh);
    assertNotEquals("String", vh);
    assertEquals(vh, vh2);
    assertNotEquals(vh, vh3);
    assertNotEquals(vh, new Object());
  }

  @Test
  void testHashCode() {
    assertEquals(vh.hashCode(), vh.hashCode());
    assertEquals(vh.hashCode(), vh2.hashCode());
    assertNotEquals(vh.hashCode(), vh3.hashCode());
  }

  @Test
  void testToString() {
    String e = "\n" + "Other on -999999999-01-01" + "\n"
        + "Fender Bender on -999999999-01-01 involving n, s";
    assertEquals(e, vh.toString());
  }
}