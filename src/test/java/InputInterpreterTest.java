import chesspieces.PieceType;
import coordination.Vector;
import logic.MoveType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import runninglogic.Board;
import runninglogic.InputInterpreter;

class InputInterpreterTest {
  @Test
  void testPosition() {
    InputInterpreter inpin;// = new runninglogic.InputInterpreter("Nxe4");

    //assertEquals(inpin.getDestination(), new Vector(5, 4) );

    //inpin = new runninglogic.InputInterpreter("f2");

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
    InputInterpreter i;
    Board b = new Board();
    b.initializeBoard();

    //i = new InputInterpreter("")
    i = new InputInterpreter("a4");
    b.update(i);

    i = new InputInterpreter("a6");
    b.update(i);

    i = new InputInterpreter("a5");
    b.update(i);

    i = new InputInterpreter("b5");
    b.update(i);

    i = new InputInterpreter("axb6e.p.");
    System.out.println(i.getDestination());
    System.out.println(i.getDeterminePieceInfo());
    b.update(i);
    b.Draw();

  }
}