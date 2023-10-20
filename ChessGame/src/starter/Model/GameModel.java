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
        whiteUsername = white;
        blackUsername = black;
        gameName = game;
        this.game = g;
    }

    /**
     * Game ID
     */
    private int gameID;
    /**
     * white username
     */
    private String whiteUsername;
    /**
     * black username
     */
    private String blackUsername;
    /**
     * gamename
     */
    private String gameName;
    /**
     * the game object
     */
    private ChessGame game;

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
    public String getWhiteUsername(){
        return whiteUsername;
    }

    /**
     * returns black username
     * @return black username
     */
    public String getBlackUsername(){
        return blackUsername;
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
    public ChessGame getGame(){
        return game;
    }

}
