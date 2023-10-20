package Requests;

/**
 * login request class
 */
public class LoginRequest {
    /**
     * constructor
     * @param u username
     * @param p password
     */
    public LoginRequest(String u, String p){
        username = u;
        password = p;
    }

    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;

    /**
     * get username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * get password
     * @return password
     */
    public String getPassword(){
        return password;
    }

}
