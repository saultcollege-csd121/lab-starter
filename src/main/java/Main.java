import core.Card;
import core.CardStack;
import core.HighCardGame;
import ui.Console;
import java.lang.String;

import java.util.List;

import static core.Card.Rank.ACE;
import static core.Card.Suit.SPADES;

public class Main {

    static void main() {

        /*
             Place your main game logic here.
             This is the ONLY code file that should have any reference to the Console class.

             The basic flow of the game is as follows:

             1. Prompt for player names
             2. Deal a shuffled deck evenly to each of the players
             3. While the players have cards and wish to continue:
                 b. All players draw one card and reveal them
                 c. The player with the higher card wins the round (or it's a tie)
         */
        // Get User name
        String name1 = Console.prompt("Enter Player 1 name: ");
        String name2 = Console.prompt("Enter Player 2 name; ");

        // Create a new game witb players name
        HighCardGame game = new HighCardGame(name1, name2);

        boolean playAgain = true;

        while (playAgain && game.canPlayRound()) {

            // Nested type that calls for game round
            HighCardGame.RoundResult result = game.playRound();

            Console.println(name1 + " drew " + result.card1());
            Console.println(name2 + " drew: " + result.card2());

            if (result.winner() == null) {
                Console.println("its a tie ");
            } else {
                Console.println(result.winner().getName() + " wins the round");
            }

            String response = Console.promptForOption("Play another round? ", new String[]{"y", "n"});
            playAgain = response.equalsIgnoreCase("y"); // check response
        }

        Console.println("Game over ");
    }
}
