package microsoft;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// Car Pooling
public class Q1094 {
    // simiar to Q253 method1 : O(nlgn) O(n)
    public boolean carPooling(int[][] trips, int capacity) {
        if(trips == null || trips.length == 0) {
            return true;
        }
        int len = trips.length;
        Arrays.sort(trips, (t1, t2) -> t1[1] - t2[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(len, (i1, i2) -> trips[i1][2] - trips[i2][2]);
        for(int i = 0; i < len; i++) {
            while(!pq.isEmpty() && trips[pq.peek()][2] <= trips[i][1]) {
                capacity += trips[pq.poll()][0];
            }
            capacity -= trips[i][0];
            if(capacity < 0) {
                return false;
            }
            pq.offer(i);
        }
        return true;
    }
    // treemap : O(nlgn) O(n)
    public boolean carPooling2(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new TreeMap<>();
        for (int[] trip : trips) {
            int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
            timestamp.put(trip[1], startPassenger);

            int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
            timestamp.put(trip[2], endPassenger);
        }
        int usedCapacity = 0;
        for (int passengerChange : timestamp.values()) {
            usedCapacity += passengerChange;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }
    // O(max(n, 1001)) O(1) - 0 <= start <= end <= 1000
    public boolean carPooling3(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int usedCapacity = 0;
        for (int number : timestamp) {
            usedCapacity += number;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }
}
