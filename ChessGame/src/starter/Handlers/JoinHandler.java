package Handlers;

import Requests.JoinRequest;
import Responses.JoinResponse;
import Services.JoinService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        String auth = request.headers("authorization");
        JoinRequest joinReq = gson.fromJson(request.body(), JoinRequest.class);
        joinReq.setAuthorization(auth);
        JoinService joinServ = new JoinService();
        JoinResponse joinResp= joinServ.join(joinReq);
        String strResp = gson.toJson(joinResp);
        response.body(strResp);
        if(joinResp.getMessage() == null) {
            response.status(200);
        }
        else if(joinResp.getMessage() == "Error: bad request") {
            response.status(400);
        }
        else if(joinResp.getMessage() == "Error: unauthorized") {
            response.status(401);
        }
        else if(joinResp.getMessage() == "Error: already taken"){
            response.status(403);
        }
        else{
            response.status(500);
        }
        return response.body();
    }
}
