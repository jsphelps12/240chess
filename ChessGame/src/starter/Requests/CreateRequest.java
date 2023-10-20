package Requests;

import Model.Auth;

/**
 * Create Request class
 */
public class CreateRequest {
    /**
     * constructor
     * @param g game name
     * @param au auth token
     */
    public CreateRequest(String g, Auth au){
        gameName = g;
        authorization = au;
    }

    /**
     * the token
     */
    private Auth authorization;
    /**
     * the game name
     */
    private String gameName;

    /**
     * get token
     * @return auth
     */
    public Auth getAuth() {
        return authorization;
    }

    /**
     * get game name
     * @return game name
     */
    public String getGameName(){
        return gameName;
    }
}
