package chesspieces;
import coordination.Vector;
import java.util.ArrayList;

public class King extends Piece {
  public King(Vector pos, Color color) {
    super(pos, color);
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
      ArrayList<Piece> useList = (ArrayList<Piece>)pieces.clone();
      if (ignorablePiece != null) {
        useList.remove(ignorablePiece);
      }
      for (Piece p : useList) {
        if (p.getColor() != color && p.canMoveToPos(useList, newPos) == 1) {
          toReturn = 0;
        }
      }
    }
    return toReturn;
  }
}
