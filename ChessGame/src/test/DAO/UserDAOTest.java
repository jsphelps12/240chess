package DAO;

import Model.User;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {


    @BeforeEach
    public void reset(){
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
    }

    @Test
    void createUser() throws DataAccessException {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.createUser(u));
        User u1 = db.readUser(u.getUserName());
        Assertions.assertNotNull(u1);
    }

    @Test
    void createUserFail() throws DataAccessException {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.createUser(u));
        User u1 = new User("newEm","user","newPass");
        Assertions.assertThrows(DataAccessException.class,()->db.createUser(u1),"Should have thrown");
        //Assertions.assertEquals(u2.getPassword(),"pass");
    }

    @Test
    void readUser() throws DataAccessException {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        String user = "user";
        Assertions.assertDoesNotThrow(() ->db.createUser(u));
        Assertions.assertDoesNotThrow(() ->db.readUser(user));
        User u1 = db.readUser(u.getUserName());
        Assertions.assertNotNull(u1);

    }
    @Test
    void readUserFail() throws DataAccessException {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        String user = "random";
        User u1 = db.readUser(user);
        Assertions.assertNull(u1);
    }

    @Test
    void clearAll() throws DataAccessException {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.createUser(u));
        User u1 = db.readUser(u.getUserName());
        Assertions.assertNotNull(u1);
        Assertions.assertDoesNotThrow(() ->db.clearAll());
        User u2 = db.readUser(u.getUserName());
        Assertions.assertNull(u2);
    }
}