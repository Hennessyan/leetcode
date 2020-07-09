package google;

// Unique Paths II
public class Q63 {

    public static void main(String[] args) {
        Q63 q = new Q63();
        int[][] obs = {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};
        System.out.println(q.uniquePathsWithObstacles(obs));
    }
    // O(m*n) O(min(m,n))
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 1) {
                    dp[i] = 0;
                } else if (i > 0) {
                    dp[i] += dp[i - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
