package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.util.Objects;

/**
 * This class represents a name of a person.
 */
public class Name {

  private final String firstName;
  private final String lastName;

  /**
   * Constructs a new Name object with the given first name and last name.
   *
   * @param firstName - first name
   * @param lastName  - last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the person.
   *
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the person.
   *
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) && Objects.equals(lastName,
        name.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return lastName + ", " + firstName;
  }
}
