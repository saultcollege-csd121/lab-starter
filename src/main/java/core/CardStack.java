package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a stack of playing cards such as a deck or a player's hand.
 */
public class CardStack {
    private final List<Card> cards;

    /**
     * Creates an empty card stack.
     */
    public CardStack() {
        this.cards = new ArrayList<>();
    }

    /**
     * Creates a card stack from the given list of cards.
     * @param cards the cards to copy into this stack
     */
    public CardStack(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    /**
     * Creates a standard 52-card deck.
     * @return a new full deck
     */
    public static CardStack standardDeck() {
        List<Card> deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }

        return new CardStack(deck);
    }

    /**
     * Shuffles this card stack.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Adds one card to the bottom of the stack.
     * @param card the card to add
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Removes and returns the top card from the stack.
     * @return the removed top card
     */
    public Card drawTop() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot draw from an empty card stack.");
        }
        return cards.remove(0);
    }

    /**
     * @return true if this stack has no cards
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * @return number of cards in the stack
     */
    public int size() {
        return cards.size();
    }

    /**
     * Deals the top half of this stack into a new stack.
     * @param count number of cards to deal
     * @return a new stack containing dealt cards
     */
    public CardStack deal(int count) {
        if (count < 0 || count > cards.size()) {
            throw new IllegalArgumentException("Invalid deal count: " + count);
        }

        List<Card> dealt = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dealt.add(drawTop());
        }
        return new CardStack(dealt);
    }

    /**
     * @return an unmodifiable copy of the cards
     */
    public List<Card> cards() {
        return List.copyOf(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CardStack other)) return false;
        return Objects.equals(cards, other.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}