/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a stack of playing cards.
 * Supports operations like drawing cards, checking if empty, and getting the size.
 */
public class CardStack {
    private final List<Card> cards;

    /**
     * Constructs an empty CardStack.
     */
    public CardStack() {
        this.cards = new ArrayList<>();
    }

    /**
     * Constructs a CardStack with the specified list of cards.
     *
     * @param cards the initial list of cards
     */
    public CardStack(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    /**
     * Adds a card to the top of the stack.
     *
     * @param card the card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Draws (removes and returns) the top card from the stack.
     *
     * @return the top card, or null if the stack is empty
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack has no cards, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gets the number of cards in the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        return cards.size();
    }

    /**
     * Shuffles the cards in this stack.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Creates a standard 52-card deck.
     *
     * @return a CardStack containing all 52 cards in a standard deck
     */
    public static CardStack createFullDeck() {
        CardStack deck = new CardStack();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.addCard(new Card(rank, suit));
            }
        }
        return deck;
    }

    /**
     * Splits this deck into two equal halves.
     *
     * @return an array of two CardStacks, each containing half the cards
     */
    public CardStack[] split() {
        int halfSize = cards.size() / 2;
        List<Card> firstHalf = new ArrayList<>(cards.subList(0, halfSize));
        List<Card> secondHalf = new ArrayList<>(cards.subList(halfSize, cards.size()));
        return new CardStack[]{new CardStack(firstHalf), new CardStack(secondHalf)};
    }

    /**
     * Checks if this CardStack is equal to another object.
     * Two CardStacks are equal if they contain the same cards in the same order.
     *
     * @param obj the object to compare to
     * @return true if the stacks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CardStack cardStack = (CardStack) obj;
        return Objects.equals(cards, cardStack.cards);
    }

    /**
     * Returns a hash code for this CardStack.
     *
     * @return hash code based on the cards list
     */
    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
