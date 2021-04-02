package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Merge Intervals
public class Q56 {
    // O(nlgn) O(n)
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        List<int[]> list = new ArrayList<>();
        int[] cur = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            if(cur[1] < intervals[i][0]) {
                list.add(cur);
                cur = intervals[i];
            } else {
                cur[1] = Math.max(cur[1], intervals[i][1]);
            }
        }
        list.add(cur);
        return list.toArray(new int[list.size()][2]);
    }
}
