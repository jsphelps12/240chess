package DAO;

import Model.User;
import dataAccess.DataAccessException;
import dataAccess.Database;

import javax.xml.crypto.Data;
import java.sql.SQLException;
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
    private static HashMap<String, User> theUsers = new HashMap<>();

    private Database db = new Database();

    /**
     * Creates user
     * @param u is user we are creating
     * @throws DataAccessException
     */
    public void createUser(User u) throws DataAccessException{
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO user (username,password,email) VALUES (?,?,?)")) {
            preparedStatement.setString(1,u.getUserName());
            preparedStatement.setString(2,u.getPassword());
            preparedStatement.setString(3,u.getEmail());
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.toString());
        } finally {
            db.returnConnection(conn);
        }
        //theUsers.put(u.getUserName(),u);
    }

    /**
     * Queries an Model.User
     * @param u is the username we are looking up
     * @return corresponding user
     * @throws DataAccessException
     */
    public User readUser(String u) throws DataAccessException{
//        if(theUsers.containsKey(u)){
//            return theUsers.get(u);
//        }
//        return null;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT username,password,email FROM user WHERE username = ?")) {
            preparedStatement.setString(1,u);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    return new User(email,username,password);
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex.toString());
        } finally {
            db.returnConnection(conn);
        }


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
        var conn = db.getConnection();
     try (var preparedStatement = conn.prepareStatement("TRUNCATE TABLE user")) {
         preparedStatement.execute();
     } catch (SQLException ex) {
         throw new DataAccessException(ex.toString());
     } finally {
         db.returnConnection(conn);
     }

    }
}
