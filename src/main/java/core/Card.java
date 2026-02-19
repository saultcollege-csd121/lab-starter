/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;
import java.util.Objects;

/**
 * Represents a playing card with a rank and suit.
 * Cards can be compared based on their rank values.
 */
public class Card {
    private final Rank rank;
    private final Suit suit;

    /**
     * Constructs a Card with the specified rank and suit.
     *
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets the rank of this card.
     *
     * @return the card's rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Gets the suit of this card.
     *
     * @return the card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Compares this card to another card based on rank.
     *
     * @param other the card to compare to
     * @return positive if this card is higher, negative if lower, 0 if equal
     */
    public int compareTo(Card other) {
        return this.rank.getValue() - other.rank.getValue();
    }

    /**
     * Returns a string representation of this card.
     * Format: rank display name followed by suit symbol (e.g., "A♥", "10♣")
     *
     * @return string representation of the card
     */
    @Override
    public String toString() {
        return rank.getDisplayName() + suit.getSymbol();
    }

    /**
     * Checks if this card is equal to another object.
     * Two cards are equal if they have the same rank and suit.
     *
     * @param obj the object to compare to
     * @return true if the cards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank == card.rank && suit == card.suit;
    }

    /**
     * Returns a hash code for this card.
     *
     * @return hash code based on rank and suit
     */
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

