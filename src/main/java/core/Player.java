package core;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The main actors in the card game
 */
public class Player {
    private final String name;
    private final CardStack hand; // containing only 26 cards
    private int score;

    /**
     * Default Constructor of the @Player class
     * @param name the String representing the player's name
     * @param hand the CardStack representing the player's hand
     */
    public Player(String name, CardStack hand){
        this.name = name;
        this.hand = hand;   // the Player's hand must be shuffled before constructing.
        score = 0;  // A new Player's score is set to 0.
    }

    public void increaseScore(){
        score++;
    }

    public void resetScore(){
        score = 0;
    }

    public String getName() { return name; }

    public int getScore() {
        return score;
    }

    /**
     * Simple boolean method to check whether a Player's hand is empty or not.
     * @return true if deck has cards, false otherwise
     */
    public boolean hasCards() {
        return hand.remainingCards() != 0;
    }

    /**
     * Wrapper method around the @shuffle() method in the @CardStack class
     */
    public void shuffleHand(){
        hand.shuffle();
    }

    /**
     * Wrapper method around the @drawOne() method in the @CardStack class
     * @return the top Card of the player's hand
     * @throws NoSuchElementException a throwable from @ArrayList's @removeFirst() method
     */
    public Card playCard() throws NoSuchElementException {
        return hand.drawOne();
    }

    /**
     * Overwritten method for @toString()
     * @return the String representation for this Player
     */
    public String toString(){
        return "Player: %s\nScore: %d\n".formatted(name, score);
    }

    /**
     * Overwritten method for @equals()
     * @param other the reference object with which to compare
     * @return whether @other is equal to this object
     */
    public boolean equals(Object other){
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Player otherPlayer = (Player)other;
        return name.equals(otherPlayer.name)
                && hand.equals(otherPlayer.hand)
                && score == otherPlayer.score;
    }

    /**
     * Overwritten method for @hashCode()
     * @return a hashed version of this object
     */
    public int hashCode(){
        return Objects.hash(name, hand, score);
    }
}
