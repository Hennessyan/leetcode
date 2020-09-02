package challenge.aug;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Find Right Interval
public class Q436 {
    // Sort + Binary search : O(nlgn) O(n)
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len];
        Map<int[], Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            map.put(intervals[i], i);
        }
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        for(int i = 0; i < len; i++) {
            int[] tmp = search(intervals, intervals[i][1], i + 1);
            res[map.get(intervals[i])] = map.getOrDefault(tmp, -1);
        }
        return res;
    }
    private int[] search(int[][] intervals, int val, int l) {
        int r = intervals.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(intervals[m][0] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l == intervals.length ? new int[]{-1, -1} : intervals[l];
    }
    // O(nlgn) O(n)
    public int[] findRightInterval1(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < len; i++) {
            map.put(intervals[i][0], i);    // n * lgn
        }
        for(int i = 0; i < len; i++) {
            Map.Entry<Integer, Integer> pos = map.ceilingEntry(intervals[i][1]);    // n * lgn
            res[i] = pos == null ? -1 : pos.getValue();
        }
        return res;
    }
    // O(nlgn) O(n)
    public int[] findRightInterval2(int[][] intervals) {
        int[][] endIntervals = Arrays.copyOf(intervals, intervals.length);
        HashMap<int[], Integer> hash = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(endIntervals, (a, b) -> a[1] - b[1]);
        int j = 0;
        int[] res = new int[intervals.length];
        // go through intervals and endIntervals only once
        for (int i = 0; i < endIntervals.length; i++) {
            while (j < intervals.length && intervals[j][0] < endIntervals[i][1]) {
                j++;
            }
            res[hash.get(endIntervals[i])] = j == intervals.length ? -1 : hash.get(intervals[j]);
        }
        return res;
    }
}
