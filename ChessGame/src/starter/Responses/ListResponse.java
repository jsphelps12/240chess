package Responses;

import Model.GameModel;

import java.util.List;

/**
 * list response class
 */
public class ListResponse {
    /**
     * constructor
     * @param m message
     */
    public ListResponse(String m,List<GameModel> g){
        games = g;
        message = m;
    }

    /**
     * message
     */
    private String message;
    /**
     * the games
     */
    private List<GameModel> games;

    /**
     * get message
     * @return message
     */
    public String getMessage(){
        return message;
    }

    public void setGames(List<GameModel> g){
        this.games = g;
    }

    /**
     * get games
     * @return games
     */
    public List<GameModel> getGames(){
        return games;
    }
}
