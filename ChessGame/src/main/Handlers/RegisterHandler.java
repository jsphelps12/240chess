package Handlers;

import Requests.RegisterRequest;
import Responses.RegisterResponse;
import Services.RegisterService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        RegisterRequest regReq = gson.fromJson(request.body(),RegisterRequest.class);
        RegisterService regServ = new RegisterService();
        RegisterResponse regResp = regServ.register(regReq);
        String strResp = gson.toJson(regResp);
        response.body(strResp);
        if(regResp.getMessage() == null) {
            response.status(200);
        }
        else if(regResp.getMessage() == "Error: bad request"){
            response.status(400);
        }
        else if(regResp.getMessage() == "Error: already taken"){
            response.status(403);
        }
        else if(regResp.getMessage() == "Error: bad access"){
            response.status(500);
        }
        return response.body();
    }
}
