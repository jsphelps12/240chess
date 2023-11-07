package DAO;

import Model.Auth;
import Model.User;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDAOTest {

    @BeforeEach
    public void reset(){
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
    }

    @Test
    void createAuth() throws DataAccessException {
        Auth a = new Auth("create");
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Auth a1 = db.readAuth(a.getAuthToken());
        Assertions.assertNotNull(a1);
    }

    @Test
    void createAuthFail() throws DataAccessException {
        Auth a = new Auth("create");
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Auth a1 = db. readAuth(a.getAuthToken());
        String auth = a1.getAuthToken();
        Auth a2 = new Auth(auth, "test");
        Assertions.assertThrows(DataAccessException.class,()->db.createAuth(a2),"Should have thrown");
    }

    @Test
    void readAuth() throws DataAccessException {
        Auth a = new Auth("read");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Assertions.assertDoesNotThrow(() ->db.readAuth(authString));
        Auth a1 = db.readAuth(authString);
        Assertions.assertNotNull(a1);
    }

    @Test
    void readAuthFail() throws DataAccessException {
        Auth a = new Auth("read");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Auth a1 = db.readAuth("bruh");
        Assertions.assertNull(a1);
    }

    @Test
    void deleteAuth() throws DataAccessException {
        Auth a = new Auth("delete");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Assertions.assertDoesNotThrow(() ->db.deleteAuth(authString));
        Auth a1 = db.readAuth(authString);
        Assertions.assertNull(a1);
    }

    @Test
    void deleteAuthFail() throws DataAccessException {
        Auth a = new Auth("delete");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Assertions.assertDoesNotThrow(() ->db.deleteAuth("idk"));
        Assertions.assertNotNull(db.readAuth(authString));
    }

    @Test
    void clearAll() throws DataAccessException {
        Auth a = new Auth("create");
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Auth a1 = db.readAuth(a.getAuthToken());
        Assertions.assertNotNull(a1);
        Assertions.assertDoesNotThrow(() ->db.clearAll());
        Auth a2 = db.readAuth(a.getAuthToken());
        Assertions.assertNull(a2);
    }
}