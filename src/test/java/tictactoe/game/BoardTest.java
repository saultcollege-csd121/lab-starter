package tictactoe.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

//Should be called "broad test" bc of how broad I have this thing (An AI wouldn't be this funny)
class BoardTest {

    private static Board boardTest;

    @BeforeAll //RE-Check This!!!
    public static void initBlankBoard(){
        boardTest= new Board();
    }

    @Test
    public void constructorTest(){
      //  assertTrue(boardTest.isEmptyAt()); // Will come back to this !!! (Maybe not?)
        assertFalse(boardTest.isFull());
        assertNotNull(boardTest);
        assertNotNull(boardTest.toString());

    }
//Battle of the mids, also known as a mid-off (Eg: Leafs and Canadians fans arguing who's the better team)
    @Test
    public void testPlace() throws ParseException {

        Position midmid = Position.parse("Middle middle");
        boardTest.place(midmid, Token.X);
        assertFalse(boardTest.isEmptyAt(midmid),"Should not be empty in the middle cells");
    }


    @Test
    public void testIfFull(){
        Board fullboard = new Board("OXOXXOXOX");
        assertTrue(fullboard.isFull());
        Board nullboard = new Board("---------");
        assertFalse(nullboard.isFull());
        Board partfull = new Board("OXOXXO--");
        assertFalse(partfull.isFull());

    }


    @Test
    public void testWinnerDiag(){

        Board oWinLeftDiag = new Board ("OXX\nXO-\n-XO");
        assertEquals(Token.O, oWinLeftDiag.getWinner().get(), ("A row of Os in the left diagonal should have returned O as the winner, but it returned %s").formatted(oWinLeftDiag.getWinner().get()));


        Board oWinRightDiag = new Board("-XO\n-OX\nOXO");
        assertEquals(Token.O, oWinRightDiag.getWinner().get(), ("A row of Os in the right diagonal should have returned O as the winner, but it returned %s").formatted(oWinRightDiag.getWinner().get()));

        Board xWinLeftDiag =  new Board("X-O\nXXO\n-OX");
        assertEquals(Token.X, xWinLeftDiag.getWinner().get(), ("A row of Xs in the left diagonal should have returned X as the winner, but it returned %s").formatted(xWinLeftDiag.getWinner().get()));

        Board xWinRightDiag = new Board("O-X\n-XO\nXO-");
        assertEquals(Token.X, xWinRightDiag.getWinner().get(), ("A row of Xs in the right diagonal should have returned X as the winner, but it returned %s").formatted(xWinRightDiag.getWinner().get()));

    }






}