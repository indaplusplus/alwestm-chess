package coordination;

import org.junit.Assert;
import org.junit.Test;

public class VectorTest {
  @Test
  public void getPos() {
    Vector v1 = new Vector(3, 2);
    Vector v2 = new Vector("b3");
    Assert.assertTrue(v1.getPos().equals("c2"));
    Assert.assertTrue(v2.getPos().equals("b3"));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void settingPos() {
    new Vector(100, 101);
  }

  @Test
  public void graphic() {
    System.out.println(Vector.graphicToVector(63).toString());
  }
}
