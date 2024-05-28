package org.example;

public class Title implements Comparable<Title> {
  private String name;
  private int year;
  private boolean includedInPlan;
  private double review;
  private double sumRates;
  private int totalReviews;
  private int duration;

  public Title() {
  }

  public Title(String name) {
    this.name = name;
  }

  void show() {
    System.out.println("Movie: " + name);
    System.out.println("Year: " + year);
    System.out.println("Duration: " + getDurationInMinutes());
    System.out.println("Included in plan: " + includedInPlan);
    System.out.println("Review: " + review);
    System.out.println("Sum Rate: " + sumRates);
    System.out.println("Rate: " + getAverageRate());
    System.out.println("Total reviews: " + totalReviews);
    System.out.println("Duration: " + duration);
  }

  void rate (double rate) {
    totalReviews++;
    sumRates += rate;
  }

  public double getAverageRate() {
    return sumRates / totalReviews;
  }

  public int getDurationInMinutes() {
    return duration * 60;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public boolean isIncludedInPlan() {
    return includedInPlan;
  }

  public void setIncludedInPlan(boolean includedInPlan) {
    this.includedInPlan = includedInPlan;
  }

  public double getReview() {
    return review;
  }

  public void setReview(double review) {
    this.review = review;
  }

  public double getSumRates() {
    return sumRates;
  }

  public void setSumRates(double sumRates) {
    this.sumRates = sumRates;
  }

  public int getTotalReviews() {
    return totalReviews;
  }

  public void setTotalReviews(int totalReviews) {
    this.totalReviews = totalReviews;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Title: " + this.getName() + ", " + this.getSumRates() + " (" + this.getYear() + ")";
  }

  @Override
  public int compareTo(Title anotherTitle) {
    return this.getName().compareTo(anotherTitle.getName());
  }
}

