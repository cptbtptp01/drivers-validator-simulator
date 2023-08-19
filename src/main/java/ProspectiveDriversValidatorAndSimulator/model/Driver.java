package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents a Driver.
 */
public class Driver {

  Name name;
  LocalDate dateOfBirth;
  License license;
  List<Vehicle> vehicles;
  DriverHistory driverHistories;

  /**
   * Constructs a Driver object
   */
  protected Driver() {
  }

  /**
   * Gets the name of the driver
   *
   * @return
   */
  public Name getName() {
    return name;
  }

  /**
   * Gets the date of birth of the driver
   *
   * @return
   */
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Gets the license of the driver
   *
   * @return
   */
  public License getLicense() {
    return license;
  }

  /**
   * Gets the vehicles of the driver
   *
   * @return
   */
  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  /**
   * Gets the driver histories of the driver
   *
   * @return
   */
  public DriverHistory getDriverHistories() {
    return driverHistories;
  }

  /**
   * Adds a vehicle to the driver
   *
   * @param vehicle
   */
  public void addVehicle(Vehicle vehicle) {
    vehicles.add(vehicle);
  }

  public void addViolation(Violation violation) {
    driverHistories.addViolation(violation);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Driver driver = (Driver) o;
    return Objects.equals(name, driver.name) && Objects.equals(dateOfBirth,
        driver.dateOfBirth) && Objects.equals(license, driver.license)
        && Objects.equals(vehicles, driver.vehicles) && Objects.equals(
        driverHistories, driver.driverHistories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dateOfBirth, license, vehicles, driverHistories);
  }

  @Override
  public String toString() {
    String result = name + "\n";
    if (this.vehicles.isEmpty()) {
      return result + "No vehicles registered";
    }
    String vehicles = this.vehicles.stream().map(v -> "\t" + v.toString())
        .collect(Collectors.joining("\n"));
    result += vehicles;
    if (!this.driverHistories.getViolations().isEmpty()) {
      result += this.driverHistories.toString();
    }
    return result;
  }
}
