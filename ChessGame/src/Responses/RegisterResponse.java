/**
 * Register response class
 */
public class RegisterResponse {
    /**
     * constructor
     * @param m message
     * @param a auth token
     * @param u username
     */
    public RegisterResponse(String m,String a,String u){
        message = m;
        authToken = a;
        userName = u;
    }

    /**
     * message
     */
    private String message;
    /**
     * authtoken
     */
    private String authToken;
    /**
     * username
     */
    private String userName;

    /**
     * get username
     * @return username
     */
    public String getUserName(){
        return userName;
    }

    /**
     * get auth token
     * @return authToken
     */
    public String getAuthToken(){
        return authToken;
    }

    /**
     * get message
     * @return message
     */
    public String getMessage(){
        return message;
    }
}
