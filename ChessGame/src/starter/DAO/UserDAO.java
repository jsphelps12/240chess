package DAO;

import Model.User;
import dataAccess.DataAccessException;

import java.util.HashMap;

/**
 * User DAO
 */
public class UserDAO {
    /**
     * constructor
     */
    public UserDAO(){}

    /**
     * hashmap of users, indexed by usernames
     */
    private HashMap<String, User> theUsers;

    /**
     * Creates user
     * @param u is user we are creating
     * @throws DataAccessException
     */
    public void createUser(User u) throws DataAccessException{
    }

    /**
     * Queries an Model.User
     * @param u is the username we are looking up
     * @return corresponding user
     * @throws DataAccessException
     */
    public User readUser(String u) throws DataAccessException{
        return new User("","","");
    }

    /**
     * Updates user
     * @param u the username of the user we are updating
     * @throws DataAccessException
     */
    public void updateUser(String u) throws DataAccessException{

    }

    /**
     * Deletes an user
     * @param u is the username of the user we are deleting
     * @throws DataAccessException
     */
    public void deleteUser(String u) throws DataAccessException{

    }

    /**
     * clears all
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{

    }
}
