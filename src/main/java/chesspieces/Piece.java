package chesspieces;

import coordination.Vector;

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
  public boolean equals(Object other) {
    boolean toReturn = false;
    if (other.getClass().equals(getClass())) {
      Piece p = (Piece)other;
      if (this.getPieceType() == p.getPieceType() && this.getColor() == p.getColor() && this.getPieceType() == p.getPieceType()) {
        toReturn = true;
      }
    }
    return toReturn;
  }
  public static Piece createNewPiece(PieceType type, Vector pos, Color color) {
    Piece p = null;
    switch (type) {
      case PAWN:
        p = new Pawn(pos, color);
        break;
      case ROOK:
        p = new Rook(pos, color);
        break;
      case KNIGHT:
        p = new Knight(pos, color);
        break;
      case BISHOP:
        p = new Bishop(pos, color);
        break;
      case QUEEN:
        p = new Queen(pos, color);
        break;
      case KING:
        p = new King(pos, color);
        break;
    }
    return p;
  }
  public String toDraw() {
    return getPieceType() + " at " + getPosition().toString();
  }
}
