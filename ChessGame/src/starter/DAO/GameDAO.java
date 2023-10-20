package DAO;

import Model.GameModel;
import chess.ChessGame;
import chess.Game;
import dataAccess.DataAccessException;

import java.util.HashMap;

/**
 * This is the gamemodel DAO
 */
public class GameDAO {
    /**
     * constructor
     */
    public GameDAO(){}

    /**
     * hashmap of game models
     */
    private static HashMap<Integer, GameModel> games = new HashMap<>();

    /**
     * Creates
     * @param g
     * @throws DataAccessException
     */
    public void createGame(GameModel g) throws DataAccessException {
        games.put(g.getGameID(),g);
    }

    /**
     * Queries a game
     * @param i is the index we are looking up
     * @return a Model.GameModel
     * @throws DataAccessException
     */
    public GameModel readGame(Integer i) throws DataAccessException{
        if(games.containsKey(i)){
            return games.get(i);
        }
        return null;
    }

    /**
     * Updates a game
     * @param i index of game we are updating
     * @throws DataAccessException
     */
    public void updateGame(Integer i) throws DataAccessException{

    }

    public void updateWhiteUserName(Integer i, String user) throws DataAccessException {
        GameModel g = readGame(i);
        g.setWhiteUsername(user);
    }

    public void updateBlackUserName(Integer i, String user) throws DataAccessException {
        GameModel g = readGame(i);
        g.setBlackUsername(user);
    }

    /**
     * Delete Game
     * @param i index of game we are deleting
     * @throws DataAccessException
     */
    public void deleteGame(Integer i) throws DataAccessException{

    }

    /**
     * clear all
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{
        games.clear();
    }

    /**
     * return the hashmap for list service
     * @return games
     */
    public HashMap<Integer, GameModel> getGames(){
        return games;
    }
}
