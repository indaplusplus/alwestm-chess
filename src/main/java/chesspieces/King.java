package chesspieces;
import coordination.Vector;
import java.util.ArrayList;

public class King extends Piece {
  public King(Vector pos, Color color) {
    super(pos, color);
    type = PieceType.KING;
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 1;
    Vector dist = pos.dist(newPos);
    Piece ignorablePiece = null;
    if (isPieceInPos(pieces, newPos)) {
      ignorablePiece = getPieceInPos(pieces, newPos);
      toReturn = 2;
    }
    if (!pos.equals(newPos)) {
      if (dist.getNumber() > 1 || dist.getLetter() > 1) {
        toReturn = 0;
      }
      ArrayList<Piece> useList = new ArrayList<Piece>();
      for (Piece p : pieces) {
        useList.add(Piece.createNewPiece(p.getPieceType(), newPos, p.getColor()));
      }
      if (ignorablePiece != null) {
        useList.remove(ignorablePiece);
        useList.remove(this);
      }
      for (Piece p : useList) {
        if (p.getPieceType() != PieceType.KING) {
          if (!p.equals(this) && p.getColor() != color && p.canMoveToPos(useList, newPos) == 1) {
            toReturn = 0;
          }
        } else if (p.getPosition().dist(newPos).getNumber() == 1 || p.getPosition().dist(newPos).getLetter() == 1) {
          toReturn = 0;
        }
      }
    }
    return toReturn;
  }
  public boolean isInCheck(ArrayList<Piece> pieces) {
    boolean toReturn = false;
    for (Piece p : pieces) {
      if (p.getColor() != getColor()) {
        if (p.canMoveToPos(pieces, getPosition()) == 2) {
          toReturn = true;
        }
      }
    }
    return toReturn;
  }
}
