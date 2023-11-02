package DAO;

import Model.Auth;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDAOTest {

    @Test
    void createAuth() {
        Auth a = new Auth("create");
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
    }

    @Test
    void readAuth() {
        Auth a = new Auth("read");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Assertions.assertDoesNotThrow(() ->db.readAuth(authString));
    }

    @Test
    void deleteAuth() {
        Auth a = new Auth("delete");
        var db = new AuthDAO();
        String authString = a.getAuthToken();
        Assertions.assertDoesNotThrow(() ->db.createAuth(a));
        Assertions.assertDoesNotThrow(() ->db.deleteAuth(authString));
    }

    @Test
    void clearAll() {
        var db = new AuthDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
    }
}