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
        userName = u;
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
    private String userName;

    /**
     * get username
     * @return username
     */
    public String getUserName(){
        return userName;
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
