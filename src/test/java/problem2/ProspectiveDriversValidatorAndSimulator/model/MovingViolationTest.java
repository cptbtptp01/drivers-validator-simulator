package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

class MovingViolationTest {

  private MovingViolation movingViolation;
  private MovingViolation movingViolation1;

  @BeforeEach
  void setUp() {
    movingViolation = new MovingViolation(LocalDate.MIN, ViolationType.MOVING,
        MovingViolationType.SPEEDING);
    movingViolation1 = new MovingViolation(LocalDate.MIN, ViolationType.MOVING,
        MovingViolationType.OTHER);
  }

  @Test
  void getMovingViolationType() {
    assertEquals(MovingViolationType.SPEEDING, movingViolation.getMovingViolationType());
  }

  @Test
  void testEquals() {
    assertEquals(movingViolation, movingViolation);
    assertNotEquals(null, movingViolation);
    assertNotEquals("string", movingViolation);
    assertNotEquals(movingViolation, movingViolation1);
  }

  @Test
  void testHashCode() {
    assertEquals(movingViolation.hashCode(), movingViolation.hashCode());
    assertNotEquals(movingViolation.hashCode(), movingViolation1.hashCode());
  }

  @Test
  void testToString() {
    String e = "Speeding on -999999999-01-01";
    assertEquals(e, movingViolation.toString());
  }
}