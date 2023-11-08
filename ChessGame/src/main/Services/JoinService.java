package Services;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.GameModel;
import Requests.JoinRequest;
import Responses.JoinResponse;
import chess.ChessGame;
import dataAccess.DataAccessException;

/**
 * Join service
 * if a game exists, and a color a specified, the caller is added to the game, if no color, then added as spectator
 */
public class JoinService {
    /**
     * join
     * @param request join request
     * @return join response
     */
    public JoinResponse join(JoinRequest request){
        try{
            String au = request.getAuthorization();
            int i = request.getGameID();
            Integer iD = (Integer) i;
            ChessGame.TeamColor color = request.getColor();
            AuthDAO aDAO = new AuthDAO();
            if(au == null || iD == null){
                return new JoinResponse("Error: bad request");
            }
            if(aDAO.readAuth(au) == null){
                return new JoinResponse("Error: unauthorized");
            }
            String user = aDAO.readAuth(au).getUsername();
            GameDAO gDAO = new GameDAO();
            if(gDAO.readGame(iD) == null){
                return new JoinResponse("Error: bad request");
            }
            GameModel gModel = gDAO.readGame(iD);
            if(color == ChessGame.TeamColor.WHITE && gModel.getWhiteUsername() != null){
                return new JoinResponse("Error: already taken");
            }
            if(color == ChessGame.TeamColor.BLACK && gModel.getBlackUsername() != null){
                return new JoinResponse("Error: already taken");
            }
            if(color == ChessGame.TeamColor.WHITE){
                gDAO.updateWhiteUserName(iD,user);
            }
            if(color == ChessGame.TeamColor.BLACK){
                gDAO.updateBlackUserName(iD,user);
            }
            return new JoinResponse(null);
        }
        catch (DataAccessException e){
            return new JoinResponse("Error: bad access");
        }
    }
}

