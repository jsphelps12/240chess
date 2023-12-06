package Server;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.Auth;
import Model.GameModel;
import chess.*;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import webSocketMessages.serverMessages.LoadGameCommand;
import webSocketMessages.serverMessages.NotificationMessage;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.MAKE_MOVECOMMAND;
import webSocketMessages.userCommands.UserGameCommand;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;


@WebSocket
public class WSServer {

    Gson gson = new Gson();

    private Session session;

    public static Map<Integer, Map<String,Session>> sessions = new HashMap<>();
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        this.session = session;
        System.out.println("Connected: " + session.getRemoteAddress().getAddress());
//        this.session.getRemote().sendString("TEST");
    }
    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, DataAccessException, InvalidMoveException {
        //System.out.print(message);
        Map messageMap = gson.fromJson(message, Map.class);
        if(messageMap.get("commandType").equals("JOIN_PLAYER")){
            joinHelper(session, messageMap);
        }
        else if(messageMap.get("commandType").equals("JOIN_OBSERVER")){
            observeHelper(session, messageMap);
        }
        else if (messageMap.get("commandType").equals("LEAVE")){
            leaveHelper(session, messageMap);
        }
        else if (messageMap.get("commandType").equals("MAKE_MOVE")){
            moveHelper(session, messageMap);
        }
        else if(messageMap.get("commandType").equals("REDRAW")){
            drawHelper(session, messageMap);
        }
        else if(messageMap.get("commandType").equals("RESIGN")){
            resignHelper(session, messageMap);
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

    public void resignHelper(Session session,Map messageMap) throws DataAccessException, IOException {
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        gameModel.getGame().resign();
        String authToken = messageMap.get("authToken").toString();
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(authToken);
        String user = auth.getUsername();
        String notification = user + " has resigned";
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            if(!key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(messageNoti);
            }
        }
        //this.session.getRemote().sendString(messageNoti);

    }

    public void drawHelper(Session session,Map messageMap) throws DataAccessException, IOException {
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        String authToken = messageMap.get("authToken").toString();
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        String game = gameModel.getGame().toString();
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(authToken);
        String user = auth.getUsername();
        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game);
        String message = gson.toJson(loadGameCommand);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            if(key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(message);
            }
        }
        //this.session.getRemote().sendString(message);
    }

    public void moveHelper(Session session,Map messageMap) throws DataAccessException, InvalidMoveException, IOException {
//        Move move = moveCommand.getMove();
        String start = messageMap.get("start").toString();
        String end = messageMap.get("end").toString();
        String toPromote = null;
        if(messageMap.containsKey("promo")){
            toPromote = messageMap.get("promo").toString();
        }
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        Move move = stringToMove(start, end, toPromote);

        String authToken = messageMap.get("authToken").toString();
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        Game game = gameModel.getGame();

        ChessPosition startPosition = move.getStartPosition();
        Collection<ChessMove> validMoves = game.validMoves(startPosition);
        Boolean valid = false;
        if(!game.getGameOver()) {
            for (ChessMove posMove : validMoves) {
                if (posMove.equals(move)) {
                    valid = true;
                }
            }
        }
        if (valid){
            game.makeMove(move);
        }
        String updatedGame = game.toString();
        gDAO.updateBoard(gameID,updatedGame);
        gameModel = gDAO.readGame(gameID);
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(authToken);
        String user = auth.getUsername();
        String gameToSend = gameModel.getGame().toString();
        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME,gameToSend);
        String message = gson.toJson(loadGameCommand);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            Session seshToSend = thisGameSessions.get(key);
            seshToSend.getRemote().sendString(message);

        }
        //this.session.getRemote().sendString(message);

        String notification = user + " has made a move" ;
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
        for(String key: thisGameSessions.keySet()){
            if(!key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(messageNoti);
            }
        }
        //this.session.getRemote().sendString(messageNoti);
    }

    public void leaveHelper(Session session,Map messageMap) throws DataAccessException, IOException {
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        String user = auth.getUsername();
        String message = user + " has left the game";
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
        String messageNoti = gson.toJson((notificationMessage));
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            if(!key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(messageNoti);
            }
        }
        thisGameSessions.remove(user);
        //this.session.getRemote().sendString(messageNoti);
    }

    public void observeHelper(Session session,Map messageMap) throws DataAccessException, IOException {
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        String game = gameModel.getGame().toString();
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        String user = auth.getUsername();
        sessions.get(gameID).put(user,session);
        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game);
        String message = gson.toJson(loadGameCommand);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            if(key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(message);
            }
        }

        //this.session.getRemote().sendString(message);

        String notification = user + " has joined the game as an observer" ;
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
        for(String key: thisGameSessions.keySet()){
            if(!key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(messageNoti);
            }
        }
        //this.session.getRemote().sendString(messageNoti);
    }

public void joinHelper(Session session,Map messageMap) throws DataAccessException, IOException {
//    {"gameID":2,"teamColor":"WHITE","commandType":"JOIN_PLAYER","authToken":"303a2536-1c61-426f-9188-c22b8831c11e"}
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        String game = gameModel.getGame().toString();
        ChessGame.TeamColor teamColor;
        String colorString = messageMap.get("playerColor").toString();
        colorString = colorString.toUpperCase();
        if(colorString.equals("WHITE")){
            teamColor = ChessGame.TeamColor.WHITE;
        }else{
            teamColor = ChessGame.TeamColor.BLACK;
        }
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        String user = auth.getUsername();

        if(!sessions.containsKey(gameID)){
            sessions.put(gameID,new HashMap<>());
        }
        sessions.get(gameID).put(user,session);


        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game, teamColor);
        String message = gson.toJson(loadGameCommand);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            if(key.equals(user)){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(message);
            }
        }
        //this.session.getRemote().sendString(message);

        String notification = user + " has joined the game as " + colorString;
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
    for(String key: thisGameSessions.keySet()){
        if(!key.equals(user)){
            Session seshToSend = thisGameSessions.get(key);
            seshToSend.getRemote().sendString(messageNoti);
        }
    }
        //this.session.getRemote().sendString(messageNoti);
    }

    public Move stringToMove(String start, String end, String piece){
        char startCol = start.charAt(0);
        startCol = Character.toLowerCase(startCol);
        int startColInt;
        HashMap<Character,Integer> charToInt = new HashMap<>();
        charToInt.put('a',1);
        charToInt.put('b',2);
        charToInt.put('c',3);
        charToInt.put('d',4);
        charToInt.put('e',5);
        charToInt.put('f',6);
        charToInt.put('g',7);
        charToInt.put('h',8);
        startColInt = charToInt.get(startCol);
        char startRow = start.charAt(1);
        int startRowInt = Character.getNumericValue(startRow);
        Position startPosition = new Position(startRowInt,startColInt);
        char endCol = end.charAt(0);
        endCol = Character.toLowerCase(endCol);
        int endColInt = charToInt.get(endCol);
        char endRow = end.charAt(1);
        int endRowInt = Character.getNumericValue(endRow);
        Position endPosition = new Position(endRowInt,endColInt);
        ChessPiece.PieceType promotion = null;
        if(piece != null){
            String promString = piece.toUpperCase();
            if(promString.equals("Q")){
                promotion = ChessPiece.PieceType.QUEEN;
            }
            else if(promString.equals("B")){
                promotion = ChessPiece.PieceType.BISHOP;
            }
            else if(promString.equals("R")){
                promotion = ChessPiece.PieceType.ROOK;
            }
            else if(promString.equals("N")){
                promotion = ChessPiece.PieceType.KNIGHT;
            }
        }
        return new Move(startPosition,endPosition,promotion);
    }




}