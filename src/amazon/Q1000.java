package amazon;

import java.util.Arrays;

// Minimum Cost to Merge Stones
public class Q1000 {
    // O(n^3) O(n^2*k)
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if((len - 1) % (K - 1) != 0) {
            return -1;
        }
        // min cost that merge from i to j into k piles.
        int[][][] dp = new int[len][len][K+1];
        int[] sum = new int[len + 1];
        int INF = (int)1e9;
        for(int[][] tmp : dp) {
            for(int[] row : tmp) {
                Arrays.fill(row, INF);
            }
        }
        for(int i = 0; i < len; i++) {
            dp[i][i][1] = 0;
            sum[i + 1] = sum[i] + stones[i];
        }

        for(int l = 2; l <= len; l++) {
            for(int i = 0; i <= len - l; i++) {
                int j = i + l - 1;
                for(int k = 2; k <= K; k++) {
                    for(int m = i; m < j; m += K - 1) { // m += K - 1 guarantees previous one can merge into one pile
                        dp[i][j][k] = Math.min(dp[i][j][k],
                                dp[i][m][1] + dp[m+1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum[j+1] - sum[i];
            }
        }
        return dp[0][len-1][1];
    }

    //O(n^3/K) O(n^2)
    public int mergeStones1(int[] stones, int K) {
        int len = stones.length;
        if((len - 1) % (K - 1) > 0) {	//需要把stones分为若干个长度为K-1的堆以及一个长度为1的堆.
            return -1;					//这样才是有解的情况:可以多次合并(每次长度为K)
        }
        int INF = (int)1e9;
        int[][] dp = new int[len][len];	//min cost to merge subarray i~j into (l-1) % (K-1) + 1 piles.
        for(int[] row : dp) {
            Arrays.fill(row, INF);
        }
        int[] sum = new int[len+1];
        for(int i = 0; i < len; i++) {
            sum[i+1] = sum[i] + stones[i];
            dp[i][i] = 0;
        }
        for(int l = 2; l <= len; l++) {				//subproblem length
            for(int i = 0; i <= len - l; i++) {		//start
                int j = i + l - 1;					//end
                for(int m = i; m < j; m += K - 1) {	//split point (因为只有在K-1和1merge的时候才有解,因此每次+(K-1)的话,左边才一直能保证是K)
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m+1][j]);
                }
                if((l-1) % (K - 1) == 0) {
                    dp[i][j] += + sum[j+1] - sum[i];
                }
            }
        }
        return dp[0][len-1];
    }
}
