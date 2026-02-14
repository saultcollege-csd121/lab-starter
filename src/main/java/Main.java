import core.Card;
import core.CardStack;
import core.HighCardGame;
import core.Player;

import static ui.Console.prompt;

void main() {

    List<Card> fullDeck = CardStack.makeDeck();
    List<List<Card>> hands = CardStack.splitDeck(fullDeck);

    String user1 = prompt("User 1 whats your name?: ");
    String user2 = prompt("User 2 whats your name?: ");

    Player player1 = new Player(user1, hands.get(0));
    Player player2 = new Player(user2, hands.get(1));

    HighCardGame game = new HighCardGame();

    for (int i = 0; i < 26; i++) {

        Object[] result = game.playOneRound(player1, player2);

        Card c1 = (Card) result[0];
        Card c2 = (Card) result[1];
        Player winner = (Player) result[2];

        IO.println(player1.name + " drew: " + c1);
        IO.println(player2.name + " drew: " + c2);
        // replace c2/c1 with E.g. “A♥”, “10♣” etc.

        if (winner == null) {
            IO.println("Tie round!");
        } else {
            IO.println(winner.name + " wins the round!");
        }

        String continueGame = prompt("Press Enter to continue or type 'quit' to end: ");
        if (continueGame.equalsIgnoreCase("quit")) {
            break;
        }
    }

    IO.println(player1.name + " points: " + player1.points);
    IO.println(player2.name + " points: " + player2.points);


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