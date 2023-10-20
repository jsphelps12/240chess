package Handlers;

import Responses.ClearResponse;
import Services.ClearService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClearHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        ClearService clearServ = new ClearService();
        ClearResponse clearResp = clearServ.clear();
        String strResp = gson.toJson(clearResp);
        response.body(strResp);
        if(clearResp.getMessage() == "Error: bad access"){
            response.status(500);
        }
        response.status(200);
        return response.body();
    }
}
