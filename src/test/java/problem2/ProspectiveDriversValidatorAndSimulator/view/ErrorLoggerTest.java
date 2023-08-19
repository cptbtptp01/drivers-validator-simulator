package problem2.ProspectiveDriversValidatorAndSimulator.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorLoggerTest {

  private ErrorLogger e1;
  private ErrorLogger e2;
  private ErrorLogger e3;

  @BeforeEach
  void setUp() {
    e1 = new ErrorLogger();
  }

  @Test
  void log() {
    e1.log("test");
    assertFalse(e1.isEmpty());
  }

  @Test
  void isEmpty() {
    assertTrue(e1.isEmpty());
  }

  @Test
  void testEquals() {
    e2 = new ErrorLogger();
    e3 = new ErrorLogger();
    e1.log("test");
    e2.log("test");
    e3.log("test1");
    assertEquals(e1, e2);
    assertNotEquals(e1, e3);
    assertNotEquals(e1, null);
    assertNotEquals(e1, new Object());
  }

  @Test
  void testHashCode() {
    e2 = new ErrorLogger();
    e3 = new ErrorLogger();
    e1.log("test");
    e2.log("test");
    e3.log("test1");
    assertEquals(e1.hashCode(), e2.hashCode());
    assertNotEquals(e1.hashCode(), e3.hashCode());
  }

  @Test
  void testToString() {
    e1.log("test");
    assertEquals("test\n", e1.toString());
  }
}