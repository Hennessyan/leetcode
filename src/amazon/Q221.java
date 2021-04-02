package amazon;
// Maximal Square
public class Q221 {
    // O(m*n) O(n)
    public int maximalSquare(char[][] matrix) {
        int maxLen = 0;
        int m = matrix.length, n = m == 0 ? 0 : matrix[0].length;
        int[] dp = new int[n + 1];
        // int pre = 0; // pre can be here rather than L11, but L11 is more reasonable.
        for(int i = 1; i <= m; i++) {
            int pre = 0;
            for(int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if(matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), pre) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                pre = tmp;
            }
        }
        return maxLen * maxLen;
    }

}
