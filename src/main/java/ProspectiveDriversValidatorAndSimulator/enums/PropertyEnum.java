package problem2.ProspectiveDriversValidatorAndSimulator.enums;

/**
 * This enum represents the properties of the driver.
 */
public enum PropertyEnum {
  FIRST_NAME("firstName"),
  LAST_NAME("lastName"),
  DATE_OF_BIRTH("dateOfBirth"),
  DATE_OF_ISSUE("dateOfIssue"),
  DATE_OF_EXPIRY("dateOfExpiry"),
  LICENSE_NUMBER("licenseNumber"),
  ADDRESS("address"),
  COUNTRY_OF_ISSUE("countryOfIssue"),
  STATE_OF_ISSUE("stateOfIssue"),
  OWNER_OR_INSURED("ownerOrInsured"),
  INSURANCE_DATE_OF_EXPIRY("insuranceDateOfExpiry"),
  VEHICLE_CRASH("vehicleCrash"),
  OFFENDER_FIRST_NAME("offenderFirstName"),
  OFFENDER_LAST_NAME("offenderLastName"),
  VEHICLE_CRASH_DATE("vehicleCrashDate"),
  VEHICLE_VIOLATION("vehicleViolation"),
  VEHICLE_VIOLATION_DATE("vehicleViolationDate"),
  DRIVER_VIOLATION("driverViolation"),
  DRIVER_VIOLATION_DATE("driverViolationDate"),
  DRIVER_VIOLATION_DETAIL("driverViolationDetail"),
  VEHICLE_MAKE("vehicleMake"),
  VEHICLE_MODEL("vehicleModel"),
  VEHICLE_YEAR("vehicleYear"),
  VEHICLE_COLOR("vehicleColor"),
  VEHICLE_PLATE_NUMBER("vehiclePlateNumber"),
  OWNER("owner"),
  NULL("null"),
  MOVING("MOVING"),
  NON_MOVING("NON-MOVING"),
  OTHER("OTHER"),
  ;
  private final String property;

  /**
   * Constructs a PropertyEnum object
   *
   * @param property - property of the enum
   */
  PropertyEnum(String property) {
    this.property = property;
  }

  /**
   * Gets the property of the enum
   *
   * @return
   */
  public String getProperty() {
    return property;
  }
}
