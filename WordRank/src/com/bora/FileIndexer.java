package com.bora;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIndexer {

  public List<TextFile> indexFiles(File indexableDirectory) {

    List<Path> textPaths = new ArrayList<>();
    List<TextFile> textFiles = new ArrayList<>();

    /* Read the text files from the directory  */

    try {
      textPaths = Files.list(Paths.get(indexableDirectory.toURI()))
        .filter(path ->
          Constants.TEXT_FILE_FORMATS.contains(  // get only text files
            Util.getExtensionByStringHandling(path.getFileName().toString())))
        .collect(Collectors.toList());

      System.out.println(textPaths.size() + " files read in directory " + indexableDirectory.toString());

    } catch (IOException e) {
      throw new RuntimeException("Exception occured : " + e.getMessage());
    }


    /* Read the content of the text files and save them */

    for (Path path : textPaths) {
      try {
        Stream<String> lines = Files.lines(path);

        Set<String> words = lines.parallel()
          .flatMap(line -> Arrays.stream(line.trim().split(" ")))
          .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())  // replace the non-alpha characters and lower the cases
          .filter(word -> word.length() > 0)
          .collect(Collectors.toSet());

        TextFile textFile = new TextFile(path.getFileName().toString(), 0, words);

        textFiles.add(textFile);

      } catch (IOException e) {
        throw new RuntimeException("Exception occured : " + e.getMessage());
      }
    }

    return textFiles;
  }

  /* Check the rank score of the input words in each file */

  public List<TextFile> searchTheWords(String inputWords, List<TextFile> textFiles) {

    inputWords = inputWords.toLowerCase(); // lower the cases for analysis
    String[] inputWordList = inputWords.trim().split(" ");

    Set<String> inputWordSet = new HashSet<>();

    for (String inputWord: inputWordList) {
      inputWordSet.add(inputWord);  // ignore the duplicates
    }

    /* calculate the rank score for each file */

    textFiles.parallelStream()
      .forEach(textFile -> {
          textFile.calculateRankScore(inputWordSet);
        }
      );


    /* print the rank scores for files */
    textFiles.stream()
      .sorted(Comparator.comparing(TextFile::getRankScore).reversed()) // compare based on rank score, put the bigger values on top
      .limit(10)  // get the top 10
      .forEach(textFile ->
        System.out.println(textFile.prettyRankScore()
        )
      );

    return textFiles;
  }


}
