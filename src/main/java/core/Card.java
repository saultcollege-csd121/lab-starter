/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;
import core.CardStack.*;

 /**
 * Represents a playing card with a rank and suit.
 * This is an immutable record that combines a Rank and Suit to form a complete card.
 * Cards display as their rank symbol followed by their suit symbol (e.g., "A♥️", "K♠️").
 *
 * @param rank the rank of the card (Ace, King, Queen, etc.)
 * @param suit the suit of the card (Hearts, Spades, Clubs, Diamonds)
 */
public record Card(Rank rank, Suit suit) {


     /**
     * Returns a string representation of the card.
     * The card is displayed as the rank symbol followed by the suit symbol.
     *
     * @return a string that combines the rank and suit (e.g., "A♥️", "10♣️")
     */
        public String toString() {
            return rank.getSymbol() + suit.getSymbol();
        }

     /**
     * Represents the four suits in a standard deck of playing cards.
     * Each suit has an associated Unicode symbol for display purposes.
     */
        public enum Suit {
            HEARTS("♥️"), SPADES("♠️"), CLUBS("♣️"), DIAMONDS("♦️");

            // field to store symbol
            private final String symbol;

            // constructor to initialize symbol
            Suit(String symbol) {
                this.symbol = symbol;
            }

            // method to get symbol
            public String getSymbol() {
                return symbol;
            }

        }
     /**
      * Represents the thirteen ranks in a standard deck of playing cards.
      * Each rank has a display symbol and a numeric value for comparison.
      */
        public enum Rank {
            ACE("A", 14),
            KING("K", 13),
            QUEEN("Q", 12),
            JACK("J", 11),
            TEN("10", 10),
            NINE("9", 9),
            EIGHT("8", 8),
            SEVEN("7", 7),
            SIX("6", 6),
            FIVE("5", 5),
            FOUR("4", 4),
            THREE("3", 3),
            TWO("2", 2);

            // field to store value
            private final int value;

            // field to store symbol
            private final String symbol;

          /**
          * Constructs a Rank with the specified symbol and value.
          *
          * @param symbol the display symbol for this rank (e.g., "A", "K", "10")
          * @param value  the numeric value for comparison (Ace=14, King=13, Two=2)
          */
            Rank(String symbol, int value) {
                this.symbol = symbol;
                this.value = value;
            }


          /**
          * Returns the display symbol for this rank.
          *
          * @return the rank symbol (e.g., "A", "K", "10")
          */
            public String getSymbol() {
                return symbol;
            }

          /**
          * Returns the numeric value of this rank for comparison.
          * Higher values represent higher-ranking cards.
          *
          * @return the numeric value of the card rank
          */
            public int getValue() {
                return value;
            }

        }
}
