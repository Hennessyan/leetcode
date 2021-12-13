package amazon;

import java.util.Arrays;

// Determine Whether Matrix Can Be Obtained By Rotation
public class Q1886 {

    public boolean findRotation(int[][] mat, int[][] target) {
        for(int i = 0; i < 4; i++) {
            if(Arrays.deepEquals(mat, target)) return true;
            rotate(mat);
        }
        return false;
    }
    private void rotate(int[][] mat) {
        for (int i = 0, j = mat.length - 1; i < j; ++i, --j) { // reverse rows order.
            int[] tmp = mat[i];
            mat[i] = mat[j];
            mat[j] = tmp;
        }
        for (int r = 0, R = mat.length; r < R; ++r) { // transpose.
            for (int c = r + 1; c < R; ++c) {
                int tmp = mat[r][c];
                mat[r][c] = mat[c][r];
                mat[c][r] = tmp;
            }
        }
    }
}
