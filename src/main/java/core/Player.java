package core;

import java.util.Objects;

/**
 * Represents one player in the high card game.
 */
public class Player {
    private final String name;
    private final CardStack hand;

    /**
     * Creates a player with the given name and hand.
     * @param name the player's name
     * @param hand the player's hand of cards
     */
    public Player(String name, CardStack hand) {
        this.name = name;
        this.hand = hand;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the player's hand
     */
    public CardStack getHand() {
        return hand;
    }

    /**
     * Draws the top card from the player's hand.
     * @return the drawn card
     */
    public Card drawTopCard() {
        return hand.drawTop();
    }

    /**@return true if the player still has cards
     */
    public boolean hasCards() {
        return !hand.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player other)) return false;
        return Objects.equals(name, other.name) && Objects.equals(hand, other.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }

    @Override
    public String toString() {
        return name;
    }
}