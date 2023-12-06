package webSocketMessages.userCommands;

import chess.ChessGame;

public class JoinPlayerCommand extends UserGameCommand{
    public JoinPlayerCommand(String authToken, int gameID, ChessGame.TeamColor teamColor) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.gameID = gameID;
        this.teamColor = teamColor;
    }

    private int gameID;
    private ChessGame.TeamColor teamColor;

    public int getGameID(){
        return gameID;
    }

    public ChessGame.TeamColor getTeamColor(){
        return teamColor;
    }








}
