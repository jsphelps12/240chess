public class Auth {
    public Auth(String au, String u){
        authToken = au;
        userName = u;
    }
    private String authToken;
    private String userName;

    public String getAuthToken(){
        return authToken;
    }

    public String getUserName(){
        return userName;
    }
}
