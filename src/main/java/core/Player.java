/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

/**
 * Represents a player in the high card game.
 * Each player has a name and a hand of cards.
 */
public class Player {
    private final String name;
    private final CardStack hand;

    /**
     * Constructs a Player with the specified name and an empty hand.
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.hand = new CardStack();
    }

    /**
     * Constructs a Player with the specified name and hand of cards.
     *
     * @param name the player's name
     * @param hand the player's initial hand of cards
     */
    public Player(String name, CardStack hand) {
        this.name = name;
        this.hand = hand;
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand of cards.
     *
     * @return the player's card stack
     */
    public CardStack getHand() {
        return hand;
    }

    /**
     * Draws a card from the player's hand.
     *
     * @return the drawn card, or null if the hand is empty
     */
    public Card drawCard() {
        return hand.drawCard();
    }

    /**
     * Checks if the player has any cards left in their hand.
     *
     * @return true if the player has cards, false otherwise
     */
    public boolean hasCards() {
        return !hand.isEmpty();
    }

    /**
     * Gets the number of cards remaining in the player's hand.
     *
     * @return the number of cards
     */
    public int getCardCount() {
        return hand.size();
    }

    /**
     * Checks if this Player is equal to another object.
     * Two Players are equal if they have the same name and hand.
     *
     * @param obj the object to compare to
     * @return true if the players are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return Objects.equals(name, player.name) && Objects.equals(hand, player.hand);
    }

    /**
     * Returns a hash code for this Player.
     *
     * @return hash code based on name and hand
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}