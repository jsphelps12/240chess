package Services;

import DAO.AuthDAO;
import DAO.UserDAO;
import Model.Auth;
import Model.User;
import Requests.LoginRequest;
import Responses.LoginResponse;
import dataAccess.DataAccessException;

/**
 * login service
 * logs in an existing user
 */
public class LoginService {
    /**
     * login
     * @param request login request
     * @return login response
     */
    public LoginResponse login(LoginRequest request) {
        try {
            String u = request.getUsername();
            String p = request.getPassword();
            UserDAO uDAO = new UserDAO();
            if (uDAO.readUser(u) == null) {
                return new LoginResponse("Error: unauthorized", null, null);
            }
            else{
                User user = uDAO.readUser(u);
                if(!user.getPassword().equals(p)){
                    return new LoginResponse("Error: unauthorized", null, null);
                }
                else{
                    Auth auth = new Auth(u);
                    AuthDAO aDAO = new AuthDAO();
                    aDAO.createAuth(auth);
                    return new LoginResponse(null,auth.getAuthToken(),auth.getUsername());
                }
            }
        } catch (DataAccessException e) {
            return new LoginResponse("Error: bad access", null, null);
        }
    }
}
