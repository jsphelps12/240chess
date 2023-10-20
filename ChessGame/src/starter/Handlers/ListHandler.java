package Handlers;

import Responses.ListResponse;
import Services.ListService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ListHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        String auth = request.headers("authorization");
        ListService listServ = new ListService();
        ListResponse listResp = listServ.listMethod(auth);
        String strResp = gson.toJson(listResp);
        response.body(strResp);
        if(listResp.getMessage() == null) {
            response.status(200);
        }
        else if(listResp.getMessage() == "Error: unauthorized") {
            response.status(401);
        }
        else{
            response.status(500);
        }

        return response.body();
    }
}
