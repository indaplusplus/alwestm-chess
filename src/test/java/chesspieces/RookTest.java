package chesspieces;

import coordination.Vector;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class RookTest {
  @Test
  public void canMove() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    Piece r = new Rook(new Vector(2, 2), Color.WHITE);
    pieces.add(r);
    pieces.add(new Pawn(new Vector(6, 2), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 5), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 1), Color.WHITE));

    Assert.assertTrue(r.canMoveToPos(pieces, r.getPosition()) == 0);
    Assert.assertTrue(r.canMoveToPos(pieces, new Vector(2, 4)) == 1);
    Assert.assertTrue(r.canMoveToPos(pieces, new Vector(6, 2)) == 2);
    Assert.assertTrue(r.canMoveToPos(pieces, new Vector(2, 5)) == 2);
    Assert.assertTrue(r.canMoveToPos(pieces, new Vector(2, 1)) == 0);

  }
}
