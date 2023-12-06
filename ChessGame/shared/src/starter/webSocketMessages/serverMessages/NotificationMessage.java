package webSocketMessages.serverMessages;

public class NotificationMessage extends ServerMessage{
    public NotificationMessage(ServerMessageType type, String message) {
        super(type);
        this.message = message;
    }
    String message;
}
