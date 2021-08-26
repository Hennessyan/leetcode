package challenge.july21;
// Reshape the Matrix
public class Q566 {
    // O(mn) O(mn)
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if(mat == null || mat.length == 0) return mat;
        int m = mat.length, n = mat[0].length;
        if(m * n != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        int i = 0;
        for(int[] row : mat) {
            for(int val : row) {
                res[i / c][i % c] = val;
                i++;
            }
        }
        return res;
    }

    public int[][] matrixReshape1(int[][] nums, int r, int c) {
        int[][] res = new int[r][c];
        if (nums.length == 0 || r * c != nums.length * nums[0].length)
            return nums;
        int rows = 0, cols = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[rows][cols] = nums[i][j];
                cols++;
                if (cols == c) {
                    rows++;
                    cols = 0;
                }
            }
        }
        return res;
    }
}
