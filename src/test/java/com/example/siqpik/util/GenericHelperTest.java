package com.example.siqpik.util;

import com.example.siqpik.domain.User;

public class GenericHelper {

  private Boolean requestIsValid(User user){
    return isNotNullNorEmpty(user.getUserName()) &&
        isNotNullNorEmpty(user.getPassword()) &&
        isNotNullNorEmpty(user.getEmail());
  }

  protected static boolean isNotNullNorEmpty(String str) {
    return str != null && !str.isEmpty();
  }
}
