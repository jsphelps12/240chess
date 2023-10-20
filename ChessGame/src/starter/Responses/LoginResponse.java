package Responses;

/**
 * login response class
 */
public class LoginResponse {
    /**
     * constructor
     * @param m message
     * @param a authToken
     * @param u userName
     */
    public LoginResponse(String m,String a,String u){
        message = m;
        authToken = a;
        username = u;
    }

    /**
     * message
     */
    private String message;
    /**
     * authToken
     */
    private String authToken;
    /**
     * userName
     */
    private String username;

    /**
     * get username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * get authToken
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
