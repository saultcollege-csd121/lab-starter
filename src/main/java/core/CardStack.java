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

public class CardStack{

    private final ArrayList<Card> cards;

    public CardStack(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static CardStack shuffled52CardsDeck(){
        ArrayList<Card> deck = new ArrayList<>();

        for (Card.Suit suit : Card.Suit.values()){
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck);
        return new CardStack(deck);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public String toString() {
        var s = "";
        for (var card : cards) {
            s += card + " ";
        }
        return s;
    }

    public CardStack deal(int n) {
        ArrayList<Card> dealt = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            dealt.add(draw());
        }

        return new CardStack(dealt);
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public void add(Card card) {
        cards.add(card);
    }
}
