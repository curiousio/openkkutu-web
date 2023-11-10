package com.glowat.openkkutuweb.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtils {

  public static String serializeMean(List<String> means) {
    StringBuilder result = new StringBuilder();
    int count = 0;
    for (String mean : means) {
      count++;
      result.append("＂").append(count).append("＂ ").append(mean).append("  ");
    }
    return result.toString();
  }

  public static List<String> deserializeMean(String mean) {
    List<String> result = new ArrayList<>();
    Matcher matcher = Pattern.compile("＂[0-9]+＂").matcher(mean);
    while (matcher.find()) {
      result.add(mean.substring(matcher.start() + 1, matcher.end() - 1));
    }
    if(result.isEmpty()) {
      return List.of();
    }
    result.remove(0);
    return result.stream()
        .map(it -> it.contains("［") ? "" : it)
        .toList();
  }

}
