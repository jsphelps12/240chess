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
        username = u;
        password = pass;
    }

    /**
     * the email
     */
    private String email;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;

    /**
     * Gets username
     * @return username
     */
    public String getUserName(){
        return username;
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
    public String getPassword(){
        return password;
    }

}
