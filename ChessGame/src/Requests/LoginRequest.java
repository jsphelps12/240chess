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
        userName = u;
        passWord = p;
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

}
