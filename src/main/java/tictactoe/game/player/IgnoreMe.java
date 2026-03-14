//package tictactoe.game.player;
//import tictactoe.game.*;
//
//import java.util.List;
//
//public record MiniMax(Position position) {
//    static List<Position> legalSpots;
//    static Boolean positionDecision = null;
//    static Position returnedPosition;
//
//
//    public static Position getPosition(Board board) {
//        legalSpots = board.getEmptyCells();
//        Token self = null;
//        Token otherPlayer;
//        if (Optimus.token == Token.O){
//            self = Token.O;
//            otherPlayer = Token.X;
//        }
//        else if (Optimus.token == Token.X) {
//            self = Token.X;
//            otherPlayer = Token.O;
//        }
//
//        for (Position i:legalSpots) {
//            int count = 0;
//            while (count<9 && positionDecision == false) {
//                Board board2 = new Board(board);
//                board2.place(i, self);
//                if (board2.getWinner().isPresent())  {
//                    if (board2.getWinner().get() == self || board2.getWinner().get() == otherPlayer){
//                        positionDecision = true;
//                        return returnedPosition = i;
//                    }
//                }
//                count++;
//            }
//            if (count == 9 && positionDecision == false) {
//                Position newSpot = legalSpots.getFirst();
//                positionDecision = true;
//                return returnedPosition = newSpot;
//            }
//            return returnedPosition;
//        }
//
//        return returnedPosition;
//    }
//}