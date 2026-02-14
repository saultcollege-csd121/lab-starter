package core;

import ui.Console;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller Class for the Card game.
 * Keeps track of each player's score and how it all begins and ends.
 */
public class HighCardGame {
    private final Player player;
    private final Player rival;
    private CardStack mainStack;

    /**
     * Defines how the Card game begins.
     * @param playerName your playable character
     * @param rivalName the character you are competing against
     * @param mainStack the starting deck of the game
     */
    public HighCardGame(String playerName, String rivalName, CardStack mainStack){
        ArrayList<CardStack> playerStacks = mainStack.cutDeck();
        player = new Player(playerName, playerStacks.removeFirst());
        rival = new Player(rivalName, playerStacks.removeLast());
    }

    /**
     * A method that checks if it's possible to continue playing.
     * @return an int option of who doesn't have cards
     */
    public int playable(){
        if (!player.hasCards()) {return 3;}
        if (!rival.hasCards()) {return 4;}
        return 0; // OK
    }

    /**
     * First checks if the round is possible to be played, then plays the cards
     * @return a List containing the played cards OR null if not possible to play
     */
    public ArrayList<Card> playRound() {
        if (playable() == 0) {
            ArrayList<Card> returnList = new ArrayList<>();
            Card playerCard = player.playCard();
            Card rivalCard = rival.playCard();
            returnList.add(playerCard);
            returnList.add(rivalCard);
            return returnList;
        } else {
            return null;
        }
    }

    /**
     * Checks who wins, if it's a tie or if a player is out of cards
     * @param playerCard the player's Card
     * @param rivalCard the rival's Card
     * @return the round outcome as an int option
     */
    public int roundResult(Card playerCard, Card rivalCard) {
        if (playable() == 3) {
            return 3;
        } else if (playable() == 4) {
            return 4;
        }
        if (playerCard.rank().value() > rivalCard.rank().value()) {
            player.increaseScore();
            return 1;
        } else if (playerCard.rank().value() < rivalCard.rank().value()) {
            rival.increaseScore();
            return 2;
        } else {
            return 0;   // tie
        }
    }

    public Player getPlayer() { return player; }

    public Player getRival() { return rival; }

    /**
     * Overwritten method for @equals()
     * @param other the reference object with which to compare
     * @return whether @other is equal to this object
     */
    public boolean equals(Object other){
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        HighCardGame otherHighCardGame = (HighCardGame)other;
        return this.player.equals(otherHighCardGame.player)
                && this.rival.equals(otherHighCardGame.rival);
    }

    /**
     * Overwritten method for @hashCode()
     * @return a hashed version of this object
     */
    public int hashCode() { return Objects.hash(player, rival, mainStack);}
}
