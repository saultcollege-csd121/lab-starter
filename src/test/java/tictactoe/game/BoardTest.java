package tictactoe.game;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void constructor_EmptyBoard() {
        Board b = new Board(); //constructor creates an empty board

        for (Row r : Row.values()) { //loop every row
            for (Col c : Col.values()) { //loop every col
                assertTrue(b.isEmptyAt(new Position(r, c))); //every cell must be empty
            }
        }
    }

    @Test
    void isEmptyAt_afterPlace_returnsFalse() {
        Board b = new Board();
        Position pos = new Position(Row.Top, Col.Left); //top-left corner
        b.place(pos, Token.X); //use X there
        assertFalse(b.isEmptyAt(pos)); //cell occupied
    }

    @Test
    void place_TokenCorrectly() {
        Board b = new Board();
        Position pos = new Position(Row.Middle, Col.Middle); //center cell
        b.place(pos, Token.O); //place O in center
     }

    @Test
    void getWinner_rowWin_X() {
        Board b = new Board("XXX------"); //X in the whole 1st row

        assertEquals(Optional.of(Token.X), b.getWinner()); //X should be a winner
    }

    @Test
    void getWinner_columnWin_O() {
        Board b = new Board(
                "O--\n" +
                "O--\n" +
                "O--" // checking left column
        );

        assertEquals(Optional.of(Token.O), b.getWinner()); //O should be the winner
    }

    @Test
    void getWinner_diagonalLefttoRight_X() {
        Board b = new Board(
                "X--\n" +
                "-X-\n" +
                "--X" //checking right diagonal from left to right
        );
        assertEquals(Optional.of(Token.X), b.getWinner()); //X should be the winner
    }

    @Test
    void copyConstructor_copiesBoard() {
        Board b1 = new Board();
        Board b2 = new Board(b1); //copy b1 into b2
        Position pos = new Position(Row.Top, Col.Left);
        b1.place(pos, Token.X); //changing the original only
        assertTrue(b2.isEmptyAt(pos)); //check that b2 is unchanged
    }

    @Test
    void boardConstructor_invalid() {
        assertThrows(IllegalArgumentException.class, () -> new Board("XOX")); //just 3 chars
    }

    @Test
    void place_overwritesToken() {
        Board b = new Board();
        Position pos = new Position(Row.Middle, Col.Middle);
        b.place(pos, Token.X); //put X first
        b.place(pos, Token.O); //checking overwrite
        assertFalse(b.isEmptyAt(pos)); //cell still used
    }

    @Test
    void toString_CorrectCharacters() {
        Board b = new Board("XOX" +
                               "-O-" +
                               "X--");
        String s = b.toString();
        String[] lines = s.split("\n"); //split into rows by newline
        assertEquals(3, lines.length);
        assertTrue(s.contains("X")); //X tokens
        assertTrue(s.contains("O")); //O tokens
        assertTrue(s.contains("."));
    }

    @Test
    void equals_sameBoard_returnsTrue() {
        Board b1 = new Board("XOX" +
                                "-O-" +
                                "X--");
        Board b2 = new Board("XOX" +
                                "-O-" +
                                "X--");
        assertEquals(b1, b2); //equals must return true
        assertEquals(b1.hashCode(), b2.hashCode()); //equal objects same hash
    }

    @Test
    void equals_differentBoard_returnsFalse() {
        Board b1 = new Board("XOX" +
                                "-O-" +
                                "X--");
        Board b2 = new Board("XOX" +
                                "O-X" +
                                "---"); //different token
        assertNotEquals(b1, b2); //must not be equal
    }

    @Test
    void equals_null_returnsFalse() {
        Board b = new Board();
        assertNotEquals(null, b); // equals must not work or return true
        assertNotEquals("not a board", b); // different type must return false
    }
}