package com.shanky.problems;

import static java.lang.Character.isSpaceChar;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ReverseWords {
  public static void reverseWords(char[] message) {
    reverse(message, 0, message.length - 1);

    // we have reversed the entire string, we will send individual words to be reversed in place and
    // we will have a solution
    int startIndex = 0;

    for (int i = 0; i <= message.length; i++) {
      if (i == message.length || isSpaceChar(message[i])) {
        reverse(message, startIndex, i - 1);
        startIndex = i + 1;
      }
    }
  }

  public static void reverse(char[] message, int startIndex, int endIndex) {
    int leftIndex = startIndex;
    int rightIndex = endIndex;

    while (leftIndex < rightIndex) {
      // swap characters
      char temp = message[leftIndex];
      message[leftIndex] = message[rightIndex];
      message[rightIndex] = temp;
      // move towards middle
      leftIndex++;
      rightIndex--;
    }
  }

  // tests
  @Test
  public void oneWordTest() {
    final char[] expected = "vault".toCharArray();
    final char[] actual = "vault".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void twoWordsTest() {
    final char[] expected = "cake thief".toCharArray();
    final char[] actual = "thief cake".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void threeWordsTest() {
    final char[] expected = "get another one".toCharArray();
    final char[] actual = "one another get".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void multipleWordsSameLengthTest() {
    final char[] expected = "the cat ate the rat".toCharArray();
    final char[] actual = "rat the ate cat the".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void multipleWordsDifferentLengthsTest() {
    final char[] expected = "chocolate bundt cake is yummy".toCharArray();
    final char[] actual = "yummy is cake bundt chocolate".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void emptyStringTest() {
    final char[] expected = "".toCharArray();
    final char[] actual = "".toCharArray();
    reverseWords(actual);
    assertArrayEquals(expected, actual);
  }

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(ReverseWords.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    if (result.wasSuccessful()) {
      System.out.println("All tests passed.");
    }
  }
}
