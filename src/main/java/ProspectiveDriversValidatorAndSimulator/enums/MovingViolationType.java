package problem2.ProspectiveDriversValidatorAndSimulator.enums;

public enum MovingViolationType {
  DISTRACTED_DRIVING("Distracted Driving"),
  DRIVING_UNDER_INFLUENCE("Driving Under Influence"),
  SPEEDING("Speeding"),
  RECKLESS_DRIVING("Reckless Driving"),
  FAILURE_TO_RESPECT_TRAFFIC_SIGNS("Failure to Respect Traffic Signs"),
  DRIVING_WITHOUT_A_VALID_LICENSE_AND_OR_INSURANCE(
      "Driving Without a Valid License and/or Insurance"),
  OTHER("Other");

  private final String violation;

  MovingViolationType(String violation) {
    this.violation = violation;
  }

  public String getViolation() {
    return violation;
  }
}
