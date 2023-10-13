package Requests;

import Model.Auth;
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
    public JoinRequest(int i, ChessGame.TeamColor c,Auth au){
        ID = i;
        color = c;
        auth = au;
    }

    /**
     * auth token
     */
    private Auth auth;
    /**
     * ID
     */
    private int ID;
    /**
     * color
     */
    private ChessGame.TeamColor color;

    /**
     * get ID
     * @return ID
     */
    public int getID(){
        return ID;
    }

    /**
     * get color
     * @return color
     */
    public ChessGame.TeamColor getColor(){
        return color;
    }

    /**
     * get auth token
     * @return auth
     */
    public Auth getAuth(){
        return auth;
    }
}
