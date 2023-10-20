package Responses;

/**
 * create response class
 */
public class CreateResponse {
    /**
     * constructor
     * @param m message
     * @param i gameID
     */
    public CreateResponse(String m, Integer i){
        message = m;
        gameID = i;
    }
    public CreateResponse(String m){
        message = m;
    }

    /**
     * message
     */
    private String message;
    /**
     * gameID
     */
    private Integer gameID;

    /**
     * get message
     * @return message
     */
    public String getMessage(){
        return message;
    }

    /**
     * get GameID
     * @return gameID
     */
    public Integer getGameID(){
        return gameID;
    }
}
