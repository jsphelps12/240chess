package chess;

import java.util.Objects;

public class Move implements ChessMove{
    public Move(Position start,Position end, ChessPiece.PieceType type){
        start = start;
        end = end;
    }
    private Position start;
    private Position end;
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
        return null;
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
        return Objects.hash(start, end);
    }
}
