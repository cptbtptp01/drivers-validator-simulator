package problem2.ProspectiveDriversValidatorAndSimulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {

  private Name name;
  private Name name2;
  private Name name3;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
    name2 = new Name("John", "Doe");
    name3 = new Name("Jane", "Doe");
  }

  @Test
  void getFirstName() {
    assertEquals("John", name.getFirstName());
  }

  @Test
  void getLastName() {
    assertEquals("Doe", name.getLastName());
  }

  @Test
  void testEquals() {
    assertEquals(name, name2);
    assertNotEquals(name, name3);
    assertNotEquals(null, name);
    assertNotEquals("John Doe", name);
  }

  @Test
  void testHashCode() {
    assertEquals(name.hashCode(), name2.hashCode());
    assertNotEquals(name.hashCode(), name3.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Doe, John", name.toString());
  }
}