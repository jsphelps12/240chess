package Model;

import java.util.UUID;

/**
 * Model.Auth Class
 */
public class Auth {
    /**
     * Constructor
     * @param u username
     */
    public Auth(String u){
        authToken = UUID.randomUUID().toString();
        username = u;
    }

    /**
     * authtoken
     */
    private String authToken;
    /**
     * username
     */
    private String username;

    /**
     * Get authtoken
     * @return the authtoken
     */
    public String getAuthToken(){
        return authToken;
    }

    /**
     * Get username
     * @return the username
     */
    public String getUsername(){
        return username;
    }
}
