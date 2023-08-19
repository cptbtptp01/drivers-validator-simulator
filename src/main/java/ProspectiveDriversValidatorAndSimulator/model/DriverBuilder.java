package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Driver builder.
 */
public class DriverBuilder {

  private final Name name;
  private LocalDate dateOfBirth;
  private License license;
  private List<Vehicle> vehicles;
  private DriverHistory driverHistories;

  /**
   * Constructs a DriverBuilder with a name
   *
   * @param name - name of the driver
   */
  public DriverBuilder(Name name) {
    this.name = name;
  }

  /**
   * Sets the date of birth of the driver
   *
   * @param dateOfBirth - date of birth of the driver
   * @return - DriverBuilder with date of birth
   */
  public DriverBuilder withDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Sets the license of the driver
   *
   * @param license - license of the driver
   * @return - DriverBuilder with license
   */
  public DriverBuilder withLicense(License license) {
    this.license = license;
    return this;
  }

  /**
   * Sets the vehicles of the driver
   *
   * @param vehicles - vehicles of the driver
   * @return - DriverBuilder with vehicles
   */
  public DriverBuilder withVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
    return this;
  }

  /**
   * Sets the driver histories of the driver
   *
   * @param driverHistories - driver histories of the driver
   * @return - DriverBuilder with driver histories
   */
  public DriverBuilder withDriverHistories(DriverHistory driverHistories) {
    this.driverHistories = driverHistories;
    return this;
  }

  /**
   * Builds a driver
   *
   * @return - a driver
   */
  public Driver build() {
    Driver driver = new Driver();
    driver.name = name;
    driver.dateOfBirth = dateOfBirth;
    driver.license = license;
    driver.vehicles = vehicles != null ? this.vehicles : new ArrayList<>();
    driver.driverHistories = driverHistories != null ? this.driverHistories : new DriverHistory();
    return driver;
  }
}
