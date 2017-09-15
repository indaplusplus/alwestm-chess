package chesspieces;

import coordination.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
  @Test
  void canMove() {
    ArrayList<Piece> pieces = new ArrayList<>();
    Piece p = new Queen(new Vector(5, 5), Color.WHITE);
    pieces.add(p);

    assertTrue(p.canMoveToPos(pieces, p.getPosition()) == 0);

    //Acting as rook
    assertTrue(p.canMoveToPos(pieces, new Vector(3, 5)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(7, 5)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(5, 3)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(5, 7)) == 1);

    //Acting as bishop
    assertTrue(p.canMoveToPos(pieces, new Vector(3, 3)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(3, 7)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(7, 3)) == 1);
    assertTrue(p.canMoveToPos(pieces, new Vector(7, 7)) == 1);

    pieces.add(new Pawn(new Vector(6,5), Color.WHITE));
    assertTrue(p.canMoveToPos(pieces, new Vector(7, 5)) == 0);

  }

}