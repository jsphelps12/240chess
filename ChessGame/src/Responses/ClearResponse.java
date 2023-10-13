/**
 * clear response class
 */
public class ClearResponse {
    /**
     * constructor
     * @param m message
     */
    public ClearResponse(String m){
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
