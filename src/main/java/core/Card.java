package core;
import java.util.Objects;      //hashCode() returns an integer hash value for the object.

/**
 * Represents a single playing card.
 */
public class Card {    //new type defined

    public enum Suit {   //enum is a fixed set of constants.
        HEARTS("♥️"),  // each symbol is a string passed to the constructor.
        DIAMONDS("♦️"),
        CLUBS("♣️"),
        SPADES("♠️");

        private final String symbol; //each suit has a symbol value
        Suit(String symbol) {
            this.symbol = symbol;
        } //when we create the symbol it calls our string.
        public String getSymbol() {
            return symbol;
        } //Allowing other classes to access the symbol safely.
    }

        public enum Rank {
        TWO(2, "2"), //for each number we assign a value and a string that represents the value.
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(11, "J"),
        QUEEN(12, "Q"),
        KING(13, "K"),
        ACE(14, "A"); //Ace is the higher, following the indications.

        private final int number; //stores a numeric value
        private final String display; //stores a display string

        Rank(int value, String display) { //running in the enum creation
            this.number = value;
            this.display = display;
        }
        public int getValue() {
            return number;
        }   //allowing safe access to private data
        public String getDisplay() {
            return display;
        }
    }
    private final Rank rank;  //each card has one rank and one suit. the 'final' indicates that the card never should change after being created.
    private final Suit suit;

    public Card(Rank rank, Suit suit) { //constructor creating a new card
        this.rank = rank;
        this.suit = suit;
    }
    public Rank getRank() { //allow controlled access, the getters instead of public variables are better because encapsulation.
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }
    public int compareRank(Card other) {  //Compares cards and returns positive if the card is higher, negative if the other one is higher and zero if is a tie.
        return Integer.compare(this.rank.getValue(), other.rank.getValue());
    }

    @Override public String toString() { //Control how the card is printed.
        return rank.getDisplay() + suit.getSymbol();
    }
    @Override public boolean equals(Object o) { //Checking if both cards are equal
        if (this == o) return true; //checking if is the same memory object
        if (!(o instanceof Card card)) return false; //checking if it is not a card
        return rank == card.rank && suit == card.suit; //Both cards must be the same rank and the same suit to be equals
    }
    @Override public int hashCode() { //Generates a hash value, and since "equals" is overridden, hashCode()must be overridden too.
        return Objects.hash(rank, suit);
    }
}