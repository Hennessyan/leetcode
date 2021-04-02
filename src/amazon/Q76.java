package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

// Minimum Window Substring
public class Q76 {
    // O(S+T) O(S+T)
    public String minWindow(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> smap = new HashMap<>();
        for(char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }

        List<Pair<Character, Integer>> list = new ArrayList<>();    // optimization
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(tmap.containsKey(c)) {
                list.add(new Pair(c, i));
            }
        }

        int[] ans = {Integer.MAX_VALUE, 0, 0};
        int count = 0, l = 0, r = 0;
        while(r < list.size()) {
            Pair<Character, Integer> p = list.get(r);
            char c = p.getKey();
            int end = p.getValue();
            int total = smap.getOrDefault(c, 0) + 1;
            smap.put(c, total);
            if(total == tmap.get(c)) {
                count++;
            }

            while(count == tmap.size() && l <= r) {
                Pair<Character, Integer> tmp = list.get(l);
                char tmpC = tmp.getKey();
                int start = tmp.getValue();
                if(end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                int tmpTotal = smap.getOrDefault(tmpC, 0) - 1;
                smap.put(tmpC, tmpTotal);
                if(tmpTotal < tmap.get(tmpC)) {
                    count--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == Integer.MAX_VALUE ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
