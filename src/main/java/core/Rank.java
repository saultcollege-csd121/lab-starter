package core;

/**
 * Represents the thirteen ranks in a standard deck of playing cards.
 * Ranks are ordered from lowest (TWO) to highest (ACE), with each having
 * a value and display name.
 */
public enum Rank {
    /** Rank 2 with value 2 */
    TWO(2, "2"),
    /** Rank 3 with value 3 */
    THREE(3, "3"),
    /** Rank 4 with value 4 */
    FOUR(4, "4"),
    /** Rank 5 with value 5 */
    FIVE(5, "5"),
    /** Rank 6 with value 6 */
    SIX(6, "6"),
    /** Rank 7 with value 7 */
    SEVEN(7, "7"),
    /** Rank 8 with value 8 */
    EIGHT(8, "8"),
    /** Rank 9 with value 9 */
    NINE(9, "9"),
    /** Rank 10 with value 10 */
    TEN(10, "10"),
    /** Jack with value 11 */
    JACK(11, "J"),
    /** Queen with value 12 */
    QUEEN(12, "Q"),
    /** King with value 13 */
    KING(13, "K"),
    /** Ace with value 14 (highest rank) */
    ACE(14, "A");

    private final int value;
    private final String displayName;

    /**
     * Constructs a Rank with its associated value and display name.
     *
     * @param value the numerical value of this rank for comparison
     * @param displayName the string representation of this rank
     */
    Rank(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    /**
     * Gets the numerical value of this rank.
     * Used for comparing card ranks.
     *
     * @return the rank value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the display name of this rank.
     *
     * @return the rank's display name
     */
    public String getDisplayName() {
        return displayName;
    }
}