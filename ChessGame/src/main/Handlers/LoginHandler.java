package Handlers;
import Requests.LoginRequest;
import Responses.LoginResponse;
import Services.LoginService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        LoginRequest logReq = gson.fromJson(request.body(), LoginRequest.class);
        LoginService logServ = new LoginService();
        LoginResponse logResp = logServ.login(logReq);
        String strResp = gson.toJson(logResp);
        response.body(strResp);
        if(logResp.getMessage() == null) {
            response.status(200);
        }
        else if(logResp.getMessage() == "Error: unauthorized") {
            response.status(401);
        }
        else{
            response.status(500);
        }
        return response.body();
    }
}
