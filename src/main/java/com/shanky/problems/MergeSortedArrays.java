package com.shanky.problems;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MergeSortedArrays {
  public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

    if (myArray == null || myArray.length == 0) {
      return alicesArray;
    } else if (alicesArray == null || alicesArray.length == 0) {
      return myArray;
    }

    int[] mergedArray = new int[myArray.length + alicesArray.length];
    // combine the sorted arrays into one large sorted array
    int i = 0;
    int j = 0;

    while (i < myArray.length && j < alicesArray.length) {
      if (myArray[i] < alicesArray[j]) {
        mergedArray[i + j] = myArray[i];
        i++;
      } else {
        mergedArray[i + j] = alicesArray[j];
        j++;
      }
    }

    while (j < alicesArray.length) {
      mergedArray[i + j] = alicesArray[j];
      j++;
    }

    while (i < myArray.length) {
      mergedArray[i + j] = myArray[i];
      i++;
    }
    return mergedArray;
  }

  // tests
  @Test
  public void bothArraysAreEmptyTest() {
    final int[] myArray = {};
    final int[] alicesArray = {};
    final int[] expected = {};
    final int[] actual = mergeArrays(myArray, alicesArray);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void firstArrayIsEmptyTest() {
    final int[] myArray = {};
    final int[] alicesArray = {1, 2, 3};
    final int[] expected = {1, 2, 3};
    final int[] actual = mergeArrays(myArray, alicesArray);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void secondArrayIsEmptyTest() {
    final int[] myArray = {5, 6, 7};
    final int[] alicesArray = {};
    final int[] expected = {5, 6, 7};
    final int[] actual = mergeArrays(myArray, alicesArray);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void bothArraysHaveSomeNumbersTest() {
    final int[] myArray = {2, 4, 6};
    final int[] alicesArray = {1, 3, 7};
    final int[] expected = {1, 2, 3, 4, 6, 7};
    final int[] actual = mergeArrays(myArray, alicesArray);
    assertArrayEquals(expected, actual);
  }

  @Test
  public void arraysAreDifferentLengthsTest() {
    final int[] myArray = {2, 4, 6, 8};
    final int[] alicesArray = {1, 7};
    final int[] expected = {1, 2, 4, 6, 7, 8};
    final int[] actual = mergeArrays(myArray, alicesArray);
    assertArrayEquals(expected, actual);
  }

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(MergeSortedArrays.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    if (result.wasSuccessful()) {
      System.out.println("All tests passed.");
    }
  }
}
