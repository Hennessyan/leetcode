package google;

import java.util.Arrays;

// Knight Dialer
public class Q935 {

    public int knightDialer(int N) {
        int[][] graph = {{4, 6}, {6, 8}, {7, 9}, {4, 8},
                {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);  // 1th
        int old = 0;
        int mode = (int)1e9 + 7;    // large number need mode
        while(--N > 0) {            // (n-1)th
            int second = old ^ 1;
            for(int i = 0; i < 10; i++) {
                dp[second][i] = 0;     // reset new array
                for(int j : graph[i]) {
                    dp[second][i] = (dp[second][i] + dp[old][j]) % mode;
                }
            }
            old = second;
        }
        long res = 0;
        for(int val : dp[old]) {
            res += val;
        }
        return (int) (res % mode);
    }
}
