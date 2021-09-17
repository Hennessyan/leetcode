package amazon;

import java.util.Arrays;

// Maximum Number of Events That Can Be Attended II
// Q1235
public class Q1751 {

    private int findMax(int[][] events) {
        int max = 0;
        for(int[] e : events) {
            max = Math.max(max, e[2]);
        }
        return max;
    }

    // O(nlgn + nk) O(nk)
    // dp[n][k] - max value for attend k events from previous n events
    public int maxValue(int[][] events, int k) {
        if(k == 1) {
            return findMax(events);
        }
        int n = events.length;
        Arrays.sort(events, (e1, e2) -> e1[1] - e2[1]);
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++) {
            int pre = binarysearch0(events, i);
            for(int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[pre][j-1] + events[i - 1][2], dp[i-1][j]);
            }
        }

        return dp[n][k];
    }
    // this method will improve a lot compared with below binarysearch() method.
    private int binarysearch0(int[][] events, int index) {
        int l = 0, r = index - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(events[m][1] < events[index - 1][0]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public int maxValue1(int[][] events, int k) {
        int n = events.length;
        if(k == 1) {
            return findMax(events);
        }
        Arrays.sort(events, (e1, e2) -> e1[1] - e2[1]);
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++) {
            int pre = binarysearch(events, i - 1);
            for(int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[pre][j-1] + events[i - 1][2], dp[i-1][j]);
            }
        }

        return dp[n][k];
    }
    private int binarysearch(int[][] events, int index) {
        int l = 0, r = index - 1, res = -1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(events[m][1] < events[index][0]) {   // not <=
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res + 1;
    }
}
