package core;

import java.util.Objects;

public class Player {
    private final String name;
    private final CardStack hand;

    /*** This creates a new player with a name and an empty hand.*/

    public Player(String name) {
        this.name = name;
        this.hand = new CardStack();
    }

    public String getName() {
        return name;
    }

    public CardStack getHand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}