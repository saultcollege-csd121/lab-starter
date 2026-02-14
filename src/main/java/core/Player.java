/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * A player in the game
 * A player has a name and a hand of cards
 *
 */

public class Player {

    private  final String name;
    private final Deque<Card> hand;

    /**
     * Constructs a Player with a name and an initial list of cards
     * @param n the players name
     * @param cards the list of cards given to the player
     */

    public  Player(String n, List<Card> cards) {
        name = n;
        hand = new ArrayDeque<>(cards);

    }

    /**
     * Returns the player's name
     *
     * @return the name of the player
     */

    public String name () {
        return  name;

    }

    /**
     * Determines whether the player still has cards remaining
     * @return true if the player has at least one card
     */
    public boolean hasCards () {
        return !hand.isEmpty();

    }

    /**
     * Removes and returns the top card from the players hand
     * @return the next card to be plauyed
     */
    public  Card draw() {
        return hand.removeFirst();

    }
}
