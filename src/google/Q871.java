package google;

import java.util.Collections;
import java.util.PriorityQueue;

// Minimum Number of Refueling Stops
public class Q871 {
    // DP : O(n^2) O(n)

    /**
     *
     * A more intuitive state definition could be: "dp[i][j] = at station i, the farthest distance someone can get by making j stops."
     * transition function: dp[i][j] = max( dp[i - 1][j - 1] + station i's fuel, dp[i - 1][j]) for j = 1 to j = i
     *
     * since we only look back for 1 element, we can optimize the dp from [i][j] to dp[j], thus getting the approach 1
     */
    public int minRefuelStops1(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];        // farthest distance can reach with i jump.
        dp[0] = startFuel;

        for(int i = 0; i < n; i++) {
            for(int t = i; t >= 0; t--) {   // must reverse order here!
                if(dp[t] >= stations[i][0]) {
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + stations[i][1]);
                } else break;
            }
        }
        for(int i = 0; i <= n; i++) {
            if(dp[i] >= target) return i;
        }
        return -1;
    }
    // heap : O(nlgn) O(n)
    public int minRefuelStops(int target, int tank, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0, pre = 0;
        for(int[] s : stations) {
            int location = s[0];
            int fuel = s[1];
            tank -= location - pre;
            while(!pq.isEmpty() && tank < 0) {
                tank += pq.poll();
                ans++;
            }
            if(tank < 0) return -1;
            pq.offer(fuel);
            pre = location;
        }
        tank -= target - pre;
        while(!pq.isEmpty() && tank < 0) {
            tank += pq.poll();
            ans++;
        }
        if(tank < 0) return -1;
        return ans;
    }
}
