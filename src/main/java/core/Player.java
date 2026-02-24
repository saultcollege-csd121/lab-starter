/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;


import java.util.Objects;

 /**
 * Represents a player in the High Card game.
 * Each player has a name and a hand of cards that they draw from during gameplay.
 */
public class Player {
    //field to store player names
    private final String name;
    // field to store player hands
    private CardStack hand;

      /**
      * Constructs a new Player with the specified name and hand of cards.
      *
      * @param name the player's name
      * @param hand the player's initial hand of cards
      */
    public Player(String name, CardStack hand) {
        this.name = name;
        this.hand = hand;
    }
      /**
      * Returns the player's current hand of cards.
      *
      * @return the CardStack representing this player's hand
      */
    public CardStack getHand() {
        return hand;
    }

      /**
      * Draws and returns the top card from the player's hand.
      * This removes the card from the player's hand.
      *
      * @return the card drawn from the top of the player's hand
      * @throws java.util.NoSuchElementException if the player's hand is empty
      */
    public Card drawFromHand(){
        return hand.drawCard();
    }
     /**
      * Compares this Player to another object for equality.
      * Two Players are considered equal if they have the same name and the same hand.
      *
      * @param obj the object to compare with
      * @return true if the objects are equal, false otherwise
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Player other = (Player) obj;
        return Objects.equals(this.name, other.name) &&
                Objects.equals(this.hand, other.hand);
    }
      /**
      * Returns a hash code value for this Player.
      * The hash code is based on the player's name and hand.
      *
      * @return a hash code value for this object
      */
    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }

}
