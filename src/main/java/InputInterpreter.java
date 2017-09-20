import logic.MoveType;
import chesspieces.PieceType;
import coordination.Vector;

import java.util.HashMap;

public class InputInterpreter {

  private Vector destination = null;
  private PieceType pieceTypeToMove = null;
  private PieceType toPromoteTo = null;
  private String determinePieceInfo = null;
  private MoveType moveType = null;

  private static final HashMap<Character, PieceType> translateCmdMap = new HashMap<Character, PieceType>();
  static {
    translateCmdMap.put('R', PieceType.ROOK);
    translateCmdMap.put('N', PieceType.KNIGHT);
    translateCmdMap.put('B', PieceType.BISHOP);
    translateCmdMap.put('Q', PieceType.QUEEN);
    translateCmdMap.put('K', PieceType.KING);
  }


  /**
   * Converts an algebraic chess notation entry into useful data for attempting to process a move.
   * Does not check for castling
   */
  public InputInterpreter(String cmd) throws IllegalArgumentException {

    moveType = MoveType.NORMAL;
    if (cmd.length() < 2) {
      throw new IllegalArgumentException ("Too few characters");
    }
    //Determines, deals with and removes eventual special instructions, checks that the instruction has a compatible length as well.
    if (Character.isAlphabetic(cmd.charAt(cmd.length() - 1))) {
      moveType = MoveType.PROMOTE;
      toPromoteTo = translateCmdMap.get(cmd.charAt(cmd.length() - 1));
      cmd = cmd.substring(0, cmd.length() - 1);
    }
    if (cmd.endsWith("++") && cmd.length() < 5) {
      moveType = MoveType.CHECKMATE;
      cmd = cmd.substring(0, cmd.length() - 2);
    }
    if (cmd.endsWith("+") && cmd.length() >= 3) {
      moveType = MoveType.CHECK;
      cmd = cmd.substring(0, cmd.length() - 1);
    }
    if (cmd.endsWith("e.p.") && cmd.length() == 8) {
      moveType = MoveType.EN_PASSANT;
      cmd = cmd.substring(0, cmd.length() - 4);
    }
    //Determines destination
    try {
      destination = new Vector(cmd.substring(cmd.length() - 2));
      cmd = cmd.substring(0, cmd.length() - 2);
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("Did not suggest a valid coordinate to move towards");

    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalArgumentException("Attempted positioning is outside board bounds");
    }
    //Checks if capturing
    if (cmd.endsWith("x") && moveType != MoveType.EN_PASSANT) {
      moveType = MoveType.CAPTURE;
      cmd = cmd.substring(0, cmd.length() - 1);
    }
    if (cmd.length() == 0 || Character.isLowerCase(cmd.charAt(0))) {
      pieceTypeToMove = PieceType.PAWN;

    } else if (Character.isUpperCase(cmd.charAt(0))) {
      pieceTypeToMove = translateCmdMap.get(cmd.charAt(0));
      cmd = cmd.substring(1);
    }
    if (cmd.length() > 2) {
      throw new IllegalArgumentException("That move is unreadable");
    }
    determinePieceInfo = cmd;
    if (pieceTypeToMove == null || destination == null || moveType == null) {
      throw new IllegalArgumentException("Did not enter sufficient information");
    }
  }

  public Vector getDestination() {
    return destination;
  }
  public PieceType getPieceTypeToMove() {
    return pieceTypeToMove;
  }
  public PieceType getToPromoteTo() {
    return toPromoteTo;
  }
  public String getDeterminePieceInfo() {
    return determinePieceInfo;
  }
  public MoveType getMoveType() {
    return moveType;
  }
}
