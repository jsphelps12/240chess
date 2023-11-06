package DAO;

import Model.Auth;
import Model.User;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.SQLException;
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
    //private static HashMap<String, Auth> theAuths = new HashMap<>();

    private Database db = new Database();


    /**
     * Creates an auth
     * @param a is the token we are creating
     * @throws DataAccessException
     */
    public void createAuth(Auth a) throws DataAccessException {
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO auth (authToken,username) VALUES (?,?)")) {
            preparedStatement.setString(1,a.getAuthToken());
            preparedStatement.setString(2,a.getUsername());
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }

        //theAuths.put(a.getAuthToken(),a);
    }

    /**
     * Queries an auth
     * @param a is the username we are looking up
     * @return the auth token
     * @throws DataAccessException
     */
    public Auth readAuth(String a) throws DataAccessException{
//        if(theAuths.containsKey(a)){
//            return theAuths.get(a);
//        }
//        return null;
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT authToken,username FROM auth WHERE authToken = ?")) {
            preparedStatement.setString(1,a);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String authToken = rs.getString("authToken");
                    String username = rs.getString("username");
                    Auth auth = new Auth(username);
                    auth.setAuthToken(authToken);
                    return auth;
                }
                return null;
            }
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
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
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM auth WHERE authToken = ?")) {
            preparedStatement.setString(1,a);
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }

        //theAuths.remove(a);
    }

    /**
     * clears all in the hashmap
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM auth")) {
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
        //theAuths.clear();
    }




}
