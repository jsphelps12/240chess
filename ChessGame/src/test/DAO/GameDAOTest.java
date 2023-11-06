package DAO;

import Model.Auth;
import Model.GameModel;
import chess.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDAOTest {

    @Test
    void createGame() {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
    }

    @Test
    void readGame() {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertDoesNotThrow(() ->db.readGame(1));
    }

    @Test
    void updateWhiteUserName() {
    }

    @Test
    void updateBlackUserName() {
    }

    @Test
    void deleteGame() {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertDoesNotThrow(() ->db.deleteGame(1));
    }

    @Test
    void clearAll() {
    }
}