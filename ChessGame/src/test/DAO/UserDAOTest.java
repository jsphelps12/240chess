package DAO;

import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void clearAll() {
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
    }

    @Test
    void createUser() {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        Assertions.assertDoesNotThrow(() ->db.createUser(u));
    }

    @Test
    void readUser() {
        User u = new User("em","user","pass");
        var db = new UserDAO();
        String user = "user";

        Assertions.assertDoesNotThrow(() ->db.readUser(user));
    }
}