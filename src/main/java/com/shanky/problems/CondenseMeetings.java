package com.shanky.problems;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CondenseMeetings {

  public List<Meeting> mergeRanges(List<Meeting> meetings) {
    if (meetings == null || meetings.size() <= 1) {
      return meetings;
    }
    // sort the array by start time
    Collections.sort(meetings);

    List<Meeting> result = new ArrayList<>();
    result.add(meetings.get(0));
    // parse over list and merge them if condition matches
    for (int i = 1; i < meetings.size(); i++) {
      Meeting lastMergedMeeting = result.get(result.size() - 1);
      if (lastMergedMeeting.getEndTime() >= meetings.get(i).getStartTime()) {
        lastMergedMeeting.setEndTime(Math.max(meetings.get(i).endTime, lastMergedMeeting.endTime));
      } else {
        result.add(meetings.get(i));
      }
    }

    return result;
  }

  public static class Meeting implements Comparable<Meeting> {

    private int startTime;
    private int endTime;

    Meeting(int startTime, int endTime) {
      // number of 30 min blocks past 9:00 am
      this.startTime = startTime;
      this.endTime = endTime;
    }

    public int getStartTime() {
      return startTime;
    }

    public void setStartTime(int startTime) {
      this.startTime = startTime;
    }

    public int getEndTime() {
      return endTime;
    }

    public void setEndTime(int endTime) {
      this.endTime = endTime;
    }

    public int compareTo(Meeting meeting) {
      return this.startTime - meeting.getStartTime();
    }

    @Override
    public String toString() {
      final StringBuffer sb = new StringBuffer("Meeting{");
      sb.append("startTime=").append(startTime);
      sb.append(", endTime=").append(endTime);
      sb.append('}');
      return sb.toString();
    }
  }

  // tests

  @Test
  public void meetingsOverlapTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsTouchTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingContainsOtherMeetingTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsStaySeparateTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void multipleMergedMeetingsTest() {
    final List<Meeting> meetings =
        Arrays.asList(new Meeting(1, 4), new Meeting(2, 5), new Meeting(5, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsNotSortedTest() {
    final List<Meeting> meetings =
        Arrays.asList(new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4), new Meeting(5, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void oneLongMeetingContainsSmallerMeetingsTest() {
    final List<Meeting> meetings =
        Arrays.asList(
            new Meeting(1, 10),
            new Meeting(2, 5),
            new Meeting(6, 8),
            new Meeting(9, 10),
            new Meeting(10, 12));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 12));
    assertEquals(expected, actual);
  }

  @Test
  public void sampleInputTest() {
    final List<Meeting> meetings =
        Arrays.asList(
            new Meeting(0, 1),
            new Meeting(3, 5),
            new Meeting(4, 8),
            new Meeting(10, 12),
            new Meeting(9, 10));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected =
        Arrays.asList(new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12));
    assertEquals(expected, actual);
  }

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(CondenseMeetings.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    if (result.wasSuccessful()) {
      System.out.println("All tests passed.");
    }
  }
}
