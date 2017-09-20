package chesspieces;

import coordination.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
  @Test
  void canMove() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    Piece r = new Rook(new Vector(2, 2), Color.WHITE);
    pieces.add(r);
    pieces.add(new Pawn(new Vector(6, 2), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 5), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 1), Color.WHITE));

    assertTrue(r.canMoveToPos(pieces, r.getPosition()) == 0);
    assertTrue(r.canMoveToPos(pieces, new Vector(2, 4)) == 1);
    assertTrue(r.canMoveToPos(pieces, new Vector(6, 2)) == 2);
    assertTrue(r.canMoveToPos(pieces, new Vector(2, 5)) == 2);
    assertTrue(r.canMoveToPos(pieces, new Vector(2, 1)) == 0);

  }
}