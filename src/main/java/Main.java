import core.Card;
import core.CardStack;
import core.HighCardGame;
import core.Player;
import ui.Console;

 /**
 * Main entry point for the High Card game application.
 * This class coordinates the game flow by:
 * <ul>
 *   <li>Prompting users for player names</li>
 *   <li>Creating and initializing the game</li>
 *   <li>Running the main game loop where players draw and compare cards</li>
 *   <li>Handling user input for continuing or ending the game</li>
 * </ul>
 */
public class Main {
      /**
      * Main method that runs the High Card game.
      * The game proceeds as follows:
      * <ol>
      *   <li>Prompts for both players' names</li>
      *   <li>Creates a HighCardGame with a shuffled deck split between players</li>
      *   <li>Enters a game loop where:
      *     <ul>
      *       <li>Each player draws their top card</li>
      *       <li>Cards are displayed</li>
      *       <li>The higher card wins (or it's a tie)</li>
      *       <li>Player is asked if they want to continue</li>
      *     </ul>
      *   </li>
      *   <li>Game ends when a player runs out of cards or chooses not to continue</li>
      * </ol>
      *
      * @param args command-line arguments (not used)
      */
   public static void main(String[] args) {

        // get player names with prompt
        var player1 = Console.prompt("Player 1, what is your name? : ");
        var player2 = Console.prompt("Player 2, what is your name? : ");

        // create HighCardGame with names from prompt
        var game = new HighCardGame(player1, player2);
        // each player draws a card
        while (game.checkGameState()) {
            Card card1 = game.getPlayer1().drawFromHand();
            Card card2 = game.getPlayer2().drawFromHand();

            // cards are displayed
            Console.println(card1.toString() + " " + card2.toString());

            // compare and announce winner
            var result = game.compareCards(card1, card2);
            if (result == 1) {
                Console.println(player1 + " wins!");
            } else if (result == 2) {
                Console.println(player2 + " wins!");
            } else {
                Console.println("Its a tie!");
            }
            if (Console.promptForOption("Play another round?", new String[]{"Yes", "No"}).equals("No")) {
                break;
            }
        }
    }
}
