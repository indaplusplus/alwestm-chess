package runninglogic;

import chesspieces.*;
import coordination.Vector;

import java.util.ArrayList;
import jdk.internal.util.xml.impl.Input;
import logic.MoveType;

public class Board {
  private ArrayList<Piece> pieces = new ArrayList<>();
  private boolean isWhiteTurn = true;
  private King whiteKing;
  private King blackKing;
  private boolean hasOfferedDraw = false;
  /**
   * 0 - Game playing
   * 1 - Draw
   * 2 - White wins
   * 3 - Black wins
   */
  private int gamestate = 0;
  public Board() {

  }
  private static final char[] validInputLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

  /**
   * Initializes board
   */
  public void initializeBoard() { //initializes board
    whiteKing = new King(new Vector("e1"), Color.WHITE);
    blackKing = new King(new Vector("e8"), Color.BLACK);

    pieces.add(whiteKing);
    pieces.add(blackKing);

    for (int i = 1; i <= 8; i++) {
      pieces.add(new Pawn(new Vector(i,2), Color.WHITE));
      pieces.add(new Pawn(new Vector(i,7), Color.BLACK));
    }
    pieces.add(new Rook(new Vector("a1"), Color.WHITE));
    pieces.add(new Knight(new Vector("b1"), Color.WHITE));
    pieces.add(new Bishop(new Vector("c1"), Color.WHITE));
    pieces.add(new Queen(new Vector("d1"), Color.WHITE));

    pieces.add(new Bishop(new Vector("f1"), Color.WHITE));
    pieces.add(new Knight(new Vector("g1"), Color.WHITE));
    pieces.add(new Rook(new Vector("h1"), Color.WHITE));


    pieces.add(new Rook(new Vector("a8"), Color.BLACK));
    pieces.add(new Knight(new Vector("b8"), Color.BLACK));
    pieces.add(new Bishop(new Vector("c8"), Color.BLACK));
    pieces.add(new Queen(new Vector("d8"), Color.BLACK));

    pieces.add(new Bishop(new Vector("f8"), Color.BLACK));
    pieces.add(new Knight(new Vector("g8"), Color.BLACK));
    pieces.add(new Rook(new Vector("h8"), Color.BLACK));
  }
  public void update(InputInterpreter inpin) throws IllegalArgumentException{

    if (inpin.getMoveType() == MoveType.KINGSIDE_CASTLING || inpin.getMoveType() == MoveType.QUEENSIDE_CASTLING) {
      King measureKing = isWhiteTurn ? whiteKing : blackKing;
      Rook measureRook = (Rook)getPieceWithPos(pieces, new Vector(inpin.getMoveType() == MoveType.QUEENSIDE_CASTLING ? 1 : 8, isWhiteTurn ? 1: 8));
      if (measureKing.getHasMoved() || measureRook.getHasMoved()) {
        throw new IllegalArgumentException("Piece has already moved");
      }
      int[] qSide = {2,3,4};
      int[] kSide = {6,7};
      int[] checkPos = inpin.getMoveType() == MoveType.QUEENSIDE_CASTLING ? qSide : kSide;
      System.out.println(inpin.getMoveType());
      for (int i = 0; i < checkPos.length; i++) {
        if (isPieceInPos(pieces, new Vector(checkPos[i], isWhiteTurn ? 1 : 8))) {
          throw new IllegalArgumentException("Pieces making castling impossible");
        }
      }
      int rookPos = inpin.getMoveType() == MoveType.QUEENSIDE_CASTLING ? 4 : 6;
      int kingPos = inpin.getMoveType() == MoveType.QUEENSIDE_CASTLING ? 3 : 7;
      measureRook.moveToPos(new Vector(rookPos, isWhiteTurn ? 1 : 8));
      measureKing.moveToPos(new Vector(kingPos, isWhiteTurn ? 1 : 8));
      if (measureKing.isInCheck(pieces)) {
        throw new IllegalArgumentException("That would put you in check");
      }
      isWhiteTurn = !isWhiteTurn;

    } else {
      Piece p = getMovingPiece(inpin);
      King k = isWhiteTurn ? whiteKing  : blackKing;
      MoveType m = MoveType.CAPTURE;

      switch (inpin.getMoveType()) {
        case NORMAL:
          p.moveToPos(inpin.getDestination());
          break;

        case CAPTURE:
          for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getPosition().equals(inpin.getDestination())) {
              pieces.remove(i);
              break;
            }
          }
          p.moveToPos(inpin.getDestination());
          break;
        case EN_PASSANT:
          boolean checkLegal = false;
          for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i) instanceof Pawn) {
              if (((Pawn) pieces.get(i)).getJustMovedTwo()) {
                pieces.remove(i);
                i--;
              }
            }
          }
          System.out.println(inpin.getDestination());
          p.moveToPos(inpin.getDestination());

          break;
        case PROMOTE:
          p.moveToPos(inpin.getDestination());
          break;


        case CHECK:
          break;


        case CHECKMATE:

          break;
        default:
          p.moveToPos(inpin.getDestination());
          break;
      }
      if ((isWhiteTurn ? whiteKing : blackKing).isInCheck(pieces)) {
        throw new IllegalArgumentException("That would put you in check");
      }
      isWhiteTurn = !isWhiteTurn;
      for (Piece piece : pieces) {
        if (!piece.equals(p) && piece instanceof Pawn) {
          ((Pawn)piece).setJustMovedTwo(false);
        }
      }
    }

  }

  /**
   *
   * Uses an inputinterpreter to figure out what piece it should move and returns that piece
   * Throws many exceptions to make sure it only returns the correct piece.
   */
  private Piece getMovingPiece(InputInterpreter inpin) {
    Piece returnPiece = null;

    Color color = isWhiteTurn ? Color.WHITE : Color.BLACK;
    ArrayList<Piece> possiblePieces = new ArrayList<>();

    //Determines if and how to use helpinginfo;
    String detInfo = inpin.getDeterminePieceInfo();
    Vector helpDetPos = null;

    //0 is irrelevant
    //1 is has a pos
    int helpState = 0;

    if (detInfo.length() != 0) {
      if (detInfo.length() == 2) {
        helpDetPos = new Vector(detInfo);
        helpState = 1;
      } else {
        if (Character.isAlphabetic(detInfo.charAt(0))) {
          helpDetPos = new Vector(detInfo+"1");
          helpState = 2;
        }
        if (Character.isDigit(detInfo.charAt(0))) {
          helpDetPos = new Vector("a"+detInfo);
          helpState = 3;
        }
      }
    }

    //Determines every piece that can move to the specified location, given the specified information
    for (Piece p : pieces) {
      if (p.getPieceType() == inpin.getPieceTypeToMove() && p.getColor() == color && p.canMoveToPos(pieces, inpin.getDestination()) != 0) {
        switch (helpState) {
          case 0:
            if (returnPiece == null) {
              returnPiece = p;
            } else {
              throw new IllegalArgumentException("No valid piece that can move to that place");
            }
            break;
          case 1:
            if (p.getPosition().equals(helpDetPos)) {
              if (returnPiece == null) {
                returnPiece = p;
              } else {
                throw new IllegalArgumentException("No valid piece that can move to that place");
              }
            }
            break;
          case 2:
            if (p.getPosition().getLetter() == helpDetPos.getLetter()) {
              if (returnPiece == null) {
                returnPiece = p;
              } else {
                throw new IllegalArgumentException("No valid piece that can move to that place");
              }
            }
            break;
          case 3:
            if (p.getPosition().getNumber() == helpDetPos.getNumber()) {
              if (returnPiece == null) {
                returnPiece = p;
              } else {
                throw new IllegalArgumentException("No valid piece that can move to that place");
              }
            }
            break;
          default:
            break;
        }
      }
    }
    if (returnPiece == null) {
      throw new IllegalArgumentException("No valid piece that can move to that place");
    }
    return returnPiece;
  }
  private boolean isPieceInPos(ArrayList<Piece> pieces, Vector pos) {
    boolean toReturn = false;
    for (Piece p : pieces) {
      if (p.getPosition().equals(pos)) {
        toReturn = true;
      }
    }
    return toReturn;
  }
  private Piece getPieceWithPos(ArrayList<Piece> searchThroughPieces, Vector pos ) throws IllegalArgumentException{
    for (Piece p : searchThroughPieces) {
      if (p.getPosition().equals(pos)) {
        return p;
      }
    }
    throw new IllegalArgumentException("No piece that can move as specified");
  }
  private boolean isInCheckMate(King k) {
    boolean toReturn = false;
    for (Piece p : pieces) {
      if (p.getColor() == k.getColor()) {

      }
    }
    return toReturn;
  }
  public ArrayList<Piece> getPieces() {
    return pieces;
  }
  public int getGamestate() {
    return gamestate;
  }
  public boolean getIsWhiteTurn() {
    return isWhiteTurn;
  }
  /**
   * Draws the board primitively
   */
  public String Draw() {
    String output = "";
    for (int i = 8; i >= 1; i--) {
      for (int j = 1; j <= 8; j++) {
        boolean hasPiece = false;
        for (Piece p : pieces) {
          if (p.getPosition().equals(new Vector(j, i))) {
            hasPiece = true;
            switch (p.getPieceType()) {
              case PAWN:
                output += "P ";
                break;
              case ROOK:
                output += "R ";
                break;
              case KNIGHT:
                output += "N ";
                break;
              case BISHOP:
                output += "B ";
                break;
              case QUEEN:
                output += "Q ";
                break;
              case KING:
                output += "K ";
                break;
              case NULL:
                break;
            }
          }
        }
        if (!hasPiece) {
          output += "O ";
        }
      }
      output += "\n";
    }
    return output;
  }
}
