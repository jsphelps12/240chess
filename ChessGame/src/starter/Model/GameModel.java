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
    public GameModel(Integer i, String white, String black, String game, ChessGame g){
        gameID = i;
        whiteUsername = white;
        blackUsername = black;
        gameName = game;
        this.game = g;
    }
    public GameModel(Integer i, String game, ChessGame g){
        gameID = i;
        gameName = game;
        this.game = g;
    }

    /**
     * Game ID
     */
    private Integer gameID;
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

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    /**
     * returns Game ID
     * @return game ID
     */
    public Integer getGameID(){
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
