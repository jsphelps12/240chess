package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Pawn extends Piece{

    public Pawn(ChessGame.TeamColor color){
        this.color = color;
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

        if(this.color == ChessGame.TeamColor.WHITE){
            //check above
            if(myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8){
                Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn());
                if(checkPos.getRow() !=8 && board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                    if(myPosition.getRow() ==2){
                        checkPos = new Position(myPosition.getRow()+2,myPosition.getColumn());
                        if(checkPos.getRow() !=8 && board.getPiece(checkPos) == null){
                            toAdd = new Move(myPosition,checkPos,null);
                            posMoves.add(toAdd);
                        }
                    }
                }
                else if(checkPos.getRow() == 8 && board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }
            }
            //right diagonal capture
            if(myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()+1 >=1 && myPosition.getColumn()+1 <= 8){
                Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()+1);
                if(checkPos.getRow() !=8 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
                else if(checkPos.getRow() == 8 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }
            }
            //left diaganol capture
            if(myPosition.getRow()+1 >=1 && myPosition.getRow()+1 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <= 8){
                Position checkPos = new Position(myPosition.getRow()+1,myPosition.getColumn()-1);
                if(checkPos.getRow() !=8 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
                else if(checkPos.getRow() == 8 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }
            }

        }
        if(this.color == ChessGame.TeamColor.BLACK){
            //check above
            if(myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8){
                Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn());
                if(checkPos.getRow() !=1 && board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                    if(myPosition.getRow() ==7){
                        checkPos = new Position(myPosition.getRow()-2,myPosition.getColumn());
                        if(checkPos.getRow() !=8 && board.getPiece(checkPos) == null){
                            toAdd = new Move(myPosition,checkPos,null);
                            posMoves.add(toAdd);
                        }
                    }
                }
                else if(checkPos.getRow() == 1 && board.getPiece(checkPos) == null){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }


            }
            //right diagonal capture
            if(myPosition.getRow()+1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()+1 <= 8){
                Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()+1);
                if(checkPos.getRow() !=1 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
                else if(checkPos.getRow() == 1 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }
            }
            //left diaganol capture
            if(myPosition.getRow()-1 >=1 && myPosition.getRow()-1 <= 8 && myPosition.getColumn()-1 >=1 && myPosition.getColumn()-1 <= 8){
                Position checkPos = new Position(myPosition.getRow()-1,myPosition.getColumn()-1);
                if(checkPos.getRow() !=1 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,null);
                    posMoves.add(toAdd);
                }
                else if(checkPos.getRow() == 1 && board.getPiece(checkPos) != null && board.getPiece(checkPos).getTeamColor() != color){
                    ChessMove toAdd = new Move(myPosition,checkPos,PieceType.ROOK);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.BISHOP);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.QUEEN);
                    posMoves.add(toAdd);
                    toAdd = new Move(myPosition,checkPos,PieceType.KNIGHT);
                    posMoves.add(toAdd);
                }
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
