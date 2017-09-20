package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

public class Queen extends Piece {
  public Queen(Vector pos, Color color) {
    super(pos, color);
    type = PieceType.QUEEN;
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 1;
    Vector dist = pos.dist(newPos);

    if (dist.getNumber() == dist.getLetter() && !pos.equals(newPos)) {
      if (isPieceInPos(pieces, newPos)) {
        toReturn = getPieceInPos(pieces, newPos).color == color ? 0 : 2;
      }
      Vector dir = newPos.sub(pos).div(dist); //1, 1 to -1, -1
      for (int i = 1; i < dist.getLetter(); i++) {
        if (isPieceInPos(pieces, pos.add(dir.mult(i, i)))) {
          toReturn = 0;
        }
      }
    } else if ((dist.getLetter() == 0 || dist.getNumber() == 0 ) && !pos.equals(newPos)) {
      if (isPieceInPos(pieces, newPos)) {
        toReturn = getPieceInPos(pieces, newPos).color == color ? 0 : 2;
        System.out.println("Fisk " + toReturn);
      }
      if (dist.getLetter() == 0) {
        for (int i = 1; i < dist.getNumber(); i++) {
          int mod = i * newPos.sub(pos).getNumber() /  dist.getNumber();
          if (isPieceInPos(pieces, pos.add(0, mod))) {
            toReturn = 0;
          }
        }
      }
      if (dist.getNumber() == 0) {
        for (int i = 1; i < dist.getLetter(); i++) { // 1, 2, 4
          int mod = i * newPos.sub(pos).getLetter() /  dist.getLetter();
          if (isPieceInPos(pieces, pos.add(mod, 0))) {
            toReturn = 0;
          }
        }
      }
    } else {
      toReturn = 0;
    }
    return toReturn;
  }
}
