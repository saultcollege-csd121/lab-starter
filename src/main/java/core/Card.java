/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

/**
 * Java record
 * Shows a single Card
 *  consists of suits--  Clubs, Diamonds, Hearts, Spades and a rank 2-Ace
 * @param rank
 * @param suit
 */

public record Card(Rank rank, Suit suit) {
    @Override
    public  String toString() {
        return  rank.getSymbol() + suit.getSymbol();

    }
    /**
     * Returns a string of the card
     * @return the card symbol
     */
}
enum  Rank {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String symbol;
    private final int value;

    /**
     * Constructs a rank with a symbol and numeric value
     * @param symbol
     * @param value
     */

    Rank(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Gets the symbol of this rank
     *
     * @return the rank symbol
     */
    public String getSymbol() {
        return symbol;

    }

    /**
     * Gets the numeric value of this rank
     * @return the numeric value
     */

    public int value() {
        return value;
    }
    /**
     * Suit of the card
     */
}
    enum Suit {


        CLUBS ("♣"),
        DIAMONDS ("♦"),
        HEARTS("♥"),
        SPADES ("♠");

    private final String symbol;

        /**
         * Constructs a suit with a symbol
         * @param symbol
         */

    Suit(String symbol) {
        this.symbol=symbol;

    }

        /**
         * Gets the symbol of this suit
         * @return the suit symbol
         */

    public String getSymbol() {
        return symbol;
    }

}




