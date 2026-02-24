import core.HighCardGame;
import ui.Console;

public class Main {

    public static void main(String[] args) { //defines the entry-point. Runts without object and has command line arguments.

        Console.println("READY TO PLAY?! "); //friendly message to start
        var names = Console.promptForNInputs("Enter player name", 2); //Ask player names and they are saved in a list of strings.

        var game = new HighCardGame(names.get(0), names.get(1)); //creates a game object
        while (game.canPlay()) { //here is where the loop starts and the main logic runs

            String result = game.playRound(); //performs the round
            Console.println(result);
            Console.println("Score: " + game.getScore()); //result is printing
            if (!game.canPlay()) { //if someone runs out of card we break the loop.
                break;
            }
            String choice = Console.promptForOption(
                    "another round?", //if they still have card they can choose to keep playing/
                    new String[]{"yes", "no"}
            );
            if (choice.equalsIgnoreCase("no")) { //if the user input is == no, the loop is braken.
                break;
            }}
        Console.println("Final Score:" + game.getScore()); //show final score
        Console.println(game.getFinalWinner()); //show the winner

        Console.println("Game over!"); //game over ;)
    }
}