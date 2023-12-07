package Server;

import DAO.AuthDAO;
import DAO.GameDAO;
import Model.Auth;
import Model.GameModel;
import chess.*;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import dataAccess.DataAccessException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import webSocketMessages.serverMessages.ErrorMessage;
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

    //private Session session;

    public static Map<Integer, Map<String,Session>> sessions = new HashMap<>();
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        //this.session = session;
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
//        for( int game : sessions.keySet()){
//            for(String user: sessions.get(game).keySet()){
//                if(sessions.get(game).get(user) == session){
//                    sessions.get(game).remove(user);
//                }
//            }
//        }
        for(Map<String,Session> gameSession : sessions.values()){
            gameSession.values().remove(session);
        }
    }

    @OnWebSocketError
    public void onError(Throwable e){
        e.printStackTrace();
    }

    public void resignHelper(Session session,Map messageMap) throws DataAccessException, IOException {
        String authToken = messageMap.get("authToken").toString();
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(authToken);
        String user = auth.getUsername();
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        if(!gameModel.getWhiteUsername().equals(user) && !gameModel.getBlackUsername().equals(user)){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: You're not a player");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        if(gameModel.getGame().getGameOver()){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Game already over");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        gameModel.getGame().resign();
        String updatedGame = gameModel.getGame().toString();
        gDAO.updateBoard(gameID,updatedGame);

        String notification = user + " has resigned";
        NotificationMessage notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
        String messageNoti = gson.toJson(notificationMessage);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        for(String key: thisGameSessions.keySet()){
            Session seshToSend = thisGameSessions.get(key);
            seshToSend.getRemote().sendString(messageNoti);

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
//      Move move = moveCommand.getMove();
        String authToken = messageMap.get("authToken").toString();
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(authToken);
        String user = auth.getUsername();
        String move1 = gson.toJson(messageMap.get("move"));
        Map moveMap = gson.fromJson(move1,Map.class);
        String start = gson.toJson(moveMap.get("start"));
        Map startVals = gson.fromJson(start,Map.class);
        Double startRowDouble = (Double) startVals.get("row");
        double srDouble = startRowDouble.doubleValue();
        int startRow = (int) Math.round(srDouble);
        Double startColDouble = (Double) startVals.get("column");
        double scDouble = startColDouble.doubleValue();
        int startCol = (int) Math.round(scDouble);
        Position startPos = new Position(startRow,startCol);
        String end = gson.toJson(moveMap.get("end"));
        Map endVals = gson.fromJson(end,Map.class);
        Double endRowDouble = (Double) endVals.get("row");
        double erDouble = endRowDouble.doubleValue();
        int endRow = (int) Math.round(erDouble);
        Double endColDouble = (Double) endVals.get("column");
        double ecDouble = endColDouble.doubleValue();
        int endCol = (int) Math.round(ecDouble);
        Position endPos = new Position(endRow,endCol);
        String promo = null;
        ChessPiece.PieceType pieceType = null;
        if(moveMap.containsKey("promotionType")){
            promo = moveMap.get("promotionType").toString();
            if(promo.equals("QUEEN")){
                pieceType = ChessPiece.PieceType.QUEEN;
            }
            else if(promo.equals("KNIGHT")){
                pieceType = ChessPiece.PieceType.KNIGHT;
            }
            else if(promo.equals("BISHOP")){
                pieceType = ChessPiece.PieceType.BISHOP;
            }
            else if(promo.equals("ROOK")){
                pieceType = ChessPiece.PieceType.ROOK;
            }
        }
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        Move move = new Move(startPos, endPos, pieceType);

        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        Game game = gameModel.getGame();

        ChessPosition startPosition = move.getStartPosition();
        Collection<ChessMove> validMoves = game.validMoves(startPosition);
        Boolean valid = false;
        if(!game.getGameOver()) {
            for (ChessMove posMove : validMoves) {
                if (posMove.equals(move)) {
                    if(!game.getGameOver()) {
                        valid = true;
                    }
                }
            }
        }
        if(!gameModel.getWhiteUsername().equals(user) && game.getColor() == ChessGame.TeamColor.WHITE){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Not Valid");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        if(!gameModel.getBlackUsername().equals(user) && game.getColor() == ChessGame.TeamColor.BLACK){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Not Valid");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }


        if (valid){
            try {
                game.makeMove(move);
            }catch(Exception e){
                ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Not Valid");
                String toSend = gson.toJson(errorMessage);
                session.getRemote().sendString(toSend);
                return;
            }
        }
        else{
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Invalid Move");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        String updatedGame = game.toString();
        gDAO.updateBoard(gameID,updatedGame);
        gameModel = gDAO.readGame(gameID);

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
        if(game.isInCheckmate(ChessGame.TeamColor.WHITE) || game.isInCheck(ChessGame.TeamColor.BLACK)){
            notification = "Check!" ;
            notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
            messageNoti = gson.toJson(notificationMessage);
            for(String key: thisGameSessions.keySet()){
                Session seshToSend = thisGameSessions.get(key);
                seshToSend.getRemote().sendString(messageNoti);
            }
        }
        if(game.getGameOver()){
            notification = "Game Over." ;
            notificationMessage = new NotificationMessage(ServerMessage.ServerMessageType.NOTIFICATION,notification);
            messageNoti = gson.toJson(notificationMessage);
            for(String key: thisGameSessions.keySet()){
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
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        if(auth == null){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Bad Auth");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        String user = auth.getUsername();

        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        if(!sessions.containsKey(gameID)){
            sessions.put(gameID,new HashMap<>());
        }
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        if(gameModel == null){
            String errorMessage = "Error: Bad Game";
            ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,errorMessage);
            String toSend = gson.toJson(errorMessage1);
            session.getRemote().sendString(toSend);
            return;
        }
        String game = gameModel.getGame().toString();
        sessions.get(gameID).put(user,session);
        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game);
        String message = gson.toJson(loadGameCommand);
        session.getRemote().sendString(message);

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
        AuthDAO authDAO = new AuthDAO();
        Auth auth = authDAO.readAuth(messageMap.get("authToken").toString());
        if(auth == null){
            ErrorMessage errorMessage = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,"Error: Bad Auth");
            String toSend = gson.toJson(errorMessage);
            session.getRemote().sendString(toSend);
            return;
        }
        String user = auth.getUsername();
        Double id = (Double) messageMap.get("gameID");
        double idDouble = id.doubleValue();
        int gameID = (int) Math.round(idDouble);
        if(!sessions.containsKey(gameID)){
            sessions.put(gameID,new HashMap<>());
        }
        sessions.get(gameID).put(user,session);
        Map<String,Session> thisGameSessions = sessions.get(gameID);
        GameDAO gDAO = new GameDAO();
        GameModel gameModel = gDAO.readGame(gameID);
        if(gameModel == null){
            String errorMessage = "Error: Bad Game";
            ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,errorMessage);
            String toSend = gson.toJson(errorMessage1);
            for(String key: thisGameSessions.keySet()){
                if(key.equals(user)){
                    Session seshToSend = thisGameSessions.get(key);
                    seshToSend.getRemote().sendString(toSend);
                    return;
                }
            }
        }
        String game = gameModel.getGame().toString();

        ChessGame.TeamColor teamColor;
        if(!messageMap.containsKey("playerColor")){
            String errorMessage = "Error: No Team given";
            ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,errorMessage);
            String toSend = gson.toJson(errorMessage1);
            for(String key: thisGameSessions.keySet()){
                if(key.equals(user)){
                    Session seshToSend = thisGameSessions.get(key);
                    seshToSend.getRemote().sendString(toSend);
                    return;
                }
            }
        }
        String colorString = messageMap.get("playerColor").toString();
        if(!colorString.equals("WHITE") && !colorString.equals("BLACK")){
            String errorMessage = "Error: Bad Team";
            ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR,errorMessage);
            String toSend = gson.toJson(errorMessage1);
            for(String key: thisGameSessions.keySet()){
                if(key.equals(user)){
                    Session seshToSend = thisGameSessions.get(key);
                    seshToSend.getRemote().sendString(toSend);
                    return;
                }
            }
        }
        colorString = colorString.toUpperCase();
        if(colorString.equals("WHITE")){
            teamColor = ChessGame.TeamColor.WHITE;
        }else{
            teamColor = ChessGame.TeamColor.BLACK;
        }

        if(teamColor == ChessGame.TeamColor.WHITE){
            if(gameModel.getWhiteUsername() == null){
                String errorMessage = "Error: Empty team";
                ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, errorMessage);
                String toSend = gson.toJson(errorMessage1);
                for (String key : thisGameSessions.keySet()) {
                    if (key.equals(user)) {
                        Session seshToSend = thisGameSessions.get(key);
                        seshToSend.getRemote().sendString(toSend);
                        return;
                    }
                }
            }
            if (!gameModel.getWhiteUsername().equals(user)) {
                String errorMessage = "Error: Wrong team";
                ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, errorMessage);
                String toSend = gson.toJson(errorMessage1);
                for (String key : thisGameSessions.keySet()) {
                    if (key.equals(user)) {
                        Session seshToSend = thisGameSessions.get(key);
                        seshToSend.getRemote().sendString(toSend);
                        return;
                    }
                }
            }
        }
        if(teamColor == ChessGame.TeamColor.BLACK){
            if(gameModel.getBlackUsername() == null){
                String errorMessage = "Error: Empty team";
                ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, errorMessage);
                String toSend = gson.toJson(errorMessage1);
                for (String key : thisGameSessions.keySet()) {
                    if (key.equals(user)) {
                        Session seshToSend = thisGameSessions.get(key);
                        seshToSend.getRemote().sendString(toSend);
                        return;
                    }
                }
            }
            if (!gameModel.getBlackUsername().equals(user)) {
                String errorMessage = "Error: Wrong team";
                ErrorMessage errorMessage1 = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, errorMessage);
                String toSend = gson.toJson(errorMessage1);
                for (String key : thisGameSessions.keySet()) {
                    if (key.equals(user)) {
                        Session seshToSend = thisGameSessions.get(key);
                        seshToSend.getRemote().sendString(toSend);
                        return;
                    }
                }
            }
        }

        LoadGameCommand loadGameCommand = new LoadGameCommand(ServerMessage.ServerMessageType.LOAD_GAME, game, teamColor);
        String message = gson.toJson(loadGameCommand);

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


}