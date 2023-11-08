package chess;

public class Board implements ChessBoard{
    public Board(){}

    private ChessPiece[][] theBoard = new ChessPiece[9][9];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        theBoard[position.getRow()][position.getColumn()] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return theBoard[position.getRow()][position.getColumn()];
    }

    public Board(ChessBoard original){
        for( int i = 1;i < 9;i++){
            for(int j = 1;j <9;j++){
                Position pos = new Position(i,j);
                if(original.getPiece(pos) != null){
                    if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.PAWN){
                        Pawn newPawn = new Pawn(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos,newPawn);
                    }
                    else if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.ROOK){
                        Rook newRook = new Rook(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos,newRook);
                    }
                    else if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.KNIGHT){
                        Knight newKnight = new Knight(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos, newKnight);
                    }
                    else if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.BISHOP){
                        Bishop newBishop = new Bishop(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos,newBishop);
                    }
                    else if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.QUEEN){
                        Queen newQueen= new Queen(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos,newQueen);
                    }
                    else if(original.getPiece(pos).getPieceType() == ChessPiece.PieceType.KING){
                        King newKing = new King(original.getPiece(pos).getTeamColor());
                        this.addPiece(pos,newKing);
                    }
                }
            }
        }
    }

    @Override
    public void resetBoard() {
        //add rooks
        theBoard[1][1] = new Rook(ChessGame.TeamColor.WHITE);
        theBoard[1][8] = new Rook(ChessGame.TeamColor.WHITE);
        theBoard[8][1] = new Rook(ChessGame.TeamColor.BLACK);
        theBoard[8][8] = new Rook(ChessGame.TeamColor.BLACK);
        //add knights
        theBoard[1][2] = new Knight(ChessGame.TeamColor.WHITE);
        theBoard[1][7] = new Knight(ChessGame.TeamColor.WHITE);
        theBoard[8][2] = new Knight(ChessGame.TeamColor.BLACK);
        theBoard[8][7] = new Knight(ChessGame.TeamColor.BLACK);
        //add bishops
        theBoard[1][3] = new Bishop(ChessGame.TeamColor.WHITE);
        theBoard[1][6] = new Bishop(ChessGame.TeamColor.WHITE);
        theBoard[8][3] = new Bishop(ChessGame.TeamColor.BLACK);
        theBoard[8][6] = new Bishop(ChessGame.TeamColor.BLACK);
        //add queens
        theBoard[1][4] = new Queen(ChessGame.TeamColor.WHITE);
        theBoard[8][4] = new Queen(ChessGame.TeamColor.BLACK);
        //add kings
        theBoard[1][5] = new King(ChessGame.TeamColor.WHITE);
        theBoard[8][5] = new King(ChessGame.TeamColor.BLACK);
        //add pawns
        for(int i = 1; i < 9; i++){
            theBoard[2][i] = new Pawn(ChessGame.TeamColor.WHITE);
            theBoard[7][i] = new Pawn(ChessGame.TeamColor.BLACK);
        }
    }
}
