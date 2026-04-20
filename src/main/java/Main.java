import core.HighCardGame;
import ui.Console;

import java.util.List;

public class Main {

    static void main() {
        List<String> names = Console.promptForNInputs("Enter player name", 2);

        HighCardGame game = new HighCardGame(names.get(0), names.get(1));

        while (game.canPlayRound()) {
            String choice = Console.promptForOption("Play another round?", new String[]{"y", "n"});
            if (choice.equalsIgnoreCase("n")) {
                break;
            }

            HighCardGame.RoundResult result = game.playRound();

            Console.println(result.player1().getName() + " drew " + result.card1());
            Console.println(result.player2().getName() + " drew " + result.card2());

            if (result.isTie()) {
                Console.println("It's a tie!");
            } else {
                Console.println(result.winner().getName() + " wins the round!");
            }

            Console.println(result.player1().getName() + " has " + result.player1().getHand().size() + " cards left.");
            Console.println(result.player2().getName() + " has " + result.player2().getHand().size() + " cards left.");
            Console.println("");
        }

        Console.println("Game over.");
    }
}