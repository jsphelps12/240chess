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
    private HashMap<Integer, GameModel> games;

    /**
     * Creates
     * @param g
     * @throws DataAccessException
     */
    public void createGame(GameModel g) throws DataAccessException {

    }

    /**
     * Queries a game
     * @param i is the index we are looking up
     * @return a Model.GameModel
     * @throws DataAccessException
     */
    public GameModel readGame(Integer i) throws DataAccessException{
        return new GameModel(1, "", "", "", new Game());
    }

    /**
     * Updates a game
     * @param i index of game we are updating
     * @throws DataAccessException
     */
    public void updateGame(Integer i) throws DataAccessException{

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

    }

    /**
     * return the hashmap for list service
     * @return games
     */
    public HashMap<Integer, GameModel> getGames(){
        return games;
    }

}
