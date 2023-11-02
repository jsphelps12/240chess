package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Queen extends Piece{
    public Queen(ChessGame.TeamColor color){
        this.color = color;
    }
    private ChessPiece.PieceType type = PieceType.QUEEN;
    private ChessGame.TeamColor color;
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    public void setColorAsBlack(){
        this.color = ChessGame.TeamColor.BLACK;
    }
    public void setColorAsWhite(){
        this.color = ChessGame.TeamColor.WHITE;
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> posMoves = new HashSet<>();
        //check moving down and left
        int i = 1;
        Position checkPos = new Position(myPosition.getRow()-i,myPosition.getColumn()-i);
        while(myPosition.getRow()-i >=1 && myPosition.getColumn()-i >=1 && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()-1, checkPos.getColumn()-1);
        }
        checkPos = new Position(myPosition.getRow()-i, myPosition.getColumn()-i);
        if(checkPos.getRow() >=1 && checkPos.getColumn() >= 1 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving down and right
        i = 1;
        checkPos = new Position(myPosition.getRow()-i,myPosition.getColumn()+i);
        while(myPosition.getRow()-i >=1 && myPosition.getColumn()+i <= 8 && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()-1, checkPos.getColumn()+1);
        }
        checkPos = new Position(myPosition.getRow()-i, myPosition.getColumn()+i);
        if(checkPos.getRow() >=1 && checkPos.getColumn() <=8 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving up and right
        i = 1;
        checkPos = new Position(myPosition.getRow()+i,myPosition.getColumn()+i);
        while(myPosition.getRow()+i <=8 && myPosition.getColumn()+i <= 8 && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()+1, checkPos.getColumn()+1);
        }
        checkPos = new Position(myPosition.getRow()+i, myPosition.getColumn()+i);
        if(checkPos.getRow() <=8 && checkPos.getColumn() <=8 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving up and left
        i = 1;
        checkPos = new Position(myPosition.getRow()+i,myPosition.getColumn()-i);
        while(myPosition.getRow()+i <=8 && myPosition.getColumn()-i >= 1 && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()+1, checkPos.getColumn()-1);
        }
        checkPos = new Position(myPosition.getRow()+i, myPosition.getColumn()-i);
        if(checkPos.getRow() <=8 && checkPos.getColumn() >= 1 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }

        //check moving down
        i = 1;
        checkPos = new Position(myPosition.getRow()-i,myPosition.getColumn());
        while(myPosition.getRow()-i >=1  && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()-1, checkPos.getColumn());
        }
        checkPos = new Position(myPosition.getRow()-i, myPosition.getColumn());
        if(checkPos.getRow() >=1 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving up
        i = 1;
        checkPos = new Position(myPosition.getRow()+i,myPosition.getColumn());
        while(myPosition.getRow()+i <=8  && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow()+1, checkPos.getColumn());;
        }
        checkPos = new Position(myPosition.getRow()+i, myPosition.getColumn());
        if(checkPos.getRow() <=8 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving left
        i = 1;
        checkPos = new Position(myPosition.getRow(),myPosition.getColumn()-i);
        while(myPosition.getColumn()-i >=1  && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow(), checkPos.getColumn()-1);
        }
        checkPos = new Position(myPosition.getRow(), myPosition.getColumn()-i);
        if(checkPos.getColumn() >=1 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }
        //check moving right
        i = 1;
        checkPos = new Position(myPosition.getRow(),myPosition.getColumn()+i);
        while(myPosition.getColumn()+i <=8  && board.getPiece(checkPos) == null){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
            i++;
            checkPos = new Position(checkPos.getRow(), checkPos.getColumn()+1);
        }
        checkPos = new Position(myPosition.getRow(), myPosition.getColumn()+i);
        if(checkPos.getColumn() <=8 && board.getPiece(checkPos).getTeamColor() != color){
            ChessMove toAdd = new Move(myPosition,checkPos,null);
            posMoves.add(toAdd);
        }

        return posMoves;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return type == queen.type && color == queen.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
