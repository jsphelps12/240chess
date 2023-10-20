package Services;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.Auth;
import Model.GameModel;
import Responses.JoinResponse;
import Responses.ListResponse;
import dataAccess.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * list service
 * gives a list of all the games
 */
public class ListService {
    /**
     * listMethod
     * @param au authorization token
     * @return list response
     */
    public ListResponse listMethod(String au){
        try {
            AuthDAO aDAO = new AuthDAO();
            if (aDAO.readAuth(au) == null) {
                return new ListResponse("Error: unauthorized",null);
            }
            GameDAO gDAO = new GameDAO();
            List<GameModel> gList = new ArrayList<>();
            for(Integer i : gDAO.getGames().keySet()){
                gList.add(gDAO.readGame(i));
            }
            return new ListResponse(null, gList);
        }
        catch (DataAccessException e){
            return new ListResponse("Error: bad access",null);
        }
    }
}
