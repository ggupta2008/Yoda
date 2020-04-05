package com.shanky.problems;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ReverseStringInPlace {
  public static void reverse(char[] arrayOfChars) {
    int leftIndex = 0;
    int rightIndex = arrayOfChars.length - 1;

    while (leftIndex < rightIndex) {

      // swap characters
      char temp = arrayOfChars[leftIndex];
      arrayOfChars[leftIndex] = arrayOfChars[rightIndex];
      arrayOfChars[rightIndex] = temp;

      // move towards middle
      leftIndex++;
      rightIndex--;
    }
  }

  // tests

  @Test
  public void emptyStringTest() {
    final char[] actual = "".toCharArray();
    reverse(actual);
    final char[] expected = "".toCharArray();
    assertArrayEquals(expected, actual);
  }

  @Test
  public void singleCharacterStringTest() {
    final char[] actual = "A".toCharArray();
    reverse(actual);
    final char[] expected = "A".toCharArray();
    assertArrayEquals(expected, actual);
  }

  @Test
  public void longerStringTest() {
    final char[] actual = "ABCDE".toCharArray();
    reverse(actual);
    final char[] expected = "EDCBA".toCharArray();
    assertArrayEquals(expected, actual);
  }

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(ReverseStringInPlace.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    if (result.wasSuccessful()) {
      System.out.println("All tests passed.");
    }
  }
}
