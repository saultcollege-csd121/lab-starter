import core.CardStack;
import core.HighCardGame;
import core.Player;
import ui.Console;
import core.Card;


/**
 * Prompts the players for their names
 * Creates and shuffles the deck
 * Deals card to each player
 * Runs a loop
 * interacts with the console
 *
 */


public class Main {

    public static void main(String[] args) {


        Console.println("--------HIGH CARD--------");

        /**
         * Prompt the player for names
         */

        String n1 = Console.prompt("Player 1: ");
            String n2 = Console.prompt("Player 2: ");

        /**
         * Create and shuffle deck
         */

        CardStack deck = new CardStack();

        /**
         * Deals half the deck to each player
         */

        Player p1 = new Player(n1, deck.dealHalf());
            Player p2 = new Player(n2, deck.dealHalf());

        /**
         * Create game controller
         */

        HighCardGame game = new HighCardGame(p1, p2);

        /**
         * Main game loop
         */

        while (game.canPlay()) {
                Card[] drawn = new Card[2];
                int winner = game.play(drawn);

                Console.println(n1 + " drew " + drawn[0]);
                Console.println(n2 + " drew " + drawn[1]);

                if (winner == 1) {
                    Console.println("Winner: " + n1);
                } else if (winner == 2) {
                    Console.println("Winner: " + n2);
                } else {
                    Console.println("Tie!");

                }

                String againRound = Console.prompt("Play a new round? (y/n): ");

                if (!againRound.equalsIgnoreCase("y")) {
                    break;
                }
                Console.println("");


            }

            Console.println("Game Over.");

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

        }
    }
