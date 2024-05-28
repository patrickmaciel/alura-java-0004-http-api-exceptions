package org.example;

import java.util.Comparator;

public class TitleYearComparator implements Comparator<Title> {

  @Override
  public int compare(Title o1, Title o2) {
    if (o1.getYear() > o2.getYear()) {
      return 1;
    }

    if (o1.getYear() < o2.getYear()) {
      return -1;
    }

    return 0;
  }
}
