/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import core.Card.*;

 /**
 * Represents a stack of playing cards, which can be a full deck or a player's hand.
 * Provides operations for creating, shuffling, drawing, and managing cards.
 * A CardStack can be initialized as a full 52-card deck or as an empty collection.
 */
public class CardStack {

    private final ArrayList<Card> deck;

     /**
     * Creates a new CardStack containing all 52 cards of a standard deck.
     * Cards are created in a specific order (all ranks for each suit).
     * The deck should be shuffled after creation for random card order.
     */
    public CardStack() {
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deck.add(new Card(r, s));
            }
        }
    }

      /**
      * Creates a new CardStack that is either full or empty.
      * This constructor allows for creating empty card stacks that can be
      * populated later (useful for splitting decks between players).
      *
      * @param createFull if true, creates a full 52-card deck; if false, creates an empty stack
      */
    public CardStack(boolean createFull) {
        deck = new ArrayList<>();
        if (createFull) {
            for (Suit s : Suit.values()) {
                for (Rank r : Rank.values()) {
                    deck.add(new Card(r, s));
                }
            }
        }
    }

      /**
      * Returns the number of cards currently in this stack.
      *
      * @return the count of cards in the stack
      */
    public int getDeckSize() {
        return deck.size();
    }

      /**
      * Randomly shuffles the cards in this stack.
      * Uses Collections.shuffle() to randomize the order of cards.
      *
      * @return the shuffled deck
      */
    public ArrayList<Card> shuffleDeck() {
        Collections.shuffle(deck);
        return deck;
    }

      /**
      * Draws (removes and returns) the top card from this stack.
      * The "top" card is considered to be the first card in the internal list.
      *
      * @return the card that was drawn from the top of the stack
      * @throws java.util.NoSuchElementException if the stack is empty
      */
    public Card drawCard() {
        return deck.removeFirst();
    }

      /**
      * Checks whether this stack is empty (contains no cards).
      *
      * @return true if the stack has no cards, false otherwise
      */
    public boolean checkIfEmpty() {
        return deck.isEmpty();
    }
      /**
      * Adds a card to this stack.
      * The card is added to the end of the internal list.
      *
      * @param card the card to add to this stack
      */
    public void addCard(Card card){
        deck.add(card);
    }
      /**
      * Compares this CardStack to another object for equality.
      * Two CardStacks are considered equal if they contain the same cards
      * in the same order.
      *
      * @param obj the object to compare with
      * @return true if the objects are equal, false otherwise
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CardStack other = (CardStack) obj;
        return Objects.equals(this.deck, other.deck);
    }
      /**
      * Returns a hash code value for this CardStack.
      * The hash code is based on the cards in the stack.
      *
      * @return a hash code value for this object
      */
    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }
}
