package core;

import java.util.Objects;

/**
 * Represents the overall state of a two-player high card game.
 */
public class HighCardGame {
    private final Player player1;
    private final Player player2;

    /**
     * Represents the result of one round.
     */
    public record RoundResult(Player player1, Card card1, Player player2, Card card2, Player winner) {
        public boolean isTie() {
            return winner == null;
        }
    }

    /**
     * Creates a new game for two players using a shuffled 52-card deck dealt evenly.
     * @param player1Name the first player's name
     * @param player2Name the second player's name
     */
    public HighCardGame(String player1Name, String player2Name) {
        CardStack deck = CardStack.standardDeck();
        deck.shuffle();

        CardStack hand1 = deck.deal(26);
        CardStack hand2 = deck.deal(26);

        this.player1 = new Player(player1Name, hand1);
        this.player2 = new Player(player2Name, hand2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    /**
     * @return true if both players still have cards
     */
    public boolean canPlayRound() {
        return player1.hasCards() && player2.hasCards();
    }

    /**
     * Plays one round by having each player draw their top card.
     * @return the result of the round
     */
    public RoundResult playRound() {
        if (!canPlayRound()) {
            throw new IllegalStateException("Cannot play round because a player has no cards left.");
        }

        Card card1 = player1.drawTopCard();
        Card card2 = player2.drawTopCard();

        int comparison = card1.compareRank(card2);

        if (comparison > 0) {
            return new RoundResult(player1, card1, player2, card2, player1);
        } else if (comparison < 0) {
            return new RoundResult(player1, card1, player2, card2, player2);
        } else {
            return new RoundResult(player1, card1, player2, card2, null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HighCardGame other)) return false;
        return Objects.equals(player1, other.player1) && Objects.equals(player2, other.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2);
    }
}