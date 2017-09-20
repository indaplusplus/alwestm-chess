package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

public class Knight extends Piece {

  public Knight (Vector pos,Color color) {
    super(pos, color);
    type = PieceType.KNIGHT;
  }

  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 0;
    if (pos.dist(newPos).getNumber() + pos.dist(newPos).getLetter() == 3) {
      if (!isPieceInPos(pieces, newPos)) {
        toReturn = 1;
      }
      if (isPieceInPos(pieces, newPos) && getPieceInPos(pieces, newPos).color != color) {
        toReturn = 2;
      }
    }
    return toReturn;
  }
}
