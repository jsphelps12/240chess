package Responses;

/**
 * join response class
 */
public class JoinResponse {
    /**
     * constructor
     * @param m message
     */
    public JoinResponse(String m){
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
