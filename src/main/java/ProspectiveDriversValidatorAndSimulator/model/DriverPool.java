package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import problem2.ProspectiveDriversValidatorAndSimulator.view.DriverView;

/**
 * Represents a driver pool.
 */
public class DriverPool implements IDriverPool {

  private static final DriverPool instance = new DriverPool();
  private final Set<Driver> drivers;

  /**
   * Constructs a driver pool.
   */
  private DriverPool() {
    this.drivers = new HashSet<>();
  }

  /**
   * Returns the instance of the driver pool.
   * @return the instance of the driver pool
   */
  public static DriverPool getInstance() {
    return instance;
  }

  /**
   * Adds a driver to the driver pool.
   *
   * @param driver - the driver to be added
   */
  @Override
  public void addDriver(Driver driver) {
    this.drivers.add(driver);
  }

  /**
   * Removes a driver from the driver pool.
   *
   * @param driver - the driver to be removed
   */
  @Override
  public void removeDriver(Driver driver) {
    if (this.drivers.contains(driver)) {
      this.drivers.remove(driver);
    } else {
      throw new IllegalArgumentException(DriverView.getDriverNotFoundMessage());
    }
  }

  /**
   * Returns the set of drivers in the driver pool.
   *
   * @return the set of drivers in the driver pool
   */
  @Override
  public Set<Driver> getAllDrivers() {
    return this.drivers;
  }

  /**
   * Returns the set of drivers in the driver pool with the given last name.
   *
   * @param lastName - the last name of the drivers to be returned
   * @return the set of drivers in the driver pool with the given last name
   */
  @Override
  public Set<Driver> getDriversByLastName(String lastName) {
    Set<Driver> driversByLastName = new HashSet<>();
    this.drivers.stream().filter(driver -> driver.getName().getLastName().equals(lastName))
        .forEach(driversByLastName::add);
    return driversByLastName;
  }

  /**
   * Returns the driver with the given name.
   *
   * @param name - the name of the driver to be returned
   * @return the driver with the given name
   */
  @Override
  public Driver getDriverByName(Name name) {
    return this.drivers.stream().filter(driver -> driver.getName().equals(name)).findFirst()
        .orElse(null);
  }

  /**
   * Returns the number of drivers in the driver pool.
   *
   * @return the number of drivers in the driver pool
   */
  @Override
  public int getNumberOfDrivers() {
    return this.drivers.size();
  }

  /**
   * Resets the driver pool.
   */
  @Override
  public void reset() {
    this.drivers.clear();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverPool that = (DriverPool) o;
    return Objects.equals(drivers, that.drivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(drivers);
  }

  @Override
  public String toString() {
    return "DriverPool{" +
        "drivers=" + drivers +
        '}';
  }
}
