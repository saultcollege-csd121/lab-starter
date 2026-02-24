package core;
import java.util.ArrayList; //Array to store cards
import java.util.Collections; //shuffle deck
import java.util.List;  //use interface type

/**
 * Represents a stack of cards (deck or hand).
 */
public class CardStack { //class to represent a deck (player hand)

    private final List<Card> cards; //reference which never changes. internal storage

    public CardStack(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    } //constructor creating a new stack
    //we ust copy the list to prevent external code from modifying our own list
    public static CardStack createShuffledDeck() {
        List<Card> deck = new ArrayList<>(); //Creating an empty list to hold cards

        for (Card.Suit suit : Card.Suit.values()) {   //Returning all enum constants
            for (Card.Rank rank : Card.Rank.values()) { //It loops into all the ranks
                deck.add(new Card(rank, suit)); //And then it creates every combination. The 52 cards.
            }
        }

        Collections.shuffle(deck); //Random order.
        return new CardStack(deck); //Put the list into the CardStack
    }
    public Card drawTopCard() { //Removes and Returns the top card.
        if (cards.isEmpty()) { //returns null if the card is empty
            return null;
        }
        return cards.remove(0);//removes first element using index.
    }
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    public int size() {
        return cards.size();
    }   //Returns number of cards
    public List<Card> getCards() {
        return new ArrayList<>(cards);

    } //Returning the copy of the list. As I mentioned before we don't wanna modify the original list
}