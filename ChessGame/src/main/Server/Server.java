package Server;

import Handlers.*;
import spark.*;

public class Server {

    public static void main(String[] args) {
        new Server().run();
    }
    private void run(){

        Spark.port(6969);

        Spark.externalStaticFileLocation("web");

        Spark.webSocket("/connect", WSServer.class);

        Spark.post("/session",new LoginHandler());
        Spark.post("/user",new RegisterHandler());
        Spark.delete("/db",new ClearHandler());
        Spark.delete("/session",new LogoutHandler());
        Spark.post("/game", new CreateHandler());
        Spark.put("/game", new JoinHandler());
        Spark.get("/game", new ListHandler());

    }



}
