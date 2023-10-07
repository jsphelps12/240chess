package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Knight extends Piece{
    public Knight(ChessGame.TeamColor color){
        this.color = color;
    }
    private ChessPiece.PieceType type = PieceType.KNIGHT;
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
        //check down two, left 1
        if(myPosition.getRow()-2 >=1 && myPosition.getRow()-2 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <=8){
            Position checkPos = new Position(myPosition.getRow()-2,myPosition.getColumn()-1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check down two, right one
        if(myPosition.getRow()-2 >=1 && myPosition.getRow()-2 <= 8 && myPosition.getColumn()+1 >=1 && myPosition.getColumn()+1 <=8){
            Position checkPos = new Position(myPosition.getRow()-2,myPosition.getColumn()+1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check down one, left two
        if(myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()-2 >=1 && myPosition.getColumn()-2 <=8){
            Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()-2);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check down one, right two
        if(myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()+2 >=1 && myPosition.getColumn()+2 <=8){
            Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()+2);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check up one, left two
        if(myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()-2 >=1 && myPosition.getColumn()-2 <=8){
            Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()-2);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check up one, right two
        if(myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()+2 >=1 && myPosition.getColumn()+2 <=8){
            Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()+2);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check up two, left one
        if(myPosition.getRow()+2 >=1 && myPosition.getRow()+2 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <=8){
            Position checkPos = new Position(myPosition.getRow()+2,myPosition.getColumn()-1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check up two, right one
        if(myPosition.getRow()+2 >=1 && myPosition.getRow()+2 <= 8 && myPosition.getColumn()+1 >=1 && myPosition.getColumn()+1 <=8){
            Position checkPos = new Position(myPosition.getRow()+2,myPosition.getColumn()+1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }


        return posMoves;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return type == knight.type && color == knight.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
