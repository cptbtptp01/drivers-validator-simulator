package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents a vehicle's history.
 */
public class VehicleHistory {

  private final List<Violation> violations;
  private final List<Crash> crashes;

  /**
   * Constructs a new VehicleHistory object.
   */
  public VehicleHistory() {
    this.violations = new ArrayList<>();
    this.crashes = new ArrayList<>();
  }

  /**
   * Returns a list of violations.
   *
   * @return a list of violations
   */
  public List<Violation> getViolations() {
    return violations;
  }

  /**
   * Returns a list of crashes.
   *
   * @return a list of crashes
   */
  public List<Crash> getCrashes() {
    return crashes;
  }

  /**
   * Returns the most recent violation or crash date.
   *
   * @return the most recent violation or crash date.
   */
  public LocalDate getMostRecentViolationOrCrash() {
    LocalDate mostRecent = LocalDate.MIN;
    for (Violation violation : violations) {
      if (violation.getDate().isAfter(mostRecent)) {
        mostRecent = violation.getDate();
      }
    }
    for (Crash crash : crashes) {
      if (crash.getDate().isAfter(mostRecent)) {
        mostRecent = crash.getDate();
      }
    }
    return mostRecent;
  }

  /**
   * Adds a violation to the list of violations.
   *
   * @param violation the violation to be added
   */
  public void addViolation(Violation violation) {
    violations.add(violation);
  }

  /**
   * Adds a crash to the list of crashes.
   *
   * @param crash the crash to be added
   */
  public void addCrash(Crash crash) {
    crashes.add(crash);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleHistory that = (VehicleHistory) o;
    return Objects.equals(violations, that.violations) && Objects.equals(crashes,
        that.crashes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations, crashes);
  }

  @Override
  public String toString() {
    String result = "";
    if (!this.violations.isEmpty()) {
      result += this.violations.stream().map(Violation::toString).collect(
          Collectors.joining("\n"));
    }
    if (!this.crashes.isEmpty()) {
      result += "\n" + this.crashes.stream().map(Crash::toString).collect(
          Collectors.joining("\n"));
    }
    return "\n" + result;
  }
}
