package core;
import java.util.Objects;

/**
 * Represents each during in the game.
 */
public class Player { //defines a type representing a player in the game

    private final String name; //final string for their name and hand for their Cardstack
    private final CardStack hand; //private using encapsulation

    public Player(String name, CardStack hand) {  //constructor creating a new player.
        this.name = name; //assigning a name and a hand to the player.
        this.hand = hand;
    }

    public String getName() {
        return name;
    } //returns player's name. Private to protect the main object
    public boolean hasCards() {
        return !hand.isEmpty();
    } //checks if the player still has cards.
    public Card drawCard() {
        return hand.drawTopCard();
    } // Take the top card.

    @Override public boolean equals(Object o) { //Inspects if the objects from the two players are equal or not.
            if (this == o) return true;
            if (!(o instanceof Player player)) return false;
            return Objects.equals(name, player.name); //players are equal if they have the same name
    }
    @Override public int hashCode() {
        return Objects.hash(name);
    } //Generates hash value based on name.
}