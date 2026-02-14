/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

// make dataset where each card has a value

// make code so the card with a (>) value wins

// give winner +1 point

public class HighCardGame {

    /**
     * each rank is given a value
     */
    public int value(Card.Rank rank) {

        return switch (rank) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
            case ACE -> 14;
        };
    }

    /**
     * compares each players card value
     * @return player who had greater card
     */
    public Player scoring(Card c1, Card c2, Player player1, Player player2) {


        int v1 = value(c1.rank());
        int v2 = value(c2.rank());

        if (v1 > v2) {
            player1.points += 1;
            return player1;
        } else if (v2 > v1) {
            player2.points += 1;
            return player2;
        } else {
            return null;
        }
    }


    /**
     * Each player draws 1 card for war
     * @return the winner
     */
    public Object[] playOneRound(Player p1, Player p2) {


        Card c1 = p1.drawCard();
        Card c2 = p2.drawCard();
        Player winner = scoring(c1, c2, p1, p2);

        return new Object[]{c1, c2, winner};
    }

}
