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
    public CreateRequest(String g, String au){
        gameName = g;
        authorization = au;
    }

    /**
     * the token
     */
    private String authorization;
    /**
     * the game name
     */
    private String gameName;

    /**
     * get token
     * @return auth
     */
    public String getAuth() {
        return authorization;
    }

    public void SetAuth(String au){
        authorization = au;
    }

    /**
     * get game name
     * @return game name
     */
    public String getGameName(){
        return gameName;
    }
}
