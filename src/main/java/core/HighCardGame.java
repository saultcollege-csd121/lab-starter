package core;
import java.util.List;

/**
 * Is responsible for all the logic during the game.
 */
public class HighCardGame { //Defining a class which is responsible for the game logic
    private final Player player1; //declaring two players that never change once the game starts
    private final Player player2;
    private int player1Score = 0;
    private int player2Score = 0;

    public HighCardGame(String name1, String name2) { //constructor that runs when a new game is created
    //the arguments are the names of the players
        CardStack deck = CardStack.createShuffledDeck(); //calling a static method

        List<Card> allCards = deck.getCards(); //make a copy again to not manipulate the original cards

        CardStack hand1 = new CardStack(allCards.subList(0, 26)); //dividing the deck
        CardStack hand2 = new CardStack(allCards.subList(26, 52));

        this.player1 = new Player(name1, hand1); //now players are objects and each player has a name and their own hand.
        this.player2 = new Player(name2, hand2);
    }
    public boolean canPlay() {
        return player1.hasCards() && player2.hasCards();
    } //checking if the game should continue.

    public String playRound() { //method to compare cards in only one round.

        Card card1 = player1.drawCard(); //Each player draws top card.
        Card card2 = player2.drawCard();

        int compare = card1.compareRank(card2); //after the card class are compared...
                                                   //we open an if block to determinate who is the winner.
        if (compare > 0) {
            player1Score++;                       //this updates the score and returns the next line if player 1 wins.
            return player1.getName() + " wins! (" + card1 + " vs " + card2 + ")"; //just the variables with some strings.
        } else if (compare < 0) { //same logic with player 2.
            player2Score++;
            return player2.getName() + " wins! (" + card1 + " vs " + card2 + ")";
        } else {         //and if there is no winner is because is a Tie!
            return "Tie! (" + card1 + " vs " + card2 + ")";
        }
    }
    public String getScore() {  //returns the string
        return player1.getName() + ": " + player1Score +   //we build a friendly return where you can see the name of the player with a bar and their score.
                " | " +
                player2.getName() + ": " + player2Score;
    }
    public String getFinalWinner() {  //determines the final winner (who wins more rounds)
        if (player1Score > player2Score) { //with an if block,it compares scores and using the same logic than the points block, returns the proper message.
            return player1.getName() + " wins the game!";
        } else if (player2Score > player1Score) {
            return player2.getName() + " wins the game!";
        } else {
            return "The game ends in a tie!";
        }
    }
}