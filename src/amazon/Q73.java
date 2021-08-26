package amazon;

import java.util.HashSet;
import java.util.Set;

// Set Matrix Zeroes
public class Q73 {
    // O(mn) O(m+n)
    public void setZeroes0(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    // O(mn) O(1)
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        for(int j = 0; j < n; j++) {
            if(matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++) {
            if(matrix[i][0] == 0) {
                zeroRow(matrix, i);
            }
        }
        for(int j = 1; j < n; j++) {
            if(matrix[0][j] == 0) {
                zeroCol(matrix, j);
            }
        }
        if(firstRowZero) {
            zeroRow(matrix, 0);
        }
        if(firstColZero) {
            zeroCol(matrix, 0);
        }
    }
    private void zeroRow(int[][] matrix, int r) {
        for(int c = 0; c < matrix[0].length; c++) {
            matrix[r][c] = 0;
        }
    }
    private void zeroCol(int[][] matrix, int c) {
        for(int r = 0; r < matrix.length; r++) {
            matrix[r][c] = 0;
        }
    }
    // this way just need one boolean variable + check matrix[0][0] for first row
    public void setZeroes1(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {
            if (matrix[i][0] == 0) {
                isCol = true;
            }
            // start from 1 !!!
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
