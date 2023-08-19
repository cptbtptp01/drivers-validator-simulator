package problem2.ProspectiveDriversValidatorAndSimulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a logger for error messages.
 */
public class ErrorLogger {

  private final List<String> log;

  /**
   * Constructs a new ErrorLogger object and initializes it to be empty.
   */
  public ErrorLogger() {
    this.log = new ArrayList<>();
  }

  /**
   * Adds the given event to the log.
   *
   * @param event - the event to be added to the log
   */
  public void log(String event) {
    this.log.add(event);
  }

  /**
   * Returns true if the log is empty, false otherwise.
   *
   * @return true if the log is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.log.size() == 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorLogger that = (ErrorLogger) o;
    return Objects.equals(log, that.log);
  }

  @Override
  public int hashCode() {
    return Objects.hash(log);
  }

  @Override
  public String toString() {
    if (this.log.isEmpty()) {
      return "Empty log";
    }
    String out = "";
    for (String entry : this.log) {
      out += entry + System.lineSeparator();
    }
    return out;
  }
}
