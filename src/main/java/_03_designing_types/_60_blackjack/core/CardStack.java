package _03_designing_types._60_blackjack.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack {

    public ArrayList<Card> getCards() {
        return cards;
    }

    private final ArrayList<Card> cards;

    public CardStack(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public static CardStack shuffled52CardDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add( new Card(rank, suit) );
            }
        }

        Collections.shuffle(deck);
        return new CardStack(deck);
    }

    public String toString() {
        var s = "";
        for ( var card : cards ) {
            s += card + " ";
        }
        return s;
    }

    public CardStack deal(int n) {
        var dealtCards = List.of(this.cards.get(0), this.cards.get(1));
        // remove the dealt cards from the stack
        cards.removeAll(dealtCards);
        return new CardStack(new ArrayList<>(dealtCards));
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public void add(Card card) {
        cards.add(card);
    }


}
