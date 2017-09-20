package coordination;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
  @org.junit.jupiter.api.Test
  void getPos() {
    Vector v1 = new Vector(3, 2);
    Vector v2 = new Vector("b3");
    assertTrue(v1.getPos().equals("c2"));
    assertTrue(v2.getPos().equals("b3"));
  }
  @org.junit.jupiter.api.Test
  void settingPos() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Vector(100, 101);
    });
  }
}