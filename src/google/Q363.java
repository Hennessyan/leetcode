package google;

import java.util.Arrays;
import java.util.TreeSet;

// Max Sum of Rectangle No Larger Than K
public class Q363 {
    // O(m*mnlgn) O(n)
    // O(n*nmlgm) O(m)
    int result = Integer.MIN_VALUE;
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[] rowsum = new int[n];
        // traverse all "rectangle" combination.
        for(int i = 0; i < m; i++) {
            Arrays.fill(rowsum, 0);
            for(int j = i; j < m; j++) {
                for(int c = 0; c < n; c++) {
                    rowsum[c] += matrix[j][c];  // just sum for same column.
                }
                updateResult(rowsum, k);
                if(result == k) return k;
            }
        }
        return result;
    }
    // presum[j] - presum[i] <= k
    // presum[i] >= presum[j] - k
    //    x      >= current_sum - k
    // main idea is use binary search in (presum) sorted set to find the minimum x

    private void updateResult(int[] row, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        for(int i = 0; i < row.length; i++) {
            sum += row[i];
            Integer x = set.ceiling(sum - k);
            if(x != null) {
                result = Math.max(result, sum - x);
            }
            set.add(sum);
        }
    }
}
// follow-up : what if col length < row length ? => build col-sum array with same idea of below method.
// O(min(m,n)^2 * max(m,n)logmax(m,n))
// O(max(m,n))

class Solution {
    int result = Integer.MIN_VALUE;

    // Standard Kadane's algorithm.
    // Kadane's algorithm gets the max possible sum of a sub-array in O(n) time
    int getMaxKadane(int[] nums) {
        int maxKadane = Integer.MIN_VALUE, currentMaxSum = 0;
        for (int num : nums) {
            currentMaxSum = Math.max(currentMaxSum + num, num);
            maxKadane = Math.max(maxKadane, currentMaxSum);
        }
        return maxKadane;
    }
    void updateResult(int[] nums, int k) {
        int kadaneSum = getMaxKadane(nums);

        // If max possible sum of any subarray of nums is <= k
        // use that result to compare with gobal maxium result and return
        if (kadaneSum <= k) {
            result = Math.max(result, kadaneSum);
            return;
        }
        int sum = 0;

        // Container to store sorted prefix sums.
        TreeSet<Integer> sortedSum = new TreeSet<>();

        // Add 0 as the prefix sum for an empty sub-array.
        sortedSum.add(0);
        for (int num : nums) {
            // Running Sum.
            sum += num;

            // Get X where Running sum - X <= k such that sum - X is closest to k.
            Integer x = sortedSum.ceiling(sum - k);

            // If such X is found in the prefix sums.
            // Get the sum of that sub array and update the global maximum result.
            if (x != null)
                result = Math.max(result, sum - x);

            // Insert the current running sum to the prefix sums container.
            sortedSum.add(sum);
        }
    }
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix[0].length > matrix.length) {
            // Stores the 1D representation of the matrix.
            int[] rowSum = new int[matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                // Initialize the 1D representation with 0s.
                Arrays.fill(rowSum, 0);
                // We convert the matrix between rows i..row inclusive to 1D array
                for (int row = i; row < matrix.length; row++) {
                    // Add the current row to the previous row.
                    // This converts the matrix between i..j to 1D array
                    for (int col = 0; col < matrix[0].length; col++)
                        rowSum[col] += matrix[row][col];

                    // Run the 1D algorithm for `rowSum`
                    updateResult(rowSum, k);

                    // If result is k, this is the best possible answer, so return.
                    if (result == k)
                        return result;
                }
            }
        } else {
            // Stores the 1D representation of the matrix column wise.
            int[] colSum = new int[matrix.length];
            for (int i = 0; i < matrix[0].length; i++) {
                // Initialize the 1D representation with 0s.
                Arrays.fill(colSum, 0);

                // We convert the matrix between columns i..col inclusive to 1D array
                for (int col = i; col < matrix[0].length; col++) {
                    // Add the current column to the previous column.
                    for (int row = 0; row < matrix.length; row++)
                        colSum[row] += matrix[row][col];

                    // Run the 1D algorithm for `colSum`
                    updateResult(colSum, k);

                    // If result is k, this is the best possible answer, so return.
                    if (result == k)
                        return result;
                }
            }
        }
        return result;
    }
}