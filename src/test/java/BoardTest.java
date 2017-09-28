import org.junit.Test;
import runninglogic.Board;
import runninglogic.InputInterpreter;

public class BoardTest {
  @Test
  public void wholeGame() {
    InputInterpreter inpin = new InputInterpreter("e4");
    Board b = new Board();
    b.initializeBoard();
    b.Draw();

    System.out.println("\n \n");

    /*b.update("e4");
    b.Draw();
    System.out.println("e4 \n \n");

    b.update("e5");
    b.Draw();
    System.out.println("e5 \n \n");

    b.update("Nf3");
    b.Draw();
    System.out.println("Nf3 \n \n");

    b.update("f6");
    b.Draw();
    System.out.println("f6 \n \n");

    b.update("Nxe5");
    b.Draw();
    System.out.println("Nxe5 \n \n");

    b.update("fxe5");
    b.Draw();
    System.out.println("fxe5 \n \n");

    b.update("Qh5+");
    b.Draw();
    System.out.println("Qh5+ \n \n");

    b.update("Ke7");
    b.Draw();
    System.out.println("Ke7+ \n \n");

    b.update("Qxe5");
    b.Draw();
    System.out.println("Qxe5 \n \n");

    b.update("Kf7");
    b.Draw();
    System.out.println("Kf7+ \n \n");*/
  }
}
