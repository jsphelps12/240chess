package webSocketMessages.serverMessages;

public class ErrorMessage extends ServerMessage{
    public ErrorMessage(ServerMessageType type, String message) {
        super(type);
        this.errorMessage = message;

    }

    String errorMessage;
}
