package _03_designing_types._60_blackjack;

import _03_designing_types._60_blackjack.core.BlackJackGame;
import _03_designing_types._60_blackjack.ui.Console;

public class Main {

    void main() {

        var game = new BlackJackGame();

        Console.println("Dealer's hand: %s".formatted(game.getDealerHand()));
        Console.println("Player's hand: %s".formatted(game.getPlayerHand()));

        while (true) {
            var response = Console.promptForOption("Hit or stand?", new String[]{"h", "s"});

            if ( response.equals("s") ) {
                Console.println("Player stands.");
                break;
            } else {

                game.playerHits();
                Console.println("Player's hand is now: %s".formatted(game.getPlayerHand()));

                var score = BlackJackGame.getHandValue(game.getPlayerHand());

                if ( score > 21 ) {
                    Console.println("Player busts with %d points!".formatted(score));
                    return;
                } else if ( score == 21 ) {
                    Console.println("Player wins with Blackjack!");
                    break;
                }
            }
        }


    }
}
