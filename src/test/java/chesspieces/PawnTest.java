package chesspieces;

import coordination.Vector;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class PawnTest {
  @Test
  public void canMove() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    Piece pWhite = new Pawn(new Vector(2, 3), Color.WHITE);
    if (pWhite.canMoveToPos(pieces, new Vector(2, 4)) == 1) {
      pWhite.moveToPos(new Vector(2, 4));
    }
    System.out.println(pWhite.getPosition());
    Assert.assertTrue(pWhite.canMoveToPos(pieces, pWhite.getPosition()) == 0);
    Assert.assertTrue(pWhite.getPosition().equals(new Vector(2, 4)));
  }

  @Test
  public void canCapture() {
    ArrayList<Piece> pieces = new ArrayList<>();

    Piece pWhite = new Pawn(new Vector(3, 2), Color.WHITE);
    pieces.add(pWhite);
    pieces.add(new Pawn(new Vector(2, 3), Color.BLACK));

    Assert.assertTrue(pWhite.canMoveToPos(pieces, new Vector(2, 3)) == 2);
  }

  @Test
  public void canEnPassant() {
    Piece pWhite = new Pawn(new Vector(2, 5), Color.WHITE);
    Piece pBlack = new Pawn(new Vector(3, 7), Color.BLACK);
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    pieces.add(pWhite);
    pieces.add(pBlack);

    pBlack.moveToPos(new Vector(3, 5));
    Assert.assertTrue(pWhite.canMoveToPos(pieces, new Vector(3,6)) == 2);
  }

  @Test
  public void canMoveTwoStepsFirstMove() {
    ArrayList<Piece> pieces = new ArrayList<>();
    Piece pBlack = new Pawn(new Vector(8, 7), Color.BLACK);
    pieces.add(pBlack);
    Assert.assertTrue(pBlack.canMoveToPos(pieces, new Vector(8, 5)) == 1);
    pBlack.moveToPos(new Vector(8, 5));
    Assert.assertFalse(pBlack.canMoveToPos(pieces, new Vector(8, 3)) == 1);
  }

  @Test
  public void canPromote() {
    Piece pWhite = new Pawn(new Vector(2, 7), Color.WHITE);
    Assert.assertEquals(PieceType.QUEEN, pWhite.getPieceType());
  }
}
