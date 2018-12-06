package com.bora;

import java.util.Optional;

public class Util {

  private Util() {}

  /* Get the extension of the file */

  public static String getExtensionByStringHandling(String filename) {
    return Optional.ofNullable(filename)
      .filter(f -> f.contains("."))
      .map(f -> f.substring(filename.lastIndexOf('.') + 1).toLowerCase()) // get the remaining part after the last period
      .orElse("");
  }
}
