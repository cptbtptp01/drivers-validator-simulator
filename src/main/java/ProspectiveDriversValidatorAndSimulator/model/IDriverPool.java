package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.util.Set;

/**
 * Represents interface for a driver pool.
 */
public interface IDriverPool {

  /**
   * Adds a driver to the driver pool.
   *
   * @param driver - the driver to be added
   */
  void addDriver(Driver driver);

  /**
   * Removes a driver from the driver pool.
   *
   * @param driver - the driver to be removed
   */
  void removeDriver(Driver driver);

  /**
   * Returns the set of drivers in the driver pool.
   *
   * @return the set of drivers in the driver pool
   */
  Set<Driver> getAllDrivers();

  /**
   * Returns the set of drivers in the driver pool with the given last name.
   *
   * @param lastName - the last name of the drivers to be returned
   * @return the set of drivers in the driver pool with the given last name
   */
  Set<Driver> getDriversByLastName(String lastName);

  /**
   * Returns the driver with the given name.
   *
   * @param name - the name of the driver to be returned
   * @return the driver with the given name
   */
  Driver getDriverByName(Name name);

  /**
   * Returns the number of drivers in the driver pool.
   *
   * @return the number of drivers in the driver pool
   */
  int getNumberOfDrivers();

  /**
   * Resets the driver pool.
   */
  void reset();
}
