package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class LinusTest {
    Board board1 = new Board();
    Board board2 = new Board();
    Board board3 = new Board();
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
        Linus bot = new Linus("Linus", Token.X);
        assert bot.getNextMove(board1).equals(pos1);
        board1.place(pos1, Token.X);

        board1.place(pos2, Token.O);
        assert bot.getNextMove(board1).equals(pos3);
        board1.place(pos3, Token.X);

        board1.place(pos4, Token.O);
        assert bot.getNextMove(board1).equals(pos5);
        board1.place(pos5, Token.X);

        board1.place(pos6, Token.O);
        assert bot.getNextMove(board1).equals(pos7);
        board1.place(pos7, Token.X);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        board2.place(pos1, Token.O);
        assert bot.getNextMove(board2).equals(pos2);
        board2.place(pos2, Token.X);

        board2.place(pos4, Token.O);
        assert bot.getNextMove(board2).equals(pos3);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        assert bot.getNextMove(board3).equals(pos1);
        board3.place(pos1, Token.X);

        board3.place(pos2, Token.O);
        assert bot.getNextMove(board3).equals(pos3);
        board3.place(pos3, Token.X);

        board3.place(pos5, Token.O);
        assert bot.getNextMove(board3).equals(pos4);
        board3.place(pos4, Token.X);

        board3.place(pos9, Token.O);
        assert bot.getNextMove(board3).equals(pos6);
        board3.place(pos6, Token.X);

        board3.place(pos7, Token.O);
        assert bot.getNextMove(board3).equals(pos8);
        board3.place(pos8, Token.X);
    }
}