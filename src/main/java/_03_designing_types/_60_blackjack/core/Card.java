package _03_designing_types._60_blackjack.core;

public record Card(Rank rank, Suit suit) {

    static public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES;

        public String toString() {
            return switch (this) {
                case HEARTS -> "♥";
                case DIAMONDS -> "♦";
                case CLUBS -> "♣";
                case SPADES -> "♠";
            };
        }
    }

    static public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

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
//    static public enum Rank {
//        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
//        JACK(10), QUEEN(10), KING(10), ACE(11);
//
//        private final int value;
//
//        Rank(int value) {
//            this.value = value;
//        }
//
//        public int value() {
//            return value;
//        }
//    }

    public String toString() {
        return "%s%s".formatted(rank, suit);
    }

}
