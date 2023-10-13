package Model;

import chess.ChessGame;

/**
 * Model.GameModel class
 */
public class GameModel {
    /**
     * Constructor
     * @param i game ID
     * @param white white username
     * @param black black username
     * @param game game Name
     * @param g the actual game
     */
    public GameModel(int i, String white, String black, String game, ChessGame g){
        gameID = i;
        whiteUserName = white;
        blackUserName = black;
        gameName = game;
        theGame = g;
    }

    /**
     * Game ID
     */
    private int gameID;
    /**
     * white username
     */
    private String whiteUserName;
    /**
     * black username
     */
    private String blackUserName;
    /**
     * gamename
     */
    private String gameName;
    /**
     * the game object
     */
    private ChessGame theGame;

    /**
     * returns Game ID
     * @return game ID
     */
    public int getGameID(){
        return gameID;
    }

    /**
     * returns white username
     * @return white username
     */
    public String getWhiteUserName(){
        return whiteUserName;
    }

    /**
     * returns black username
     * @return black username
     */
    public String getBlackUserName(){
        return blackUserName;
    }

    /**
     * get game name
     * @return game name
     */
    public String getGameName(){
        return gameName;
    }

    /**
     * gets the game
     * @return the game
     */
    public ChessGame getTheGame(){
        return theGame;
    }

}
