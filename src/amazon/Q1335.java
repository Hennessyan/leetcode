package amazon;
// Minimum Difficulty of a Job Schedule
public class Q1335 {
    // DP: O(d*n*n) O(d*n)
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(n < d) {
            return -1;
        }
        int[][] dp = new int[d][n]; // minimum difficulty for d-th day util n-th job.
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[0][i] = max;
        }
        for(int i = 1; i < d; i++) {        // d天可以切d-1次,可以看作最后一次留下的值从J(N-1)开始.
            for(int j = 1; j < n; j++) {    // dp[i][j] = min(dp[i-1][k]+max(job[k+1]~job[j]))
                int maxJ = jobDifficulty[j];
                int min = dp[i-1][j-1] + maxJ;
                for(int k = j - 2; k >= i - 1; k--) {   // 因此k的下界可以一直到i-1
                    maxJ = Math.max(maxJ, jobDifficulty[k + 1]);
                    min = Math.min(min, dp[i-1][k] + maxJ);
                }
                dp[i][j] = min;
            }
        }
        return dp[d-1][n-1];
    }
}
