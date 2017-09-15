package chesspieces;

import coordination.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
  @Test
  void canMove() {
    ArrayList<Piece> pieces = new ArrayList<>();
    Piece p = new Knight(new Vector(3, 1), Color.WHITE);
    pieces.add(p);
    pieces.add(new Pawn(new Vector(4, 3), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 3), Color.WHITE));

    assertTrue(p.canMoveToPos(pieces, p.getPosition()) == 0);
    assertTrue(p.canMoveToPos(pieces, new Vector(5, 2)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(4, 3)) == 2);
  }

}