package Requests;

/**
 * Register Request class
 */
public class RegisterRequest {
    /**
     * constructor
     * @param u username
     * @param p password
     * @param e email
     */
    public RegisterRequest(String u, String p,String e){
        username = u;
        password = p;
        email = e;
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
     * email
     */
    private String email;

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

    /**
     * get email
     * @return email
     */
    public String getEmail(){
        return email;
    }
}
