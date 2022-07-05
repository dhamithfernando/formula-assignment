package src;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class  Formula1ChampionshipManager implements ChampionshipManager {
    //    global scanner reference
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {

        int userInput;

        restoreDate();
//        getDrivers();
//        getRaces();

//        allFormula1Drivers.add(new Formula1Driver(1, "damith", "panadura", "BMW", 10, 20, 30, 300, 6));
//        allFormula1Drivers.add(new Formula1Driver(2, "dulith", "panadura", "bens", 1, 5, 7, 60, 6));
//        allFormula1Drivers.add(new Formula1Driver(3, "thilina", "halawatha", "Audi", 3, 4, 7, 60, 6));
        saveData();

        while (true) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf(" |%65s %n", "Formula 1 Championship Management \t\t\t\t\t|");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("\t[1] Create a new driver ");
            System.out.println("\t[2] Delete a driver ");
            System.out.println("\t[3] Change the driver for an existing constructor team ");
            System.out.println("\t[4] Display the various statistics for a selected existing driver ");
            System.out.println("\t[5] Display the Formula 1 Driver Table ");
            System.out.println("\t[6] Add a race completed with its date and the positions ");
            System.out.println("\t[7] Saving in a file of all the information ");
            System.out.println("\t[8] Open GUI ");

            System.out.print("Enter an option to continue > ");
            userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    addDriver(); // call add driver method
                    break;
                case 2:
                    deleteDriver(); // delete driver
                    break;
                case 3:
                    updateDriverConstructorTeam();  // update drivers constructor team using driver id
                    break;
                case 4:
                    displayDriverStatistics();  // get statistics for selected driver
                    break;
                case 5:
                    viewAllDrivers(); // view all drivers statistics
                    break;
                case 6:
                    addRaceToDriver(); // add a new race to a race data list with race drivers
                    break;
                case 7:
                    saveData(); // save all drivers and race data to text (.txt) file
                    break;
                case 8:
                    openGUI(); // open the graphical user interface
                    break;
                default:
                    System.out.println("Wrong Input Entered");
                    break;
            }
        }
    }

    private static void openGUI() {
        new GUI();
    }

    //    add driver method
    private static void addDriver() {
//        getting appropriate information about driver and storing in an array list
        int driverId;
        while (true) {
            System.out.print("Enter Driver Id : ");
            isNextInt("Enter Driver Id : ");
            driverId = input.nextInt();
            if (validateId(driverId)) {
                break;
            } else {
                System.out.println("The Driver Id is already existing!");
            }
        }

        input.nextLine();
        System.out.print("Enter driver Name : ");
        String driverName = input.nextLine();

        System.out.print("Enter the driver location : ");
        String driverLocation = input.nextLine();

        System.out.print("Enter the driver constructor team : ");
        String driverConstructorTeam = input.nextLine();

        System.out.print("Enter the count of first positions : ");
        isNextInt("Enter the count of first positions : ");
        int firstPositions = input.nextInt();

        System.out.print("Enter the count of second positions : ");
        isNextInt("Enter the count of second positions : ");
        int secondPositions = input.nextInt();

        System.out.print("Enter the count of third positions : ");
        isNextInt("Enter the count of third positions : ");
        int thirdPositions = input.nextInt();

        System.out.print("Enter number of races participated : ");
        isNextInt("Enter number of races participated : ");
        int noParticipation = input.nextInt();

        System.out.print("Enter number of points gained : ");
        isNextInt("Enter number of points gained : ");
        int points = input.nextInt();

        Formula1Driver formula1Driver = new Formula1Driver(driverId, driverName, driverLocation, driverConstructorTeam, firstPositions, secondPositions, thirdPositions, points, noParticipation);
        ChampionshipData.allFormula1Drivers.add(formula1Driver);
        System.out.println(" Driver added successfully");
        saveData(); // save new driver detail in file
    }

    //    check user input is int or not
    private static void isNextInt(String s) {
        while (!input.hasNextInt()) {
            System.out.println("Invalid Input!");
            System.out.print(s);
            input.next();
        }
    }

    //    delete driver method
    private static void deleteDriver() {
        int driverId;
        while (true) {
            System.out.print("Enter the id of the driver you want to delete: ");
            isNextInt("Enter the id of the driver you want to delete: ");
            driverId = input.nextInt();
            if (validateId(driverId)) {
                System.out.println("The Driver is found for this Id!");
            } else {
                break;
            }
        }
        for (Formula1Driver formula1Driver : ChampionshipData.allFormula1Drivers) {
            if (formula1Driver.getDriverId() == driverId) {
                ChampionshipData.allFormula1Drivers.remove(formula1Driver);
                System.out.println(driverId + " has been deleted from the championship.");
                saveData();
                return;
            }
        }
    }

    //    Change the driver for an existing constructor team
    private static void updateDriverConstructorTeam() {
        int driverId;
        while (true) {
            System.out.print("Enter Driver Id : ");
            isNextInt("Enter Driver Id : ");
            driverId = input.nextInt();
            if (!validateId(driverId)) {
                System.out.print("Enter New Driver Name : ");
                String newDriverName = input.next();
                System.out.print("Enter New Driver Location : ");
                String newDriverLocation = input.next();
                for (Formula1Driver formula1Driver : ChampionshipData.allFormula1Drivers) {
                    if (formula1Driver.getDriverId() == driverId) {
                        formula1Driver.setDriverName(newDriverName);
                        formula1Driver.setDriverLocation(newDriverLocation);
                        saveData(); // update driver details in txt file by restoring them
                    }
                }
                break;
            } else {
                System.out.println("The Driver Id is not existing!");
            }
        }
    }

    //    display specific driver statistics
    private static void displayDriverStatistics() {
        int driverId;
        while (true) {
            System.out.print("Enter the id of the driver you want to check statistics : ");
            isNextInt("Enter the id of the driver you want to check statistics : ");
            driverId = input.nextInt();
            if (!validateId(driverId)) {
                for (Formula1Driver formula1Driver : ChampionshipData.allFormula1Drivers) {
                    if (formula1Driver.getDriverId() == driverId) {
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%80s %n", "statistics for selected driver\t\t\t\t\t\t\t\t\t\t\t\t|");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------\n");
                        System.out.println("+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+");
                        System.out.println("|driverName |driverLocation  |driverTeam   |firstPositions |secondPositions |thirdPositions |points |noParticipation |");
                        System.out.println("+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+");
                        System.out.printf("|%-11s|%-16s|%-13s|%15d|%16d|%15d|%7d|%16d|\n",
                                formula1Driver.getDriverName(),
                                formula1Driver.getDriverLocation(),
                                formula1Driver.getDriverTeam(),
                                formula1Driver.getFirstPositions(),
                                formula1Driver.getSecondPositions(),
                                formula1Driver.getThirdPositions(),
                                formula1Driver.getPoints(),
                                formula1Driver.getNoParticipation());
                        System.out.println("+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+\n");
                        return;
                    }
                }
            } else {
                System.out.println("No driver were found for this Driver Id");
            }
        }

    }

    //    view all driver
    private static void viewAllDrivers() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%80s %n", "View All Drivers\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("----------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("+-----------+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+");
        System.out.println("|driverId   |driverName |driverLocation  |driverTeam   |firstPositions |secondPositions |thirdPositions |points |noParticipation |");
        System.out.println("+-----------+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+");
        ArrayList<Formula1Driver> list = new ArrayList<>(ChampionshipData.allFormula1Drivers);
        list.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        for (Formula1Driver formula1Driver : list) {
            System.out.printf("|%-11s|%-11s|%-16s|%-13s|%15d|%16d|%15d|%7d|%16d|\n",
                    formula1Driver.getDriverId(),
                    formula1Driver.getDriverName(),
                    formula1Driver.getDriverLocation(),
                    formula1Driver.getDriverTeam(),
                    formula1Driver.getFirstPositions(),
                    formula1Driver.getSecondPositions(),
                    formula1Driver.getThirdPositions(),
                    formula1Driver.getPoints(),
                    formula1Driver.getNoParticipation());
        }
        System.out.println("+-----------+-----------+----------------+-------------+---------------+----------------+---------------+-------+----------------+\n");
    }

    //    create a new race and add drivers to that race
    private static void addRaceToDriver() {
        int[] placePoints = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        Map<Formula1Driver, Integer> formula1DriverIntegerMap = new HashMap<>();
        System.out.print("Enter Date : (DD/MM/YYYY) : ");
        String date = input.next();
        if (validateDate(date)) {
            Map<Integer, Integer> raceResult = new HashMap<>();
            System.out.print("Enter Driver IDs separately With commas (Example :- 001,002)  : ");
            String driverIds = input.next();

            String[] driversIdList = driverIds.split(",");
            for (String driverId : driversIdList) {
                if (!validateId(Integer.parseInt(driverId))) {
                    System.out.print("Enter the place for Driver Id " + driverId + " : ");
                    int point = input.nextInt();
                    raceResult.put(Integer.parseInt(driverId), point);
                } else {
                    System.out.println("Invalid driver Id...");
                }
            }
            try {
                raceResult.forEach((driverId, place) -> {
                    for (Formula1Driver driver : ChampionshipData.allFormula1Drivers) {
                        if (driver.getDriverId() == driverId) {
                            switch (place) {
                                case 1:
                                    driver.setFirstPositions(driver.getFirstPositions() + 1);
                                    driver.setPoints(driver.getPoints() + placePoints[place - 1]);
                                    break;
                                case 2:
                                    driver.setSecondPositions(driver.getSecondPositions() + 1);
                                    driver.setPoints(driver.getPoints() + placePoints[place - 1]);
                                    break;
                                case 3:
                                    driver.setThirdPositions(driver.getThirdPositions() + 1);
                                    driver.setPoints(driver.getPoints() + placePoints[place - 1]);
                                    break;
                                default:
                                    driver.setPoints(driver.getPoints() + placePoints[place - 1]);
                            }
                            driver.setNoParticipation(driver.getNoParticipation() + 1);
                            formula1DriverIntegerMap.put(driver, place);
                        }
                    }

                });
                // Add New Race to List
                ChampionshipData.allRaces.add(new Race(new SimpleDateFormat("dd/MM/yyyy").parse(date), formula1DriverIntegerMap));
                saveData(); // save new race data to .txt file
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid Date. Please enter valid date.");
        }
    }

    public static boolean validateId(int driverId) {
        for (Formula1Driver formula1Driver : ChampionshipData.allFormula1Drivers) {
            if (formula1Driver.getDriverId() == driverId) {
                return false;
            }
        }
        return true;
    }

    //    this method gets user date and split it to date and month and year. check whether a valid date
    public static boolean validateDate(String date) {
        String[] formatDate = date.split("/");
        int userYear = Integer.parseInt(formatDate[2]);
        int userMonth = Integer.parseInt(formatDate[1]);
        int userDay = Integer.parseInt(formatDate[0]);
        return userMonth <= 12 && userDay <= getDayCount(userYear, userMonth);
    }

    //    it outputs the total days for the months according to the relevant year
    private static int getDayCount(int userYear, int userMonth) {
        int[] notLeapMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return (userYear % 4 == 0 ? leapMonths[userMonth - 1] : notLeapMonths[userMonth - 1]);
    }

    //    save race details and drivers detail in .txt file
    public static void saveData() {
        try {
            try (FileOutputStream fos = new FileOutputStream("driversData.txt", false)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    for (Formula1Driver driver : ChampionshipData.allFormula1Drivers) {
                        oos.writeObject(driver);
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream("racesData.txt", false)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    for (Race race : ChampionshipData.allRaces) {
                        oos.writeObject(race);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    retrieve previous saved data from .txt file
    private static void restoreDate() {
        try {
            try (FileInputStream fis = new FileInputStream("driversData.txt")) {
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    for (; ; ) {
                        try {
                            Formula1Driver formula1Driver = (Formula1Driver) ois.readObject();
                            ChampionshipData.allFormula1Drivers.add(formula1Driver);
                        } catch (EOFException e) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("races data Loaded from file racesData.txt");
        try {
            try (FileInputStream fis = new FileInputStream("racesData.txt")) {
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    for (; ; ) {
                        try {
                            Race race = (Race) ois.readObject();
                            ChampionshipData.allRaces.add(race);
                        } catch (EOFException e) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Drivers data Loaded from file driversData.txt");
    }

}
