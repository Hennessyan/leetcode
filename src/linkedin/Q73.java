package linkedin;
// Set Matrix Zeroes
public class Q73 {
    // O(mn) O(n) - use set to record r and c
    // O(mn) O(1)
    public void setZeroes(int[][] mat) {
        boolean isRowZero = false;
        int m = mat.length, n = mat[0].length;
        for(int r = 0; r < m; r++) {
            if(mat[r][0] == 0) isRowZero = true;
            for(int c = 1; c < n; c++) {    // start from 1
                if(mat[r][c] == 0) {
                    mat[r][0] = mat[0][c] = 0;
                }
            }
        }
        for(int r = 1; r < m; r++) {
            for(int c = 1; c < n; c++) {
                if(mat[r][0] == 0 || mat[0][c] == 0) {
                    mat[r][c] = 0;
                }
            }
        }
        if(mat[0][0] == 0) {
            for(int c = 1; c < n; c++) {
                mat[0][c] = 0;
            }
        }
        if(isRowZero) {
            for(int r = 0; r < m; r++) {    // start from 0
                mat[r][0] = 0;
            }
        }
    }
}
