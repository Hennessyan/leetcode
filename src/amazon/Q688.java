package amazon;
// Knight Probability in Chessboard
// Q576
public class Q688 {
    // O(n^2k) O(n^2)

    private int[][] dirs = {{-2,-1},{-1,-2},{1,-2},{2,-1},
            {2,1},{1,2},{-1,2},{-2,1}};
    public double knightProbability(int n, int k, int row, int column) {
        double[][] dp = new double[n][n];
        dp[row][column] = 1;
        for(int i = 1; i <= k; i++) {
            double[][] tmp = new double[n][n];
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    for(int[] d : dirs) {
                        int x = r + d[0];
                        int y = c + d[1];
                        if(x >= 0 && x < n && y >= 0 && y < n) {
                            tmp[x][y] += dp[r][c];  // tmp[r][c] += dp[x][y] is correct as well.
                        }
                    }
                }
            }
            dp = tmp;
        }
        double total = 0;
        for(double[] vals : dp) {
            for(double val : vals) {
                total += val;
            }
        }
        // should not use total / (total + out_scope_count)
        return total / Math.pow(8, k);
    }

    public double knightProbability1(int N, int K, int sr, int sc) {
        double[][] dp = new double[N][N];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        dp[sr][sc] = 1;
        for (; K > 0; K--) {
            double[][] dp2 = new double[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < 8; k++) {
                        int cr = r + dr[k];
                        int cc = c + dc[k];
                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                            dp2[cr][cc] += dp[r][c] / 8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double ans = 0.0;
        for (double[] row: dp) {
            for (double x: row) ans += x;
        }
        return ans;
    }
}
