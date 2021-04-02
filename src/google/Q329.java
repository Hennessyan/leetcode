package google;

import java.util.LinkedList;
import java.util.Queue;

// Longest Increasing Path in a Matrix
public class Q329 {
    // O(V) = O(mn),O(E) = O(4V) = O(mn)
    // O(mn) O(mn)
    int m, n;
    int[][] res;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath1(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        res = new int[m][n];
        int max = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                max = Math.max(max, dfs(matrix, r, c));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int r, int c) {
        if(res[r][c] != 0) {
            return res[r][c];
        }
        for(int[] d : dirs) {
            int x = r + d[0];
            int y = c + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n && matrix[r][c] < matrix[x][y]) {
                res[r][c] = Math.max(res[r][c], dfs(matrix, x, y));
            }
        }
        return ++res[r][c];
    }
    // peeling the onion (one of topological sort way)
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int[][] out_degree = new int[m][n];
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                for(int[] d : dirs) {
                    int x = r + d[0];
                    int y = c + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && matrix[r][c] < matrix[x][y]) {
                        out_degree[r][c]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(out_degree[r][c] == 0) {
                    queue.add(new int[]{r,c});
                }
            }
        }
        int height = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for(int i = 0; i < size; i++) {
                int[] p = queue.poll();
                for(int[] d : dirs) {
                    int x = p[0] + d[0];
                    int y = p[1] + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && matrix[p[0]][p[1]] > matrix[x][y]) {
                        if(--out_degree[x][y] == 0) {
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return height;
    }
}
