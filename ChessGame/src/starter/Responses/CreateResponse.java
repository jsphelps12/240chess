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
    public CreateResponse(String m,int i){
        message = m;
        gameID = i;
    }

    /**
     * message
     */
    private String message;
    /**
     * gameID
     */
    private int gameID;

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
    public int getGameID(){
        return gameID;
    }
}
