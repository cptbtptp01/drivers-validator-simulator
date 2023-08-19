package problem2.ProspectiveDriversValidatorAndSimulator.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a driver data processor.
 */
public class DriverDataProcessor {

  private static final String CSV_DELIMITER = ",";
  private static final String REGEX = "^\"|\"$";
  private final List<Map<String, String>> data;

  /**
   * Constructs a DriverDataProcessor object and initializes it with the given path.
   *
   * @param path the path of the CSV file
   */
  public DriverDataProcessor(String path) {
    this.data = this.inputData(path);
  }

  /**
   * Returns a list of Maps, where each Map represents a line in the CSV file with the keys being
   * the property names and the values being the property values.
   *
   * @param path the path of the CSV file
   * @return a list of Maps
   */
  private List<Map<String, String>> inputData(String path) {
    List<Map<String, String>> data = new ArrayList<>();
    List<String[]> lines = this.readCSV(path);
    return this.processData(lines);
  }

  /**
   * Reads a CSV file and returns a list of Maps, where each Map represents a line in the CSV file
   * with the keys being the property names and the values being the property values.
   *
   * @param lines
   * @return
   */
  private List<Map<String, String>> processData(List<String[]> lines) {
    List<Map<String, String>> data = new ArrayList<>();
    boolean isHeader = true;
    String[] header = null;
    for (int i = 0; i < lines.size(); i++) {
      Map<String, String> driverData = new HashMap<>();
      if (isHeader) {
        header = lines.get(i);
        for (int j = 0; j < header.length; j++) {
          header[j] = header[j];
        }
        isHeader = false;
        continue;
      }
      String[] fields = lines.get(i);
      for (int j = 0; j < fields.length; j++) {
        driverData.put(header[j], fields[j]);
      }
      data.add(driverData);
    }
    return data;
  }

  /**
   * Reads a CSV file and returns a list of String arrays, where each String array represents a line
   * in the CSV file.
   *
   * @param path the path of the CSV file
   * @return a list of String arrays, where each String array represents a line in the CSV file
   */
  private List<String[]> readCSV(String path) {
    List<String[]> lines = new ArrayList<>();
    try (BufferedReader inputFile = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        String[] fields = line.split(CSV_DELIMITER);
        for (int i = 0; i < fields.length; i++) {
          fields[i] = fields[i].replaceAll(REGEX, "").trim(); // Remove enclosing quotes
        }
        lines.add(fields);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
      throw new RuntimeException("File Not Found", fnfe);
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
      throw new RuntimeException("IO Exception", ioe);
    }
    return lines;
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public List<Map<String, String>> getData() {
    return this.data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverDataProcessor processor = (DriverDataProcessor) o;
    return Objects.equals(data, processor.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    return "DriverDataProcessor{" +
        "data=" + data +
        '}';
  }
}