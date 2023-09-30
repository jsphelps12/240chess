package chess;

import java.util.Objects;

public class Move implements ChessMove{
    public Move(ChessPosition start,ChessPosition end, ChessPiece.PieceType type){
        start = start;
        end = end;
        promotionType = type;
    }
    private Position start;
    private Position end;

    private ChessPiece.PieceType promotionType;
    @Override
    public ChessPosition getStartPosition() {
        return start;
    }

    @Override
    public ChessPosition getEndPosition() {
        return end;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(start, move.start) && Objects.equals(end, move.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, promotionType);
    }
}
