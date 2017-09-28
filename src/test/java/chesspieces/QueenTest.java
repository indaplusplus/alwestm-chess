package chesspieces;

import coordination.Vector;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class QueenTest {
  @Test
  public void canMove() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    Piece p = new Queen(new Vector(5, 5), Color.WHITE);
    pieces.add(p);

    Assert.assertTrue(p.canMoveToPos(pieces, p.getPosition()) == 0);

    //Acting as rook
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(3, 5)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 5)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(5, 3)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(5, 7)) == 1);

    //Acting as bishop
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(3, 3)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(3, 7)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 3)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 7)) == 1);

    pieces.add(new Pawn(new Vector(6,5), Color.WHITE));
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 5)) == 0);

  }

}
