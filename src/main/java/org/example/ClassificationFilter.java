package org.example;

public class ClassificationFilter {
  private String recommendation;

  public String getRecommendation() {
    return recommendation;
  }

  public void filter(Classification classification) {
    int classificationValue = classification.classify();
    if (classificationValue == 5) {
      recommendation = "Recommended";
    } else if (classificationValue == 3) {
      recommendation = "Average";
    } else {
      recommendation = "Not recommended";
    }
  }
}

