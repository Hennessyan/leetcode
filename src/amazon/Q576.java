package amazon;
// Out of Boundary Paths
// Q688
public class Q576 {
    // O(mnM) O(mn)
    public int findPaths(int m, int n, int moves, int x, int y) {
        int[][] dp = new int[m][n];
        dp[x][y] = 1;
        int M = (int) (1e9 + 7);
        int count = 0;
        for(int move = 1; move <= moves; move++) {
            int[][] tmp = new int[m][n];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(r == 0) count = (count + dp[r][c]) % M;
                    if(r == m - 1) count = (count + dp[r][c]) % M;
                    if(c == 0) count = (count + dp[r][c]) % M;
                    if(c == n - 1) count = (count + dp[r][c]) % M;
                    tmp[r][c] = (((r > 0 ? dp[r-1][c] : 0) + (r < m - 1 ? dp[r+1][c] : 0)) % M
                            + ((c > 0 ? dp[r][c-1] : 0) + (c < n - 1 ? dp[r][c+1] : 0)) % M) % M;
                }
            }
            dp = tmp;
        }
        return count;
    }
    // O(mnM) O(mnM)
    int M = (int) (1e9+7);
    public int findPaths1(int m, int n, int moves, int x, int y) {
        Integer[][][] memo = new Integer[m][n][moves+1];    // size of int is less than Integer
        return find(m,n,moves,x,y,memo);
    }
    private int find(int m, int n, int moves, int x, int y, Integer[][][] memo) {
        if(x < 0 || x == m || y < 0 || y == n) {
            return 1;
        }
        if(moves == 0) return 0;
        if(memo[x][y][moves] != null) {
            return memo[x][y][moves];
        }
        memo[x][y][moves] = ((find(m,n,moves-1,x-1,y,memo) + find(m,n,moves-1,x,y-1,memo)) % M +
                (find(m,n,moves-1,x+1,y,memo) + find(m,n,moves-1,x,y+1,memo)) % M) % M;
        return memo[x][y][moves];
    }
}
