package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Rook extends Piece{
    public Rook(ChessGame.TeamColor color){
        color = color;
    }
    private ChessPiece.PieceType type = PieceType.ROOK;
    private ChessGame.TeamColor color;
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> posMoves = new HashSet<>();

        int i = 1;
        //check moving down
        Position checkPos = new Position(myPosition.getRow()-i,myPosition.getColumn());
        while(myPosition.getRow()-i >=1 && myPosition.getRow()-i <= 8 && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos.row--;
        }
        if(myPosition.getRow()-i >=1 && myPosition.getRow()-i <= 8 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }

        i = 1;
        //check moving up
        Position checkPos2 = new Position(myPosition.getRow()+i,myPosition.getColumn());
        while(myPosition.getRow()+i >=1 && myPosition.getRow()+i <= 8 && board.getPiece(checkPos2) == null){
            ChessMove toAdd = new Move(myPosition,checkPos2,null);
            posMoves.add(toAdd);
            i++;
            checkPos2.row++;
        }
        if(myPosition.getRow()+i >=1 && myPosition.getRow()+i <= 8 && board.getPiece(checkPos2).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos2,null);
            posMoves.add(toAdd);
        }
        //check moving right
        i = 1;
        Position checkPos3 = new Position(myPosition.getRow(),myPosition.getColumn()+i);
        while(myPosition.getColumn()+i >=1 && myPosition.getColumn()+i <= 8 && board.getPiece(checkPos3) == null){
            ChessMove toAdd = new Move(myPosition,checkPos3,null);
            posMoves.add(toAdd);
            i++;
            checkPos3.column++;
        }
        if(myPosition.getColumn()+i >=1 && myPosition.getColumn()+i <= 8 && board.getPiece(checkPos3).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos3,null);
            posMoves.add(toAdd);
        }
        //check moving left
        i = 1;
        Position checkPos4 = new Position(myPosition.getRow(),myPosition.getColumn()-i);
        while(myPosition.getColumn()-i >=1 && myPosition.getColumn()-i <= 8 && board.getPiece(checkPos4) == null){
            ChessMove toAdd = new Move(myPosition,checkPos4,null);
            posMoves.add(toAdd);
            i++;
            checkPos4.column--;
        }
        if(myPosition.getColumn()-i >=1 && myPosition.getColumn()-i <= 8 && board.getPiece(checkPos4).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos4,null);
            posMoves.add(toAdd);
        }

        return posMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return type == rook.type && color == rook.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
