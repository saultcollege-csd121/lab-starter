package tictactoe.game;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void checkIfBoardIsValid() {

        //    1
        Board b = new Board();
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                assertTrue(b.isEmptyAt(pos));
            }
        }
    }

    @Test
    public void checkIfPositionIsEmpty() {
        //    2
        Board b = new Board();
        Position pos1 = new Position(Row.Top, Col.Left);
        b.place(pos1, Token.X);
        Position pos2 = new Position(Row.Middle, Col.Right);
        Position pos3 = new Position(Row.Bottom, Col.Left);
        Position pos4 = new Position(Row.Middle, Col.Middle);
        Position pos5 = new Position(Row.Top, Col.Right);
        b.place(pos5, Token.O);
        assertFalse(b.isEmptyAt(pos1));
        assertTrue(b.isEmptyAt(pos2));
        assertTrue(b.isEmptyAt(pos3));
        assertTrue(b.isEmptyAt(pos4));
        assertFalse(b.isEmptyAt(pos5));
    }

    @Test
    public void CheckIfPlaceIsValid() {
        //    3
        Board b = new Board();
        Position pos1 = new Position(Row.Top, Col.Left);
        b.place(pos1, Token.X);
        assertFalse(b.isEmptyAt(pos1));
        Position pos2 = new Position(Row.Middle, Col.Right);
        b.place(pos2, Token.O);
        assertFalse(b.isEmptyAt(pos2));
        Position pos3 = new Position(Row.Bottom, Col.Middle);
        assertTrue(b.isEmptyAt(pos3));
        Position pos4 = new Position(Row.Middle, Col.Middle);
        assertTrue(b.isEmptyAt(pos4));
        Position pos5 = new Position(Row.Top, Col.Right);
        assertTrue(b.isEmptyAt(pos5));
        }

    @Test
    public void CheckWinnerResult() {
       // Use Board(String b) to create a premade board, check if a token is in the required spaces
        Board b = new Board("XOX-XO--O");
        Optional<Token> winner1 = b.getWinner();
        assertFalse(winner1.isPresent());
        Board c = new Board("XO-XXOX-O");
        Optional<Token> winner2 = c.getWinner();
        assertTrue(winner2.isPresent());
        Board d = new Board("OOOX-X-X-");
        Optional<Token> winner3 = d.getWinner();
        assertTrue(winner3.isPresent());
        Board e = new Board("XO-OX---X");
        Optional<Token> winner4 = e.getWinner();
        assertTrue(winner4.isPresent());
        Board f = new Board("O-X-OXO-X");
        Optional<Token> winner5 = f.getWinner();
        assertTrue(winner5.isPresent());
    }



}