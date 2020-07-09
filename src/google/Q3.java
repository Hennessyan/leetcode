package google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Longest Substring Without Repeating Characters
public class Q3 {

    public static void main(String[] args) {
        Q3 q = new Q3();
        System.out.println(q.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(q.lengthOfLongestSubstring("pwwkew")); // 3
    }

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, max = 0;
        while(i < n && j < n) {
            if(!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, i = 0, j = 0, n = s.length();
        while(j < n) {
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                i = Math.max(i, map.get(c));
            }
            max = Math.max(max, j - i + 1);
            map.put(c, ++j);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] map = new int[128];
        int max = 0, i = 0, j = 0, n = s.length();
        while(j < n) {
            char c = s.charAt(j);
            i = Math.max(i, map[c]);
            max = Math.max(max, j - i + 1);
            map[c] =  ++j;
        }
        return max;
    }
}
