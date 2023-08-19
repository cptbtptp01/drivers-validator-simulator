package problem2.ProspectiveDriversValidatorAndSimulator.model;

/**
 * This class represents a Vehicle builder.
 */
public class VehicleBuilder {

  private final String make;
  private final String model;
  private final Integer year;
  private String vehicleColor;
  private String vehicleId;
  private Name ownerName;
  private Insurance insurance;
  private VehicleHistory vehicleHistories;

  /**
   * Constructs a VehicleBuilder with a make, model, and year
   *
   * @param make  - make of the vehicle
   * @param model - model of the vehicle
   * @param year  - year of the vehicle
   */
  public VehicleBuilder(String make, String model, Integer year) {
    this.make = make;
    this.model = model;
    this.year = year;
  }

  /**
   * Sets the vehicle color of the vehicle
   *
   * @param vehicleColor - vehicle color of the vehicle
   * @return - VehicleBuilder with vehicle color
   */
  public VehicleBuilder withVehicleColor(String vehicleColor) {
    this.vehicleColor = vehicleColor;
    return this;
  }

  /**
   * Sets the vehicle id of the vehicle
   *
   * @param vehicleId - vehicle id of the vehicle
   * @return - VehicleBuilder with vehicle id
   */
  public VehicleBuilder withVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
    return this;
  }

  /**
   * Sets the owner name of the vehicle
   *
   * @param ownerName - owner name of the vehicle
   * @return - VehicleBuilder with owner name
   */
  public VehicleBuilder withOwnerName(Name ownerName) {
    this.ownerName = ownerName;
    return this;
  }

  /**
   * Sets the insurance of the vehicle
   *
   * @param insurance - insurance of the vehicle
   * @return - VehicleBuilder with insurance
   */
  public VehicleBuilder withInsurance(Insurance insurance) {
    this.insurance = insurance;
    return this;
  }

  /**
   * Sets the vehicle histories of the vehicle
   *
   * @param vehicleHistories - vehicle histories of the vehicle
   * @return - VehicleBuilder with vehicle histories
   */
  public VehicleBuilder withVehicleHistories(VehicleHistory vehicleHistories) {
    this.vehicleHistories = vehicleHistories;
    return this;
  }

  /**
   * Builds a Vehicle
   *
   * @return - Vehicle
   */
  public Vehicle build() {
    Vehicle vehicle = new Vehicle();
    vehicle.make = make;
    vehicle.model = model;
    vehicle.year = year;
    vehicle.vehicleColor = vehicleColor;
    vehicle.vehicleId = vehicleId;
    vehicle.ownerName = ownerName;
    vehicle.insurance = insurance != null ? this.insurance : new Insurance();
    vehicle.vehicleHistories =
        vehicleHistories != null ? this.vehicleHistories : new VehicleHistory();
    return vehicle;
  }
}
