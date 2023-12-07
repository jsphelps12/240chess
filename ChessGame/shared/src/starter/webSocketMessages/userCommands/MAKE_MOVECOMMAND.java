package webSocketMessages.userCommands;

import chess.Move;

public class MAKE_MOVECOMMAND extends UserGameCommand{
    public MAKE_MOVECOMMAND(String authToken, int gameID, Move move) {
        super(authToken);
        this.gameID = gameID;
        this.move = move;
        this.commandType = CommandType.MAKE_MOVE;
    }

    private int gameID;

   private Move move;

    public Move getMove(){
        return move;
    }

    public int getGameID(){
        return  gameID;
    }

}
