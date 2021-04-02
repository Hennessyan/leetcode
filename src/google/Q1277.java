package google;
// Count Square Submatrices with All Ones
public class Q1277 {
    // O(m*n) O(m*n) / O(1)
    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int sum = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    sum += dp[i][j];
                }
            }
        }
        return sum;
    }
    // matrix[i - 1][j - 1] != 0 && matrix[i - 1][j] != 0 && matrix[i][j - 1] != 0 is not necessary
    public int countSquares1(int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1 && i >= 1 && j >= 1 &&
                        matrix[i - 1][j - 1] != 0 && matrix[i - 1][j] != 0 && matrix[i][j - 1] != 0) {
                    matrix[i][j] = Math.min(Math.min( matrix[i - 1][j - 1], matrix[i - 1][j]), matrix[i][j - 1]) + 1;
                }
                result += matrix[i][j];
            }
        }
        return result;
    }
}
