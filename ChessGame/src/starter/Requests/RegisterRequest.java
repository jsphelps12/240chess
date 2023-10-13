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
        userName = u;
        passWord = p;
        email = e;
    }

    /**
     * username
     */
    private String userName;
    /**
     * password
     */
    private String passWord;

    /**
     * email
     */
    private String email;

    /**
     * get username
     * @return username
     */
    public String getUserName(){
        return userName;
    }

    /**
     * get password
     * @return password
     */
    public String getPassWord(){
        return passWord;
    }

    /**
     * get email
     * @return email
     */
    public String getEmail(){
        return email;
    }
}
