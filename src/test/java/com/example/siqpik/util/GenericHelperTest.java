package com.example.siqpik.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenericHelperTest {

  @Test
  public void testIsNotNullNorEmpty() {
    String case1 = null;
    String case2 = "";
    String case3 = "Hallo";

    assertFalse(GenericHelper.isNotNullNorEmpty(case2));
    assertFalse(GenericHelper.isNotNullNorEmpty(case1));
    assertTrue(GenericHelper.isNotNullNorEmpty(case3));
  }

  @Test
  public void testAreNotNullNorEmpty() {
    String[] strs = {"", ""};
    assertFalse(GenericHelper.areNotNullNorEmpty(strs));

    String[] strs2 = {"2", ""};
    assertFalse(GenericHelper.areNotNullNorEmpty(strs2));

    String[] strs3 = {"2", "3"};
    assertTrue(GenericHelper.areNotNullNorEmpty(strs3));

    String[] strs4 = {"2", null};
    assertFalse(GenericHelper.areNotNullNorEmpty(strs4));
  }
}
