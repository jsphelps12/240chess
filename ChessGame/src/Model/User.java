public class User {
    public User(String em, String u, String pass){
        email = em;
        userName = u;
        passWord = pass;
    }

    private String email;
    private String userName;
    private String passWord;

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassWord(){
        return passWord;
    }

}
