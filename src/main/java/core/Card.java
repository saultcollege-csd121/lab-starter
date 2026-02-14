/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

public record Card(Rank rank, Suit suit) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

     public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    public String toString() {
        String suitSymbol = "";
        String rankSymbol = "";


        if (suit == Suit.HEARTS) suitSymbol = "♥";
        if (suit == Suit.DIAMONDS) suitSymbol = "♦";
        if (suit == Suit.CLUBS) suitSymbol = "♣";
        if (suit == Suit.SPADES) suitSymbol = "♠";

        if (rank == Rank.TWO) rankSymbol ="2";
        if (rank == Rank.THREE) rankSymbol ="3";
        if (rank == Rank.FOUR) rankSymbol ="4";
        if (rank == Rank.FIVE) rankSymbol ="5";
        if (rank == Rank.SIX) rankSymbol ="6";
        if (rank == Rank.SEVEN) rankSymbol ="7";
        if (rank == Rank.EIGHT) rankSymbol ="8";
        if (rank == Rank.NINE) rankSymbol ="9";
        if (rank == Rank.TEN) rankSymbol ="10";
        if (rank == Rank.JACK) rankSymbol ="J";
        if (rank == Rank.QUEEN) rankSymbol ="Q";
        if (rank == Rank.KING) rankSymbol ="K";
        if (rank == Rank.ACE) rankSymbol ="A";

        return rankSymbol + " " + suitSymbol;

    }

     public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }


}
