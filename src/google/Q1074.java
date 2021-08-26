package google;

import java.util.HashMap;
import java.util.Map;

// Number of Submatrices That Sum to Target
public class Q1074 {
    // O(cr^2) O(rc)
    public int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ps = new int[m +1][n + 1];
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                ps[r + 1][c + 1] = ps[r + 1][c] + ps[r][c + 1] - ps[r][c] + matrix[r][c];
            }
        }
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int r = 1; r < m + 1; r ++) {
            for(int r2 = r; r2 < m + 1; r2++) {
                map.clear();
                map.put(0, 1);
                for(int c = 1; c < n + 1; c++) {
                    int curSum = ps[r2][c] - ps[r - 1][c];
                    if(map.containsKey(curSum - target)) {
                        total += map.get(curSum - target);
                    }
                    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
                }
            }
        }
        return total;
    }
    // O(rc^2) O(rc)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;

        // compute 2D prefix sum
        int[][] ps = new int[r + 1][c + 1];
        for (int i = 1; i < r + 1; ++i) {
            for (int j = 1; j < c + 1; ++j) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int count = 0, currSum;
        Map<Integer, Integer> h = new HashMap();
        for (int c1 = 1; c1 < c + 1; ++c1) {
            for (int c2 = c1; c2 < c + 1; ++c2) {
                h.clear();
                h.put(0, 1);
                for (int row = 1; row < r + 1; ++row) {
                    // current 1D prefix sum
                    currSum = ps[row][c2] - ps[row][c1 - 1];

                    // add subarrays which sum up to (currSum - target)
                    count += h.getOrDefault(currSum - target, 0);

                    // save current prefix sum
                    h.put(currSum, h.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }

}
