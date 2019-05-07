/**
 * Author:      Pawel Wodyk
 * Student ID:  B00122935
 * Written on:  04/11/12
 * Last Edit:   10/11/18
 * Function:    
 *      Simple Ticketing Software
 *      Read the user input for Show name, Surname, Number of people
 *      Calculate the price based on the number of people and discount for parties over 6 people
 *      Ask user do they want to buy another ticket, otherwise read back to the user his tickets and sum the total amount to pay
 */

import java.util.ArrayList; // import ArrayList class
import java.util.Scanner;   // import Scanner class

public class Ass1B00122935 {

    // Main Method
    public static void main(String[] args) {
        // initiate Scanner instance
        Scanner sc = new Scanner(System.in);

        // initiate ArrayList for each variable I need to get from User
        ArrayList<String> show = new ArrayList<String>();
        ArrayList<String> surname = new ArrayList<String>();
        ArrayList<Integer> partySize = new ArrayList<Integer>();
        ArrayList<Double> totalCost = new ArrayList<Double>();

        // initiate the rest of variables:
        double total = 0;           // double for the final cost 
        String input = "";          // String for the user input when exiting the application
        final double COST = 12.0;   // constant variable COST for a cost per ticket this value will not change so it could be a constant
        final String EURO = "EUR";  // I tried to use Unicode for Euro Symbol "\u20AC" however it did not work in windows console,
                                    // hence I replaced it with String "EUR" 
        int iteration = 0;          // simple iterator I used to count correct index for arraylist 
        boolean correctInput;       // boolean input for checking is the user inserting correct input
        
        // main loop iterates the whole program until user enter "n" to exit the loop
        do {
            System.out.println("****************************");
            System.out.println("Please Enter name of the show");   // ask user for Show Name
            show.add(sc.nextLine());                               // take the String input from the user and add it to ArrayList show

            System.out.println("Please enter your Surname");   // ask user for Surname
            surname.add(sc.nextLine());                        // take the String input from the user and add it to ArrayList show
            
            System.out.println("Please Enter number of people in your group");  // ask user for Number of People in the group
            
            //this loop will ask user to re-enter value if user input number less then 1
            do {
                // initialize temporary integer that exist only within this loop
                int temp_group;
                try {
                    // take the String input from the user and parse it to temporary integer
                    temp_group = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException numEx) { // checks for exception if the value is in incorrect format e.g. String
                    System.out.println("ERROR: You have not entered numeric value.");
                    temp_group = -1; // assign the integer value that is less then 0 so the loop will iterate
                }
                
                //check is the value entered is correct ie. more then 0
                if (temp_group > 0) {
                    partySize.add(temp_group);
                    // this if statement calculates the total cost of tickets
                    if (temp_group > 0 && temp_group < 7) {
                        // if the partysize is between 1 and 6 it multiply the number of people by cost of a ticket
                        totalCost.add(partySize.get(iteration) * COST);
                    } else {
                        // if the partysize is more then 6 it multiply the number of people by cost of a ticket
                        // and by 0.9 to calculate 10% reduction on price
                        totalCost.add(partySize.get(iteration) * COST * 0.9);
                    }
                    correctInput = true; // set the value of correctInput exiting the loop
                }
                else {
                    // user asked to enter correct value
                    System.out.println("Minimum party size is 1 person. Please re-enter the party size");
                    correctInput=false;
                }
            } while (!correctInput);
            

            // print the details user entered by reading it from each ArrayList
            System.out.printf("Show name:\t\t%s%n", show.get(iteration));
            System.out.printf("Ticket on Surname:\t%s%n", surname.get(iteration));
            System.out.printf("Number of people:\t%d%n", partySize.get(iteration));
            System.out.printf("Total Cost:\t\t%,.2f %s%n", totalCost.get(iteration), EURO);
            System.out.println();

            // increase the iteration to get the correct index for ArrayList in the next iteration
            iteration++;

            // ask user do they want to buy another ticket
            System.out.println("Enter Y if you would like to buy another ticket or N if you wish to quit and print your order");

            // this loop ensures the user entered correct value in the console
            do {
                input = sc.nextLine(); // reads the input from the console
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                    correctInput = true; // if the user enter y or n the correct value will change to true causing the loop to terminate
                }
                else {
                    correctInput = false; // if the user enter other string the correct value will change to false causing the loop to iterate
                    System.out.println("Please enter either Y or N"); // ask user to re-enter the input
                }
                
            } while(!correctInput);
            
        } while (input.equalsIgnoreCase("y")); // iterate if the user want to buy another ticket

        // this for loop writers all the tickets that were entered
        for (int index = 0; index < show.size(); index++) {
            System.out.println("****************************");
            System.out.printf("*** Ticket #%d ***%n", (index+1));
            System.out.printf("Surname: \t%s%n", surname.get(index));
            System.out.printf("Show Name: \t%s%n", show.get(index));
            System.out.printf("Party Size: \t%d%n", partySize.get(index));
            System.out.printf("Total Cost: \t%,.2f %s%n", totalCost.get(index), EURO);
            total += totalCost.get(index);
        }

        System.out.println("****************************");
        System.out.printf("Total amount to Pay: %,.2f %s%n", total, EURO);  //this code return the total cost of all tickets
        System.out.println("****************************");

        System.out.println("\n### Thank you for your purchase and see you in the Cinema ###");  // thank the user for using your service
        

        sc.close(); // close the Scanner after it is no longer needed
    }
}