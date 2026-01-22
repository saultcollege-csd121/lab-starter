/**
 * Calculates the total cost of all items in the grocery list.
 *
 * @param prices ArrayList of double values representing item prices
 * @return the sum of all prices as a double
 */
double calculateTotal(ArrayList<Double> prices) {
    var groceryTotal = 0.0;
    for(double price : prices) {
        groceryTotal += price;
    }
    return groceryTotal;
}

/**
 * prompts the user to enter a valid price and handles input errors.
 * Continues to prompt until a valid non-negative price is entered.
 *
 * @param scanner Scanner object for reading user input
 * @return a valid price as a double (certain to be >= 0)
 */
double getValidPrice(Scanner scanner) {
    var price = 0.0;
    var isPriceValid = false;
    while (!isPriceValid) {
        try {
            IO.print("Enter item price: $");
            price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                IO.println("Price cannot be negative. Try again.");
            } else {
                isPriceValid = true;
            }
            //
        } catch (NumberFormatException e) {
            IO.println("Invalid price. Please enter a number. ");
        }
    }
    return price;
}

/**
 * Displays grocery list to the console.
 * Shows each item, price and the total cost at the end
 *
 * @param itemNames ArrayList of String values containing item names
 * @param itemPrices ArrayList of Double values containing item prices
 * @param total the total cost of all items
 */
void displayGroceryList(ArrayList<String> itemNames,
                        ArrayList<Double> itemPrices,
                        double total) {
    IO.println("\n=== Your Grocery List ===");
    int i;
    for (i = 0; i < itemNames.size(); i++) {
        IO.println(String.format("%d. %s - $%.2f\n",
                i + 1,
                itemNames.get(i),
                itemPrices.get(i)));
    }
    IO.println(String.format("Total: $%.2f\n\n", total));
}

/**
 * Saves the grocery list to a text file named "grocery_list.txt".
 * Includes all items with prices and the total amount.
 * Displays a success message or error message if file writing fails.
 *
 * @param itemNames ArrayList of String values containing item names
 * @param itemPrices ArrayList of Double values containing item prices
 * @param total the total cost of all items
 */
 void saveToFile(ArrayList<String> itemNames,
                 ArrayList<Double> itemPrices,
                 double total) {
    try {
        FileWriter createFile = new FileWriter("grocery_list.txt");
        createFile.write("=== Grocery List ===\n\n");
        for (int i = 0; i < itemNames.size(); i++) {
            createFile.write(String.format("%d. %s - $%.2f\n",
                    i + 1,
                    itemNames.get(i),
                    itemPrices.get(i)));
        }
        createFile.write(String.format("\nTotal: $%.2f\n", total));
        createFile.close();
        IO.println("✓ List saved to grocery_list.txt");
    } catch (IOException e) {
        IO.println("Error saving file: " + e.getMessage());
    }
}

void main() {
    var readInput = new Scanner(System.in);
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Double> itemPrices = new ArrayList<>();

    IO.println("=== Grocery List Manager ===\n");

    var keepGoing = true;
    // Loop for collecting items from user
    while (keepGoing) {
        IO.print("Enter item name: ");
        String itemName = readInput.nextLine();
        itemNames.add(itemName);

        var price = getValidPrice(readInput);
        itemPrices.add(price);

        IO.print("Add another item (y/n): ");
        String response = readInput.nextLine().toLowerCase();
        if (!response.equals("y")) {
            keepGoing = false;
        }
        IO.println();
    }
    //Call to calculateTotal function
    var total = calculateTotal(itemPrices);

    //Display grocery list to console
    displayGroceryList(itemNames, itemPrices, total);

    //write to file with error handling
    saveToFile(itemNames, itemPrices, total);

    readInput.close();
}