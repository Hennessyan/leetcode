package amazon;

import java.util.PriorityQueue;

// Kth Smallest Element in a Sorted Matrix
// next : Q215
public class Q378 {
    // method1 : X - min(n, k) O(X + klgX) O(X)
    // utilize the matrix is sorted.
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int X = Math.min(n, k);
        PriorityQueue<int[]> pq = new PriorityQueue<>(X, (a, b) -> a[2] - b[2]);

        for(int i = 0; i < n; i++) {
            pq.offer(new int[]{i, 0, matrix[i][0]});
        }
        for(int i = 1; i < k; i++) {
            int[] tmp = pq.poll();
            if(tmp[1] < n - 1) pq.offer(new int[]{tmp[0], tmp[1] + 1, matrix[tmp[0]][tmp[1] + 1]});
        }
        return pq.peek()[2];
    }
    // binary search : O(nlg(max - min)) O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, start = matrix[0][0], end = matrix[n-1][n-1];
        while(start < end) {    // start < end here
            int mid = start + (end - start) / 2;
            // p[0] : largest element less or equal to mid
            // p[1] : smallest element that greater than mid
            int[] smallLargePair = new int[]{start, end};
            int count = countLessOrEqual(matrix, mid, smallLargePair);
            if(count == k) {
                return smallLargePair[0];
            } else if(count < k) {
                start = smallLargePair[1];
            } else {
                end = smallLargePair[0];
            }
        }
        return start;
    }
    // always traverse the "whole" matrix to count the elements less or equal to mid
    private int countLessOrEqual(int[][] matrix, int mid, int[] pair) {
        int n = matrix.length, count = 0;
        int row = n - 1, col = 0;
        while(row >= 0 && col < n) {
            if(matrix[row][col] > mid) {
                pair[1] = Math.min(pair[1], matrix[row][col]);
                row--;
            } else {
                count += row + 1;
                pair[0] = Math.max(pair[0], matrix[row][col]);
                col++;
            }
        }
        return count;
    }
}
