package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

class OmolaTest {
    Board newBoard = new Board();
    Board board1 = new Board();
    Position pos1 = new Position(Row.Top, Col.Left);
    Position pos2 = new Position(Row.Top, Col.Middle);
    Position pos3 = new Position(Row.Top, Col.Right);
    Position pos4 = new Position(Row.Middle, Col.Left);
    Position pos5 = new Position(Row.Middle, Col.Middle);
    Position pos6 = new Position(Row.Middle, Col.Right);
    Position pos7 = new Position(Row.Bottom, Col.Left);
    Position pos8 = new Position(Row.Bottom, Col.Middle);
    Position pos9 = new Position(Row.Bottom, Col.Right);

    @Test
    public void getNextMoveIsRight() {
        Omola bot = new Omola("Omola", Token.X);
        assert bot.getNextMove(newBoard).equals(pos1);
        newBoard.place(pos1, Token.X);

        newBoard.place(pos2, Token.O);
        assert bot.getNextMove(newBoard).equals(pos3);
        newBoard.place(pos3, Token.X);

        newBoard.place(pos4, Token.O);
        assert bot.getNextMove(newBoard).equals(pos5);
        newBoard.place(pos5, Token.X);

        newBoard.place(pos6, Token.O);
        assert bot.getNextMove(newBoard).equals(pos7);

        board1.place(pos1, Token.O);
        assert bot.getNextMove(board1).equals(pos2);
        board1.place(pos2, Token.X);

        board1.place(pos4, Token.O);
        assert bot.getNextMove(board1).equals(pos7);
    }
}

// okay well the idea here was to go through every position and make sure i got the expected result...that isn't working out
// it is working out
// boom, found a bug. turns out having the while loop within the for loop was not only entirely unnecessary but also making it not work.
// who woulda thought.
//