package Services;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.GameModel;
import Requests.CreateRequest;
import Responses.CreateResponse;
import chess.Game;
import dataAccess.DataAccessException;

/**
 * create service
 * creates a new game
 */
public class CreateService {
    /**
     * create
     * @param request create request
     * @return create response
     */
    public CreateResponse create(CreateRequest request){
        try{
            AuthDAO aDAO = new AuthDAO();
            if(aDAO.readAuth(request.getAuth()) == null){
                return new CreateResponse("Error: unauthorized",null);
            }
            String gName = request.getGameName();
            GameDAO gDAO = new GameDAO();
            GameModel gModel = new GameModel(gName,new Game());
            gDAO.createGame(gModel);

            return new CreateResponse(null, gModel.getGameID());
        }
        catch(DataAccessException e){
            return new CreateResponse("Error: bad access",null);
        }
    }
}
