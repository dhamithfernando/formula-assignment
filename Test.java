package src;

import java.util.Scanner;

public class Test {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
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
    }

    private static void isNextInt(String s) {
        while (!input.hasNextInt()) {
            System.out.println("Invalid Input!");
            System.out.print(s);
            input.next();
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
}
