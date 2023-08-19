# Rideshare System – Prospective Drivers Validator and Simulator
![image](/src/main/resources/diagram1.png)
## Content
- [Overview](#overview)
- [Usage](#usage)
- [Answering Questions](#answering-questions)
- [Key challenges](#key-challenges)
- [Sources and resources](#sources-and-resources)
## Overview
The program is a command-line application that validates prospective drivers for a rideshare system. The program first reads a CSV file containing prospective drivers’ information, validates the information, and add the valid drivers to the accepted driver pool. After process all the data, user will be able to interact with the program by searching for potential prospective drivers based on their last name.
## Usage
### Prerequisites
- Java 17, Gradle
- IDE: IntelliJ IDEA
- CSV file: `src/main/resources/drivers.csv`
- Please note that the current program not allow to input a new csv file with different entries.
### Run the program
- To run the program, run the `RideshareDriverValidator` class in the IDE
- To query the driver information, enter the last name of the driver in the console, for example, enter `Doe` to query the driver information of all the drivers with the last name `Doe`

## Answering Questions
1. Please include a code snippet showing how have you used inheritance and
   composition in your code.
    - **use of inheritance** in `model/NonMovingViolation.java`,
      `model/MovingViolation.java`, `NonMovingViolation` and `MovingViolation` are both types of violations, so they both inherit from the abstract class `Violation`
   ```java
    public abstract class Violation {...} 
    public class NonMovingViolation extends Violation {...}
    public class MovingViolation extends Violation {...}
   ```
   - **use of composition**, for example, in `model/Driver.java`, `Driver` has a `DriverHistory` object, and `DriverHistory` has a `List<Violation>` object.
   ```java
    public class Driver {
    Name name;
    LocalDate dateOfBirth;
    License license;
    List<Vehicle> vehicles;
    DriverHistory driverHistories;
    //code continues...
    }
    public class DriverHistory {
    private final List<Violation> violations;
    //code continues...
    }
    ```
2. Please include a code snippet showing how have you used an interface or an abstract class in your code.
   1. use of abstract class in `model/Violation.java`.
    ```java
    public abstract class Violation {
      private final LocalDate date;
      private final ViolationType type;
      //code continues...
    }
   ```
   2. **use of interface** implement `IDriverPool` interface which layout the operation of accepted driver database at high-level, and then add the `DriverPool` class to implement the methods in the interface. Current functionality including add a driver, remove a driver, get all the drivers, get drivers by last name, get driver by name, get number of drivers, reset the driver pool.
   ```java
    public interface IDriverPool {
      void addDriver(Driver driver);
      void removeDriver(Driver driver);
      Set<Driver> getAllDrivers();
      Set<Driver> getDriversByLastName(String lastName);
      Driver getDriverByName(Name name);
      int getNumberOfDrivers();
      void reset();
      //methods can be added to the interface if needed
    }
    // Data container - DriverPool class implements the IDriverPool interface
    public class DriverPool implements IDriverPool {
      private final Set<Driver> drivers;
      //code continues...
    }
   ```
3. Please include code example of a method overriding and method overloading from your code, or explain why you have not used any overloading or overriding.
   - **for overloading**, since most classes are responsible for single functionality, there is no need to overload methods.
   - **for overriding**, besides to have basic methods such as hashCode, equals, toString in classes; in a interface, for example the method `isVehicleInUse` is overriden in `VehiclePool` class.
   ```java
    public class VehiclePool implements IVehiclePool {
      //code continues...
      @Override
      public boolean isVehicleInUse(Vehicle vehicle) {
        return vehicles.contains(vehicle);
      }
      //code continues...
    }
   ```
4. Please include a code example showing how have you used encapsulation, or explain why you did not need encapsulation in your code.
    - **use encapsulation at method level**, `RegistrationValidator` class is used to validate the registration of a driver, and it has private methods checking for driver's age, license, insurance, vehicle history, driver history. It has a public method `isDriverValid` which calls all the private methods to validate the driver.
   ```java
    public class RegistrationValidator {
      // private methods
      private static boolean isMinimumAgeMet(Driver driver) {...}
      private static boolean isValidLicense(Driver driver) {...}
      private static boolean isVehicleOld(Driver driver) {...}
      private static boolean isInsuranceExpired(Driver driver) {...}
      private static boolean validateDriverHistory(Driver driver) {...}
      private static boolean validateVehicleHistory(Driver driver) {...}
      // public method
      public static boolean isDriverValid(Driver driver, ErrorLogger errorLogger) {...}
    }
    ```
    - **use encapsulation at class level**, then in `DriverService` class, when registering a driver, it calls `RegistrationValidator.isDriverValid` to validate the driver, and if the driver is valid, it will register the driver, otherwise, it will display the error message in the console.
    ```java
     public class DriverService {
        public void registerDriver(Driver driver) {
        if (RegistrationValidator.isDriverValid(driver, errorLogger)) {
          // process registration
        } else {
          // display error message
        }
    }
   }
   ```
5. Please include a code example of subtype polymorphism from your code, or explain why you did not need subtype polymorphism.
   - use of polymorphism in `model/Violation.java`. First of all, since the way to initialize a `DriverHistory` is to pass in a list of violation object `List<Violation>`, since `NonMovingViolation` and `MovingViolation` both inherit from `Violation`, we can use `Violation` to refer to either `NonMovingViolation` or `MovingViolation`, we then can add either `NonMovingViolation` or `MovingViolation` to the `DriverHistory` object.
   ```java
    public class DriverHistory {
   
      private final List<Violation> violations;
   
      public void addViolation(Violation violation) {
         violations.add(violation);
       }
      //code continues...
    }
   ```
6. Please include a code snippet of generics from your code.
   - for `DriverHistory`, `VehicleHistory`, `Vehicles` that a `Driver` have, the accepted driver pool, which all used List, Set, Map, etc. Used generics to restrict the type of the elements in the collection.
   ```java
    public class DriverHistory {
      private final List<Violation> violations;
      //code continues...
    }
    public class VehicleHistory {
      private final List<Crash> crashes;
      private final List<Violation> violations;
      //code continues...
    }
    public class Driver {
      private final List<Vehicle> vehicles;
      //code continues...
    }
    public class DriverPool implements IDriverPool {
      private final Set<Driver> drivers;
      //code continues...
    }
   ```
7. Please include a code snippet showing how have you used some of the built-in data collections from the Java Collections Framework, or explain why you had no need for any data collections.
   - used ArrayList, Map, Set base on their functionality suit for different use cases. Shown example of using `Map` below when passing drivers processed from csv file to `DriverService` class.
   ```java
    public class DriverService {
      public void startRegistration() {
        for (Map<String, String> driverData : data) {
          Driver driver = DriverGenerator.processDriver(driverData);
      registerDriver(driver);
      }
    //code continues...
   }
    public class DriverGenerator {
      //a simple example for creating name object from data passed from the DriverService class
      private static Name processDriverName(Map<String, String> driverData) {
      Name name = new Name(driverData.get(PropertyEnum.FIRST_NAME.getProperty()), driverData.get(PropertyEnum.LAST_NAME.getProperty()));
      return name;
      } 
   // code continues...
   }
    ```
8. Please include a code snippet showing how have you used interfaces Iterable and Iterator, or explain why you had no need for these two interfaces.
   - I did not use these two interfaces in my code. Since I did not have a need to iterate through a collection of objects. And for example I can use index to access elements in a list, a key to access elements in a map, etc.
9. Please include a code snippet showing how have you used interfaces Comparable and Comparator, or explain why you had no need for these two interfaces.
    - use of comparator to sepcify the order of the elements in a collection, when displaying the driver information, sort the driver by first name, then print the driver information alphabetically by first name.
    ```java
    public static void provideDriverInformation(Set<Driver> driver) {
      // sort the driver by first name, then print the driver information
      driver.stream().sorted(Comparator.comparing(d -> d.getName().getFirstName())).forEach(System.out::println);
    }
    ```
10. Please include a code snippet showing how have you used regular expressions, or explain why you had no need for it.
    - used regular expression when processing csv file to remove all space, double quotes, and single quotes in the data.
    ```java
    public class DriverDataProcessor {
      private static final String REGEX  = "^\"|\"$";
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
      } catch ....
    // code continues...
    }
    ```
11. Please include a code snippet showing how have you used nested classes, or justify why you had no need for nested classes.
    - I have builder class for `Vehicle` and `Driver`, at first I implemented builder class as inner class of `Vehicle` and `Driver`, but then the class becomes too big, since there are too many attributes to be initialized, so I decided to move the builder class out of the Vehicle and Driver class.
12. Please include code example showing how have you used components of functional programming, such as lambdas and streams, or explain why you had no need for it in your code.
    - use of lambdas and streams when operating on collections, for example, when validating a driver's vehicle is old in `RegistrationValidator` class.
    ```java
    private static boolean isVehicleOld(Driver driver) {
    return driver.getVehicles().stream()
        .anyMatch(vehicle -> LocalDate.now().getYear() - vehicle.getYear() > MAX_VEHICLE_AGE);
    }
    ```
13. Please include code snippet(s) showing how have you used creational, structural and/or behavioral design patterns. Please list which design patterns have you used, or explain why you had no need for design patterns in your solution.
    - **Creational design pattern**
      - **builder**: `DriverBuilder` and `VehicleBuilder` are used to create `Driver` and `Vehicle` objects respectively. The builder class allows to construct complex objects step by step, also allow to have multiple optional parameters. It also keeps the construction process separate from the main object and keeps the process clean and easy to understand. Example when creating a Driver object:
    ```java
    public class Driver{
      // empty constructor, prevent creating Driver object without using the builder
      protected Driver() {
      }
    }
    public class DriverBuilder {
      private final Driver driver;
    
      public DriverBuilder(Name name){...}
      public DriverBuilder withDateOfBirth(LocalDate dateOfBirth) {...}
      public DriverBuilder withLicense(License license) {...}
      public DriverBuilder withVehicles(List<Vehicle> vehicles) {...}
      public DriverBuilder withDriverHistories(DriverHistory driverHistories) {...}
      
      public Driver build() {
        Driver driver = new Driver();
        driver.name = name;
        driver.dateOfBirth = dateOfBirth;
        driver.license = license;
        driver.vehicles = vehicles != null ? this.vehicles : new ArrayList<>();
        driver.driverHistories = driverHistories != null ? this.driverHistories : new DriverHistory();
        return driver;
    }
      // code continues...
    }
    
    // when creat a Driver:
    Driver driver = new DriverBuilder(name)
        .withDateOfBirth(dob)
        .withLicense(license)
        .build();
    ```
      - **singleton**: `DriverPool` serves as the data container for all the accepted drivers, and it is a singleton class, since there is only one instance of `DriverPool` class in the application and making sure only one instance exists throughout the application's lifecycle. Example when adding a driver to the accepted driver pool:
    ```java
    public class DriverPool implements IDriverPool {
      private static final DriverPool instance = new DriverPool();
      private final Set<Driver> drivers;
      private DriverPool() {
        this.acceptedDrivers = new HashSet<>();
      // the getInstance method is static, so it can be called without creating an instance of the class
      public static DriverPool getInstance() {
        return instance;
      }
    }
    
    public class DriverService {
      // constructor in DriverService, using getInstance method to get the instance of DriverPool
      public DriverService() {
      this.processor = new DriverDataProcessor(PATH);
      this.drivers = DriverPool.getInstance();
      this.data = processor.getData();
      this.scanner = new Scanner(System.in);
      this.errorLogger = new ErrorLogger();
      }
    }
    ```
14. Please include code snippets showing examples of MVC architecture, or justify why you had no need for MVC architecture in your design.
    - **model:** serve as the data container for the application, including all the data classes, such as `Driver`, `Vehicle`, `DriverHistory`, `VehicleHistory`, `Violation`, `NonMovingViolation`, `MovingViolation`, `Crash`, `License`, `Name`, `DriverPool`, etc.
    - **view**: serve as the user interface, including `ErroLogger`, `QueryView`, `RegistrationView`, prompt user for input and display information to the user.
    - **controller**: serve as the logic of the application, including `DriverDataProcessor`, `DriverGenerator`, `DriverService`, `RegistrationValidator`, etc.
      - Example of MVC architecture when registering drivers from input file, in the DriverService class, the view is the RegistrationView, the model is the DriverPool, the controller is the DriverGenerator, RegistrationValidator
    ```java
    public class DriverService {
      public void startRegistration() {
        for (Map<String, String> driverData : data) {
          // call the controller to process the data
          Driver driver = DriverGenerator.processDriver(driverData);
          registerDriver(driver); // call the controller add data to the model
        }
      }
      public void registerDriver(Driver driver) {
        // call the controller to validate the driver
        if (RegistrationValidator.isDriverValid(driver, errorLogger)) {
            // call the model to return the data
            Driver existingDriver = drivers.getDriverByName(driver.getName());
            if (existingDriver == null) {
              // call the model to add the data
              drivers.addDriver(driver);
        } else{
            // call the model to add the data
            addNewVehicle(driver, existingDriver);}
      } else{
        // call the view to display information to the user
        DriverView.displayErrors(errorLogger);
        }
      }
    }
    ```
15. Please include code examples showing data and stamp coupling in your code.
    >RideshareDriverValidator -> DriverService -> DriverPool -> DriverGenerator -> DriverDataProcessor -> ErrorLogger, RegistrationView, QueryView
    1. `RideshareDriverValidator` class is the main class of the application, it depends on the `DriverService` class to start the registration process, and query the driver information
    2. `DriverService` class: the `DriverService` class depends on the `DriverPool` class to get the accepted drivers, and `ErrorLogger`, `RegistrationView`,`QueryView` to display information to the user.
    3. then the `DriverPool` class depends on the `DriverGenerator` class to add a driver to the accepted driver pool. 
    4. Then the input to `DriverGenerator` depends on the `DriverDataProcessor` class to get the data from the csv file.
    ```java
    public class RideshareDriverValidator {
        public static void main(String[] args) {
          // create an instance of DriverService class
          DriverService driverService = new DriverService();
          // start the registration process and prompt user for input to search
          driverService.run();
        }
    }
    public class DriverService {
      public DriverService() {
        // call the DriverDataProcessor to get the data from the csv file when initialize the DriverService
        this.processor = new DriverDataProcessor(PATH);
        // call the DriverPool to get the accepted drivers when initialize the DriverService
        this.drivers = DriverPool.getInstance();
        // call the processor initialized earlier to get the data
        this.data = processor.getData();
        this.scanner = new Scanner(System.in);
        // call the ErrorLogger to store error message
        this.errorLogger = new ErrorLogger();
    }
      public void startRegistration() {
        for (Map<String, String> driverData : data) {
          // call the DriverGenerator to process the driver data
          Driver driver = DriverGenerator.processDriver(driverData);
          registerDriver(driver); // call the controller to register the driver
        }
      }
      public void registerDriver(Driver driver) {
        // call the RegistrationValidator to validate the driver
        if (RegistrationValidator.isDriverValid(driver, errorLogger)) {
          // call DriverPool to add the driver to the accepted driver pool
        } else {
          // call ErrorLogger to save and display error message
        }
      }
      // code continues...
    }
    
    public class DriverDataProcessor {
      // process the data from the csv file and return a list of map
      public DriverDataProcessor(String path) {
        this.data = this.inputData(path);
      }
      // private methods to read the csv file
      private List<Map<String, String>> inputData(String path) {
        List<Map<String, String>> data = new ArrayList<>();
        List<String[]> lines = this.readCSV(path);
        return this.processData(lines);
      }
      // code continues...
    }
    ```
## Key challenges
- **challenge 1**: Given the size and the requirements, before start writing the program, it is essential to have a clear idea of how the application should work, what classes to have, how to organize the classes, how to make the classes interact with each other, etc.
- **challenge 2**: If just create by calling the constructor it will be extremely hard and error-prone to create a Driver object, so I decided to use builder pattern to create the Driver object, and also use builder pattern to create Vehicle object.
- **challenge 3**: When processing the data from the csv file, and creating a Driver object. To create a Driver object, multiple sub-objects need to be created, such as Name, License, Vehicle, DriverHistory, etc. The creation process can be complex if done in one single methods, by breaking down the creation into several helper methods, retrieve the requiered data and passing to the helper methods to create the sub-objects, then pass the sub-objects to the DriverBuilder to create the Driver object.
- **challenge 4**: To address shared vehicle policy, I've come up with a solution and can refactor the current package if I have more time. Given a driver can register multiple vehicles, and a vehicle can have multiple drivers, but if a vehicle is in use, then it cannot be used by another driver. Please refer to the `IVehiclePool` interface and `VehiclePool` class for the solution.
  - add a field in vehicle: `boolean isInUse`
  - add a field in driver: `List<vehicle> unavalibaleVehicles`
  - Add a `VehiclePool`(Set) class, when driver register,
    - for drivers, if the vehicle's `isInUse` is `true` then add the vehicle to the driver's `notAvaliableVehicles` list
    - since each vehicle has the boolean `isInUse` to track, so when a second driver register with a car, if the car found in the pool, with isInsue is true, then add the vehicle to the `unavalibaleVehicles` list belong to the driver.
  - Then before a driver want to use the vehicle, check if the vehicle is in the driver's `unavalibaleVehicles` list, if yes, then the vehicle is in use, otherwise, the vehicle is available.
   ```java
    public interface IVehiclePool {
      void addVehicle(Vehicle vehicle);
      void removeVehicle(Vehicle vehicle);
      // can have mupltiple ways to check if a vehicle is in use
      boolean isVehicleInUse(Vehicle vehicle);
      boolean isVehicleInUse(String vin);
      boolean isVehicleInUseByDriver(Driver driver, Vehicle vehicle);
      boolean isVehicleInUseByDriver(Driver driver, String vin);
      //code continues...
    }
    // Data container - VehiclePool class implements the IVehiclePool interface
    public class VehiclePool implements IVehiclePool {
      private final Set<Vehicle> vehicles;
      //code continues...
    }
   ```
## Sources and resources
- Understand MVC architecture: https://www.guru99.com/mvc-tutorial.html
- Understand design patterns: https://refactoring.guru/design-patterns/builder 
- Understand design patterns: https://refactoring.guru/design-patterns/singleton
