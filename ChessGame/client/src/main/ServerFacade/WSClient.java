package ServerFacade;


import chess.ChessGame;
import chess.ChessPiece;
import chess.Game;
import chess.Position;
import com.google.gson.Gson;

import javax.websocket.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import static ui.EscapeSequences.*;
import static ui.EscapeSequences.RESET_BG_COLOR;

//need to extend Endpoint for websocket to work properly
public class WSClient extends Endpoint {

    Session session;

    Gson gson = new Gson();



    public static void main(String[] args) throws Exception {
        var ws = new WSClient("http://localhost:6969");
//        ws.send("PlaceHolder");
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("Enter a message you want to echo");
        while (true) ws.send(scanner.nextLine());
    }


    public WSClient(String url) {
        try {
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/connect");

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    //TODO
                    //System.out.print("Message Received\n");
                    Map messageMap = gson.fromJson(message, Map.class);

                    if(messageMap.get("serverMessageType").equals("LOAD_GAME")){
                        loadHelper(messageMap);
                    }
                    else if(messageMap.get("serverMessageType").equals("NOTIFICATION")) {
                        notificationHelper(messageMap);
                    }
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    public void send(String msg) throws Exception {
        this.session.getBasicRemote().sendText(msg);
    }

    //Endpoint requires this method, but you don't have to do anything
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    public void notificationHelper(Map messageMap){
        String message = messageMap.get("message").toString();
        System.out.print(RESET_BG_COLOR);
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(message);
    }

    public void loadHelper(Map messageMap){
        String color = messageMap.get("teamColor").toString();
        color = color.toUpperCase();
        String game = messageMap.get("game").toString();
        if(color.equals("BLACK")){
            drawBlackSide(game);
        }
        else{
            drawWhiteSide(game);
        }
    }

    public void drawWhiteSide(String game){
        Game tempGame = new Game(game);
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
//        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    A  B  C  D  E  F  G  H    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");
        boolean whiteSquare = false;
        for(int i = 8; i > 0; i--){
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print( " " + i + " ");
            if(i%2 != 0){
                whiteSquare = true;
            }
            else{
                whiteSquare = false;
            }
            for(int j = 1; j < 9; j++){
                out.print(SET_BG_COLOR_RED);
                if(whiteSquare){
                    out.print(SET_BG_COLOR_BLUE);
                }
                Position checkPos = new Position(i,j);
                if(tempGame.gameBoard.getPiece(checkPos) == null){
                    out.print("   ");
                }
                else{
                    out.print(SET_TEXT_COLOR_WHITE);
                    if(tempGame.gameBoard.getPiece(checkPos).getTeamColor() == ChessGame.TeamColor.BLACK){
                        out.print(SET_TEXT_COLOR_BLACK);
                    }
                    if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KING){
                        out.print(BLACK_KING);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.QUEEN){
                        out.print(BLACK_QUEEN);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.ROOK){
                        out.print(BLACK_ROOK);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.BISHOP){
                        out.print(BLACK_BISHOP);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KNIGHT){
                        out.print(BLACK_KNIGHT);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.PAWN){
                        out.print(BLACK_PAWN);
                    }
                }
                if(whiteSquare){
                    whiteSquare = false;
                }
                else {
                    whiteSquare = true;
                }
            }
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print( " " + i + " ");
            out.print(RESET_BG_COLOR);
            out.print("\n");
        }
//        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    A  B  C  D  E  F  G  H    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");

    }
    public  void drawBlackSide(String game){
        Game tempGame = new Game(game);
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
//        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    H  G  F  E  D  C  B  A    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");
        boolean whiteSquare = false;
        for(int i = 1; i < 9; i++){
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print( " " + i + " ");
            if(i%2 == 0){
                whiteSquare = true;
            }
            else{
                whiteSquare = false;
            }
            for(int j = 8; j > 0; j--){
                out.print(SET_BG_COLOR_RED);
                if(whiteSquare){
                    out.print(SET_BG_COLOR_BLUE);
                }
                Position checkPos = new Position(i,j);
                if(tempGame.gameBoard.getPiece(checkPos) == null){
                    out.print("   ");
                }
                else{
                    out.print(SET_TEXT_COLOR_WHITE);
                    if(tempGame.gameBoard.getPiece(checkPos).getTeamColor() == ChessGame.TeamColor.BLACK){
                        out.print(SET_TEXT_COLOR_BLACK);
                    }
                    if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KING){
                        out.print(BLACK_KING);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.QUEEN){
                        out.print(BLACK_QUEEN);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.ROOK){
                        out.print(BLACK_ROOK);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.BISHOP){
                        out.print(BLACK_BISHOP);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KNIGHT){
                        out.print(BLACK_KNIGHT);
                    }
                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.PAWN){
                        out.print(BLACK_PAWN);
                    }
                }
                if(whiteSquare){
                    whiteSquare = false;
                }
                else {
                    whiteSquare = true;
                }
            }
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print( " " + i + " ");
            out.print(RESET_BG_COLOR);
            out.print("\n");
        }
//        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    H  G  F  E  D  C  B  A    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");

    }

}