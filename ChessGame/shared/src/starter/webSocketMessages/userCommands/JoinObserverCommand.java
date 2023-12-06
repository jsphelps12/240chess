package webSocketMessages.userCommands;

import Model.User;

public class JoinObserverCommand extends UserGameCommand {
    public JoinObserverCommand(String authToken, int gameID) {
        super(authToken);
        this.gameID = gameID;
        commandType = CommandType.JOIN_OBSERVER;
    }

    private int gameID;

}
