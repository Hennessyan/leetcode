package amazon;

import java.util.HashSet;
import java.util.Set;

// Number of Distinct Islands
public class Q694 {
    // O(mn) O(mn)
    //使用method1不需要找到中断位置,因为计算的相对位置保证了只能代表一种情况.
    //使用method2不用计算所以更快,但是得确定中断位置，否则结果可能代表不同情况.
    //method1 : dfs + hash table : O(m*n) O(m*n)
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, i, j, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, int i2, int j2, StringBuilder sb) {
        int m = grid.length, n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        sb.append((i - i2) + "-" + (j - j2) + "|");
        dfs(grid, i - 1, j, i2, j2, sb);
        dfs(grid, i + 1, j, i2, j2, sb);
        dfs(grid, i, j - 1, i2, j2, sb);
        dfs(grid, i, j + 1, i2, j2, sb);
    }

    int m, n;
    Set<String> shape;
    public int numDistinctIslands2(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        shape = new HashSet<>();

        for(int r = 0; r < m; r ++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, r, c, sb, 'o');
                    shape.add(sb.toString());
                }
            }
        }
        return shape.size();
    }
    private void dfs(int[][] grid, int r, int c, StringBuilder sb, char direction) {
        if(r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0) {
            return;
        }
        grid[r][c] = 0;
        sb.append(direction);
        dfs(grid, r + 1, c, sb, 'd');
        dfs(grid, r - 1, c, sb, 'u');
        dfs(grid, r, c - 1, sb, 'l');
        dfs(grid, r, c + 1, sb, 'r');
        sb.append('|');
    }
}
