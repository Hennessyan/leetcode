package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Shortest Path to Get Food
public class Q1730 {
    // O(mn) O(min(m,n))
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length, foods = 0;

        Queue<int[]> queue = new LinkedList<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == '*') {
                    queue.add(new int[]{r, c});
                    grid[r][c] = 'X';
                } else if(grid[r][c] =='#') {
                    foods++;
                }
            }
        }
        if(foods == 0) return -1;
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] p = queue.poll();

                for(int[] d : dirs) {
                    int x = p[0] + d[0];
                    int y = p[1] + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X') {
                        if(grid[x][y] == '#') {
                            return step + 1;
                        }
                        queue.add(new int[]{x, y});
                        grid[x][y] = 'X';
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
