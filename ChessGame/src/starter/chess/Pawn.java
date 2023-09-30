package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Pawn extends Piece{

    public Pawn(ChessGame.TeamColor color){
        color = color;
    }
    private ChessPiece.PieceType type = PieceType.PAWN;
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
        //whitecheck
        if(color == ChessGame.TeamColor.WHITE && myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8){
            Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn());
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //blackcheck
        if(color == ChessGame.TeamColor.BLACK && myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8){
            Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn());
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //white inital move up two
        if(color == ChessGame.TeamColor.WHITE && myPosition.getRow() == 2){
            if(myPosition.getRow()+2 >=1 && myPosition.getRow()+2 <= 8){
                Position checkPos = new Position(myPosition.getRow()+2,myPosition.getColumn());
                if(board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
            }
        }
        //black initial move up two
        else if(color == ChessGame.TeamColor.BLACK && myPosition.getRow() == 7){
            if(myPosition.getRow()-2 >=1 && myPosition.getRow()-2 <= 8){
                Position checkPos = new Position(myPosition.getRow()-2,myPosition.getColumn());
                if(board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
            }
        }

        //check diagonal top left for white
        if(color == ChessGame.TeamColor.WHITE && myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <=8){
            Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()-1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check diagonal top right for White
        if(color == ChessGame.TeamColor.WHITE && myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()+1 >=1 && myPosition.getColumn()+1 <=8){
            Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()+1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check diagonal bottom left for black
        if(color == ChessGame.TeamColor.BLACK && myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <=8){
            Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()-1);
            if(board.getPiece(checkPos) == null){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
            else if(board.getPiece(checkPos).getTeamColor() != color){
                ChessMove toAdd = new Move(myPosition,checkPos,null);
                posMoves.add(toAdd);
            }
        }
        //check diagonal bottom right for black
        if(color == ChessGame.TeamColor.BLACK && myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()+1 >=1 && myPosition.getColumn()+1 <=8){
            Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()+1);
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
        Pawn pawn = (Pawn) o;
        return type == pawn.type && color == pawn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
