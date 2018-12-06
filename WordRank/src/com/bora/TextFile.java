package com.bora;

import java.util.Set;

public class TextFile {

  private String fileName;
  private long rankScore;
  private Set<String> words; // the words in the file, set chosen for O(n) complexity for add / contains operations

  public TextFile(String fileName, int rankScore, Set<String> words) {
    this.fileName = fileName;
    this.rankScore = rankScore;
    this.words = words;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public long getRankScore() {
    return rankScore;
  }

  public void setRankScore(long rankScore) {
    this.rankScore = rankScore;
  }

  public Set<String> getWords() {
    return words;
  }

  public void setWords(Set<String> words) {
    this.words = words;
  }

  /* Calculate the rank score based on the input words to be found in the file divided by the total size of the input words , duplicates ignored */

  public void calculateRankScore(Set<String> inputWordSet) {

    rankScore = 0l;

    long count = inputWordSet.parallelStream()
      .filter(words::contains)
      .count();

    if (count != 0) {
      rankScore = (long)((float)count / inputWordSet.size() * 100);
    }
  }

  /* return the rank score in the pretty format */

  public String prettyRankScore() {
    return fileName + " : " + rankScore + "%";
  }
}
