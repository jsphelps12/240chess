package webSocketMessages.serverMessages;

import chess.ChessGame;

public class ErrorMessage extends ServerMessage{
    public ErrorMessage(ServerMessageType type, String message) {
        super(type);
        this.message = message;
    }

    String message;
}
