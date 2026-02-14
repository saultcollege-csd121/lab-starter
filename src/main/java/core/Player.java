/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;


import java.util.List;
import java.util.Objects;

public class Player {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(name, player.name) && Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand, points);
    }

    public String name;
    public List<Card> hand;
    public int points;

    /**
     * assigns the name, hand, and points to a player.
     */
    public Player(String name, List<Card> hand) {

        this.name = name;
        this.hand = hand;
        this.points = 0;
    }

    /**
     * draws a card from the hand
     * @return the hand with one less card
     */
    public Card drawCard() {

        return hand.removeFirst();
    }
}