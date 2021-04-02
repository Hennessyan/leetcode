package microsoft;

import java.util.Arrays;

// Valid Anagram
public class Q242 {
    // O(nlgn) O(1)
    public boolean isAnagram0(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();  // actually make a copy of string, so it's O(n)
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);              // if we use heapsort, the space complexity is O(1). For java, it's O(n) actually.
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
    // O(n) O(1) - faster than isAnagram2
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
    // O(n) O(1)
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
