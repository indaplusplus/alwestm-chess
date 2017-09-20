package coordination;

import java.util.HashMap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
/**
 * Vector for saving chess position.
 */
public class Vector {
  private static final BiMap<Character, Integer> numbersAndLetters;
  static {
    numbersAndLetters = HashBiMap.create();
    numbersAndLetters.put('a', 1);
    numbersAndLetters.put('b', 2);
    numbersAndLetters.put('c', 3);
    numbersAndLetters.put('d', 4);
    numbersAndLetters.put('e', 5);
    numbersAndLetters.put('f', 6);
    numbersAndLetters.put('g', 7);
    numbersAndLetters.put('h', 8);
  }

  private int letter;
  private int number;

  public Vector() {
    letter = 1;
    number = 1;
  }
  public Vector(int letter, int number) {
    if (letter > 8 || number > 8 || letter < 1 || number < 1) {
      throw new IndexOutOfBoundsException("Error, number out of chess board bounds.");
    }
    this.letter = letter;
    this.number = number;
  }
  private Vector(int letter, int number, boolean check) {
    if (check) {
      this.letter = letter;
      this.number = number;
    }
  }
  public Vector(String str) throws IllegalArgumentException, IndexOutOfBoundsException{
    if (!numbersAndLetters.containsKey(str.charAt(0)) || !Character.isDigit(str.charAt(1))) {
      throw new IllegalArgumentException("Error: Must be a valid chess coordinate");
    }
    letter = numbersAndLetters.get(str.charAt(0));
    number = Character.getNumericValue(str.charAt(1));
    if ((letter > 8  || number > 8 || letter < 1 || number < 1)) {
      throw new IndexOutOfBoundsException("Error: Must be a valid chess coordinate");
    }
  }
  public Vector add(Vector other) {
    return new Vector (letter + other.letter, number + other.number, true);
  }
  public Vector add(int letter, int number) {
    return new Vector(this.letter + letter, this.number + number, true);
  }
  public Vector sub(Vector other) {
    return new Vector(letter - other.letter,number - other.number, true);
  }
  public Vector sub(int letter, int number) {
    return new Vector(this.letter - letter, this.number - number, true);
  }
  public Vector dist(Vector other) {
    return new Vector(Math.abs(letter - other.letter),Math.abs(number - other.number), true);
  }
  public Vector dist(int letter, int number) {
    return new Vector(Math.abs(this.letter - letter), Math.abs(this.number - number), true);
  }
  public Vector div(Vector other) {
    return new Vector(letter / other.letter, number / other.number, true);
  }
  public Vector div(int letter, int number) {
    return new Vector(this.letter / letter, this.number / number, true);
  }

  public Vector mult(Vector other) {
    return new Vector(letter * other.letter, number * other.number, true);
  }
  public Vector mult(int letter, int number) {
    return new Vector(this.letter * letter, this.number * number, true);
  }
  public String getPos() {
    return ""+numbersAndLetters.inverse().get(letter) + number;
  }

  /**
   * Column
   */
  public int getLetter() {
    return letter;
  }

  /**
   * Row
   */
  public int getNumber() {
    return number;
  }

  public boolean equals (Object other) {
    if (!(other instanceof Vector)) {
      return false;
    }
    Vector vOther = (Vector)other;
    return (letter == vOther.getLetter() && number == vOther.getNumber());
  }

  public String toString() {
    return "Letter: "+letter + " Number: "+number;
  }
}
