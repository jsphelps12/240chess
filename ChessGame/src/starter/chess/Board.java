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
        if(theBoard[position.getRow()][position.getColumn()] != null){
            return theBoard[position.getRow()][position.getColumn()];
        }
        return null;
    }

    @Override
    public void resetBoard() {

    }
}
