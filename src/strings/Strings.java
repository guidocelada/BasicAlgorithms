package strings;

import java.util.HashMap;

public class Strings {

  /**
   * Given two strings, write a method to decide if one is a permutation of the
   * other. Example: "hello" is a permutation of "lleho"
   * 
   */
  public static boolean isPermutation(String s1, String s2) {
    if (s1.length() != s2.length())
      return false;

    HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
    HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();

    for (int i = 0; i < s1.length(); i++) {
      if (map1.containsKey(s1.charAt(i)))
        map1.put(s1.charAt(i), map1.get(s1.charAt(i)) + 1);
      else
        map1.put(s1.charAt(i), 1);

      if (map2.containsKey(s2.charAt(i)))
        map2.put(s2.charAt(i), map2.get(s2.charAt(i)) + 1);
      else
        map2.put(s2.charAt(i), 1);
    }

    return (map1.equals(map2));
  }

  public static String replaceWhiteSpace(String s) {
    char[] charArray = s.toCharArray();
    int i = charArray.length - 1;
    int j = i;

    while (i >= 0 && charArray[i] == ' ')
      // skip the whites
      i--;

    while (i >= 0) {
      while (i >= 0 && charArray[i] != ' ') { // move the word to the end
        charArray[j] = charArray[i];
        i--;
        j--;
      }

      if (i >= 0) { // replace whitespace with “%20”
        charArray[j] = '0';
        j--;
        charArray[j] = '2';
        j--;
        charArray[j] = '%';
        j--;
        i--;
      }
    }

    return String.valueOf(charArray);
  }

  public static String compress(String s) {
    if (s == null || s.isEmpty())
      return s;

    char[] charArray = s.toCharArray();
    StringBuilder retString = new StringBuilder();

    char auxChar = charArray[0];
    int count = 0;

    for (char c : charArray) {
      if (c == auxChar) {
        count++;
      } else {
        retString.append(auxChar + String.valueOf(count));
        auxChar = c;
        count = 1;
      }
    }
    retString.append(auxChar + String.valueOf(count));

    if (retString.length() < s.length())
      return retString.toString();
    else
      return s;
  }
  
  public static int countSubstring(String literal, String substring) {
    int i = 0;
    int j = 0;
    int count = 0;

    while (i < literal.length()) {
      if (literal.charAt(i) == substring.charAt(j) || literal.charAt(i) == substring.charAt(0)) {
        if (literal.charAt(i) == substring.charAt(0))
          j = 1;
        else
          j++;
        
        i++;
        if (j == substring.length()) {
          count++;
          j = 0;
        }
      } else {
        i++;
        j = 0;
      }
    }

    return count;
  }
  
  public static void main(String[] args) {
    System.out.println(countSubstring("cdecdecdecdecdecdecddcdex","cde"));
  }
  
  
}