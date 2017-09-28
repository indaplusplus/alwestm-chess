package chesspieces;

import coordination.Vector;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class BishopTest {
  @Test
  public void canMove() {
    //Check can move regularly all four directions.
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    chesspieces.Piece p = new Bishop(new Vector(5, 3), Color.WHITE);
    pieces.add(p);

    Assert.assertTrue(p.canMoveToPos(pieces, p.getPosition()) == 0);

    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(4, 2)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 1)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(7, 5)) == 1);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(6, 2)) == 1);

    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(5, 2)) == 0);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(5, 4)) == 0);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(6, 3)) == 0);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(4, 3)) == 0);


    p.moveToPos(new Vector(4, 4));
    pieces.add(new Pawn(new Vector(6, 6), Color.BLACK));
    pieces.add(new Pawn(new Vector(2, 6), Color.WHITE));

    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(6, 6)) == 2);
    Assert.assertTrue(p.canMoveToPos(pieces, new Vector(2, 6)) == 0);
  }

}
