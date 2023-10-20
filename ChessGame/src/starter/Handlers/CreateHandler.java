package Handlers;

import Requests.CreateRequest;
import Responses.CreateResponse;
import Services.CreateService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        String auth = request.headers("authorization");
        CreateRequest createReq = gson.fromJson(request.body(),CreateRequest.class);
        createReq.SetAuth(auth);
        CreateService createServ = new CreateService();
        CreateResponse createResp = createServ.create(createReq);
        String strResp = gson.toJson(createResp);
        response.body(strResp);
        if(createResp.getMessage() == null) {
            response.status(200);
        }
        else if(createResp.getMessage() == "Error: unauthorized") {
            response.status(401);
        }
        else{
            response.status(500);
        }
        return response.body();
    }
}
