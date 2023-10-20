package Services;

import DAO.*;
import Requests.CreateRequest;
import Requests.JoinRequest;
import Requests.LoginRequest;
import Requests.RegisterRequest;
import Responses.*;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.ChessGame.TeamColor.WHITE;

public class ServiceUnitTests {

    @BeforeEach
    public void ClearAll() throws DataAccessException {
        UserDAO uDAO = new UserDAO();
        uDAO.clearAll();
        GameDAO gDAO = new GameDAO();
        gDAO.clearAll();
        AuthDAO aDAO = new AuthDAO();
        aDAO.clearAll();
    }
    @Test
    public void RegisterSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        Assertions.assertEquals(null,resp.getMessage(),"Bad Register");
    }
    @Test
    public void RegisterFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        RegisterRequest req1 = new RegisterRequest("Test","Hi","email");
        RegisterResponse resp1 = serv.register(req);
        Assertions.assertEquals("Error: already taken",resp1.getMessage(),"Didn't work");
    }
    @Test
    public void LoginSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        Assertions.assertEquals(null,lresp.getMessage(),"Bad Register");
    }
    @Test
    public void LoginFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        RegisterRequest req1 = new RegisterRequest("Test","Hi","email");
        RegisterResponse resp1 = serv.register(req);
        LoginRequest lreq = new LoginRequest("Bruh","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        Assertions.assertEquals("Error: unauthorized",lresp.getMessage(),"Didn't Work");
    }
    @Test
    public void LogoutSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        LogoutService loserv = new LogoutService();
        String auth = lresp.getAuthToken();
        LogoutResponse loresp = loserv.logout(auth);
        Assertions.assertEquals(null,lresp.getMessage(),"Bad Register");
    }
    @Test
    public void LogoutFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        LogoutService loserv = new LogoutService();
        String auth = lresp.getAuthToken();
        LogoutResponse loresp = loserv.logout("bruh");
        Assertions.assertEquals("Error: unauthorized",loresp.getMessage(),"Didn't Work");
    }

    @Test
    public void CreateSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        Assertions.assertEquals(null,cresp.getMessage(),"Bad Register");
    }
    @Test
    public void CreateFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh","bad");
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        Assertions.assertEquals("Error: unauthorized",cresp.getMessage(),"Didn't Work");
    }

    @Test
    public void JoinSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        JoinRequest jreq = new JoinRequest(1,WHITE,auth);
        JoinService jserv = new JoinService();
        JoinResponse jresp = jserv.join(jreq);
        Assertions.assertEquals(null,jresp.getMessage(),"Bad Register");
    }
    @Test
    public void JoinFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        JoinRequest jreq = new JoinRequest(3,WHITE,auth);
        JoinService jserv = new JoinService();
        JoinResponse jresp = jserv.join(jreq);
        Assertions.assertEquals("Error: bad request",jresp.getMessage(),"Didn't Work");
    }
    @Test
    public void ListSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        ListService liserv = new ListService();
        ListResponse liresp = liserv.listMethod(auth);
        Assertions.assertEquals(null,liresp.getMessage(),"Bad Register");
    }
    @Test
    public void ListFail(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        ListService liserv = new ListService();
        ListResponse liresp = liserv.listMethod("Bad");
        Assertions.assertEquals("Error: unauthorized",liresp.getMessage(),"Didn't Work");
    }
    @Test
    public void ClearSuccess(){
        RegisterRequest req = new RegisterRequest("Test","Hi","email");
        RegisterService serv = new RegisterService();
        RegisterResponse resp = serv.register(req);
        LoginRequest lreq = new LoginRequest("Test","Hi");
        LoginService lserv = new LoginService();
        LoginResponse lresp = lserv.login(lreq);
        String auth = lresp.getAuthToken();
        CreateRequest creq = new CreateRequest("Yuh",auth);
        CreateService cserv = new CreateService();
        CreateResponse cresp = cserv.create(creq);
        ClearService clserv = new ClearService();
        ClearResponse clresp = clserv.clear();
        Assertions.assertEquals(null,clresp.getMessage(),"Bad Register");
    }
}
