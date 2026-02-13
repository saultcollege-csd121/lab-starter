package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a stack (collection) of playing cards
 *
 * <p>A CardStack can represent different collections of cards in a game</p>
 * <ul>
 *   <li>A full deck of 52 cards</li>
 *   <li>A player's hand</li>
 *   <li>A discard pile</li>
 *   <li>Any other collection of cards needed for gameplay</li>
 * </ul>
 *
 * <p>Cards are stored internally in an ArrayList, allowing for flexible
 * manipulation including drawing, dealing, adding, and shuffling cards</p>
 */
public class CardStack{

    /**
     * Internal storage for the cards in this stack
     * Cards are ordered from top (index 0) to bottom (last index)
     * Using final means the reference can't change, but the contents can
     */
    private final ArrayList<Card> cards;

    /**
     * Constructs a CardStack with the given collection of cards
     *
     * <p>This constructor allows creating various types of card collections:</p>
     * <ul>
     *   <li>A full 52-card deck</li>
     *   <li>A player's hand with specific cards</li>
     *   <li>An empty stack to add cards to later</li>
     * </ul>
     *
     * <p><b>Note:</b> The provided ArrayList is used directly (not copied),
     * so modifications to this stack will affect the original list.</p>
     */
    public CardStack(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Returns the list of cards in this stack.
     *
     * <p><b>Warning:</b> This returns the actual internal ArrayList,
     * not a copy. Modifying the returned list will modify this CardStack.</p>
     *
     * @return the ArrayList containing all cards in this stack
     */
    public ArrayList<Card> getCards() {
        return cards;
    } // getCards() > CardStack > ArrayList<Card>

    /**
     * Creates and returns a shuffled standard 52-card deck.
     *
     * <p>This factory method generates a complete deck containing:</p>
     * <ul>
     *   <li>13 ranks (2 through Ace)</li>
     *   <li>4 suits (Hearts, Diamonds, Clubs, Spades)</li>
     *   <li>Total: 13 × 4 = 52 cards</li>
     * </ul>
     *
     * @return a new CardStack containing 52 shuffled cards
     */
    public static CardStack shuffled52CardsDeck(){
        ArrayList<Card> deck = new ArrayList<>();

        for (Card.Suit suit : Card.Suit.values()){
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        // Shuffle the deck randomly
        Collections.shuffle(deck);
        return new CardStack(deck); // Return a CardStack with the shuffled cards
    }
    /**
     * Checks if this stack has no cards.
     *
     * @return true if the stack is empty, false if it contains at least one card
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    } // isEmpty() > CardStack > boolean

    /**
     * Returns the number of cards currently in this stack.
     *
     * @return the count of cards in this stack (0 or more)
     */
    public int size() { // Return how many cards are in this stack
        return cards.size();
    }

    /**
     * Returns a string representation of all cards in this stack.
     *
     * <p>Cards are displayed separated by spaces. Each card shows its
     * rank and suit (e.g., "A♠️ K❤️ 7♣️").</p>
     *
     * @return a String containing all cards separated by spaces,
     * or an empty string if the stack is empty
     */
    public String toString() { // Convert all cards in the stack to a String
        var s = "";
        for (var card : cards) {
            s += card + " ";
        }
        return s;
    }

    /**
     * Deals a specified number of cards from this stack into a new stack
     *
     * <p>This method:</p>
     * <ol>
     *   <li>Removes cards from the top of this stack</li>
     *   <li>Places them into a new CardStack</li>
     *   <li>Returns the new stack with the dealt cards</li>
     * </ol>
     *
     * @param n the number of cards to deal
     * @return a new CardStack containing the dealt cards
     */
    public CardStack deal(int n) { // Deal from this stack and return a new CardStack
        ArrayList<Card> dealt = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            dealt.add(draw());
        }

        return new CardStack(dealt); // Remove cards from this stack and put into another stack
    }

    /**
     * Draws (removes and returns) one card from the top of this stack
     *
     * <p>The card at index 0 is considered the "top" of the stack
     * This method removes that card from the stack and returns it.</p>
     *
     * <p><b>Important:</b> This modifies the stack by removing the card.
     * After calling draw(), the stack will have one fewer card</p>
     *
     * @return the Card from the top of the stack
     */
    public Card draw() {
        return cards.removeFirst();
    }

    /**
     * Adds a card to the bottom of this stack
     *
     * <p>The card is appended to the end of the internal ArrayList,
     * making it the last card in the stack.</p>
     *
     * @param card the Card to add to this stack
     */
    public void add(Card card) { // Add one card to the stack
        cards.add(card);
    }
}
