package ui;

import Requests.RegisterRequest;
import ServerFacade.ServerFacade;
import chess.ChessGame;
import chess.ChessPiece;
import chess.Game;
import chess.Position;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import static ServerFacade.ServerFacade.loginHelper;
import static ServerFacade.ServerFacade.registerHelper;
import static ui.EscapeSequences.*;


public class Client {

    private static String authToken = null;

    private static Game tempGame = new Game();
    private ServerFacade serverFacade = new ServerFacade();
    public static void main(String[] args) throws Exception{
        tempGame.resetBoard();
        System.out.println("Welcome to BetterChess.com. Type 'help' to get started!");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();
            var arguments = line.split(" ");

            if(arguments[0].equals("help")){
                helpOption();
            }
            else if(arguments[0].equals("login")){
                loginOption(arguments);
            }
            else if(arguments[0].equals("register")){
                registerOption(arguments);
            }
            else if(arguments[0].equals("quit")){
                break;
            }
            else if(arguments[0].equals("test")){
                drawWhiteSide();
                System.out.print("\n\n");
                drawBlackSide();
            }
        }
    }

    public static void drawWhiteSide(){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
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
        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    A  B  C  D  E  F  G  H    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");

    }
    public static void drawBlackSide(){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    A  B  C  D  E  F  G  H    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");
        boolean whiteSquare = false;
        for(int i = 1; i < 9; i++){
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
        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    A  B  C  D  E  F  G  H    ");
        out.print(RESET_BG_COLOR);
        out.print("\n");

    }

/* needs a username, a password, and an email. Calls the login API, then moves us to postlogin*/
    public static void registerOption(String[] arguments) throws Exception{
        Map response = registerHelper(arguments);
        authToken = response.get("authToken").toString();
        PostLogin postLogin = new PostLogin();
        postLogin.main(authToken, response.get("username").toString());
        authToken = null;
    }
    public static void loginOption(String[] arguments) throws Exception{
        Map response = loginHelper(arguments);
        authToken = response.get("authToken").toString();
        PostLogin postLogin = new PostLogin();
        postLogin.main(authToken, response.get("username").toString());
        authToken = null;
    }

    public static void helpOption(){
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("register <USERNAME> <PASSWORD> <EMAIL>");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - to create an account\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("login <USERNAME> <PASSWORD>");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - to play chess\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("quit");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - playing chess\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("help");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - possible commands\n");
    }

}
