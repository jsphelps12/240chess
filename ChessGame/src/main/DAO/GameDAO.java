package DAO;

import Model.GameModel;
import chess.Game;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the gamemodel DAO
 */
public class GameDAO {
    /**
     * constructor
     */
    public GameDAO(){}

    /**
     * hashmap of game models
     */
    //private static HashMap<Integer, GameModel> games = new HashMap<>();

    private Database db = new Database();

    /**
     * Creates
     * @param g
     * @throws DataAccessException
     */
    public void createGame(GameModel g) throws DataAccessException {
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO game (whiteUser,blackUser,name,board) VALUES (?,?,?,?)")) {
            preparedStatement.setString(1,g.getWhiteUsername());
            preparedStatement.setString(2,g.getBlackUsername());
            preparedStatement.setString(3,g.getGameName());
            preparedStatement.setString(4,g.getGameString());
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
        boolean commit1 = true;
        var conn1 = db.getConnection();
        try (var preparedStatement = conn1.prepareStatement("SELECT id FROM game WHERE name = ? AND board = ?")) {
            preparedStatement.setString(1,g.getGameName());
            preparedStatement.setString(2,g.getGame().toString());
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    g.setGameID(id);
                }
            }
        } catch (SQLException ex) {
            commit1 = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit1);
        }
        //games.put(g.getGameID(),g);
    }

    /**
     * Queries a game
     * @param i is the index we are looking up
     * @return a Model.GameModel
     * @throws DataAccessException
     */
    public GameModel readGame(Integer i) throws DataAccessException{
//        if(games.containsKey(i)){
//            return games.get(i);
//        }
//        return null;
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT id,whiteUser,blackUser,name,board FROM game WHERE id = ?")) {
            preparedStatement.setInt(1,i);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String white = rs.getString("whiteUser");
                    String black = rs.getString("blackUser");
                    String name = rs.getString("name");
                    String codifiedGame = rs.getString("board");
                    Game game = new Game(codifiedGame);
                    GameModel gm = new GameModel(id,white,black,name,game);
                    return gm;
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
     * Updates a game
     * @param i index of game we are updating
     * @throws DataAccessException
     */


    public void updateWhiteUserName(Integer i, String user) throws DataAccessException {
//        GameModel g = readGame(i);
//        g.setWhiteUsername(user);
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("UPDATE game SET whiteUser = ? WHERE id = ?")) {
            preparedStatement.setString(1,user);
            preparedStatement.setInt(2,i);
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
    }


    public void updateBlackUserName(Integer i, String user) throws DataAccessException {
//        GameModel g = readGame(i);
//        g.setBlackUsername(user);
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("UPDATE game SET blackUser = ? WHERE id = ?")) {
            preparedStatement.setString(1,user);
            preparedStatement.setInt(2,i);
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
    }

    public void updateBoard(Integer i, String newBoard) throws DataAccessException {
//        GameModel g = readGame(i);
//        g.setBlackUsername(user);
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("UPDATE game SET board = ? WHERE id = ?")) {
            preparedStatement.setString(1,newBoard);
            preparedStatement.setInt(2,i);
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
    }

    /**
     * Delete Game
     * @param i index of game we are deleting
     * @throws DataAccessException
     */
    public void deleteGame(Integer i) throws DataAccessException{
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM game WHERE id = ?")) {
            preparedStatement.setInt(1,i);
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }
    }

    /**
     * clear all
     * @throws DataAccessException
     */
    public void clearAll() throws DataAccessException{
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM game")) {
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        }
        try(var preparedStatement = conn.prepareStatement("ALTER TABLE game AUTO_INCREMENT = 1")){
            preparedStatement.execute();
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        }finally {
            db.closeConnection(commit);
        }
        //games.clear();
    }

    /**
     * return the hashmap for list service
     *
     * @return games
     */
    public List<GameModel> getGames() throws DataAccessException {
        List<GameModel> theList = new ArrayList<>();
        boolean commit = true;
        var conn = db.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT id,whiteUser,blackUser,name,board FROM game")) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String white = rs.getString("whiteUser");
                    String black = rs.getString("blackUser");
                    String name = rs.getString("name");
                    String codifiedGame = rs.getString("board");
                    Game game = new Game(codifiedGame);
                    GameModel gm = new GameModel(id,white,black,name,null);
                    theList.add(gm);
                }
            }
        } catch (SQLException ex) {
            commit = false;
            throw new DataAccessException(ex.toString());
        } finally {
            db.closeConnection(commit);
        }


        //return games;
        return theList;
    }
}
