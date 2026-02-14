package core;

/**
 * The base of any card game. Defines each Card's rank and suit
 * @param rank
 * @param suit
 */
public record Card(Rank rank, Suit suit) {

    static public enum Suit {
        HEARTS('♥'), DIAMONDS('♦'), CLUBS('♣'), SPADES('♠');

        private final char symbol;

        Suit(char symbol) {
            this.symbol = symbol;
        }

        public char symbol() {
            return symbol;
        }
    }

    static public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10),
        ACE(11);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    /**
     * Overwritten method for @toString()
     * @return the String representation for this Card
     */
    public String toString() {
        return switch (rank) {
            case ACE -> "A%s".formatted(suit().symbol());
            case JACK -> "J%s".formatted(suit().symbol());
            case QUEEN -> "Q%s".formatted(suit().symbol());
            case KING -> "K%s".formatted(suit().symbol());
            default -> "%d%s".formatted(rank().value(), suit().symbol());
        };
    }

    /**
     * Overwritten method for @equals()
     * @param other the reference object with which to compare
     * @return whether @other is equal to this object
     */
    public boolean equals(Object other){
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Card otherCard =  (Card)other;
        return suit.equals(otherCard.suit)
                && rank.equals(otherCard.rank);
    }
}
