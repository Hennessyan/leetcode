package google;
// Number of Closed Islands
public class Q1254 {

    int m, n;
    public int closedIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        System.out.println(m + " " + n);
        int res = 0;
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(grid[i][j] == 0 && dfs(grid, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }
    private boolean dfs(int[][] grid, int i, int j) {
        if(grid[i][j] == 1) {
            return true;
        }
        if(i == 0 || j == 0 || i == m - 1 || j == n - 1) {
            return false;
        }
        grid[i][j] = 1;
        //如果是这样写的话要用位运算(&)而不是(&&).因为&&的话前边有false后边就不接着计算了.
        return dfs(grid, i - 1,j) & dfs(grid,i + 1, j) &
                dfs(grid, i, j - 1) & dfs(grid, i, j + 1);
    }
}
