package problem2.ProspectiveDriversValidatorAndSimulator.enums;

public enum NonMovingViolationType {
  PARKING_VIOLATION("Parking Violation"),
  PAPERWORK_ISSUES("Paperwork Issues"),
  PROBLEMS_WITH_THE_VEHICLE("Problems with the Vehicle"),
  OTHER("Other");
  private final String violation;

  NonMovingViolationType(String violation) {
    this.violation = violation;
  }

  public String getViolation() {
    return violation;
  }
}
