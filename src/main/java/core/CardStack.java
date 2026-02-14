/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CardStack {
    /**
     * Creates a deck with every card
     * @return The 52 unique card deck
     */
    public static List<Card> makeDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }

    /**
     * Splits a full deck in two halfs
     * @return two hands of 26 unique cards
     */
    public static List<List<Card>> splitDeck(List<Card> fullDeck) {
        Collections.shuffle(fullDeck);
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();

        for (int i = 0; i < fullDeck.size(); i++) {
            if (i % 2 == 0) hand1.add(fullDeck.get(i));
            else hand2.add(fullDeck.get(i));
        }

        List<List<Card>> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);

        return hands;
    }

}



