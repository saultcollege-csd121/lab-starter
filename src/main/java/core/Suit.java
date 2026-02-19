package core;

/**
 * Represents the four suits in a standard deck of playing cards.
 * Each suit has a symbol representation for display purposes.
 */
public enum Suit {
    /** Hearts suit with symbol ♥ */
    HEARTS("♥"),
    /** Diamonds suit with symbol ♦ */
    DIAMONDS("♦"),
    /** Clubs suit with symbol ♣ */
    CLUBS("♣"),
    /** Spades suit with symbol ♠ */
    SPADES("♠");

    private final String symbol;

    /**
     * Constructs a Suit with its associated symbol.
     *
     * @param symbol the Unicode symbol for this suit
     */
    Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the Unicode symbol for this suit.
     *
     * @return the suit symbol
     */
    public String getSymbol() {
        return symbol;
    }
}