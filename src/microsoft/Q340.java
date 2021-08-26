package microsoft;

import java.util.LinkedHashMap;
import java.util.Map;

// Longest Substring with At Most K Distinct Characters
public class Q340 {
    // method1 : O(n) O(1) - ask the scope of characters.
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s.length() <= k) {
            return s.length();
        }
        int[] map = new int[128];
        int l = 0, r = 0, n = s.length(), max = 0,count = 0;;
        while(r < n) {
            char c = s.charAt(r++);
            if(map[c]++ == 0) {
                count++;
            }
            while(count > k) {
                if(--map[s.charAt(l++)] == 0) {
                    count--;
                }
            }
            max = Math.max(max, r - l);
        }
        return max;
    }

    //O(n) O(k)
    //ordered dictionary:LinkedHashMap
    //如果key-value重复出现,LinkedHashMap记录首次出现的位置.
    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if(s == null || k == 0) {
            return 0;
        }
        int len = s.length();
        if(len < k) {
            return len;
        }
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(k + 1);
        int max = 0, l = 0, r = 0;
        while(r < len) {
            char c = s.charAt(r);
            if(map.containsKey(c)) {	//不能少,不然针对abaccc的case,会变成a-2 b-1 c-3,然后remove掉a-2
                map.remove(c);
            }
            map.put(c, r++);
            if(map.size() > k) {
                Map.Entry<Character, Integer> entry = map.entrySet().iterator().next();
                map.remove(entry.getKey());
                l = entry.getValue() + 1;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }
}
