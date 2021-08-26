package facebook;
// Range Sum Query 2D - Immutable
// Q818
public class Q304 {
    // SC : O(mn)
    class NumMatrix {
        int[][] memo;
        // TC : O(mn)
        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            memo = new int[m + 1][n + 1];
            for(int r = 1; r <= m; r++) {
                for(int c = 1; c <= n; c++) {
                    memo[r][c] = matrix[r-1][c-1] + memo[r-1][c] + memo[r][c-1] - memo[r-1][c-1];
                }
            }
        }
        // TC : O(1)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return memo[row2+1][col2+1] + memo[row1][col1]
                    - memo[row1][col2+1] - memo[row2+1][col1];
        }
    }


}
