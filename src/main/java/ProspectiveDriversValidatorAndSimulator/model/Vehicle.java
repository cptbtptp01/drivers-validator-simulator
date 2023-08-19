package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.util.Objects;

/**
 * This class represents a Vehicle object. It defines all the details of a Vehicle.
 */
public class Vehicle {

  String make;
  String model;
  Integer year;
  String vehicleColor;
  String vehicleId;
  Name ownerName;
  Insurance insurance;
  VehicleHistory vehicleHistories;

  /**
   * Constructs a new Vehicle object.
   */
  Vehicle() {
  }

  /**
   * Returns a vehicle's make.
   *
   * @return a vehicle's make.
   */
  public String getMake() {
    return make;
  }

  /**
   * Returns a vehicle's model.
   *
   * @return a vehicle's model.
   */
  public String getModel() {
    return model;
  }

  /**
   * Returns a vehicle's year.
   *
   * @return a vehicle's year.
   */
  public Integer getYear() {
    return year;
  }

  /**
   * Returns a vehicle's color.
   *
   * @return a vehicle's color.
   */
  public String getVehicleColor() {
    return vehicleColor;
  }

  /**
   * Returns a vehicle's ID.
   *
   * @return a vehicle's ID.
   */
  public String getVehicleId() {
    return vehicleId;
  }

  /**
   * Returns a vehicle's owner's name.
   *
   * @return a vehicle's owner's name.
   */
  public Name getOwnerName() {
    return ownerName;
  }

  /**
   * Returns a vehicle's insurance.
   *
   * @return a vehicle's insurance.
   */
  public Insurance getInsurance() {
    return insurance;
  }

  /**
   * Returns a vehicle's history.
   *
   * @return a vehicle's history.
   */
  public VehicleHistory getVehicleHistories() {
    return vehicleHistories;
  }

  /**
   * Sets a vehicle's name
   *
   * @param ownerName a vehicle's owner name
   */
  public void setOwnerName(Name ownerName) {
    this.ownerName = ownerName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(make, vehicle.make) && Objects.equals(model,
        vehicle.model) && Objects.equals(year, vehicle.year) && Objects.equals(
        vehicleColor, vehicle.vehicleColor) && Objects.equals(vehicleId, vehicle.vehicleId)
        && Objects.equals(ownerName, vehicle.ownerName) && Objects.equals(
        insurance, vehicle.insurance) && Objects.equals(vehicleHistories,
        vehicle.vehicleHistories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(make, model, year, vehicleColor, vehicleId, ownerName,
        insurance, vehicleHistories);
  }

  @Override
  public String toString() {
    return year + " " + vehicleColor + " " + make + " " + model + ", " + vehicleId;
  }
}
