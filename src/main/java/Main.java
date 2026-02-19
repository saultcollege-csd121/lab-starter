import core.Card;
import core.CardStack;
import core.HighCardGame;
import core.Player;
import ui.Console;
import java.util.Scanner;

/**
 * Main entry point for the High Card game.
 * Manages game flow and user interaction.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to run the High Card game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Console.println("Welcome to High Card Game!");
        Console.println("");

        // Get player names
        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);

        // Create the game
        HighCardGame game = HighCardGame.createGame(player1Name, player2Name);

        Console.println("");
        Console.println("Game setup complete! Each player has 26 cards.");
        Console.println("");

        // Play rounds
        playGame(game);

        Console.println("");
        Console.println("Thanks for playing!");
    }

    /**
     * Prompts for and returns a player's name.
     *
     * @param playerNumber the player number (1 or 2)
     * @return the player's name
     */
    private static String getPlayerName(int playerNumber) {
        Console.println("Enter name for Player " + playerNumber + ": ");
        return scanner.nextLine();
    }

    /**
     * Main game loop that plays rounds until players choose to stop or run out of cards.
     *
     * @param game the HighCardGame instance
     */
    private static void playGame(HighCardGame game) {
        while (game.canPlayRound()) {
            Console.println("Play a round? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("y") && !response.equals("yes")) {
                break;
            }

            playRound(game);
            Console.println("");
        }

        if (!game.canPlayRound()) {
            Console.println("Game over! One or both players are out of cards.");
        }
    }

    /**
     * Plays a single round of the game.
     * Each player draws a card, and the winner is determined.
     *
     * @param game the HighCardGame instance
     */
    private static void playRound(HighCardGame game) {
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        // Draw cards
        Card card1 = player1.drawCard();
        Card card2 = player2.drawCard();

        // Display cards
        Console.println("");
        Console.println(player1.getName() + " draws: " + card1);
        Console.println(player2.getName() + " draws: " + card2);
        Console.println("");

        // Determine winner
        int comparison = card1.compareTo(card2);

        if (comparison > 0) {
            Console.println(player1.getName() + " wins this round!");
        } else if (comparison < 0) {
            Console.println(player2.getName() + " wins this round!");
        } else {
            Console.println("It's a tie!");
        }

        // Display remaining cards
        Console.println(player1.getName() + " has " + player1.getCardCount() + " cards left.");
        Console.println(player2.getName() + " has " + player2.getCardCount() + " cards left.");
    }
}