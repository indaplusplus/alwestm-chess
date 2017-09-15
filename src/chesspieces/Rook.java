package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

public class Rook extends Piece {
  public Rook(Vector pos, Color color) {
    super(pos, color);
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 1;
    Vector dist = pos.dist(newPos);
    if (dist.getLetter() == 0 ||dist.getNumber() == 0 ) {
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
          System.out.println(mod);
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
    }
    return toReturn;
  }
}
