import chesspieces.PieceType;
import coordination.Vector;
import logic.MoveType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputInterpreterTest {
  @Test
  void testPosition() {
    InputInterpreter inpin;// = new InputInterpreter("Nxe4");

    //assertEquals(inpin.getDestination(), new Vector(5, 4) );

    //inpin = new InputInterpreter("f2");

    //assertEquals(inpin.getDestination(), new Vector(6, 2));
    inpin = new InputInterpreter("Rdxe4");
    Assertions.assertEquals(inpin.getPieceTypeToMove(), PieceType.ROOK);
    System.out.println(inpin.getDeterminePieceInfo());
    Assertions.assertEquals(inpin.getDestination(), new Vector(5, 4));
    Assertions.assertEquals(inpin.getDeterminePieceInfo(), "d");


  }

  @Test
  void testTypeIsCorrect() {
    InputInterpreter inpin = new InputInterpreter("Bxe1");
    Assertions.assertEquals(inpin.getPieceTypeToMove(), PieceType.BISHOP);


  }

  @Test
  void canPromote() {

    InputInterpreter inpin = new InputInterpreter("e8Q");
    Assertions.assertEquals(inpin.getDestination(), new Vector(5, 8));
    Assertions.assertEquals(inpin.getToPromoteTo(), PieceType.QUEEN);
    Assertions.assertEquals(inpin.getPieceTypeToMove(), PieceType.PAWN);
  }

  @Test
  void testSpecialCommands() {
    InputInterpreter i = new InputInterpreter("a3++");
    Assertions.assertEquals(i.getMoveType(), MoveType.CHECKMATE);

    i = new InputInterpreter("a3+");
    Assertions.assertEquals(i.getMoveType(), MoveType.CHECK);

    i = new InputInterpreter("a3Q");
    Assertions.assertEquals(i.getMoveType(), MoveType.PROMOTE);

    i = new InputInterpreter("exa3");
    Assertions.assertEquals(i.getMoveType(), MoveType.CAPTURE);

    i = new InputInterpreter("dxe3e.p.");
    Assertions.assertEquals(i.getMoveType(), MoveType.EN_PASSANT);


  }
}