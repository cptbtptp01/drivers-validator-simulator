package problem2.ProspectiveDriversValidatorAndSimulator.model;

public class VehiclePool implements IVehiclePool {

  /**
   * add vehicle to the pool
   *
   * @param vehicle vehicle to be added
   */
  @Override
  public void addVehicle(Vehicle vehicle) {

  }

  /**
   * remove vehicle from the pool
   *
   * @param vehicle vehicle to be removed
   */
  @Override
  public void removeVehicle(Vehicle vehicle) {

  }

  /**
   * get vehicle from the pool based on vehicle id
   *
   * @param vehicleId vehicle id
   * @return vehicle
   */
  @Override
  public Vehicle getVehicle(String vehicleId) {
    return null;
  }

  /**
   * check if vehicle is available
   *
   * @param vehicle vehicle
   * @return true if vehicle is available, false otherwise
   */
  @Override
  public boolean isVehicleInUse(Vehicle vehicle) {
    return false;
  }

  /**
   * check if vehicle is available
   *
   * @param id vehicle id
   * @return true if vehicle is available, false otherwise
   */
  @Override
  public boolean isVehicleInUse(String id) {
    return false;
  }

  /**
   * check if vehicle is available
   *
   * @param driver  driver
   * @param vehicle vehicle
   * @return true if vehicle is available, false otherwise
   */
  @Override
  public boolean isVehicleInUseByDriver(Driver driver, Vehicle vehicle) {
    return false;
  }

  /**
   * check if vehicle is available
   *
   * @param driver driver
   * @param id     vehicle id
   * @return true if vehicle is available, false otherwise
   */
  @Override
  public boolean isVehicleInUseByDriver(Driver driver, String id) {
    return false;
  }

  /**
   * get vehicle count
   *
   * @return vehicle count
   */
  @Override
  public int getVehicleCount() {
    return 0;
  }

  /**
   * clear the pool, use with caution
   */
  @Override
  public void clear() {

  }
}
