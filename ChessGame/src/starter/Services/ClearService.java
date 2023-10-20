package Services;

import DAO.*;
import Responses.ClearResponse;
import Responses.RegisterResponse;
import dataAccess.DataAccessException;

/**
 * clear service
 * clears all the databases
 */
public class ClearService {
    /**
     * clear
     * @return clear response
     */
    public ClearResponse clear(){
        try {
            UserDAO uDAO = new UserDAO();
            uDAO.clearAll();
            AuthDAO aDAO = new AuthDAO();
            aDAO.clearAll();
            GameDAO gDAO = new GameDAO();
            gDAO.clearAll();
            return new ClearResponse(null);
        }
        catch(DataAccessException e){
            return new ClearResponse("Error: bad access");
        }
    }
}
