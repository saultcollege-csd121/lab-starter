/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

public class HighCardGame {

    private final Player player1;
    private final Player player2;

    public HighCardGame(String name1, String name2){
        CardStack deck = CardStack.shuffled52CardsDeck();

        CardStack hand1 = deck.deal(26);
        CardStack hand2 = deck.deal(26);

        player1 = new Player(name1, hand1);
        player2 = new Player(name2, hand2);
    }

    public boolean canPlayRound() {
        return player1.hasCards() && player2.hasCards();
    }

    public RoundResult playRound() {
        Card c1 = player1.drawCard();
        Card c2 = player2.drawCard();

        int r1 = c1.rank().ordinal();
        int r2 = c2.rank().ordinal();

        Player winner = null;

        if (r1 > r2) winner = player1;
        else if (r2 > r1) winner = player2;

        return new RoundResult(c1, c2, winner);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public record RoundResult(Card card1, Card card2, Player winner) {}
}
