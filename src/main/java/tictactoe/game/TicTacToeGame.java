package tictactoe.game;

import tictactoe.game.player.Player;

/**
 * Represents the current state of a tictactoe game
 */
public class TicTacToeGame {

    public record TurnData(Player whoseTurn, Position positionPlayed, Board newBoardState) {}

    public enum Status { InProgress, Draw, XWins, OWins }

    private final Player playerX;
    private final Player playerO;

    private final Board board = new Board();

    private Player whoseTurn;

    public TicTacToeGame(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.whoseTurn = playerX;
    }

    public TurnData doNextTurn() {
        var player = whoseTurn;

        var pos = player.getNextMove(board);

        placeTokenAt(pos);

        return new TurnData(player, pos, board);
    }

    private void placeTokenAt(Position pos) {
        // ✅ FIXED: token() → getToken()
        board.place(pos, whoseTurn.getToken());

        if (getStatus() == Status.InProgress) {
            if (whoseTurn == playerX) {
                whoseTurn = playerO;
            } else {
                whoseTurn = playerX;
            }
        }
    }

    public Status getStatus() {
        if (board.getWinner().isEmpty()) {
            if (!board.isFull()) {
                return Status.InProgress;
            } else {
                return Status.Draw;
            }
        } else {
            return board.getWinner().get() == Token.X ? Status.XWins : Status.OWins;
        }
    }
}