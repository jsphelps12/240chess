package Services;

import Model.Auth;
import Responses.LogoutResponse;

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
    public LogoutResponse logout(Auth au){
        return new LogoutResponse("");
    }
}
