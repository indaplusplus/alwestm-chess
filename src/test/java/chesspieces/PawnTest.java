package chesspieces;

import coordination.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

  @Test
  void canMove() {
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    Piece pWhite = new Pawn(new Vector(2, 3), Color.WHITE);
    if (pWhite.canMoveToPos(pieces, new Vector(2, 4)) == 1) {
      pWhite.moveToPos(new Vector(2, 4));
    }
    System.out.println(pWhite.getPosition());
    assertTrue(pWhite.canMoveToPos(pieces, pWhite.getPosition()) == 0);
    assertTrue(pWhite.getPosition().equals(new Vector(2, 4)));
  }
  @Test
  void canCapture() {
    ArrayList<Piece> pieces = new ArrayList<>();

    Piece pWhite = new Pawn(new Vector(3, 2), Color.WHITE);
    pieces.add(pWhite);
    pieces.add(new Pawn(new Vector(2, 3), Color.BLACK));

    assertTrue(pWhite.canMoveToPos(pieces, new Vector(2, 3)) == 2);
  }

  @Test
  void canEnPassant() {
    Piece pWhite = new Pawn(new Vector(2, 5), Color.WHITE);
    Piece pBlack = new Pawn(new Vector(3, 7), Color.BLACK);
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    pieces.add(pWhite);
    pieces.add(pBlack);

    pBlack.moveToPos(new Vector(3, 5));
    assertTrue(pWhite.canMoveToPos(pieces, new Vector(3,6)) == 2);
  }

  @Test
  void canMoveTwoStepsFirstMove() {
    ArrayList<Piece> pieces = new ArrayList<>();
    Piece pBlack = new Pawn(new Vector(8, 7), Color.BLACK);
    pieces.add(pBlack);
    assertTrue(pBlack.canMoveToPos(pieces, new Vector(8, 5)) == 1);
    pBlack.moveToPos(new Vector(8, 5));
    assertFalse(pBlack.canMoveToPos(pieces, new Vector(8, 3)) == 1);
  }

  @Test
  void canPromote() {
    Piece pWhite = new Pawn(new Vector(2, 7), Color.WHITE);
    assertTrue(pWhite.getPieceType() == PieceType.QUEEN);
  }
}