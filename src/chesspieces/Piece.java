package chesspieces;

import coordination.Vector;
import tiling.Tile;

import java.util.ArrayList;

public abstract class Piece {
  protected PieceType type;
  protected Vector pos;
  protected Color color;
  public Piece (Vector pos,Color color) {
    this.pos = pos;
    this.color = color;
  }
  public abstract int canMoveToPos(ArrayList<Piece> pieces, Vector newPos);
  public void moveToPos(Vector newPos) {
    pos = newPos;
  }
  public PieceType getPieceType() {
    return type;
  }
  public Color getColor() {
    return color;
  }
  public Vector getPosition() {
    return pos;
  }
  protected boolean isPieceInPos(ArrayList<Piece> pieces, Vector pos) {
    boolean toReturn = false;
    for (Piece p : pieces) {
      if (p.getPosition().equals(pos)) {
        toReturn = true;
      }
    }
    return toReturn;
  }
  protected Piece getPieceInPos(ArrayList<Piece> pieces, Vector pos) {
    Piece toReturn = null;
    for (Piece p : pieces) {
      if (p.getPosition().equals(pos)) {
        toReturn = p;
      }
    }
    return toReturn;
  }
}
