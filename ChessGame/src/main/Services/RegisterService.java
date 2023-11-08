package Services;

import DAO.AuthDAO;
import DAO.UserDAO;
import Model.Auth;
import Model.User;
import Requests.RegisterRequest;
import Responses.RegisterResponse;
import dataAccess.DataAccessException;

/**
 * Register Service Class
 * registers a new user
 */
public class RegisterService {
    /**
     * Register service method
     * @param request register request
     * @return register response
     */
    public RegisterResponse register(RegisterRequest request){
        try{


            String u = request.getUsername();
            String p = request.getPassword();
            String e = request.getEmail();
            if(u == null || p == null || e == null){
                return new RegisterResponse("Error: bad request",null,null);
            }
            UserDAO uDAO = new UserDAO();
            if(uDAO.readUser(u) != null){
                return new RegisterResponse("Error: already taken",null,null);
            }
            User user = new User(e,u,p);
            uDAO.createUser(user);
            Auth auth = new Auth(u);
            AuthDAO aDAO = new AuthDAO();
            aDAO.createAuth(auth);

            return new RegisterResponse(null, auth.getAuthToken(),user.getUserName());

        } catch (DataAccessException e) {
            return new RegisterResponse("Error: bad access", null,null);
        }
    }
}
