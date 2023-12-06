package webSocketMessages.userCommands;

public class ResignCommand extends UserGameCommand{
    public ResignCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        this.commandType = CommandType.RESIGN;
    }

    public int gameID;
}
