package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.NonMovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

class NonMovingViolationTest {

  private NonMovingViolation nonMovingViolation;
  private NonMovingViolation nonMovingViolation2;

  @BeforeEach
  void setUp() {
    nonMovingViolation = new NonMovingViolation(LocalDate.MIN, ViolationType.NON_MOVING,
        NonMovingViolationType.OTHER);
    nonMovingViolation2 = new NonMovingViolation(LocalDate.MAX, ViolationType.NON_MOVING,
        NonMovingViolationType.PARKING_VIOLATION);
  }

  @Test
  void getNonMovingViolationType() {
    assertEquals(NonMovingViolationType.OTHER, nonMovingViolation.getNonMovingViolationType());
  }

  @Test
  void testEquals() {
    assertEquals(nonMovingViolation, nonMovingViolation);
    assertNotEquals(null, nonMovingViolation);
    assertNotEquals("String", nonMovingViolation);
    assertNotEquals(nonMovingViolation, nonMovingViolation2);
  }

  @Test
  void testHashCode() {
    assertEquals(nonMovingViolation.hashCode(), nonMovingViolation.hashCode());
    assertNotEquals(nonMovingViolation.hashCode(), nonMovingViolation2.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Other on -999999999-01-01", nonMovingViolation.toString());
  }
}