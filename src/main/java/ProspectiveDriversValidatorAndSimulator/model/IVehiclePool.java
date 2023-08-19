package problem2.ProspectiveDriversValidatorAndSimulator.model;

/**
 * IVehiclePool interface
 */
public interface IVehiclePool {

  /**
   * add vehicle to the pool
   *
   * @param vehicle vehicle to be added
   */
  void addVehicle(Vehicle vehicle);

  /**
   * remove vehicle from the pool
   *
   * @param vehicle vehicle to be removed
   */
  void removeVehicle(Vehicle vehicle);

  /**
   * get vehicle from the pool based on vehicle id
   *
   * @param vehicleId vehicle id
   * @return vehicle
   */
  Vehicle getVehicle(String vehicleId);

  /**
   * check if vehicle is available
   *
   * @param vehicle vehicle
   * @return true if vehicle is available, false otherwise
   */
  boolean isVehicleInUse(Vehicle vehicle);

  /**
   * check if vehicle is available
   *
   * @param id vehicle id
   * @return true if vehicle is available, false otherwise
   */
  boolean isVehicleInUse(String id);

  /**
   * check if vehicle is available
   *
   * @param driver  driver
   * @param vehicle vehicle
   * @return true if vehicle is available, false otherwise
   */
  boolean isVehicleInUseByDriver(Driver driver, Vehicle vehicle);

  /**
   * check if vehicle is available
   *
   * @param driver driver
   * @param id     vehicle id
   * @return true if vehicle is available, false otherwise
   */
  boolean isVehicleInUseByDriver(Driver driver, String id);

  /**
   * get vehicle count
   *
   * @return vehicle count
   */
  int getVehicleCount();

  /**
   * clear the pool, use with caution
   */
  void clear();
}
