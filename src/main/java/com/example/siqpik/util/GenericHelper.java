package com.example.siqpik.util;

import java.util.stream.Stream;

public class GenericHelper {

  public static boolean isNotNullNorEmpty(String str) {
    return str != null && !str.isEmpty();
  }

  public static boolean areNotNullNorEmpty(String... strs) {
    return Stream.of(strs)
        .allMatch(GenericHelper::isNotNullNorEmpty);
  }
}
