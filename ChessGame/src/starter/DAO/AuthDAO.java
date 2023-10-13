package DAO;

import Model.Auth;
import dataAccess.DataAccessException;

import java.util.HashMap;

/**
 * This is the authorization token DAO
 */
public class AuthDAO {
    /**
     * constructor
     */
    public AuthDAO() {}

    /**
     * hashmap of tokens
     */
    private HashMap<String, Auth> theAuths;

    /**
     * Creates an auth
     * @param a is the token we are creating
     * @throws DataAccessException
     */
    public void createAuth(Auth a) throws DataAccessException {

    }

    /**
     * Queries an auth
     * @param u is the username we are looking up
     * @return the auth token
     * @throws DataAccessException
     */
    public Auth readAuth(String u) throws DataAccessException{
        return new Auth("","");
    }

    /**
     * Updates an auth
     * @param u is the username we are looking up
     * @throws DataAccessException
     */
    public void updateAuth(String u) throws DataAccessException{

    }

    /**
     * Deletes an auth
     * @param u is the username we are looking up
     * @throws DataAccessException
     */
    public void deleteAuth(String u) throws DataAccessException{

    }

    /**
     * clears all in the hashmap
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{

    }




}
