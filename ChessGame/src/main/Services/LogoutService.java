package Services;

import DAO.AuthDAO;
import Responses.LogoutResponse;
import dataAccess.DataAccessException;

/**
 *Logout Service
 * logs an user out
 */
public class LogoutService {
    /**
     * logout
     * @param au authorization token
     * @return logout resopnse
     */
    public LogoutResponse logout(String au){
        try {
            AuthDAO aDAO = new AuthDAO();
            if(aDAO.readAuth(au) == null){
                return new LogoutResponse("Error: unauthorized");
            }
            else {
                aDAO.deleteAuth(au);
            }
            return new LogoutResponse(null);
        }
        catch(DataAccessException e){
            return new LogoutResponse("Error: bad access");
        }

    }
}
