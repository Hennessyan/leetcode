package amazon;

import java.util.Arrays;

// Non-overlapping Intervals
public class Q435 {
    // O(nlgn) O(lgn)
    // greedy, sort by end point
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);
        int count = 0, end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }
    // greedy, sort by start point
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        int end = intervals[0][1], prev = 0, count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev][1] > intervals[i][0]) {
                if (intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }
}
