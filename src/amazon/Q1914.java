package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Cyclically Rotating a Grid
public class Q1914 {
    // O(mn + min(m,n) * k) O(m+n)
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < Math.min(m / 2, n / 2); i++) {
            int count = k % ((m-1-2*i + n-1-2*i)*2); // can't use k = k % ...
            // if(count == 0) break;                 // can't break here !!!
            rotate(grid, i, m, n, count);
        }
        return grid;
    }
    private void rotate(int[][] grid, int i, int m, int n, int k) {
        while(k-- > 0) {
            int cur = grid[i][i];
            for(int c = i; c < n - 1 - i; c++)  {
                grid[i][c] = grid[i][c+1];
            }
            for(int r = i; r < m - 1 - i; r++) {
                grid[r][n - 1 - i] = grid[r + 1][n - 1 - i];
            }
            for(int c = n - 1 - i; c > i; c--) {
                grid[m - 1 - i][c] = grid[m - 1 - i][c - 1];
            }
            for(int r = m - 1 - i; r > i; r--) {
                grid[r][i] = grid[r-1][i];
            }
            grid[i+1][i] = cur;
        }
    }

    private void rotate1(int[][] grid, int i, int m, int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for(int c = i; c < n - 1 - i; c++)  {
            queue.add(grid[i][c]);
        }
        for(int r = i; r < m - 1 - i; r++) {
            queue.add(grid[r][n - 1 - i]);
        }
        for(int c = n - 1 - i; c > i; c--) {
            queue.add(grid[m - 1 - i][c]);
        }
        for(int r = m - 1 - i; r > i; r--) {
            queue.add(grid[r][i]);
        }
        while(k-- > 0) {
            queue.add(queue.poll());
        }
        for(int c = i; c < n - 1 - i; c++)  {
            grid[i][c] = queue.poll();
        }
        for(int r = i; r < m - 1 - i; r++) {
            grid[r][n - 1 - i] = queue.poll();
        }
        for(int c = n - 1 - i; c > i; c--) {
            grid[m - 1 - i][c] = queue.poll();
        }
        for(int r = m - 1 - i; r > i; r--) {
            grid[r][i] = queue.poll();
        }
    }
}
