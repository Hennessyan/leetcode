package amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Longest Substring Without Repeating Characters
public class Q3 {

    public int lengthOfLongestSubstring0(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int max = 0, l = 0, r = 0, n = s.length();
        int[] chs = new int[128];
        while(r < n) {
            char c = s.charAt(r);
            if(++chs[c] == 1) {
                max = Math.max(max, r - l + 1);
            } else {
                while(l <= r && chs[c] != 1) {
                    chs[s.charAt(l++)]--;
                }
            }
            r++;
        }
        return max;
    }
    // O(n) O(min(m,n))
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, max = 0;
        while(i < n && j < n) { // i < n is not necessary, it can't exceed j.
            if(!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }
    // O(n) O(min(m,n))
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
    // O(n) O(m) m - length of array -> O(1)
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
