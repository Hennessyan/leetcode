package amazon;
// Minimum Path Sum
public class Q64 {
    // O(mn) O(1)
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(r == 0 && c == 0) {
                    dp[c] = grid[r][c];
                } else if(r == 0) {
                    dp[c] = dp[c - 1] + grid[r][c];
                } else if(c == 0) {
                    dp[c] += grid[r][c];
                } else {
                    dp[c] = Math.min(dp[c - 1], dp[c]) + grid[r][c];
                }
            }
        }
        return dp[n-1];
    }
}
