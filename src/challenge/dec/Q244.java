package challenge.dec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Shortest Word Distance II
public class Q244 {

    class WordDistance {
        //SC:O(n)
        Map<String, List<Integer>> map;
        public WordDistance(String[] words) {
            map = new HashMap<>();
            for(int i = 0; i < words.length; i++) {
                map.computeIfAbsent(words[i], x -> new ArrayList<>()).add(i);
            }
        }
        //TC: O(len1 + len2) 比两个for循环嵌套的O(len1*len2)快 => O(n) ?
        public int shortest(String word1, String word2) {
            int res = Integer.MAX_VALUE;
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            for(int i = 0, j = 0; i < list1.size() && j < list2.size();){
                int index1 = list1.get(i);
                int index2 = list2.get(j);
                if(index1 < index2){
                    res = Math.min(res, index2 - index1);
                    i++;
                }else{
                    res = Math.min(res, index1 - index2);
                    j++;
                }
            }
            return res;
        }
    }
}
