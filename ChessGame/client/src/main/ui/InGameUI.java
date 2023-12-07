package ui;

import ServerFacade.WSClient;
import chess.*;
import com.google.gson.Gson;
import webSocketMessages.userCommands.LeaveCommand;
import webSocketMessages.userCommands.MAKE_MOVECOMMAND;
import webSocketMessages.userCommands.RedrawCommand;
import webSocketMessages.userCommands.ResignCommand;

import java.util.HashMap;
import java.util.Scanner;

import static ui.EscapeSequences.*;

public class InGameUI {
    private String authToken = null;
    private int gameID;

    Gson gson = new Gson();

    WSClient wsClient;

    public  void main(String auth, int gameID, WSClient wsClient) throws Exception {
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
                leaveOption(arguments);
                break;
            }
            else if(command.equals("move")){
                moveOption(arguments);
            }
            else if(command.equals("draw")){
                drawOption();
            }
            else if(command.equals("resign")){
                resignOption();
            }
            else if(command.equals("highlight")){
                highlightOption(arguments);
            }
        }
    }

    public void highlightOption(String[] arguments){
        String start = arguments[1];
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
        wsClient.highlightMoves(startPosition);
    }

    public void resignOption() throws Exception {
        ResignCommand resignCommand = new ResignCommand(authToken,gameID);
        String toSendWS = gson.toJson(resignCommand);
        wsClient.send(toSendWS);
    }
    public void drawOption() throws Exception {
        RedrawCommand redrawCommand = new RedrawCommand(authToken,gameID);
        String toSendWS = gson.toJson(redrawCommand);
        wsClient.send(toSendWS);
    }


    public void moveOption(String[] arguments) throws Exception {
        String start = arguments[1];
        String end = arguments[2];
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
        String toPromote = null;
        if(arguments.length == 4){
            String promString = arguments[3].toUpperCase();
            toPromote = promString;
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
        Move move = new Move(startPosition,endPosition,promotion);
        MAKE_MOVECOMMAND makeMovecommand = new MAKE_MOVECOMMAND(authToken,gameID,move);
        String toSend = gson.toJson(makeMovecommand);
        wsClient.send(toSend);


    }
    public void leaveOption(String[] arguments) throws Exception {
        LeaveCommand leaveCommand = new LeaveCommand(authToken,gameID);
        String toSendWS = gson.toJson(leaveCommand);
        wsClient.send(toSendWS);
    }

    public  void showHelp(){
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("help");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - possible commands\n");
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.printf("draw ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.printf(" - to reload the game\n");
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
