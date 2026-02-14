package core;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A stack containing 52 Card objects at most
 */
public class CardStack {
    private final ArrayList<Card> deck;

    /**
     * Default Constructor
     * Creates and shuffles a new 52 Card Deck
     */
    public CardStack(){
        deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        this.shuffle();
    }

    /**
     * Constructor method used for cutting a deck.
     * @param halfStack A cut deck by half
     */
    public CardStack(ArrayList<Card> halfStack){
        deck = halfStack;
        this.shuffle();
    }

    /**
     * Dynamically cuts the CardStack in half depending on its size.
     * @return a List containing the two split halves of 52-card stack
     */
    public ArrayList<CardStack> cutDeck() {
        ArrayList<CardStack> cutStacks = new ArrayList<>();
        // for both halves of the stacks
        CardStack tempStack = null;
        ArrayList<Card> halfDeck = new ArrayList<>();
        // for half the size of the original stack
        for (int j = 0; j < 26; j++) {
            halfDeck.add(j, deck.removeFirst());
        }
        tempStack = new CardStack(halfDeck);
        cutStacks.addFirst(tempStack);
        halfDeck = new ArrayList<>();
        // for half the size of the original stack
        for (int j = 0; j < 26; j++) {
            halfDeck.add(j, deck.removeFirst());
        }
        tempStack = new CardStack(halfDeck);
        cutStacks.addFirst(tempStack);
        return cutStacks;
    }

    /**
     * Shuffles the deck by swapping card positions
     */
    public void shuffle() {
        for (Card curr: deck) {
            // Bubble logic for switching the curr Card with any random position in the deck
            int tempIndex = deck.indexOf(curr);
            Card temp = deck.set((int)(Math.random()*deck.size()), curr);
            try {
                deck.set(tempIndex, temp);
            } catch (IndexOutOfBoundsException e) {
                // default to append the card to the Stack.
                deck.add(temp);
            }
        }
    }

    /**
     * Deletes the top card in the stack and returns it.
     * Throws: NoSuchElementException
     * @return the Top Card of the Stack
     */
    public Card drawOne() throws NoSuchElementException { return deck.removeFirst(); }

    /**
     * @return the amount of Cards left in the Stack
     */
    public int remainingCards(){ return deck.size(); }

    /**
     * Overwritten method for @toString()
     * @return the String representation for this CardStack
     */
    public String toString(){
        return deck.toString();
    }

    /**
     * Overwritten method for @equals()
     * @param other the reference object with which to compare
     * @return whether @other is equal to this object
     */
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()){
            return false;
        }

        CardStack otherCardStack = (CardStack)other;
        return this.deck.equals(otherCardStack.deck);
    }

    /**
     * Overwritten method for @hashCode()
     * @return a hashed version of this object
     */
    public int hashCode(){ return Objects.hash(deck);}
}
