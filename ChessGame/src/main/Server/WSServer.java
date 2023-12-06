package Server;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.Auth;
import Model.GameModel;
import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import webSocketMessages.serverMessages.LoadGameCommand;
import webSocketMessages.serverMessages.NotificationMessage;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.UserGameCommand;


import java.io.IOException;
import java.util.Map;
import java.util.Timer;


@WebSocket
public class WSServer {

    Gson gson = new Gson();

    private Session session;
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        this.session = session;
        System.out.println("Connected: " + session.getRemoteAddress().getAddress());
//        this.session.getRemote().sendString("TEST");
    }
    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, DataAccessException {
        //System.out.print(message);
        Map messageMap = gson.fromJson(message, Map.class);
        if(messageMap.get("commandType").equals("JOIN_PLAYER")){
            joinHelper(messageMap);
        }
        else if(messageMap.get("commandType").equals("JOIN_OBSERVER")){
            observeHelper(messageMap);
        }

    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Closed: " + statusCode + " - " + reason);
    }

    @OnWebSocketError
    public void onError(Throwable e){
        e.printStackTrace();
    }



    public void observeHelper(Map messageMap) throws DataAccessException, IOException {
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        String game = gameModel.getGame().toString();

        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game);
        String message = gson.toJson(loadGameCommand);
        this.session.getRemote().sendString(message);
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        String user = auth.getUsername();
        String notification = user + " has joined the game as an observer" ;
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
        this.session.getRemote().sendString(messageNoti);
    }

public void joinHelper(Map messageMap) throws DataAccessException, IOException {
//    {"gameID":2,"teamColor":"WHITE","commandType":"JOIN_PLAYER","authToken":"303a2536-1c61-426f-9188-c22b8831c11e"}
    Double id = (Double) messageMap.get("gameID");
    double idDouble = id.doubleValue();
    int gameID = (int) Math.round(idDouble);
    GameDAO gDAO = new GameDAO();
    GameModel gameModel = gDAO.readGame(gameID);
    String game = gameModel.getGame().toString();
    ChessGame.TeamColor teamColor;
    String colorString = messageMap.get("teamColor").toString();
    colorString = colorString.toUpperCase();
    if(colorString.equals("WHITE")){
        teamColor = ChessGame.TeamColor.WHITE;
    }else{
        teamColor = ChessGame.TeamColor.BLACK;
    }

    LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game, teamColor);
    String message = gson.toJson(loadGameCommand);
    this.session.getRemote().sendString(message);
    AuthDAO authDAO = new AuthDAO();
    Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
    String user = auth.getUsername();
    String notification = user + " has joined the game as " + colorString;
    NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
    String messageNoti = gson.toJson(notificationMessage);
    this.session.getRemote().sendString(messageNoti);
}





}