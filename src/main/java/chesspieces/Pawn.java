package chesspieces;

import coordination.Vector;

import java.util.ArrayList;

/*
TODO -

 */
public class Pawn extends Piece {

  private boolean justMovedTwo = false;
  private boolean hasMoved = false;
  private int moveDir = 1;
  public Pawn(Vector pos, Color color) {
    super(pos, color);
    type = PieceType.PAWN;
    moveDir = color == Color.WHITE ? 1 : -1;
  }
  @Override
  public int canMoveToPos(ArrayList<Piece> pieces, Vector newPos) {
    int toReturn = 0;
    if (newPos.getLetter() == pos.getLetter()) { //Regular movement, single and double step
      if (pos.add(0, moveDir).equals(newPos) && !isPieceInPos(pieces, newPos)) {
        toReturn = 1;
      }
      if (pos.add(0, moveDir * 2).equals(newPos) && !isPieceInPos(pieces, newPos) && !isPieceInPos(pieces, pos.add(0, moveDir)) && !hasMoved) {
        toReturn = 1;
        System.out.println("Pawn: "+pos.toString() + " "+color + " has moved is "+hasMoved);
      }
    } else { //Capturing
      if (pos.dist(newPos).equals(new Vector(1, 1))) {
        if ((pos.add(1, moveDir).equals(newPos) || pos.add(-1, moveDir).equals(newPos))) {
          if (isPieceInPos(pieces, newPos) && getPieceInPos(pieces, newPos).color != color) { //Regular capturing
            toReturn = 2;
          }
          for (int i = -1; i < 2; i+=2) { //En passant
            if (isPieceInPos(pieces, pos.add(i, 0)) && (getPieceInPos(pieces, pos.add(i, 0)) instanceof  Pawn) && ((Pawn) getPieceInPos(pieces, pos.add(i, 0))).getJustMovedTwo() && getPieceInPos(pieces, pos.add(i, 0)).color != color) {
              toReturn = 2;
            }
          }
        }
      }
    }
    return toReturn;
  }
  @Override
  public void moveToPos(Vector newPos) {
    if (pos.dist(newPos).getNumber() == 2) {
      justMovedTwo = true;
    }
    hasMoved = true;
    pos = newPos;


  }
  public boolean getJustMovedTwo() {
    return justMovedTwo;
  }
  public void setJustMovedTwo(boolean justMovedTwo) {
    this.justMovedTwo = justMovedTwo;
  }
}
