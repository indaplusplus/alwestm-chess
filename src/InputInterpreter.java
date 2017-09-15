import chesspieces.PieceType;
import coordination.Vector;

import java.util.HashMap;

public class InputInterpreter {

  private Vector destination;
  private PieceType pieceTypeToMove;
  private boolean isTakingPiece = false;
  private String additionalInfo;
  private boolean isCapturing = false;
  private static final HashMap<Character, PieceType> translateCmdMap = new HashMap<Character, PieceType>();
  static {
    translateCmdMap.put('r', PieceType.ROOK);
    translateCmdMap.put('h', PieceType.KNIGHT);
    translateCmdMap.put('b', PieceType.BISHOP);
    translateCmdMap.put('q', PieceType.QUEEN);
    translateCmdMap.put('k', PieceType.KING);
  }


  public InputInterpreter(String cmd) {
    boolean isPawn = !Character.isUpperCase(cmd.charAt(0));
    destination = new Vector(cmd.substring(cmd.length() - 2));
    pieceTypeToMove = isPawn ? PieceType.PAWN : translateCmdMap.get(cmd.charAt(0));
    if (cmd.contains("x")) {
      isTakingPiece = true;
    }
    if (cmd.length() > 3) {
      additionalInfo = cmd.substring(1, cmd.length() - 2);
    }
  }

  public Vector getDestination() {
    return destination;
  }
  public PieceType getPieceTypeToMove() {
    return pieceTypeToMove;
  }
  public boolean getIsTakingPiece() {
    return isTakingPiece;
  }
  public String getAdditionalInfo() {
    return additionalInfo;
  }
  public boolean getIsCapturing() {
    return isCapturing;
  }
}
