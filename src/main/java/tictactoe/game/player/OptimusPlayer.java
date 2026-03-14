package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

/**
 * A player who always plays TicTacToe optimally and
 * therefore will always win or draw.
 *
 *  If the board is empty, pick a random position on the board,
 *  otherwise use the position returned by ‘minimax’ algorithm
 */
public class OptimusPlayer extends Player {

    /**
     * Constructs an OptimusPlayer using the given token
     *
     * @param token The token used by the player (X or O)
     */
    public OptimusPlayer(Token token) {
        super("Optimus", token);
    }

    /**
     * Determines the best move by evaluating the board
     * with the minimax algorithm.
     *
     * @param board The current game board
     * @return The best position for the next move
     */
    @Override
    public Position getNextMove(Board board) {
        return minimax(board, token).position();
    }

    /**
     * A helper record used to store the result of a minimax evaluation
     *
     * @param score The evaluated score of the board state
     * @param position The position associated with that score
     */
    private record Result(int score, Position position) {}

    /**
     * Recursively evaluates the game using the minimax algorithm
     *
     * @param board The current board state
     * @param currentToken The token whose turn it is
     * @return The best possible result for the current player
     */
    private Result minimax(Board board, Token currentToken) {

        var winner = board.getWinner();     //check if someone won

        if (winner.isPresent()) {
            if (winner.get() == Token.X) {
                return new Result(1, null); //score 1 if x wins
            } else {
                return new Result(-1, null); //score -1 if O wins
            }
        }

        if (board.isFull()) {                //draw
            return new Result(0, null);
        }

        Result best;          //the best move found so far is stored

        if (currentToken == Token.X) {
            best = new Result(-100, null); //X starts with a bad score. It "must" win
        } else {
            best = new Result(100, null); //O "wants" the lower score
        }

        for (Position pos : board.getEmptyCells()) { //returns all available moves

            Board copy = new Board(board); //simulating the move
            copy.place(pos, currentToken); //pretending the player places a token
            Token nextToken = (currentToken == Token.X) ? Token.O : Token.X;
            Result result = minimax(copy, nextToken); //it calls itself again until the game ends

            if (currentToken == Token.X && result.score > best.score) { //If X is playing and this move is better than the "best"
                best = new Result(result.score, pos);   //update the best move
            }

            if (currentToken == Token.O && result.score < best.score) { //If O is playing and this move is better (A lower score)
                best = new Result(result.score, pos);    //update best
            }
        }

        return best;
    }
}