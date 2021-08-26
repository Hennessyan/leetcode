package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Flood Fill
public class Q733 {
    // BFS
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        int m = image.length, n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int color = image[sr][sc];
        queue.add(new int[]{sr, sc});

        while(!queue.isEmpty()) {
            int[] t = queue.poll();
            image[t[0]][t[1]] = newColor;
            for(int[] d : dirs) {
                int x = d[0] + t[0];
                int y = d[1] + t[1];
                if(x >= 0 && x < m && y >= 0 && y < n && image[x][y] == color) {
                    queue.add(new int[]{x, y});
                }
            }
        }
        return image;
    }
    // DFS : O(n) O(n)
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        int color = image[sr][sc];
        dfs(image, sr, sc, color, newColor);
        return image;
    }
    private void dfs(int[][] image, int r, int c, int color, int newColor) {
        if(image[r][c] == color) {
            image[r][c] = newColor;

            if(r >= 1) dfs(image, r - 1, c, color, newColor);
            if(r < image.length - 1) dfs(image, r + 1, c, color, newColor);
            if(c >= 1) dfs(image, r, c - 1, color, newColor);
            if(c < image[0].length - 1) dfs(image, r, c + 1, color, newColor);
        }
    }
}
