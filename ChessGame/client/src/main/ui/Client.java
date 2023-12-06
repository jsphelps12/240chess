package ui;

import Requests.RegisterRequest;
import ServerFacade.*;
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
    private ServerFacade serverFacade = new ServerFacade();
    public static void main(String[] args) throws Exception{
        System.out.println("Welcome to BetterChess.com. Type 'help' to get started!");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();
            var arguments = line.split(" ");
            String command = arguments[0].toLowerCase();

            if(command.equals("help")){
                helpOption();
            }
            else if(command.equals("login")){
                loginOption(arguments);
            }
            else if(command.equals("register")){
                registerOption(arguments);
            }
            else if(command.equals("quit")){
                break;
            }
        }
    }


/* needs a username, a password, and an email. Calls the login API, then moves us to postlogin*/
    public static void registerOption(String[] arguments) throws Exception{
        Map response = registerHelper(arguments);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
        authToken = response.get("authToken").toString();

        PostLogin postLogin = new PostLogin();
        postLogin.main(authToken, response.get("username").toString());
        authToken = null;
    }
    public static void loginOption(String[] arguments) throws Exception{
        Map response = loginHelper(arguments);
        if(response.containsKey("message")){
            System.out.print(response.get("message"));
            System.out.print("\n");
            return;
        }
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
        System.out.print(SET_TEXT_COLOR_WHITE);
    }

}
