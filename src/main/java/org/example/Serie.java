package org.example;

public class Serie  extends Title implements Classification {
  private int seasons;
  private int episodes;
  private int episodeDuration;

  public int getEpisodeDuration() {
    return episodeDuration;
  }

  public void setEpisodeDuration(int episodeDuration) {
    this.episodeDuration = episodeDuration;
  }

  public int getEpisodes() {
    return episodes;
  }

  public void setEpisodes(int episodes) {
    this.episodes = episodes;
  }

  public int getSeasons() {
    return seasons;
  }

  public void setSeasons(int seasons) {
    this.seasons = seasons;
  }

  @Override
  public int getDuration() {
    return getSeasons() * getEpisodes() * getEpisodeDuration();
  }

  @Override
  public int getDurationInMinutes() {
    return getSeasons() * getEpisodes() * getEpisodeDuration();
  }

  @Override
  public int classify() {
    if (getTotalReviews() >= 9) {
      return 5;
    } else if (getTotalReviews() >= 7) {
      return 4;
    } else if (getTotalReviews() >= 5) {
      return 3;
    } else if (getTotalReviews() >= 3) {
      return 2;
    } else {
      return 1;
    }
  }
}

