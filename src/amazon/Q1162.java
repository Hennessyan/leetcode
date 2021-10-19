package amazon;

import java.util.LinkedList;
import java.util.Queue;

// As Far from Land as Possible
public class Q1162 {
    // BFS : O(n^2) O(n^2)
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        boolean[][] seen = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
        if(queue.size() == 0 || queue.size() == n * n) return -1;
        int dist = -1;  // last loop will find no next node which will increase the dist as well !
        while(!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            for(int i = 0; i < size; i++) {
                int[] t = queue.poll();
                for(int[] d : dirs) {
                    int x = d[0] + t[0];
                    int y = d[1] + t[1];
                    if(x >= 0 && x < n && y >= 0 && y < n && !seen[x][y]) {
                        seen[x][y] = true;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return dist;
    }
    // DP
    public int maxDistance1(int[][] grid) {
        int n = grid.length;
        int[][] arr = grid.clone();
        int max = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(arr[r][c] == 1) continue;
                arr[r][c] = 200;
                if(r > 0) arr[r][c] = Math.min(arr[r][c], arr[r - 1][c] + 1);
                if(c > 0) arr[r][c] = Math.min(arr[r][c], arr[r][c - 1] + 1);
            }
        }

        for(int r = n - 1; r >= 0; r--) {
            for(int c = n - 1; c >= 0; c--) {
                if(arr[r][c] == 1) continue;
                if(r + 1 < n) arr[r][c] = Math.min(arr[r][c], arr[r + 1][c] + 1);
                if(c + 1 < n) arr[r][c] = Math.min(arr[r][c], arr[r][c + 1] + 1);
                max = Math.max(max, arr[r][c]);
            }
        }
        // all 1 return 0 - 1 => -1
        // all 0 => max = 200 => -1
        // rest case => max - 1
        return max == 200 ? -1 : max - 1;
    }
}
