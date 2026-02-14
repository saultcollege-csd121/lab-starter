package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*** This manages the collection of cards for the deck.*/
public class CardStack {
    private final List<Card> cards = new ArrayList<>();

    public void createFullDeck() {
        cards.clear();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                cards.add(new Card(r, s));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}