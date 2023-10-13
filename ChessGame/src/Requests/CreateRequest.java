import chess.ChessGame;

/**
 * Create Request class
 */
public class CreateRequest {
    /**
     * constructor
     * @param g game name
     * @param au auth token
     */
    public CreateRequest(String g, Auth au){
        gameName = g;
        auth = au;
    }

    /**
     * the token
     */
    private Auth auth;
    /**
     * the game name
     */
    private String gameName;

    /**
     * get token
     * @return auth
     */
    public Auth getAuth() {
        return auth;
    }

    /**
     * get game name
     * @return game name
     */
    public String getGameName(){
        return gameName;
    }
}
