package chesspieces;

import coordination.Vector;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class KingTest {
  @Test
  public void move() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    Piece p = new King(new Vector(2,2), Color.WHITE);

    pieces.add(p);
    pieces.add(new Rook(new Vector(7, 3), Color.BLACK));
    pieces.add(new Knight(new Vector(3, 3), Color.BLACK));

    Assert.assertEquals(1, p.canMoveToPos(pieces, new Vector(2, 3)));
    Assert.assertEquals(0, p.canMoveToPos(pieces, new Vector(3, 3)));
  }
}
