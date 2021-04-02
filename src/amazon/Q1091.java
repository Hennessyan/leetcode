package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Shortest Path in Binary Matrix
public class Q1091 {
    // O(n) O(n)
    int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int m, n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0][0] == 1) {
            return -1;
        }
        this.m = grid.length;
        this.n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        grid[0][0] = 1;
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int r = val / n;
            int c = val % n;
            int dist = grid[r][c];
            if(r == m - 1 && c == n - 1) {
                return dist;
            }
            for(int[] d : dirs) {
                int x = r + d[0];
                int y = c + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    grid[x][y] = dist + 1;
                    queue.add(x * n + y);
                }
            }
        }
        return -1;
    }
}
