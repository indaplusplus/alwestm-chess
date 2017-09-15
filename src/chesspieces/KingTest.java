package chesspieces;

import coordination.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
  @Test
  void move() {
    ArrayList<Piece> pieces = new ArrayList<>();
    Piece p = new King(new Vector(2,2), Color.WHITE);

    pieces.add(p);
    pieces.add(new Rook(new Vector(7, 3), Color.BLACK));
    pieces.add(new Knight(new Vector(3, 3), Color.BLACK));

    assertTrue(p.canMoveToPos(pieces, new Vector(2, 3)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(3, 3)) == 0);
  }

}