package amazon;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

// Meeting Rooms II
public class Q253 {
    // O(nlgn) O(n)
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        int len = intervals.length;
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        PriorityQueue<Integer> endQueue = new PriorityQueue<>((a, b) -> a - b);
        endQueue.add(intervals[0][1]);
        for(int i = 1; i < len; i++) {
            if(endQueue.peek() <= intervals[i][0]) {
                endQueue.poll();
            }
            endQueue.add(intervals[i][1]);
        }
        return endQueue.size();
    }
    // O(nlgn) O(n)
    public int minMeetingRooms2(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        int len = intervals.length;
        int[] s = new int[len];
        int[] e = new int[len];
        for(int i = 0; i < len; i++) {
            s[i] = intervals[i][0];
            e[i] = intervals[i][1];
        }
        Arrays.sort(s);
        Arrays.sort(e);
        int count = 1, j = 0;
        for(int i = 1; i < len; i++) {
            if(s[i] < e[j]) {
                count++;
            } else {
                j++;
            }
        }

        return count;
    }
    // TreeMap : O(nlgn) O(n) - slow!
    public int minMeetingRooms3(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        int maxR = 0, maxC = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int[] i : intervals) {
            tm.put(i[0], tm.getOrDefault(i[0], 0) + 1);
            tm.put(i[1], tm.getOrDefault(i[1], 0) - 1);
        }
        for(int key : tm.keySet()) {
            maxR = Math.max(maxR, maxC += tm.get(key));
        }
        return maxR;
    }
}
