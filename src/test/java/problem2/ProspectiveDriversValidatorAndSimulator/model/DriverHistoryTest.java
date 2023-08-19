package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.NonMovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

class DriverHistoryTest {

  private DriverHistory driverHistory;
  private DriverHistory driverHistory2;
  private NonMovingViolation nonMovingViolation;
  private MovingViolation movingViolation;

  @BeforeEach
  void setUp() {
    driverHistory = new DriverHistory();
    driverHistory2 = new DriverHistory();
    nonMovingViolation = new NonMovingViolation(LocalDate.MIN, ViolationType.NON_MOVING,
        NonMovingViolationType.OTHER);
    movingViolation = new MovingViolation(LocalDate.MIN, ViolationType.MOVING,
        MovingViolationType.OTHER);
  }

  @Test
  void getViolations() {
    assertEquals(0, driverHistory.getViolations().size());
  }

  @Test
  void addViolation() {
    driverHistory.addViolation(nonMovingViolation);
    assertEquals(1, driverHistory.getViolations().size());
    assertEquals(nonMovingViolation, driverHistory.getViolations().get(0));
  }

  @Test
  void hasViolations() {
    assertFalse(driverHistory.hasViolations());
    driverHistory.addViolation(nonMovingViolation);
    assertTrue(driverHistory.hasViolations());
  }

  @Test
  void testEquals() {
    assertEquals(driverHistory, driverHistory);
    assertNotEquals(null, driverHistory);
    assertNotEquals(driverHistory, new Object());
    assertEquals(driverHistory, driverHistory2);
    driverHistory.addViolation(nonMovingViolation);
    assertNotEquals(driverHistory, driverHistory2);
    driverHistory2.addViolation(nonMovingViolation);
    assertEquals(driverHistory, driverHistory2);
    driverHistory.addViolation(movingViolation);
    assertNotEquals(driverHistory, driverHistory2);
    driverHistory2.addViolation(movingViolation);
    assertEquals(driverHistory, driverHistory2);
  }

  @Test
  void testHashCode() {
    assertEquals(driverHistory.hashCode(), driverHistory2.hashCode());
    driverHistory.addViolation(nonMovingViolation);
    assertNotEquals(driverHistory.hashCode(), driverHistory2.hashCode());
    driverHistory2.addViolation(nonMovingViolation);
    assertEquals(driverHistory.hashCode(), driverHistory2.hashCode());
    driverHistory.addViolation(movingViolation);
    assertNotEquals(driverHistory.hashCode(), driverHistory2.hashCode());
    driverHistory2.addViolation(movingViolation);
    assertEquals(driverHistory.hashCode(), driverHistory2.hashCode());
  }

  @Test
  void testToString() {
    String e = "\n\tDriving violations:\n";
    assertEquals(e, driverHistory.toString());
  }
}