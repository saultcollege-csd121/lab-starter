/* ********************************

Add your Lab 1 code to this file.

It should consist of ONLY a set of functions, including the main function that is the entry point.

NO classes need be defined for this lab. (If you don't know what a class is, don't worry about it!)

*/
import java.util.Scanner;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;


void main() {

// Allows Keyboard to read the input
    Scanner scanner = new Scanner(System.in);

    //Asks the user for the budget and the items that they are buying

    double Budget = readDouble(scanner, "What is your budget? ");
    int ItemCount = readInt(scanner, "How many items are you buying? ");

    //Calculates total cost of all the grocery items
    double TotalPrice = calculateTotal(scanner, ItemCount);

    // Prints the total price on the screen
    System.out.println("Total is: $" + TotalPrice);


    //A condition that checks if the user is within the budget or over
    if (TotalPrice <= Budget) {
        System.out.println("You can afford this!");
    } else {
        System.out.println("Too expensive! Reconsider your list.");

    }
        saveResult(Budget, TotalPrice);

}
    // Reads a valid number from the user

    int readInt(Scanner scanner, String prompt) {

    while (true) {
        System.out.print(prompt);

    try {
        return Integer.parseInt(scanner.nextLine()); // converts input to integer

    } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number. "); // if the user enters an invalid input

      }
    }
 }

    // Reads a valid number
    double readDouble(Scanner scanner, String prompt) {
        while (true) {

        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine()); // converts input to a decimal number
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount"); // If user enters an invalid decimal number
        }

    }
}

    // This Creates a list of all the prices and iterate through it to add them.
    //Asks for the price of the current item
        double calculateTotal(Scanner scanner, int count) {
        List<Double> prices = new ArrayList<>();

    // loops once for each item
        for (int i = 1; i <= count; i++) {

            double price = readDouble(scanner, "Price of item" + i + ":");
            prices.add(price);
        }
        double total = 0.0;
        for (double price: prices) {
            total += price; // This adds price to total
        }

            return total; // Returns the final Cost
        }

    void saveResult (double budget, double total){   // Saves  budget and total cost to a txt file

        String result = ("Budget: $ " + budget + "Total Cost: $ " + total);

    try { // Writes the result to a file
        Files.writeString(Path.of("budget-result.txt"), result );
        System.out.println("Result Saved.");

    }   catch (IOException e){

        System.out.println("Result could not saved."); // if the file cannot be written
    }


    }







