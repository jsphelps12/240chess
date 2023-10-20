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
    private static HashMap<String, Auth> theAuths = new HashMap<>();

    /**
     * Creates an auth
     * @param a is the token we are creating
     * @throws DataAccessException
     */
    public void createAuth(Auth a) throws DataAccessException {
        theAuths.put(a.getAuthToken(),a);
    }

    /**
     * Queries an auth
     * @param a is the username we are looking up
     * @return the auth token
     * @throws DataAccessException
     */
    public Auth readAuth(String a) throws DataAccessException{
        if(theAuths.containsKey(a)){
            return theAuths.get(a);
        }
        return null;
    }

    /**
     * Updates an auth
     * @param a is the username we are looking up
     * @throws DataAccessException
     */
    public void updateAuth(String a) throws DataAccessException{

    }

    /**
     * Deletes an auth
     * @param a is the username we are looking up
     * @throws DataAccessException
     */
    public void deleteAuth(String a) throws DataAccessException{
        theAuths.remove(a);
    }

    /**
     * clears all in the hashmap
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{
        theAuths.clear();
    }




}
