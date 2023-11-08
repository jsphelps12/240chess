package Responses;

/**
 * logout response class
 */
public class LogoutResponse {
    /**
     * constructor
     * @param m message
     */
    public LogoutResponse(String m){
        message = m;
    }

    /**
     * message
     */
    private String message;

    /**
     * get message
     * @return message
     */
    public String getMessage(){
        return message;
    }
}
