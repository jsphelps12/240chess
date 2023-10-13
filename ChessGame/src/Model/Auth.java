/**
 * Auth Class
 */
public class Auth {
    /**
     * Constructor
     * @param au auToken string
     * @param u username
     */
    public Auth(String au, String u){
        authToken = au;
        userName = u;
    }

    /**
     * authtoken
     */
    private String authToken;
    /**
     * username
     */
    private String userName;

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
    public String getUserName(){
        return userName;
    }
}
