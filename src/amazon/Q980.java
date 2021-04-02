package amazon;
// Unique Paths III
public class Q980 {

    int ans;
    int[][] grid;
    int tr, tc;
    int[] dr = new int[]{0, -1, 0, 1};
    int[] dc = new int[]{1, 0, -1, 0};
    int R, C;
    //backtracking / dfs : O(4*3^mn) O(mn)
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;

        int todo = 0;
        int sr = 0, sc = 0;
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] != -1) {	//注意这里是 != -1
                    todo++;
                }

                if (grid[r][c] == 1) {
                    sr = r;
                    sc = c;
                } else if (grid[r][c] == 2) {
                    tr = r;
                    tc = c;
                }
            }

        ans = 0;
        dfs(sr, sc, todo);
        return ans;
    }

    public void dfs(int r, int c, int todo) {
        todo--;
        if (todo < 0) return;	//不需要,所有点访问过后会被mark,不会有小于0的情况
        if (r == tr && c == tc) {
            if (todo == 0) ans++;
            return;
        }

        grid[r][c] = 3;
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (grid[nr][nc] % 2 == 0)
                    dfs(nr, nc, todo);
            }
        }
        grid[r][c] = 0;
    }
    /*
    int[][] grid;
    int m, n;
    int count;
    int dr, dc;
    public int uniquePathsIII(int[][] grid) {
        count = 0;
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int sr = 0, sc = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] >= 0) {
                    count++;
                    if(grid[i][j] == 1) {
                        sr = i;
                        sc = j;
                    }
                    if(grid[i][j] == 2) {
                        dr = i;
                        dc = j;
                    }
                }
            }
        }
        return dfs(sr, sc, count);
    }
    private int dfs(int r, int c, int count) {
        count--;                //注意COUNT在刚开始就减了
        if(r == dr && c == dc) {
            if(count == 0) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        grid[r][c] = 3;
        for(int[] d : dirs) {
            int x = d[0] + r;
            int y = d[1] + c;
            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] % 2 == 0) { // grid[x][y] % 2 == 0避免了起点被再次访问
                res += dfs(x, y, count);
            }
        }
        grid[r][c] = 0;
        return res;
    }
    int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    */

    //memorization (dp) (重复的机会很小,没必要用,但方法很好)
    //https://www.youtube.com/watch?v=dSXtmaGr4Fc
    //O(m*n*2^(m*n)) O(m*n*2^(m*n)) (有m*n*2^(m*n)个子问题,每个子问题只需要对应4个方向(O(1)),所以是O(m*n*2^(m*n)))
    /*
    int target, er, ec;
    Integer[][][] memo;
    int m = 0, n = 0;
    int[] dr = new int[]{0, -1, 0, 1};
    int[] dc = new int[]{1, 0, -1, 0};
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        target = 0;
        int sr = 0, sc = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] % 2 == 0) {
                    target |= convert(i, j);
                }
                if(grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if(grid[i][j] == 2) {
                    er = i;
                    ec = j;
                }
            }
        }
        memo = new Integer[m][n][1 << m*n];
        return dfs(sr, sc, target);
    }
    private int convert(int i, int j) {
        return 1 << (i*n + j);
    }
    private int dfs(int sr, int sc, int target) {
        if(memo[sr][sc][target] != null) {
            return memo[sr][sc][target];
        }
        if(sr == er && sc == ec) {
            return target == 0 ? 1 : 0;
        }
        int ans = 0;
        for(int i = 0; i < 4; i++) {
            int x = sr + dr[i];
            int y = sc + dc[i];
            if(x >= 0 && x < m && y >= 0 && y < n) {
                if((target & convert(x, y)) > 0) {
                    ans += dfs(x, y, target ^ convert(x, y));
                }
            }
        }
        memo[sr][sc][target] = ans;
        return ans;
    }
    */
}
