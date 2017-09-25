package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

public class Rook extends Piece {
  private boolean hasMoved = false;
  public Rook(Vector pos, Color color) {
    super(pos, color);
    type = PieceType.ROOK;
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 1;
    Vector dist = pos.dist(newPos);
    if (!(dist.getLetter() == 0 && dist.getNumber() == 0) && (dist.getNumber() == 0 || dist.getLetter() == 0)) {
      if (isPieceInPos(pieces, newPos)) {
        toReturn = getPieceInPos(pieces, newPos).color == color ? 0 : 2;
      }
      Vector dir = newPos.sub(pos).dir();
      int measure = dist.getLetter() > dist.getNumber() ? dist.getLetter() : dist.getNumber();
      for (int i = 1; i < measure; i++) {
        if (isPieceInPos(pieces, pos.add(dir.mult(i, i)))) {
          toReturn = 0;

        }
      }
      if (toReturn == 1 && isPieceInPos(pieces, newPos) && getPieceInPos(pieces, newPos).color != color) {
        toReturn = 2;
      }
    } else {
      toReturn = 0;
    }
    return toReturn;
  }
  @Override
  public void moveToPos(Vector newPos) {
    super.moveToPos(newPos);
    hasMoved = true;
  }
  public boolean getHasMoved() {
    return hasMoved;
  }
}
