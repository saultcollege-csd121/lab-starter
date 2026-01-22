
// Variables that I am using
String w = "\n Welcome to the to database. \n [--Please Select an option to continue--]";
List<String> store = List.of("Milk", "Eggs", "Dog Food", "Apples", "Bread");

List<String> menu_choices = List.of("1. Write to notes", " 2. View errors", "3. Console Spam", "4. Show grocery list", "5. Math Questions", "6. Exit");
String n = "Rodney";
String entry = "savednote.txt";


//This welcomes the defined person
void greet(String n) {
    IO.println("""
            ┌─────────────────────────────┐
            │  Welcome, To The Lab Menu!  │
            └─────────────────────────────┘""");
}

void main() {
    greet(n);

    //This tells us to press enter and then the selection menu pops up.
    IO.println("Press Enter to continue...");
    IO.readln("");

    while (true) {
        String selection = IO.readln("""
        ┌───────────────────────────┐
        │  PLEASE SELECT AN OPTION  │
        ├───────────────────────────┤
        │  1. Write to notes        │
        │  2. View errors           │
        │  3. Console Spam          │
        │  4. Browse Store Stock    │
        │  5. Make a purchase       │
        │  6. Exit                  │
        └───────────────────────────┘
         >""");

        //This Defines what the options do in the main menu
        switch (selection) {

            case "1" -> {
                try {
                    //Saving note my savednote.txt file
                    String note = IO.readln("Write your note: ");
                    String content = note + System.lineSeparator();
                    Files.write(
                            Paths.get(entry),
                            content.getBytes(),
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND
                    );
                    IO.println("Note saved to " + entry);
                    IO.println("[Press Any Key TO RETURN TO MAIN MENU]");
                    IO.readln("");

                } catch (IOException e) {
                    IO.println("Error:");
                }
            }

            //Just a fun fake error check
            case "2" -> {
                IO.println("Checking for errors...");
                IO.println("Error 404: Just kidding! You will have to check yourself ;)");
                IO.println("[Press Any Key TO RETURN TO MAIN MENU]");
                IO.readln("");
            }

            //just spamming the console to show off a loop
            case "3" -> {
                IO.println("Looping Message");
                for (int i = 0; i < 50; i++)
                    IO.println("Looping....... HeHe" + i);
                IO.println("[Press Any Key TO RETURN TO MAIN MENU]");
                IO.readln("");
            }

            case "4" -> {
                IO.println(store);
                IO.println("This weeks grocery list is:" + "\n" + store + "\n [Press Any Key TO RETURN TO MAIN MENU]");
                IO.readln("");
            }

            case "5" -> {
                IO.println("Which of the following items would you like to purchase?\n" + store + "\n >");
                String choose_item = IO.readln(); // read what the user types

                switch (choose_item) {
                    case "Milk" -> IO.println("Milk Is $2.50 CA");
                    case "Eggs" -> IO.println("Eggs are $3.60 CA");
                    case "Dog Food" -> IO.println("Dog Food Is $11.70 CA");
                    case "Apples" -> IO.println("Apples are $1.00 CA Each");
                    case "Bread" -> IO.println("Bread Is $1.50 CA");
                    default -> IO.println("Sorry, that item is not available.");
                }

                IO.println("[Press Any Key TO RETURN TO MAIN MENU]");
                IO.readln("");
            }


            //just a fake exiting message idk I had an extra button
            case "6" -> {
                IO.println("Exiting The Menu. Bye! Bye! ;)");
                return;
            }

            default -> { //This is logic for incorrect input selection in menu

                IO.println("That is not a valid option.");
                IO.println("\n Please select a valid option:" + "\n" + menu_choices);
                IO.println("[Press Any Key TO RETURN TO MAIN MENU]");
                IO.readln("");

            }
        }

    }
}






