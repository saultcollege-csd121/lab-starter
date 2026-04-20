package core;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    public int compareRank(Card other) {
        return Integer.compare(this.rank.value(), other.rank.value());
    }
    public int getValue() {
        return rank.value(); // ← THIS is why value() must exist
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}