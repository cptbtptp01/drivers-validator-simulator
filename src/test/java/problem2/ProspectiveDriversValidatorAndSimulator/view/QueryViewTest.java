package problem2.ProspectiveDriversValidatorAndSimulator.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QueryViewTest {

  @Test
  void getStartMessage() {
    assertEquals("\nWelcome to the Prospective Drivers Validator and Simulator!",
        QueryView.getStartMessage());
  }

  @Test
  void getErrorMessage() {
    assertEquals("A valid last name must be provided", QueryView.getErrorMessage());
  }

  @Test
  void getQueryPrompt() {
    assertEquals("\nPlease enter a last name to search for:", QueryView.getQueryPrompt());
  }

  @Test
  void getDriverNotFoundMessage() {
    assertEquals("No registered driver found with the last name Doe",
        QueryView.getDriverNotFoundMessage("Doe"));
  }
}