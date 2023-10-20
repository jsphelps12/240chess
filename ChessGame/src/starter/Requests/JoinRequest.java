package Requests;

import chess.ChessGame;

/**
 * join request class
 */
public class JoinRequest {
    /**
     * constructor
     * @param i ID
     * @param c color
     * @param au auth token
     */
    public JoinRequest(Integer i, ChessGame.TeamColor c,String au){
        gameID = i;
        playerColor = c;
        authorization = au;
    }

    /**
     * auth token
     */
    private String authorization;
    /**
     * ID
     */
    private Integer gameID;
    /**
     * color
     */
    private ChessGame.TeamColor playerColor;

    /**
     * get ID
     * @return ID
     */
    public Integer getGameID(){
        return gameID;
    }

    public void setAuthorization(String au){
        authorization = au;
    }

    /**
     * get color
     * @return color
     */
    public ChessGame.TeamColor getColor(){
        return playerColor;
    }

    /**
     * get auth token
     * @return auth
     */
    public String getAuthorization(){
        return authorization;
    }
}
