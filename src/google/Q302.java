package google;

import java.util.LinkedList;
import java.util.Queue;

// Smallest Rectangle Enclosing Black Pixels
public class Q302 {

    public static void main(String[] args) {

    }

    // O(mn) O(1)
    public int minArea1(char[][] image, int x, int y) {
        int top = x, bottom = x;
        int left = y, right = y;
        for (x = 0; x < image.length; ++x) {
            for (y = 0; y < image[0].length; ++y) {
                if (image[x][y] == '1') {
                    top = Math.min(top, x);
                    bottom = Math.max(bottom, x + 1);
                    left = Math.min(left, y);
                    right = Math.max(right, y + 1);
                }
            }
        }
        return (right - left) * (bottom - top);
    }

    // BFS: O(mn) O(m+n)
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minArea2(char[][] image, int x, int y) {
        if (image == null || image.length == 0) {
            return 0;
        }
        int m = image.length, n = image[0].length;
        int t = x, d = x, l = y, r = y;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x * n + y);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            int x0 = v / n;
            int y0 = v % n;
            if (y0 < l) {
                l = y0;
            } else if (y0 > r) {
                r = y0;
            }
            if (x0 < t) {
                t = x0;
            } else if (x0 > d) {
                d = x0;
            }
            for (int[] dir : dirs) {
                int x1 = x0 + dir[0];
                int y1 = y0 + dir[1];
                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && image[x1][y1] == '1') {
                    image[x1][y1] = '0';
                    queue.add(x1 * n + y1);
                }
            }
        }
        return (d - t + 1) * (r - l + 1);
    }

    // DFS: O(mn) O(mn)
    private int top, bottom, left, right;

    public int minArea3(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (right - left) * (bottom - top);
    }

    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length ||
                image[x][y] == '0')
            return;
        image[x][y] = '0'; // mark visited black pixel as white
        top = Math.min(top, x);
        bottom = Math.max(bottom, x + 1);
        left = Math.min(left, y);
        right = Math.max(right, y + 1);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y - 1);
        dfs(image, x, y + 1);
    }

    // binary search: O(mlgn + nlgm) O(1)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = searchColumns(image, 0, y, 0, m, true);
        int right = searchColumns(image, y + 1, n, 0, m, false);
        int top = searchRows(image, 0, x, left, right, true);
        int bottom = searchRows(image, x + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }

    private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
        while (i != j) {
            int k = top, mid = (i + j) / 2;
            while (k < bottom && image[k][mid] == '0') ++k;
            if (k < bottom == whiteToBlack) // k < bottom means the column mid has black pixel
                j = mid; //search the boundary in the smaller half
            else
                i = mid + 1; //search the boundary in the greater half
        }
        return i;
    }

    private int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
        while (i != j) {
            int k = left, mid = (i + j) / 2;
            while (k < right && image[mid][k] == '0') ++k;
            if (k < right == whiteToBlack) // k < right means the row mid has black pixel
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
}
