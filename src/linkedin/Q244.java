package linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Shortest Word Distance II
// amazon
public class Q244 {
    // O(n) O(n)
    class WordDistance {
        Map<String, List<Integer>> map;
        public WordDistance(String[] wordsDict) {
            map = new HashMap<>();
            for(int i = 0; i < wordsDict.length; i++) {
                map.computeIfAbsent(wordsDict[i], x -> new ArrayList<>()).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2 = map.get(word2);
            int i = 0, j = 0, m = l1.size(), n = l2.size(), diff = Integer.MAX_VALUE;
            while(i < m && j < n) {
                int i1 = l1.get(i);
                int i2 = l2.get(j);
                if(i1 < i2) {
                    diff = Math.min(diff, i2 - i1);
                    i++;
                } else {
                    diff = Math.min(diff, i1 - i2);
                    j++;
                }
            }
            return diff;
        }
    }
}
