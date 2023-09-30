package chess;

import java.util.Collection;

public class King extends Piece{

    public King(ChessGame.TeamColor color){
        color = color;
    }

    private ChessGame.TeamColor color;
    private ChessPiece.PieceType type  = PieceType.KING;
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


        return null;
    }
}
