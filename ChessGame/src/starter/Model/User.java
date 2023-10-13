package Model;

/**
 * user class
 */
public class User {
    /**
     * constructor
     * @param em email
     * @param u username
     * @param pass password
     */
    public User(String em, String u, String pass){
        email = em;
        userName = u;
        passWord = pass;
    }

    /**
     * the email
     */
    private String email;
    /**
     * username
     */
    private String userName;
    /**
     * password
     */
    private String passWord;

    /**
     * Gets username
     * @return username
     */
    public String getUserName(){
        return userName;
    }

    /**
     * gets email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassWord(){
        return passWord;
    }

}
