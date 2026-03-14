package tictactoe.game;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

    /**
     * I just took the tests from the last lab and mofifi
     */


class BoardTest {

    /**
     * Verifies that the default constructor creates an empty board.
     * Every position on the board should be empty.
     */
    @Test
    void constructor_EmptyBoard() {

        Board b = new Board();

        for (Row r : Row.values()) {
            for (Col c : Col.values()) {
                assertTrue(b.isEmptyAt(new Position(r, c)));
            }
        }
    }

    /**
     * Verifies that a cell is no longer empty after placing a token.
     */
    @Test
    void isEmptyAt_afterPlace_returnsFalse() {

        Board b = new Board();
        Position pos = new Position(Row.Top, Col.Left);
        b.place(pos, Token.X);
        assertFalse(b.isEmptyAt(pos));
    }

    /**
     * Verifies that a token can be placed on the board without errors.
     */
    @Test
    void place_TokenCorrectly() {

        Board b = new Board();
        Position pos = new Position(Row.Middle, Col.Middle);
        b.place(pos, Token.O);
        assertFalse(b.isEmptyAt(pos));
    }

    /**
     * Verifies that a row of X tokens is correctly detected as a win.
     */
    @Test
    void getWinner_rowWin_X() {
        Board b = new Board("XXX------");
        assertEquals(Optional.of(Token.X), b.getWinner());
    }

    /**
     * Verifies that a column of O tokens is correctly detected as a win.
     */
    @Test
    void getWinner_columnWin_O() {

        Board b = new Board("""
                                O--
                                O--
                                O--
                                """);

        assertEquals(Optional.of(Token.O), b.getWinner());
    }

    /**
     * Verifies that a diagonal win from top-left to bottom-right is detected.
     */
    @Test
    void getWinner_diagonalLefttoRight_X() {

        Board b = new Board("""
                                X--
                                -X-
                                --X
                                """);

        assertEquals(Optional.of(Token.X), b.getWinner());
    }

    /**
     * Verifies that the copy constructor creates an independent copy
     * of the original board.
     */
    @Test
    void copyConstructor_copiesBoard() {

        Board original = new Board();
        Board copy = new Board(original);
        Position pos = new Position(Row.Top, Col.Left);
        original.place(pos, Token.X);
        assertTrue(copy.isEmptyAt(pos));
    }

    /**
     * Verifies that the constructor throws an exception when
     * an invalid board string is provided.
     */
    @Test
    void boardConstructor_invalid() {

        assertThrows(IllegalArgumentException.class,
                () -> new Board("XOX"));
    }

    /**
     * Verifies that placing a token twice on the same position
     * overwrites the previous token.
     */
    @Test
    void place_overwritesToken() {

        Board b = new Board();
        Position pos = new Position(Row.Middle, Col.Middle);

        b.place(pos, Token.X);
        b.place(pos, Token.O);

        assertFalse(b.isEmptyAt(pos));
    }

    /**
     * Verifies that the string representation of the board
     * contains the correct characters.
     */
    @Test
    void toString_CorrectCharacters() {

        Board b = new Board("XOX-O-X--");
        String s = b.toString();
        String[] lines = s.split("\n");

        assertEquals(3, lines.length);
        assertTrue(s.contains("X"));
        assertTrue(s.contains("O"));
        assertTrue(s.contains("."));
    }

    /**
     * Verifies that two boards with the same state are equal
     * and produce the same hash code.
     */
    @Test
    void equals_sameBoard_returnsTrue() {

        Board b1 = new Board("XOX-O-X--");
        Board b2 = new Board("XOX-O-X--");

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    /**
     * Verifies that boards with different states are not equal.
     */
    @Test
    void equals_differentBoard_returnsFalse() {

        Board b1 = new Board("XOX-O-X--");
        Board b2 = new Board("XOXO-X---");

        assertNotEquals(b1, b2);
    }

    /**
     * Verifies that the equals method returns false when comparing
     * a board with null or with an object of a different type.
     */
    @Test
    void equals_null_returnsFalse() {

        Board b = new Board();

        assertNotEquals(null, b);
        assertNotEquals("not a board", b);
    }
}