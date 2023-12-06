package ui;

import Model.GameModel;
import Responses.ListResponse;
import ServerFacade.WSClient;
import chess.ChessGame;
import chess.ChessPiece;
import chess.Game;
import chess.Position;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import webSocketMessages.userCommands.JoinObserverCommand;
import webSocketMessages.userCommands.JoinPlayerCommand;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static ServerFacade.ServerFacade.*;
import static ui.EscapeSequences.*;

public class PostLogin {

    //public static Game tempGame = new Game();

    public static Gson gson = new Gson();

    public static WSClient wsClient;

    private static String authToken = null;
    public void main(String auth, String username) throws Exception{
        //tempGame.resetBoard();
        authToken =  auth;
        wsClient = new WSClient("http://localhost:6969");
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.println("Logged in as " + username + "!");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();
            var arguments = line.split(" ");
            String command = arguments[0].toLowerCase();

            if(command.equals("help")){
                helpOption();
            }
            else if(command.equals("logout")){
                logoutOption(arguments);
                break;
            }
            else if(command.equals("create")){
                createOption(arguments);
            }
            else if(command.equals("list")){
                listOption(arguments);
            }
            else if(command.equals("observe")){
                observeOption(arguments);
            }
            else if(command.equals("join")){
                joinOption(arguments);
            }

        }
    }

//    public static void drawWhiteSide(){
//        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
////        out.print(ERASE_SCREEN);
//        out.print(SET_TEXT_COLOR_BLACK);
//        out.print(SET_BG_COLOR_LIGHT_GREY);
//        out.print(SET_TEXT_BOLD);
//        out.print("    A  B  C  D  E  F  G  H    ");
//        out.print(RESET_BG_COLOR);
//        out.print("\n");
//        boolean whiteSquare = false;
//        for(int i = 8; i > 0; i--){
//            out.print(SET_TEXT_COLOR_BLACK);
//            out.print(SET_BG_COLOR_LIGHT_GREY);
//            out.print( " " + i + " ");
//            if(i%2 != 0){
//                whiteSquare = true;
//            }
//            else{
//                whiteSquare = false;
//            }
//            for(int j = 1; j < 9; j++){
//                out.print(SET_BG_COLOR_RED);
//                if(whiteSquare){
//                    out.print(SET_BG_COLOR_BLUE);
//                }
//                Position checkPos = new Position(i,j);
//                if(tempGame.gameBoard.getPiece(checkPos) == null){
//                    out.print("   ");
//                }
//                else{
//                    out.print(SET_TEXT_COLOR_WHITE);
//                    if(tempGame.gameBoard.getPiece(checkPos).getTeamColor() == ChessGame.TeamColor.BLACK){
//                        out.print(SET_TEXT_COLOR_BLACK);
//                    }
//                    if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KING){
//                        out.print(BLACK_KING);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.QUEEN){
//                        out.print(BLACK_QUEEN);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.ROOK){
//                        out.print(BLACK_ROOK);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.BISHOP){
//                        out.print(BLACK_BISHOP);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KNIGHT){
//                        out.print(BLACK_KNIGHT);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.PAWN){
//                        out.print(BLACK_PAWN);
//                    }
//                }
//                if(whiteSquare){
//                    whiteSquare = false;
//                }
//                else {
//                    whiteSquare = true;
//                }
//            }
//            out.print(SET_TEXT_COLOR_BLACK);
//            out.print(SET_BG_COLOR_LIGHT_GREY);
//            out.print( " " + i + " ");
//            out.print(RESET_BG_COLOR);
//            out.print("\n");
//        }
////        out.print(ERASE_SCREEN);
//        out.print(SET_TEXT_COLOR_BLACK);
//        out.print(SET_BG_COLOR_LIGHT_GREY);
//        out.print(SET_TEXT_BOLD);
//        out.print("    A  B  C  D  E  F  G  H    ");
//        out.print(RESET_BG_COLOR);
//        out.print("\n");
//
//    }
//    public static void drawBlackSide(){
//        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
////        out.print(ERASE_SCREEN);
//        out.print(SET_TEXT_COLOR_BLACK);
//        out.print(SET_BG_COLOR_LIGHT_GREY);
//        out.print(SET_TEXT_BOLD);
//        out.print("    H  G  F  E  D  C  B  A    ");
//        out.print(RESET_BG_COLOR);
//        out.print("\n");
//        boolean whiteSquare = false;
//        for(int i = 1; i < 9; i++){
//            out.print(SET_TEXT_COLOR_BLACK);
//            out.print(SET_BG_COLOR_LIGHT_GREY);
//            out.print( " " + i + " ");
//            if(i%2 == 0){
//                whiteSquare = true;
//            }
//            else{
//                whiteSquare = false;
//            }
//            for(int j = 8; j > 0; j--){
//                out.print(SET_BG_COLOR_RED);
//                if(whiteSquare){
//                    out.print(SET_BG_COLOR_BLUE);
//                }
//                Position checkPos = new Position(i,j);
//                if(tempGame.gameBoard.getPiece(checkPos) == null){
//                    out.print("   ");
//                }
//                else{
//                    out.print(SET_TEXT_COLOR_WHITE);
//                    if(tempGame.gameBoard.getPiece(checkPos).getTeamColor() == ChessGame.TeamColor.BLACK){
//                        out.print(SET_TEXT_COLOR_BLACK);
//                    }
//                    if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KING){
//                        out.print(BLACK_KING);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.QUEEN){
//                        out.print(BLACK_QUEEN);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.ROOK){
//                        out.print(BLACK_ROOK);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.BISHOP){
//                        out.print(BLACK_BISHOP);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.KNIGHT){
//                        out.print(BLACK_KNIGHT);
//                    }
//                    else if(tempGame.gameBoard.getPiece(checkPos).getPieceType() == ChessPiece.PieceType.PAWN){
//                        out.print(BLACK_PAWN);
//                    }
//                }
//                if(whiteSquare){
//                    whiteSquare = false;
//                }
//                else {
//                    whiteSquare = true;
//                }
//            }
//            out.print(SET_TEXT_COLOR_BLACK);
//            out.print(SET_BG_COLOR_LIGHT_GREY);
//            out.print( " " + i + " ");
//            out.print(RESET_BG_COLOR);
//            out.print("\n");
//        }
////        out.print(ERASE_SCREEN);
//        out.print(SET_TEXT_COLOR_BLACK);
//        out.print(SET_BG_COLOR_LIGHT_GREY);
//        out.print(SET_TEXT_BOLD);
//        out.print("    H  G  F  E  D  C  B  A    ");
//        out.print(RESET_BG_COLOR);
//        out.print("\n");
//
//    }

//    public static void drawBoards(){
//        drawWhiteSide();
//        drawBlackSide();
//        System.out.print(RESET_TEXT_COLOR);
//        System.out.print("\n\n");
//        System.out.print(RESET_BG_COLOR);
//        System.out.print(SET_TEXT_COLOR_WHITE);
//
//    }

    public static void joinOption(String[] arguments) throws Exception{
        Map response = joinHelper(arguments,authToken);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
        int id = Integer.parseInt(arguments[1]);
        String color = arguments[2].toUpperCase();
        ChessGame.TeamColor teamColor;
        if (color.equals("WHITE")){
            teamColor = ChessGame.TeamColor.WHITE;
        }
        else{
            teamColor = ChessGame.TeamColor.BLACK;
        }
        JoinPlayerCommand joinPlayerCommand = new JoinPlayerCommand(authToken,id,teamColor);
        String toSendWS = gson.toJson(joinPlayerCommand);
        wsClient.send(toSendWS);
        InGameUI inGameUI = new InGameUI();
        inGameUI.main(authToken,id,wsClient);

        //drawBoards();
    }

    public static void observeOption(String[] arguments) throws Exception{
        Map response = observeHelper(arguments,authToken);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
        int id = Integer.parseInt(arguments[1]);
        JoinObserverCommand joinObserverCommand = new JoinObserverCommand(authToken,id);
        String toSendWS = gson.toJson(joinObserverCommand);
        wsClient.send(toSendWS);
        InGameUI inGameUI = new InGameUI();
        inGameUI.main(authToken,id,wsClient);
        //drawBoards();
    }

    public static void listOption(String[] arguments) throws Exception{
        ListResponse response = listHelper(arguments,authToken);
        if(response.getMessage() != null){
            System.out.print(response.getMessage());
            System.out.print("\n");
            return;
        }
        List<GameModel> games = response.getGames();
        for(GameModel g : games){
            System.out.print("ID: " + g.getGameID() + " Name: " + g.getGameName());
            if(g.getWhiteUsername() != null){
                System.out.print(" White: " + g.getWhiteUsername());
            }
            if(g.getBlackUsername() != null){
                System.out.print(" Black: " + g.getBlackUsername());
            }
            System.out.print("\n");
        }
    }
    public static void logoutOption(String[] arguments) throws Exception{
        Map response = logoutHelper(arguments,authToken);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
    }

    public static void createOption(String[] arguments) throws Exception{
        Map response = createHelper(arguments,authToken);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
    }


    public static void helpOption(){
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("create <Name>");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - a game\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("list ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - games\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("join <ID> [WHITE|BLACK|<empty>]");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - a game\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("observe <ID>");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - a game\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("logout ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - when you are done\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("help");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - possible commands\n");
        System.out.print(SET_TEXT_COLOR_WHITE);
    }













}
