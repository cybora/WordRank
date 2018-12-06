package com.bora;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("​No directory given to index.");
    }

    final File indexableDirectory = new File(args[0]);

    List<Path> textFiles = new ArrayList<>();

    FileIndexer fileIndexer = new FileIndexer();
    List<TextFile> indexedFiles = fileIndexer.indexFiles(indexableDirectory);

    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.print("​search> ");
      final String line = keyboard.nextLine();
      fileIndexer.searchTheWords(line, indexedFiles);
    }
  }
}
