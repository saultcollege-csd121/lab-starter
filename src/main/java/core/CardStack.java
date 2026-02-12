/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static core.Card.*;


public class CardStack {
    //establishes all predetermined variables that are all going to be the same type
    static ArrayList<Card> deck = new ArrayList<>();
    public static ArrayList<Card> playerDeck = new ArrayList<>();
    public static ArrayList<Card> dealerDeck = new ArrayList<>();
    public static int playerHandValue;
    public static int dealerHandValue;


    //method that loops through two lists to create a proper list of cards
    public static void shuffleDeck() {
        ArrayList<String> Ranks = new ArrayList<String>(Arrays.asList("TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "KING", "QUEEN", "ACE"));
        ArrayList<String> Suits = new ArrayList<String>(Arrays.asList("HEARTS", "CLUBS", "SPADES", "DIAMONDS"));
        for (String rank : Ranks) {
            for (String suit : Suits) {
                deck.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(deck);
    }

    //adds to two separate hands from one shuffled deck, creating two usable starting decks
    public static void makeHands() {
        playerDeck.add(deck.getFirst());
        deck.removeFirst();
        playerDeck.add(deck.getFirst());
        deck.removeFirst();

        dealerDeck.add(deck.getFirst());
        deck.removeFirst();
        dealerDeck.add(deck.getFirst());
        deck.removeFirst();
    }

    //adds a card to the player's hand. I couldn't figure out how to make them one.
    public static void addPlayerCard() {
        playerDeck.add(deck.getFirst());
        deck.removeFirst();
    }

    //adds a card to the dealer's hand. still couldn't figure out how to make them one.
    public static void addDealerCard() {
        dealerDeck.add(deck.getFirst());
        deck.removeFirst();
    }

    //establishes the total value of the player's hand
    public static int playerStackValue() {
        int playerHandValue = 0;
        for (Card c : playerDeck) {
            playerHandValue += c.getValue(playerHandValue);
        }
        return playerHandValue;
    }


    //establishes the total value of the dealers deck
    public static int dealerStackValue() {
        int dealerHandValue = 0;
        for (Card c : dealerDeck) {
            dealerHandValue += c.getValue(dealerHandValue);
        }
        return dealerHandValue;
    }
}


