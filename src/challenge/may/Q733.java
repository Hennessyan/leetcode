package challenge.may;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Flood Fill
public class Q733 {

    public static void main(String[] args) {
        Q733 q = new Q733();
        int[][] image = {{1,1,1}, {1,1,0}, {1,0,1}};
        int[][] res = q.floodFill(image, 1,1, 2);
        for(int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }

    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Queue<Integer> queue = new LinkedList<>();
        int m = image.length, n = image[0].length, color = image[sr][sc];
        queue.add(sr * n + sc);
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int x0 = val / n;
            int y0 = val % n;
            for(int[] d : dirs) {
                int x = x0 + d[0];
                int y = y0 + d[1];
                if(x >= 0 && y >= 0 && x < m && y < n && image[x][y] == color && image[x][y] != newColor) {
                    queue.add(x * n + y);
                }
            }
            image[x0][y0] = newColor;
        }
        return image;
    }

    private int m, n, color;
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        color = image[sr][sc];
        dfs(image, sr, sc, newColor);
        return image;
    }
    private void dfs(int[][] image, int sr, int sc, int newColor) {
        image[sr][sc] = newColor;
        for(int[] d : dirs) {
            int x = sr + d[0];
            int y = sc + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n && image[x][y] == color && image[x][y] != newColor) {
                dfs(image, x, y, newColor);
            }
        }
    }
}
