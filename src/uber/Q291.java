package uber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Word Pattern II
// Q254
public class Q291 {

    public static void main(String[] args) {
        Q291 q = new Q291();
        System.out.println(q.wordPatternMatch("abab", "redblueredblue"));   //true
    }

    // O(n^m) => Cn-m   O(m)
    // m - pattern.length()
    // n - str.length()
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || str == null || pattern.length() > str.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> dup = new HashSet<>();
        return isMatch(pattern, str, map, dup);
    }

    private boolean isMatch(String pattern, String str, Map<Character, String> map, Set<String> dup) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }
        char c = pattern.charAt(0);
        if (map.containsKey(c)) {
            String word = map.get(c);
            int len = word.length();
            if (str.length() < len) {  // avoid index OutOfBound
                return false;
            }
            if (!word.equals(str.substring(0, len))) {
                return false;
            }
            return isMatch(pattern.substring(1), str.substring(len), map, dup);
        } else {
            for (int i = 1; i <= str.length(); i++) {
                String word = str.substring(0, i);
                if (dup.contains(word)) {
                    continue;   // not false
                }
                dup.add(word);
                map.put(c, word);
                if (isMatch(pattern.substring(1), str.substring(i), map, dup)) {
                    return true;
                }
                dup.remove(word);
                map.remove(c);
            }
        }
        return false;
    }
}
