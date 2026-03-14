package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

public abstract class Player {

    private final String name;
    private final Token token;

    public Player(String name, Token token){
        // subclasses call this with super() so we dont repeat this code everywhere
        this.name = name;
        this.token = token;
    }

    public String name(){
        return name;
    }

    public Token token() {
        return token;
    }

    // every subclass MUST implement this their own way
    public abstract Position getNextMove(Board board);
}
