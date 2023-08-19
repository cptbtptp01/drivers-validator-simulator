package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.Objects;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.NonMovingViolationType;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

/**
 * This class represents a non-moving violation.
 */
public class NonMovingViolation extends Violation {

  private final NonMovingViolationType nonMovingViolationType;

  /**
   * Constructs a new NonMovingViolation object and initializes it with the given date, type and
   * nonMovingViolationType.
   *
   * @param date                   - the date of the violation
   * @param type                   - the type of the violation
   * @param nonMovingViolationType - the non-moving violation type
   */
  public NonMovingViolation(LocalDate date, ViolationType type,
      NonMovingViolationType nonMovingViolationType) {
    super(date, type);
    this.nonMovingViolationType = nonMovingViolationType;
  }

  /**
   * Returns the non-moving violation type.
   *
   * @return the non-moving violation type
   */
  public NonMovingViolationType getNonMovingViolationType() {
    return nonMovingViolationType;
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
    NonMovingViolation that = (NonMovingViolation) o;
    return nonMovingViolationType == that.nonMovingViolationType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nonMovingViolationType);
  }

  @Override
  public String toString() {
    return nonMovingViolationType.getViolation() + " on " + super.getDate();
  }
}
