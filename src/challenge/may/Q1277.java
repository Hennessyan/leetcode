package challenge.may;

// Count Square Submatrices with All Ones
public class Q1277 {

    public static void main(String[] args) {
        Q1277 q = new Q1277();
        int[][] matrix = {{0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}};
        System.out.println(q.countSquares(matrix));     //10 + 4 + 1
    }

    /*
        首先考虑这个问题的时候,感觉只要RAW, COLUMN, DIAGONAL一样，就可以构成当前最大的SQUARE,但是实际不对:
        1 0 0 1
        1 1 1 1
        1 1 1 1
        1 1 1 1
        ---------------------------
          0 1 1 1      0 1 1 1
          1 1 1 1  =>  1 1 2 2
          0 1 1 1      0 1 2 3
     */
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
}
