package webSocketMessages.userCommands;

public class RedrawCommand extends UserGameCommand{
    public RedrawCommand(String authToken, int gameID) {
        super(authToken);
        this.commandType = CommandType.REDRAW;
        this.gameID = gameID;

    }

    public int gameID;

}
