package amazon;

import java.util.ArrayList;
import java.util.List;

// Partition Labels
public class Q763 {
    // O(n) O(1)
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        int len = S.length();
        int[] last = new int[26];
        for(int i = 0; i < len; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        int end = 0, start = 0;
        for(int i = 0; i < len; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if(i == end) {
                list.add(end - start + 1);
                start = i + 1;
            }
        }
        return list;
    }
}
