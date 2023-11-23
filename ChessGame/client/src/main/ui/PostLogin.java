package ui;

import Model.GameModel;
import Responses.ListResponse;
import chess.Game;
import com.google.gson.internal.LinkedTreeMap;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static ServerFacade.ServerFacade.*;
import static ui.EscapeSequences.*;

public class PostLogin {

    private Game tempGame = new Game();

    private static String authToken = null;
    public void main(String auth, String username) throws Exception{
        tempGame.resetBoard();
        authToken =  auth;
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.println("Logged in as " + username + "!");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();
            var arguments = line.split(" ");

            if(arguments[0].equals("help")){
                helpOption();
            }
            else if(arguments[0].equals("logout")){
                logoutOption(arguments);
                break;
            }
            else if(arguments[0].equals("create")){
                createOption(arguments);
            }
            else if(arguments[0].equals("list")){
                listOption(arguments);
            }
            else if(arguments[0].equals("observe")){
                observeOption(arguments);
            }
            else if(arguments[0].equals("join")){
                joinOption(arguments);
            }

        }
    }

    public static void drawBoards(){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_BOLD);
        out.print("    1  2  3  4  5  6  7  8    ");


    }

    public static void joinOption(String[] arguments) throws Exception{
        Map response = joinHelper(arguments,authToken);
        var x = 0;
    }

    public static void observeOption(String[] arguments) throws Exception{
        Map response = observeHelper(arguments,authToken);
        var x = 0;
    }

    public static void listOption(String[] arguments) throws Exception{
        ListResponse response = listHelper(arguments,authToken);
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
        var x = 0;
    }
    public static void logoutOption(String[] arguments) throws Exception{
        Map response = logoutHelper(arguments,authToken);
        var x = 0;
    }

    public static void createOption(String[] arguments) throws Exception{
        Map response = createHelper(arguments,authToken);
        var x = 0;
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
    }













}
