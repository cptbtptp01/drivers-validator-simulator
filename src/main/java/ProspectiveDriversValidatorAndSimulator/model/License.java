package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class representing a driver's license.
 */
public class License {

  private final String number;
  private final Name name;
  private final String address;
  private final LocalDate dateOfBirth;
  private final String countryOfIssue;
  private final String stateOfIssue;
  private final LocalDate dateOfIssue;
  private final LocalDate dateOfExpiry;

  /**
   * Constructs a new License object and initializes it with the given parameters.
   *
   * @param number         - the license number
   * @param name           - the name of the license holder
   * @param address        - the address of the license holder
   * @param dateOfBirth    - the date of birth of the license holder
   * @param countryOfIssue - the country of issue of the license
   * @param stateOfIssue   - the state of issue of the license
   * @param dateOfIssue    - the date of issue of the license
   * @param dateOfExpiry   - the date of expiry of the license
   */
  public License(String number, Name name, String address,
      LocalDate dateOfBirth, String countryOfIssue, String stateOfIssue, LocalDate dateOfIssue,
      LocalDate dateOfExpiry) {
    this.number = number;
    this.name = name;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.countryOfIssue = countryOfIssue;
    this.stateOfIssue = stateOfIssue;
    this.dateOfIssue = dateOfIssue;
    this.dateOfExpiry = dateOfExpiry;
  }

  /**
   * Returns the license number.
   *
   * @return the license number
   */
  public String getNumber() {
    return number;
  }

  /**
   * Returns the name of the license holder.
   *
   * @return the name of the license holder
   */
  public Name getName() {
    return name;
  }

  /**
   * Returns the address of the license holder.
   *
   * @return the address of the license holder
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the date of birth of the license holder.
   *
   * @return the date of birth of the license holder
   */
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Returns the country of issue of the license.
   *
   * @return the country of issue of the license
   */
  public String getCountryOfIssue() {
    return countryOfIssue;
  }

  /**
   * Returns the state of issue of the license.
   *
   * @return the state of issue of the license
   */
  public String getStateOfIssue() {
    return stateOfIssue;
  }

  /**
   * Returns the date of issue of the license.
   *
   * @return the date of issue of the license
   */
  public LocalDate getDateOfIssue() {
    return dateOfIssue;
  }

  /**
   * Returns the date of expiry of the license.
   *
   * @return the date of expiry of the license
   */
  public LocalDate getDateOfExpiry() {
    return dateOfExpiry;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    License license = (License) o;
    return Objects.equals(number, license.number) && Objects.equals(name,
        license.name) && Objects.equals(address, license.address)
        && Objects.equals(dateOfBirth, license.dateOfBirth) && Objects.equals(
        countryOfIssue, license.countryOfIssue) && Objects.equals(stateOfIssue,
        license.stateOfIssue) && Objects.equals(dateOfIssue, license.dateOfIssue)
        && Objects.equals(dateOfExpiry, license.dateOfExpiry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, name, address, dateOfBirth, countryOfIssue, stateOfIssue,
        dateOfIssue,
        dateOfExpiry);
  }

  @Override
  public String toString() {
    return number + ", " + name + ", " + address + ", " + dateOfBirth + ", " + countryOfIssue
        + ", " + stateOfIssue + ", " + dateOfIssue + ", " + dateOfExpiry;
  }
}
