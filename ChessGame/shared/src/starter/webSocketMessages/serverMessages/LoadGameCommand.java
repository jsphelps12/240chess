package webSocketMessages.serverMessages;

import chess.ChessGame;

public class LoadGameCommand extends ServerMessage{
    public LoadGameCommand(ServerMessageType type, String game, ChessGame.TeamColor teamColor) {
        super(type);
        this.game = game;
        this.teamColor = teamColor;
    }
    public LoadGameCommand(ServerMessageType type, String game) {
        super(type);
        this.game = game;
    }
    String game;
    ChessGame.TeamColor teamColor = ChessGame.TeamColor.WHITE;

}
