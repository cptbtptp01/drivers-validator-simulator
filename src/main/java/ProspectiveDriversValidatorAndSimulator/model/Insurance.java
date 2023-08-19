package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class representing an insurance policy.
 */
public class Insurance {

  private final LocalDate dateOfExpiry;
  private final Name ownerName;
  private final List<Name> insuredDrivers;

  /**
   * Constructs an insurance policy object with the specified date of expiry, owner name and
   *
   * @param dateOfExpiry   - the date of expiry of the insurance policy
   * @param ownerName      - the name of the owner of the insurance policy
   * @param insuredDrivers - the list of names of the insured drivers
   */
  public Insurance(LocalDate dateOfExpiry, Name ownerName,
      List<Name> insuredDrivers) {
    this.dateOfExpiry = dateOfExpiry;
    this.ownerName = ownerName;
    this.insuredDrivers = insuredDrivers != null ? insuredDrivers : new ArrayList<>();
  }

  /**
   * Constructs an insurance policy object with the specified date of expiry without owner name.
   */
  protected Insurance() {
    this.dateOfExpiry = LocalDate.MIN;
    this.ownerName = null;
    this.insuredDrivers = new ArrayList<>();
  }

  /**
   * Returns the date of expiry of the insurance policy.
   *
   * @return the date of expiry of the insurance policy
   */
  public LocalDate getDateOfExpiry() {
    return dateOfExpiry;
  }

  /**
   * Returns the name of the owner of the insurance policy.
   *
   * @return the name of the owner of the insurance policy
   */
  public Name getOwnerName() {
    return ownerName;
  }

  /**
   * Returns the list of names of the insured drivers.
   *
   * @return the list of names of the insured drivers
   */
  public List<Name> getInsuredDrivers() {
    return insuredDrivers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Insurance insurance = (Insurance) o;
    return Objects.equals(dateOfExpiry, insurance.dateOfExpiry) && Objects.equals(ownerName,
        insurance.ownerName) && Objects.equals(insuredDrivers,
        insurance.insuredDrivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateOfExpiry, ownerName, insuredDrivers);
  }

  @Override
  public String toString() {
    return "Insurance{" +
        "dateOfExpiry=" + dateOfExpiry +
        ", ownerName=" + ownerName +
        ", insuredDrivers=" + insuredDrivers +
        '}';
  }
}
