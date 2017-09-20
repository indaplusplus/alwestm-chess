package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

public class Bishop extends Piece {

  public Bishop(Vector pos, Color color) {
    super(pos, color);
    type = PieceType.BISHOP;
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 1;
    if (pos.dist(newPos).getNumber() == pos.dist(newPos).getLetter() && !newPos.equals(pos)) {
      if (isPieceInPos(pieces, newPos)) {
        toReturn = getPieceInPos(pieces, newPos).color == color ? 0 : 2;
      }
      Vector dir = newPos.sub(pos).div(newPos.dist(pos)); //1, 1 to -1, -1
        for (int i = 1; i < pos.dist(newPos).getLetter(); i++) {
          if (isPieceInPos(pieces, pos.add(dir.mult(i, i)))) {
            toReturn = 0;
          }
        }
    } else {
      toReturn = 0;
    }
    return toReturn;
  }
}
