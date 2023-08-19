package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.Objects;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.ViolationType;

/**
 * This class represents a violation of a driver.
 */
public abstract class Violation {

  private final LocalDate date;
  private final ViolationType type;

  /**
   * Constructs a new Violation object and initializes it with the given date and type.
   *
   * @param date - the date of the violation
   * @param type - the type of the violation
   */
  public Violation(LocalDate date, ViolationType type) {
    this.date = date;
    this.type = type;
  }

  /**
   * Returns the date of the violation.
   *
   * @return the date of the violation
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Returns the type of the violation.
   *
   * @return the type of the violation
   */
  public ViolationType getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Violation violation = (Violation) o;
    return Objects.equals(date, violation.date) && type == violation.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, type);
  }

  @Override
  public String toString() {
    return "Violation{" +
        "date=" + date +
        ", type=" + type +
        '}';
  }
}
