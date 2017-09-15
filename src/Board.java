import chesspieces.Color;
import chesspieces.Pawn;
import chesspieces.Piece;
import coordination.Vector;

import java.util.ArrayList;

public class Board {
  private ArrayList<Piece> pieces = new ArrayList<>();
  private boolean isWhiteTurn = true;
  private static final char[] validInputLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

  /**
   * Initializes board
   */
  public void initializeBoard() { //initializes board
    //White back rank
    for (int i = 1; i <= 8; i++) {
      pieces.add(new Pawn(new Vector(i,2), Color.WHITE));
      pieces.add(new Pawn(new Vector(i,7), Color.BLACK));
    }

  }
  public String update(String input) {
    String toReturn = "Operation failed";
    if (input.equals("0-0") || input.equals("0-0-0")) { //Check if changeling

    }
    InputInterpreter inpin = new InputInterpreter(input);
    Piece p = getMovingPiece(inpin);
    if (p != null) {
      if (p.getColor() == Color.WHITE && isWhiteTurn || p.getColor() == Color.BLACK && !isWhiteTurn) {
        if (p.canMoveToPos(pieces, inpin.getDestination()) == 1 && !inpin.getIsCapturing()) {
          p.moveToPos(inpin.getDestination());
          toReturn = "Operation succeded";
        }
        if (p.canMoveToPos(pieces, inpin.getDestination()) == 2 && inpin.getIsCapturing()) {
          p.moveToPos(inpin.getDestination());
          toReturn = "Operation succeded";
        }
      }
    }

    isWhiteTurn = !isWhiteTurn;
    return "don't bother me";
  }
  private Piece getMovingPiece(InputInterpreter inpin) {
    ArrayList<Piece> possiblePieces = new ArrayList<>();
    for (Piece p : pieces) {
      if (p.getPieceType() == inpin.getPieceTypeToMove()) {
        possiblePieces.add(p);
      }
    }

    boolean moreThanOneAnswer = false;
    Piece returnPiece = null;
    for (Piece p : possiblePieces) {
      if (p.canMoveToPos(pieces, inpin.getDestination()) != 0) {
        if (moreThanOneAnswer) {
          throw new IllegalArgumentException("What piece are you attempting to move?");
        }
        returnPiece = p;
        moreThanOneAnswer = true;
      }
    }
    if (returnPiece == null) {
      throw new IllegalArgumentException("No valid piece that can move to that place");
    }
    return returnPiece;
  }
  private Piece getPieceWithPos(Vector pos) {
    for (Piece p : pieces) {
      if (p.getPosition().equals(pos)) {
        return p;
      }
    }
    throw new IllegalArgumentException("No piece that can move as specified");
  }
}
