package google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 01 Matrix
// next : Q1730
public class Q542 {

    //BFS : O(rc) O(rc) - maintain the queue.
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[][] res = new int[r][c];
        for(int[] row : res) {
            Arrays.fill(row, 10000);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(matrix[i][j] == 0) {
                    res[i][j] = 0;
                    queue.add(i*c+j);
                }
            }
        }
        while(!queue.isEmpty()) {
            int val = queue.poll();
            for(int[] dir : dirs) {
                int x = val / c + dir[0];
                int y = val % c + dir[1];
                if(x >= 0 && x < r && y >= 0 && y < c) {
                    if(res[x][y] > res[val / c][val %c] + 1) {
                        res[x][y] = res[val / c][val %c] + 1;
                        queue.offer(x*c+y);
                    }
                }
            }
        }
        return res;
    }
    //DP: O(rc) O(1)
    public int[][] updateMatrix0(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        for(int i = 0; i < r; i++) {	//不创建res矩阵的话就要把1的值变成10000。
            for(int j = 0; j < c; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 10000;
                }
            }
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(matrix[i][j] == 0) {
                    continue;
                }
                if(i > 0 && matrix[i][j] > matrix[i - 1][j] + 1) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
                if(j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                }
            }
        }
        for(int i = r - 1; i >= 0; i--) {
            for(int j = c - 1; j >= 0; j--) {
                if(i < r - 1 && matrix[i][j] > matrix[i + 1][j] + 1) {
                    matrix[i][j] = matrix[i + 1][j] + 1;
                }
                if(j < c - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }

    public int[][] updateMatrix2(int[][] mat) {
        int m = mat.length, n = mat[0].length, inf = 10000;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(mat[r][c] != 0) {
                    int top = r == 0 ? inf : mat[r-1][c];
                    int left = c == 0 ? inf : mat[r][c-1];
                    mat[r][c] = Math.min(top,left) + 1;
                }
            }
        }

        for(int r = m - 1; r >= 0; r--) {
            for(int c = n - 1; c >= 0; c--) {
                if(mat[r][c] != 0) {
                    int down = r == m - 1 ? inf : mat[r+1][c];
                    int right = c == n - 1 ? inf : mat[r][c+1];
                    mat[r][c] = Math.min(mat[r][c], Math.min(down ,right) + 1);
                }
            }
        }
        return mat;
    }

    //DFS O(rc*rc) O(rc)
    public int[][] updateMatrix1(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[][] seen = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(matrix[i][j] == 0) {
                    continue;
                }
                matrix[i][j] = dfs(matrix, i, j, seen);
            }
        }
        return matrix;
    }
    private int dfs(int[][] matrix, int i, int j, int[][] seen) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || seen[i][j] == 1) {
            return 10000;
        }

        if (i - 1 >= 0 && matrix[i - 1][j] == 0) {
            return 1;
        }
        if (j - 1 >= 0 && matrix[i][j - 1] == 0) {
            return 1;
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] == 0) {
            return 1;
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] == 0) {
            return 1;
        }
        seen[i][j] = 1;
        int min = 10000;
        for(int[] dir : dirs) {
            min = Math.min(min, 1 + dfs(matrix, i + dir[0], j + dir[1], seen));
        }
        seen[i][j] = 0;
        return min;
    }
}
