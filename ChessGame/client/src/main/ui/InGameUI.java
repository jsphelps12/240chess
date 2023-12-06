package ui;

import ServerFacade.WSClient;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class InGameUI {
    private String authToken = null;
    private int gameID;

    WSClient wsClient;

    public  void main(String auth, int gameID, WSClient wsClient){
        this.wsClient = wsClient;
        authToken = auth;
        this.gameID = gameID;
        System.out.print(SET_TEXT_COLOR_WHITE);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();
            var arguments = line.split(" ");
            String command = arguments[0].toLowerCase();

            if(command.equals("help")){
                showHelp();
            }
            else if(command.equals("leave")){
                //leaveOption();
                break;
            }
        }
    }

    public void leaveOption(){

    }

    public  void showHelp(){
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("help");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - possible commands\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("move <a4> <b5> [R|B|N|Q|<empty>]");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - to move a piece, \n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("leave");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - the game\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("resign");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - to forfeit the game\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("highlight <a4> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - highlight possible moves\n");
    }






}
