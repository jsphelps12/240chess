package Handlers;

import Responses.LogoutResponse;
import Services.LogoutService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        String auth = request.headers("authorization");
        LogoutService logServ = new LogoutService();
        LogoutResponse logResp = logServ.logout(auth);
        String strResp = gson.toJson(logResp);
        response.body(strResp);
        if(logResp.getMessage() == null){
            response.status(200);
        }
        else if(logResp.getMessage() == "Error: unauthorized"){
            response.status(401);
        }
        else {
            response.status(500);
        }
        return response.body();
    }
}
