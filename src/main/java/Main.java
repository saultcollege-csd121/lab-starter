import core.Card;
import core.CardStack;
import core.HighCardGame;
import ui.Console;
import java.lang.String;

import java.util.List;

import static core.Card.Rank.ACE;
import static core.Card.Suit.SPADES;

/**
 * Main entry point for the High Card game application
 * This class handles the user interface and game flow, allowing two players
 * to compete by drawing cards and comparing them.
 */
public class Main {
    /**
     * Runs the High Card game between two players.
     *
     * The game follows these steps:
     * <ol>
     *   <li>Prompts for two player names</li>
     *   <li>Creates a new game with those players</li>
     *   <li>Plays rounds where each player draws a card</li>
     *   <li>Displays the winner of each round</li>
     *   <li>Continues until the user quits or cards run out</li>
     * </ol>
     *
     * Game continues as long as:
     * <ul>
     *   <li>The user chooses to play again (enters 'y')</li>
     *   <li>There are enough cards left in the deck</li>
     * </ul>
     */
    static void main() {

        String name1 = Console.prompt("Enter Player 1 name: ");
        String name2 = Console.prompt("Enter Player 2 name; ");

         /**
         Here we create a new HighCardGame object
         We pass the two names to the constructor so the game can create
         the players inside the HighCardGame class

         Main class only control the game, but HighCardGame class do the logic
         */
        HighCardGame game = new HighCardGame(name1, name2);

        boolean playAgain = true; // Control if the user want play again

        // keeps running the game until the user wants to quit or cards run out

        while (playAgain && game.canPlayRound()) {

             /*
             playRound() return a RoundResult record.
             RoundResult > store card1, card2 and winner player
             */

            HighCardGame.RoundResult result = game.playRound(); // record > (card1, card2, winner)

            Console.println(name1 + " drew " + result.card1());
            Console.println(name2 + " drew: " + result.card2());

            if (result.winner() == null) { // winner() > record > player object
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
