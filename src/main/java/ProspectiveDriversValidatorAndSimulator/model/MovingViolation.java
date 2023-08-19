package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.Objects;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.MovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

/**
 * This class represents a moving violation.
 */
public class MovingViolation extends Violation {

  private final MovingViolationType movingViolationType;

  /**
   * Constructs a new MovingViolation object.
   *
   * @param date                - the date of the violation
   * @param type                - the type of the violation
   * @param movingViolationType - the moving violation type
   */
  public MovingViolation(LocalDate date, ViolationType type,
      MovingViolationType movingViolationType) {
    super(date, type);
    this.movingViolationType = movingViolationType;
  }

  /**
   * Returns the moving violation type.
   *
   * @return - the moving violation type
   */
  public MovingViolationType getMovingViolationType() {
    return movingViolationType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    MovingViolation that = (MovingViolation) o;
    return movingViolationType == that.movingViolationType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), movingViolationType);
  }

  @Override
  public String toString() {
    return movingViolationType.getViolation() + " on " + super.getDate();
  }
}
