package com.pluralsight.NorthwindTradersAPI.util;

import java.util.ArrayList;
import java.util.Scanner;

public class Utility {

    public static final Scanner scanner = new Scanner(System.in);
    public static int getUserInt(String prompt){
        System.out.print(prompt);

        boolean notChosen = true;
        int option = -1;

        do {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }
    public static void displayMenu(ArrayList<String> options,String label){
        int i = 1;
        System.out.println("\n\n\n"+label);
        for (String s : options){
            if (s.equals("Exit")){System.out.printf("[%d] %s%n",0,s);}else{
                System.out.printf("[%d] %s%n",i,s);
                i++;
            }
        }
    }

    public static double getUserDouble(String prompt){
        System.out.print(prompt);

        boolean notChosen = true;
        double option = -1;

        do {
            try {
                option = scanner.nextDouble();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }

    public static String getUserString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim().toUpperCase();
    }
}
