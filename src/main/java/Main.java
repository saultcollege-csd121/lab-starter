import core.Card;
import core.CardStack;
import core.HighCardGame;
import ui.Console;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Main {

    /**
     * Main execution of the program is as follows:
     *
     * <ol>
     *     <li>Prompt for player names (2)</li>
     *     <li>Create a deck of cards and shuffle it</li>
     *     <li>Cut the deck in half and hand it to both players</li>
     *     <li>Keep playing rounds/games until the player wishes to exit</li>
     * </ol>
     */
    static void main() {
        String name1 = Console.prompt("What is your player's name?\n");
        String name2 = Console.prompt("What is your rival's name?\n");

        CardStack mainStack = new CardStack();
        HighCardGame currentGame = new HighCardGame(name1, name2, mainStack);
        boolean exit = false;
        int round = 0;
        int gameCounter = 1;
        while (!exit) {
            // ask to continue next round
            String tempStr = Console.promptForOption("Want to continue playing?", new String[] {"y", "n"});
            if (Objects.equals(tempStr, "n")) {
                exit = true;
            }
            Console.println("\n%s's Score: %d".formatted(currentGame.getPlayer().getName(),
                    currentGame.getPlayer().getScore()));
            Console.println("%s's Score: %d".formatted(currentGame.getRival().getName(),
                    currentGame.getRival().getScore()));
            Console.println("");
            round ++;
            ArrayList<Card> plays = currentGame.playRound(); // Only two cards
            Console.println("Game #%d".formatted(gameCounter));
            Console.println("Round #%d".formatted(round));
            Console.println("%s plays %s!".formatted(currentGame.getPlayer().getName(), plays.getFirst()));
            Console.println("%s plays %s!".formatted(currentGame.getRival().getName(), plays.getLast()));
            Console.println("");
            try {
                int outcome = currentGame.roundResult(plays.removeFirst(), plays.removeLast());
                if (outcome == 0) {
                    Console.println("It's a Tie!\tNo points for anyone!");
                } else if (outcome == 1) {
                    Console.println("%s Wins!".formatted(currentGame.getPlayer().getName()));
                    Console.println("%s's Score: %d".formatted(currentGame.getPlayer().getName(),
                            currentGame.getPlayer().getScore()));
                } else if (outcome == 2) {
                    Console.println("%s Wins!".formatted(currentGame.getRival().getName()));
                    Console.println("%s's Score: %d".formatted(currentGame.getRival().getName(),
                            currentGame.getRival().getScore()));
                }
                if (outcome == 3) {
                    Console.println("Player %s has no more cards\n".formatted(currentGame.getPlayer().getName()));
                    tempStr = Console.promptForOption("Do you wish to make a new game?\n", new String[]{"y", "n"});
                    if (Objects.equals(tempStr, "y")) {
                        mainStack = new CardStack();
                        currentGame = new HighCardGame(name1, name2, mainStack);
                        gameCounter++;
                    } else {
                        exit = true;
                    }
                } else if (outcome == 4) {
                    Console.println("Player %s has no more cards".formatted(currentGame.getRival().getName()));
                    tempStr = Console.promptForOption("Do you wish to make a new game?\n", new String[]{"y", "n"});
                    if (Objects.equals(tempStr, "y")) {
                        mainStack = new CardStack();
                        currentGame = new HighCardGame(name1, name2, mainStack);
                        gameCounter++;
                    } else {
                        exit = true;
                    }
                }
                Console.println("\n");
            } catch (NoSuchElementException e) {
                Console.println(e.getMessage());
            }

        }
    }
}
