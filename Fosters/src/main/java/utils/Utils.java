package utils;

public class Utils {
  public static boolean includes(String[] arr, String val) {
    for (String s : arr) {
      if (s.equals(val)) return true;
    }

    return false;
  }
}
