package problem2.ProspectiveDriversValidatorAndSimulator.model;

import java.time.LocalDate;
import java.util.Objects;
import problem2.ProspectiveDriversValidatorAndSimulator.enums.CrashType;

/**
 * This class represents a Crash.
 */
public class Crash {

  private final LocalDate date;
  private final CrashType crashType;
  private final Name offenderName;

  /**
   * Constructs a new Crash object.
   *
   * @param date         - the date of the crash
   * @param crashType    - the type of the crash
   * @param offenderName - the name of the offender
   */
  public Crash(LocalDate date, CrashType crashType, Name offenderName) {
    this.date = date;
    this.crashType = crashType;
    this.offenderName = offenderName;
  }

  /**
   * Returns the date of the crash.
   *
   * @return the date of the crash
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Returns the type of the crash.
   *
   * @return the type of the crash
   */
  public CrashType getCrashType() {
    return crashType;
  }

  /**
   * Returns the name of the offender.
   *
   * @return the name of the offender
   */
  public Name getOffenderName() {
    return offenderName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Crash crash = (Crash) o;
    return Objects.equals(date, crash.date) && crashType == crash.crashType
        && Objects.equals(offenderName, crash.offenderName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, crashType, offenderName);
  }

  @Override
  public String toString() {
    return crashType.getCrash() + " on " + this.date + " involving " + this.offenderName;
  }
}
