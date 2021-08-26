package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Meeting Scheduler
public class Q1229 {
    // O(mlgm+nlgn) O(1), but SC: O(lgm+lgn) - for sort actually
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int p1 = 0, p2 = 0;

        while (p1 < slots1.length && p2 < slots2.length) {
            // find the boundaries of the intersection, or the common slot
            int l = Math.max(slots1[p1][0], slots2[p2][0]);
            int r = Math.min(slots1[p1][1], slots2[p2][1]);
            if (r - l >= duration) {
                return new ArrayList<Integer>(Arrays.asList(l, l + duration));
            }
            // always move the one that ends earlier
            if (slots1[p1][1] < slots2[p2][1]) {
                p1++;
            } else {
                p2++;
            }
        }
        return new ArrayList<Integer>();
    }
    // faster than method1 : O((m+n)lg(m+n)) O(m+n)
    public List<Integer> minAvailableDuration1(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> timeslots = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int[] s : slots1) {
            if(s[1] - s[0] >= duration) {
                timeslots.offer(s);
            }
        }
        for(int[] s : slots2) {
            if(s[1] - s[0] >= duration) {
                timeslots.offer(s);
            }
        }
        while(timeslots.size() > 1) {
            int[] s = timeslots.poll();
            int[] e = timeslots.peek();
            if(s[1] - e[0] >= duration) {
                return Arrays.asList(e[0], e[0] + duration);
            }
        }
        return new ArrayList<Integer>();
    }
}
