package problem2.ProspectiveDriversValidatorAndSimulator.enums;

public enum CrashType {
  FENDER_BENDER("Fender Bender"),
  WITHOUT_BODILY_INJURIES("Without Bodily Injuries"),
  INVOLVING_BODILY_INJURIES("Involving Bodily Injuries");
  private final String crash;

  CrashType(String crash) {
    this.crash = crash;
  }

  public String getCrash() {
    return crash;
  }
}
