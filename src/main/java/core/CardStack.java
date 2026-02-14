/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.*;

/**
 * Shows a full deck of cards
 *
 */

public class CardStack {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Constructs a new shuffled deck of cards
     * contains both ranks and suits
      */


    public CardStack() {

        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                cards.add(new Card(rank, suit));


    Collections.shuffle(cards);

    }

    /**
     * Deals half of the remaining cards
     * @return a list containing half of the deck
     */


    public List<Card> dealHalf () {
        int half = cards.size () / 2;

        List<Card> hand = new ArrayList<>(cards.subList(0,half));
        cards.subList(0,half).clear();

        return hand;


    }

}
