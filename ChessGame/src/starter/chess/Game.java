package chess;

import java.util.Collection;
import java.util.HashSet;

public class Game implements ChessGame{

    public Game(){
        this.color = TeamColor.WHITE;
    }

    private ChessGame.TeamColor color ;

    private ChessBoard gameBoard = new Board();


    @Override
    public TeamColor getTeamTurn() {
        return color;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        color = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(gameBoard.getPiece(startPosition) == null){
            return null;
        }
        //for every possible move, check to see if in check
        Collection<ChessMove> tryMoves = new HashSet<>();
        Collection<ChessMove> goodMoves = new HashSet<>();
        tryMoves = gameBoard.getPiece(startPosition).pieceMoves(gameBoard,startPosition);
        for(ChessMove move : tryMoves){
            ChessBoard boardCopy = new Board(gameBoard);
            gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
            gameBoard.addPiece(move.getStartPosition(), null);
            if(!isInCheck(gameBoard.getPiece(move.getEndPosition()).getTeamColor())){
                goodMoves.add(move);
            }
            gameBoard = new Board(boardCopy);
        }
        return goodMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //see if startposition is null or not
        if(gameBoard.getPiece(move.getStartPosition()) == null){
            throw new InvalidMoveException("No Piece Here");
        }
        //see if it is my turn to move
        if(gameBoard.getPiece(move.getStartPosition()).getTeamColor() != color){
            throw new InvalidMoveException("Not your piece");
        }
        //check if it is a valid move
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if(!validMoves.contains(move)){
            throw new InvalidMoveException("Not a valid Move");
        }

        //handle pawn promotion
        if(gameBoard.getPiece(move.getStartPosition()).getPieceType() == ChessPiece.PieceType.PAWN){
            if(move.getPromotionPiece()== ChessPiece.PieceType.ROOK){
                Rook promoRook = new Rook(gameBoard.getPiece(move.getStartPosition()).getTeamColor());
                gameBoard.addPiece(move.getEndPosition(), promoRook);
                gameBoard.addPiece(move.getStartPosition(), null);
            }
            else if(move.getPromotionPiece()== ChessPiece.PieceType.BISHOP){
                Bishop promoBishop = new Bishop(gameBoard.getPiece(move.getStartPosition()).getTeamColor());
                gameBoard.addPiece(move.getEndPosition(), promoBishop);
                gameBoard.addPiece(move.getStartPosition(), null);
            }
            else if(move.getPromotionPiece()== ChessPiece.PieceType.KNIGHT){
                Knight promoKnight= new Knight(gameBoard.getPiece(move.getStartPosition()).getTeamColor());
                gameBoard.addPiece(move.getEndPosition(), promoKnight);
                gameBoard.addPiece(move.getStartPosition(), null);
            }
            else if(move.getPromotionPiece()== ChessPiece.PieceType.QUEEN){
                Queen promoQueen = new Queen(gameBoard.getPiece(move.getStartPosition()).getTeamColor());
                gameBoard.addPiece(move.getEndPosition(), promoQueen);
                gameBoard.addPiece(move.getStartPosition(), null);
            }
            else{
                gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
                gameBoard.addPiece(move.getStartPosition(), null);
            }
        }
        //else the next two lines
        else {
            gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
            gameBoard.addPiece(move.getStartPosition(), null);
        }
        if(color == TeamColor.WHITE){
            color = TeamColor.BLACK;
        }
        else{
            color = TeamColor.WHITE;
        }

//        if(isInCheckmate(color) || isInStalemate(color)){
//            throw new InvalidMoveException("Game is Over");
//        }
        //check for checkmate or stalemate, end game
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        Position kingPos = new Position(0,0);
        int rowOfKing = 0, colOfKing = 0;
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9;j++){
                if(gameBoard.getPiece(kingPos) != null && gameBoard.getPiece(kingPos).getTeamColor() == teamColor && gameBoard.getPiece(kingPos).getPieceType() == ChessPiece.PieceType.KING){
                    rowOfKing = i;
                    colOfKing = j;
                }
                kingPos = new Position(kingPos.getRow(),kingPos.getColumn()+1);
            }
            kingPos = new Position(kingPos.getRow(),0);
            kingPos = new Position(kingPos.getRow()+1,kingPos.getColumn());
        }
        kingPos = new Position(rowOfKing,colOfKing);

        Position piecePos = new Position(0,0);
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9;j++){
                if(gameBoard.getPiece(piecePos) != null) {
                    ChessPiece debugPiece = gameBoard.getPiece(piecePos);
                    TeamColor debugColor = gameBoard.getPiece(piecePos).getTeamColor();
                }
                if((gameBoard.getPiece(piecePos) != null) && (gameBoard.getPiece(piecePos).getTeamColor() != teamColor)){
                    Collection<ChessMove> theseMoves = new HashSet<>();
                    theseMoves = gameBoard.getPiece(piecePos).pieceMoves(gameBoard,piecePos);
                    for(ChessMove move: theseMoves){
                        int x = 0;
                        if(move.getEndPosition().equals(kingPos)){
                            x = 0;
                            return true;
                        }
                    }
                }
                piecePos = new Position(piecePos.getRow(), piecePos.getColumn()+1);
            }
            piecePos = new Position(piecePos.getRow(),0);
            piecePos = new Position(piecePos.getRow()+1, piecePos.getColumn());
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)) {
            return false;
        }
        for(int i = 1;i <9;i++){
            for(int j = 1;j<9;j++){
                Position pos = new Position(i,j);
                if(gameBoard.getPiece(pos) != null){
                    if(gameBoard.getPiece(pos).getTeamColor() == teamColor){
                        Collection<ChessMove> posMoves = new HashSet<>();
                        posMoves = validMoves(pos);
                        if(!posMoves.isEmpty()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)) {
            return false;
        }
        for(int i = 1;i <9;i++){
            for(int j = 1;j<9;j++){
                Position pos = new Position(i,j);
                if(gameBoard.getPiece(pos) != null){
                    if(gameBoard.getPiece(pos).getTeamColor() == teamColor){
                        Collection<ChessMove> posMoves = new HashSet<>();
                        posMoves = validMoves(pos);
                        if(!posMoves.isEmpty()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void setBoard(ChessBoard board) {
        this.gameBoard = board;
    }

    @Override
    public ChessBoard getBoard() {
        return gameBoard;
    }
}
