package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.TicTacToeGame;
import tictactoe.game.TicTacToeGame.Status;
import tictactoe.game.Token;

import java.util.Random;

import static tictactoe.game.TicTacToeGame.Status.XWins;

public class Optimus extends Player {


    public Optimus(String name, Token token) {
        super(name, token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();
        if(emptyCells.size() == 1) { //last cell, no need to return minimax
            return emptyCells.get(0);
        } else if (emptyCells.size() == 9) {
            var random = new Random();
            return emptyCells.get(random.nextInt(emptyCells.size()));
        } else {
            return miniMax(board, this.token()).position();
        }
    }
    MiniMax miniMax(Board board, Token currentToken) {
        var winner = board.getWinner();
        if (winner.isPresent()) {
            return new MiniMax(winner.get() == Token.X ? 1 : -1, null);
        }
        if (board.isFull()) {
            return new MiniMax(0, null);
        }
        Token nextToken = (currentToken == Token.X) ? Token.O : Token.X;
        MiniMax bestResult = null;
        var emptyCells = board.getEmptyCells();
        for (var cell : emptyCells) {
            var currentBoardCopy = new Board(board);
//            if (emptyCells.size() % 2 != 0) {
//                currentToken = Token.X;  ...i was overwriting the currentToken parameter here with these assignments xD
//            } else {                       .... took me way too long to figure this out
//                currentToken = Token.O;
//            }
            currentBoardCopy.place(cell, currentToken);
            int score = miniMax(currentBoardCopy, nextToken).score;
            MiniMax miniMaxResult = new MiniMax(score, cell);
            if (bestResult == null) {
                bestResult = miniMaxResult;
            } else if (currentToken == Token.X && miniMaxResult.score() > bestResult.score()) {
                bestResult = new MiniMax(miniMaxResult.score(), cell);
            } else if (currentToken == Token.O && miniMaxResult.score() < bestResult.score()) {
                bestResult = new MiniMax(miniMaxResult.score(), cell);
            }
        }
        return bestResult;
    }
    public record MiniMax(int score, Position position) {
    }
}

