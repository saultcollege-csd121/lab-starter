/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

 /**
 * Manages the overall state and logic of the High Card game.
 * This class is responsible for setting up the game by creating a shuffled deck,
 * splitting it between two players, and providing methods to compare cards and
 * check if the game can continue.
 */
public class HighCardGame {
    // fields to remember the players
    private final Player player1;
    private final Player player2;
    private CardStack hand1;
    private CardStack hand2;


     /**
      * Constructs a new High Card game with two players.
      * This constructor performs the following setup:
      * <ul>
      *   <li>Creates and shuffles a full 52-card deck</li>
      *   <li>Splits the deck evenly between two players (26 cards each)</li>
      *   <li>Creates Player objects with the provided names and their respective hands</li>
      * </ul>
      *
      * @param name1 the name of the first player
      * @param name2 the name of the second player
      */
    public HighCardGame(String name1, String name2){

        CardStack fullDeck = new CardStack(true);

        fullDeck.shuffleDeck();

        this.hand1 = new CardStack(false);
        this.hand2 = new CardStack(false);

        for (int i = 0; i < 26; i++){
            Card card = fullDeck.drawCard();
            hand1.addCard(card);
        }
        for (int i = 0; i < 26; i++){
            Card card = fullDeck.drawCard();
            hand2.addCard(card);
        }

        this.player1 = new Player(name1, hand1);
        this.player2 = new Player(name2, hand2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

      /**
      * Compares two cards to determine which is higher.
      * The comparison is based on the numeric value of each card's rank.
      * Ace (value 14) is the highest rank, and Two (value 2) is the lowest.
      *
      * @param card1 the first card to compare
      * @param card2 the second card to compare
      * @return 1 if card1 is higher, 2 if card2 is higher, 0 if they are equal (tie)
      */
    public int compareCards(Card card1, Card card2){
       int value1 =  card1.rank().getValue();
       int value2 =  card2.rank().getValue();
        if (value1 > value2){
            return 1;
        } else if (value1 < value2) {
            return 2;

        }
        return 0;
    }

      /**
      * Checks whether the game can continue.
      * The game can continue if both players still have cards in their hands.
      *
      * @return true if both players have cards remaining, false if either player is out of cards
      */
    public boolean checkGameState(){
        return !player1.getHand().checkIfEmpty() && !player2.getHand().checkIfEmpty();
        }
      /**
      * Compares this HighCardGame to another object for equality.
      * Two HighCardGame objects are considered equal if they have the same hands
      * for both players.
      *
      * @param obj the object to compare with
      * @return true if the objects are equal, false otherwise
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HighCardGame other = (HighCardGame) obj;
        return Objects.equals(this.hand1, other.hand1) &&
                Objects.equals(this.hand2, other.hand2);
    }
      /**
      * Returns a hash code value for this HighCardGame.
      * The hash code is based on both players' hands and player objects.
      *
      * @return a hash code value for this object
      */
    @Override
    public int hashCode() {
        return Objects.hash(hand1, hand2, player1, player2);
    }
}


