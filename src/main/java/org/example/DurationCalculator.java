package org.example;

public class DurationCalculator {
  private int totalDuration;

  public int getTotalDuration() {
    return totalDuration;
  }

  public void addDuration(Title title) {
    totalDuration += title.getDuration();
  }
}
