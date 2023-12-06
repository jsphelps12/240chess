package Model;

import chess.ChessGame;
import chess.Game;

/**
 * Model.GameModel class
 */
public class GameModel {
    /**
     * Constructor
     * @param i game ID
     * @param white white username
     * @param black black username
     * @param name game Name
     * @param g the actual game
     */
    public GameModel(Integer i, String white, String black, String name, Game g){
        gameID = i;
        whiteUsername = white;
        blackUsername = black;
        gameName = name;
        this.game = g;
    }
    public GameModel( String white, String black, String name, Game g){
        whiteUsername = white;
        blackUsername = black;
        gameName = name;
        this.game = g;
    }
    public GameModel(String name, Game g){
        gameName = name;
        this.game = g;
    }
    public GameModel(Integer i, String game, Game g){
        gameID = i;
        gameName = game;
        this.game = g;
    }

    public String toString(){
        return game.toString();
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
    private Game game;

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

    public void setGameID(int i ){
        this.gameID = i;
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
    public Game getGame(){
        return game;
    }
    public String getGameString(){
        return game.toString();
    }

}
