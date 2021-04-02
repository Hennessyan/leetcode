package amazon;

import java.util.Arrays;

// Minimum Number of Arrows to Burst Balloons
public class Q452 {
    // O(nlgn) O(lgn) - quick sort space
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // can't sort based on points[i][0]: [[1, 100], [2,10], [3, 50]].
        Arrays.sort(points, (p1, p2) -> {   // can't use p1[1] - p2[1] directly => integer overflow.
            if(p1[1] < p2[1]) return -1;
            if(p1[1] > p2[1]) return 1;
            return 0;
        });
        int arrows = 1, end = points[0][1];
        for(int[] p : points) {
            if(p[0] > end) {
                arrows++;
                end = p[1];
            }
        }
        return arrows;
    }
}
