package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents a driver's history of driving violations.
 */
public class DriverHistory {

  private final List<Violation> violations;

  /**
   * Constructs a driver's history of driving violations.
   */
  public DriverHistory() {
    this.violations = new ArrayList<>();
  }

  /**
   * Returns the list of violations of this driver's history.
   *
   * @return the list of violations of this driver's history
   */
  public List<Violation> getViolations() {
    return violations;
  }

  /**
   * Adds the specified violation to this driver's history.
   *
   * @param violation - the violation to be added
   */
  public void addViolation(Violation violation) {
    violations.add(violation);
  }

  /**
   * Returns true if this driver's history has violations, false otherwise.
   *
   * @return true if this driver's history has violations, false otherwise
   */
  public boolean hasViolations() {
    return !violations.isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverHistory that = (DriverHistory) o;
    return Objects.equals(violations, that.violations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations);
  }

  @Override
  public String toString() {
    return "\n\tDriving violations:\n" + violations.stream().map(v -> "\t\t" + v.toString())
        .collect(Collectors.joining("\n"));
  }
}
