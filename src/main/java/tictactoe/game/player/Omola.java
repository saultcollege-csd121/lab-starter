package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.List;

public class Omola extends Player {
    public Omola(String name, Token token) {
        this.name = "Omola";
        this.token = token;
    }

    @Override
    public Position getNextMove(Board board) {
        Token self = this.token;
        List<Position> legalSpots;           //empty variable to be used for open spots on the board
        Boolean positionDecision = false;      // variable used as extra padding to ensure no loops are iterated unnecessarily
        Position returnedPosition = null;       // variable to hold the position that the user will move to next


        legalSpots = board.getEmptyCells(); // holds empty spots in case no winning spot
        Token otherPlayer = null; // holds the token that doesn't belong to THIS bot
        if (self == Token.O) { //token assignment
            otherPlayer = Token.X;
        } else if (self == Token.X) { //token assignment
            otherPlayer = Token.O;
        }

        for (Position i : legalSpots) { // loop! checks every single spot AVAILABLE on the board
            Board board2 = new Board(board);
            Board oppBoard = new Board(board);
            board2.place(i, self);
            oppBoard.place(i, otherPlayer);
            if (board2.getWinner().isPresent() || oppBoard.getWinner().isPresent()) { // returns a position if that would result in a winning move for EITHER player
                positionDecision = true;
                return returnedPosition = i;
            }
//            System.out.println(board2.toString());        this is how I checked that my while loop was FUCKING ME
        }
        if (!positionDecision) { // picks a random spot if there are no winning moves
            Position newSpot = legalSpots.getFirst();
            positionDecision = true;
            return returnedPosition = newSpot;
        }
        return returnedPosition;
    }
}


//        for this function i need to establish what optimus' token is and what the other player's token is and
//        store those in variables, then i need to iterate through each position available and check if changing any of those
//        positions would result in a win for either of them, and take it immediately
//
//         at every position, make a copy of the board, change the token at that position on the board, and check for a win
//        if win, break, else keep looping
//
//        assign both tokens - DONE
//        new board variable - DONE
//        place token at current position - DONE
//        check board with new token for win - this is returning optional and idfk - okay i figured that out, DONE
//        return position for next moove if win - DONE

//        NEW TASK LIST
//        comment on everything - DONE
//        javadoc -
//        unit tests -
//        rename and reorganize -

// what the hell do I write unit tests for??? everything is so intertwined
// maybe I write unit tests for small things like the parse string function on the positions?
// but im supposed to be testing the PLAYERS
// and the players rotate so how am I supposed to know what they'll do??
// i guess omola is just supposed to win or draw so i could test for that
// but WHERE do i test that??? does it go in the check for winner thing? and how does it know how many turns to go?
// and linus is easy in THEORY but how do I write tests that depend on each other?? I dont think a test runs just by itself it simulates the program
// and linus is only predictable if there's another player
// and im probably supposed to test that other player too and test if the players go against each other
// omg two linuses would permanently have x win haha
// okkkk but how do i set up two linuses ... maybe a main test? and it does the rest?

// FIRST ORDER OF BUSINESS - having a main test is stupid, the only function in there is VOID, THERE'S NO POINT

// I CAN test omola but it's a pain, i need to have a board object to give it, and know what the expected outcome is
// but edge cases can't really be tested in a true test because Omola's functions are nested so deep in the program,
// so many things wouldn't even get to it, i want to see if it breaks with things it will actually see

// okay new issue, how do i test a function that can't be static???