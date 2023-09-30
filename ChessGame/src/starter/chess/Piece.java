package chess;

import java.util.Collection;

public abstract class Piece implements ChessPiece{

    public Piece(){}


    @Override
    public abstract ChessGame.TeamColor getTeamColor();

    @Override
    public abstract PieceType getPieceType();

    @Override
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}
