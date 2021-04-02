package google;

import java.util.Stack;

// Max Area of Island
public class Q695 {
    // O(mn) O(mn)
    int m, n;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int max = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                max = Math.max(max, dfs(grid, r, c));
            }
        }
        return max;
    }
    private int dfs(int[][] grid, int r, int c) {
        if(r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c)
                + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }

    int[][] dirs ={{-1,0},{1,0},{0,-1},{0,1}};
    public int maxAreaOfIsland1(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int max = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    int shape = 0;
                    grid[r][c] = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{r,c});
                    while(!stack.isEmpty()) {
                        int[] p = stack.pop();
                        shape++;
                        for(int[] d : dirs) {
                            int x = d[0] + p[0];
                            int y = d[1] + p[1];
                            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                                stack.push(new int[]{x,y});
                                grid[x][y] = 0;
                            }
                        }
                    }
                    max = Math.max(max, shape);
                }
            }
        }
        return max;
    }
}
