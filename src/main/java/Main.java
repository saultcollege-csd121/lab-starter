import core.*;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        String name1 = Console.prompt("Enter name for Player 1: ");
        String name2 = Console.prompt("Enter name for Player 2: ");

        Player p1 = new Player(name1);
        Player p2 = new Player(name2);
        CardStack deck = new CardStack();

        // 3. This creates and shuffle's the deck for the round.
        deck.createFullDeck();
        deck.shuffle();

        // 4. This deals the cards
        while (deck.hasCards()) {
            p1.getHand().addCard(deck.draw());
            p2.getHand().addCard(deck.draw());
        }

        // 5. This will loop the game
        while (p1.getHand().hasCards()) {
            String input = Console.prompt("Play a round? (y/n): ");
            if (!input.equalsIgnoreCase("y")) {
                break;
            }

            Card c1 = p1.getHand().draw();
            Card c2 = p2.getHand().draw();

            if (c1 == null || c2 == null) break;

            Console.println(p1.getName() + " drew: " + c1);
            Console.println(p2.getName() + " drew: " + c2);

            if (c1.rank().ordinal() > c2.rank().ordinal()) {
                Console.println(p1.getName() + " wins the round!");
            } else if (c2.rank().ordinal() > c1.rank().ordinal()) {
                Console.println(p2.getName() + " wins the round!");
            } else {
                Console.println("It's a tie!");
            }
            Console.println("-------------------------");
        }

        Console.println("Game Over. Thanks for playing!");
    }
}