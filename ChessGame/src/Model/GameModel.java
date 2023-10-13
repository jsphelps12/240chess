import chess.ChessGame;

public class GameModel {
    public GameModel(int i, String white, String black, String game, ChessGame cg){
        gameID = i;
        whiteUserName = white;
        blackUserName = black;
        gameName = game;
        theGame = cg;
    }

    private int gameID;
    private String whiteUserName;
    private String blackUserName;
    private String gameName;
    private ChessGame theGame;

    public int getGameID(){
        return gameID;
    }
    public String getWhiteUserName(){
        return whiteUserName;
    }
    public String getBlackUserName(){
        return blackUserName;
    }
    public String getGameName(){
        return gameName;
    }
    public ChessGame getTheGame(){
        return theGame;
    }

}
