package DAO;

import Model.Auth;
import Model.GameModel;
import chess.Game;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GameDAOTest {


    @BeforeEach
    public void reset(){
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.clearAll());
    }
    @Test
    void createGame() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        GameModel g1 = db.readGame(1);
        Assertions.assertNotNull(g1);
    }

    @Test
    void createGameFail() {
        Game g = new Game();
        GameModel gm = new GameModel(null,"black",null,g);
        var db = new GameDAO();
        Assertions.assertThrows(DataAccessException.class,() ->db.createGame(gm), "Bruh");
    }

    @Test
    void readGame() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertDoesNotThrow(() ->db.readGame(1));
        GameModel gm1 = db.readGame(1);
        Assertions.assertNotNull(gm1);
    }
    @Test
    void readGameFail() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        GameModel g2 = db.readGame(2);
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertNull(g2);
    }

    @Test
    void updateWhiteUserName() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        String white = "userWhite";
        Assertions.assertDoesNotThrow(()->db.updateWhiteUserName(1,white));
        GameModel gm1 = db.readGame(1);
        Assertions.assertEquals(white,gm1.getWhiteUsername());
    }
    @Test
    void updateWhiteUserNameFail() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        String white = "userWhite";
        db.updateWhiteUserName(444,white);
        GameModel g1 = db.readGame(1);
        Assertions.assertNotEquals(white,g1.getWhiteUsername(),"Bruh");
    }

    @Test
    void updateBlackUserName() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        String black = "userBlack";
        Assertions.assertDoesNotThrow(()->db.updateBlackUserName(1,black));
        GameModel gm1 = db.readGame(1);
        Assertions.assertEquals(black,gm1.getBlackUsername());
    }

    @Test
    void updateBlackUserNameFail() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        String black = "userBlack";
        db.updateWhiteUserName(444,black);
        GameModel g1 = db.readGame(1);
        Assertions.assertNotEquals(black,g1.getBlackUsername(),"Bruh");
    }

    @Test
    void deleteGame() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertDoesNotThrow(() ->db.deleteGame(1));
        GameModel gm1 = db.readGame(1);
        Assertions.assertNull(gm1);
    }

    @Test
    void deleteGameFail() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        Assertions.assertDoesNotThrow(() ->db.deleteGame(3));
        GameModel g1 = db.readGame(1);
        Assertions.assertNotNull(g1);
    }
    @Test
    void getGames() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("1","2","3",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        GameModel gm1 = new GameModel("1","2","3",g);
        Assertions.assertDoesNotThrow(() ->db.createGame(gm1));
        Assertions.assertDoesNotThrow(()->db.getGames());
        List<GameModel> theList = db.getGames();
        Assertions.assertNotNull(theList);
        GameModel gm2 = theList.get(0);
        Assertions.assertEquals(gm2.getGameName(),"3");
    }
    @Test
    void getGamesFail() throws DataAccessException {
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(()->db.getGames());
        List<GameModel> theList = db.getGames();
        Assertions.assertEquals(0,theList.size());
    }

    @Test
    void clearAll() throws DataAccessException {
        Game g = new Game();
        GameModel gm = new GameModel("white","black","name",g);
        var db = new GameDAO();
        Assertions.assertDoesNotThrow(() ->db.createGame(gm));
        GameModel g1 = db.readGame(1);
        Assertions.assertNotNull(g1);
        Assertions.assertDoesNotThrow(() ->db.clearAll());
        GameModel g2 = db.readGame(1);
        Assertions.assertNull(g2);
    }


}