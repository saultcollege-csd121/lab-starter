package tictactoe.game;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    private Position pos3;

@Test //how the flip do I test this
    void GetWinnerTest(){
//    Optional[Token] for winner and Optional.empty() for no winner
        var board = new Board();
        assertEquals(Optional.empty(), board.getWinner());
        var xboard = new Board("XOXOX---X");
        assertEquals(Optional.of(Token.X), xboard.getWinner());
        var yboard = new Board("OXOXO---O");
        assertEquals(Optional.of(Token.O), xboard.getWinner());
    }
 @Test
    void isFullFalse(){
        var board = new Board();
        assertFalse(board.isFull());
        var board2 = new Board("123456789");
        assertFalse(board2.isFull());
        var board3 = new Board("jdhbgfiuwnfkjkjs");
        assertFalse(board3.isFull());
        var board4 = new Board("987654321");
        assertFalse(board4.isFull());
        var board5 = new Board("---------");
        assertFalse(board5.isFull());
        var board6 = new Board("!@#$%^&*(");
        assertFalse(board6.isFull());
    }
    @Test
    void isFullTrue(){
        var board = new Board("XOXOXOXOX");
        assertTrue(board.isFull());
        var board2 = new Board("OOOOOOOOO");
        assertTrue(board2.isFull());
        var board3 = new Board("XXXXXXXXX");
        assertTrue(board3.isFull());
        var board4 = new Board("XXXOOOXXX");
        assertTrue(board4.isFull());
        var board5 = new Board("XXXOXOXOX");
        assertTrue(board5.isFull());
    }

    @Test
    void isEmptyAtTrue(){
        var board = new Board();
        var pos = new Position(Row.Top, Col.Left);
        var pos2 = new Position(Row.Middle, Col.Right);
        var pos3 = new Position(Row.Bottom, Col.Middle);
        assertTrue(board.isEmptyAt(pos));
        assertTrue(board.isEmptyAt(pos2));
        assertTrue(board.isEmptyAt(pos3));
        var board2 = new Board("OOO---XXX");
        var pos4 = new Position(Row.Middle, Col.Left);
        var pos5 = new Position(Row.Middle, Col.Middle);
        var pos6 = new Position(Row.Middle, Col.Right);
        assertTrue(board.isEmptyAt(pos4));
        assertTrue(board.isEmptyAt(pos5));
        assertTrue(board.isEmptyAt(pos6));
    }

    @Test
    void isEmptyAtFalse(){
        var board = new Board("XOX---OXO");
        var pos = new Position(Row.Top, Col.Left);
        var pos2 = new Position(Row.Top, Col.Middle);
        var pos3 = new Position(Row.Bottom, Col.Right);
        assertFalse(board.isEmptyAt(pos));
        assertFalse(board.isEmptyAt(pos2));
        assertFalse(board.isEmptyAt(pos3));
    }
    @Test
    void placeFails(){
        var board = new Board("XOXOXOXOX");
        var pos = new Position(Row.Bottom, Col.Middle);
        var pos2 = new Position(Row.Top, Col.Left);
        board.place(pos, Token.X);
        board.place(pos, Token.O);
        board.place(pos2, Token.X);
        board.place(pos2, Token.O);
    }
    @Test
    void placePasses(){
        /**
         * I think I found the bug haha. When calling the place function, it originally just placed the token
         * regardless of whether the spot was taken or not. I added an if statement to throw an exception if the position
         * is taken and these tests ensure that no matter what, you cannot put a token in a place where there already is one.
         * I very well could be wrong seeing as how this is a very internal part of the code, but better safe than sorry.
         */
        var board = new Board();
        var pos = new Position(Row.Top, Col.Right);
        var pos2 = new Position(Row.Middle, Col.Left);
        var pos3 = new Position(Row.Bottom, Col.Middle);
        board.place(pos, Token.X);
        assertThrows(IllegalArgumentException.class, () -> {board.place(pos, Token.O);});
        board.place(pos2, Token.X);
        assertThrows(IllegalArgumentException.class, () -> {board.place(pos2, Token.O);});
        board.place(pos3, Token.X);
        assertThrows(IllegalArgumentException.class, () -> {board.place(pos3, Token.O);});


//        reference: assertThrows(ParseException.class, () -> {Position.parse("1a");});
    }
}