package google;

import java.util.LinkedList;
import java.util.Queue;

// Shortest Distance from All Buildings
public class Q317 {
    // BFS : O((mn)^2) O(mn) - worst case : half 0 and half 1
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int[][] grid, dist;
    int m, n;
    public int shortestDistance(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid.clone();
        this.dist = new int[m][n];
        int level = 0, total = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    total--;
                    bfs(r, c, level--);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(this.grid[r][c] == total && dist[r][c] < min) {
                    min = dist[r][c];
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private void bfs(int r, int c, int level) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for(int i = 0; i < size; i++) {
                int[] t = queue.poll();
                for(int[] d : dirs) {
                    int x = d[0] + t[0];
                    int y = d[1] + t[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && this.grid[x][y] == level) {
                        this.grid[x][y]--;
                        this.dist[x][y] += step;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
}
