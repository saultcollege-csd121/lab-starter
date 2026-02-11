/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

public class Player {

    private final String name;
    private final CardStack hand;

    public Player (String name, CardStack hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public boolean hasCards() {
        return hand.size() > 0;
    }

    public Card drawCard() {
        return hand.draw();
    }

    public CardStack getHand() {
        return hand;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return name.equals(player.name) && hand.equals(player.hand);
    }
    public int hashCode() {
        return Objects.hash(name, hand);
    }

}
