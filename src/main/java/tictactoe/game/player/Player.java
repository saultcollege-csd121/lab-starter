package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public abstract class Player {
    private final String name;
    private final Token token;

    public Player(String name, Token token){
        this.name = name;
        this.token = token;
    }
    public String name(){return name;}
    public Token token(){return token;}

    public abstract Position getNextMove(Board board);
}

