/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents a single playing card from a standard 52-card deck.
 * Each card has a rank (2-10, Jack, Queen, King, Ace) and a suit
 * (Hearts, Diamonds, Clubs, Spades).
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 * Card aceOfSpades = new Card(Rank.ACE, Suit.SPADES);
 * System.out.println(aceOfSpades); // Prints: A♠️
 * </pre>
 */
public record Card(Rank rank, Suit suit){

    /**
     * Enum representing the four suits in a standard deck of cards.
     * Each suit has a visual symbol representation (emoji).
     *
     * <p>The suits are:</p>
     * <ul>
     *   <li>HEARTS (❤️)</li>
     *   <li>DIAMONDS (♦️)</li>
     *   <li>CLUBS (♣️)</li>
     *   <li>SPADES (♠️)</li>
     * </ul>
     */
    static public enum Suit{
        HEARTS, DIAMONDS, CLUBS, SPADES;

        /**
         * This toString() method change how Suit is printed
         * switch(this) means check which suit is this object now
         * Returns the emoji symbol for this suit
         *
         * <p>This overrides the default toString() to display a visual
         * symbol instead of the enum name (e.g., "❤️" instead of "HEARTS").</p>
         *
         * @return a String containing the suit's emoji symbol
         */
        public String toString() {
            return switch (this) {
                case HEARTS -> "❤️";
                case DIAMONDS -> "♦️";
                case CLUBS -> "♣️";
                case SPADES -> "♠️";
            };
        }
    }

    /**
     * Enum representing the thirteen ranks in a standard deck of cards.
     * Ranks are ordered from lowest (TWO) to highest (ACE).
     *
     * <p>This ordering is used for comparing cards in game logic.
     * The ordinal values increase from TWO (0) to ACE (12), making
     * it easy to determine which card is higher.</p>
     *
     * <p><b>Example comparison:</b></p>
     * <pre>
     * Rank.ACE.ordinal() > Rank.KING.ordinal() // true, ACE is higher
     * </pre>
     */
    static public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        /**
         * Returns the short-form representation of this rank.
         *
         * <p>Converts rank names to standard card notation:</p>
         * <ul>
         *   <li>Number cards (TWO-TEN) become "2"-"10"</li>
         *   <li>Face cards become single letters: "J", "Q", "K", "A"</li>
         * </ul>
         *
         * @return a String containing the rank's display symbol (e.g., "A", "K", "7")
         */
        public String toString() {
            return switch (this) {
                case TWO -> "2";
                case THREE -> "3";
                case FOUR -> "4";
                case FIVE -> "5";
                case SIX -> "6";
                case SEVEN -> "7";
                case EIGHT -> "8";
                case NINE -> "9";
                case TEN -> "10";
                case JACK -> "J";
                case QUEEN -> "Q";
                case KING -> "K";
                case ACE -> "A";
            };
        }
    }

    /**
     * Returns a string representation of this card in standard notation.
     * Combines the rank symbol with the suit emoji.
     *
     * @return a String in the format "RankSuit" (e.g., "A♠️", "7❤️")
     */
    public String toString() {
        return "%s%s".formatted(rank, suit);
    }

}
