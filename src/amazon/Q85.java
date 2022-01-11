package amazon;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Maximal Rectangle
public class Q85 {
    // O(mn) O(n)
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n];
        int max = 0;
        for(char[] row : matrix) {
            for(int i = 0; i < n; i++) {
                if(row[i] == '1') {
                    dp[i]++;
                } else {
                    dp[i] = 0;
                }
            }
            max = Math.max(max, help(dp));
        }
        return max;
    }
    // solution of Q84
    private int help(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            while(stack.peek() != -1 && arr[stack.peek()] > arr[i]) {   // >= is fine
                max = Math.max(max, arr[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while(stack.peek() != -1) {
            max = Math.max(max, arr[stack.pop()] * (arr.length - stack.peek() - 1));
        }
        return max;
    }

    // DP: https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
    public int maximalRectangle1(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] l = new int[n];
        int[] r = new int[n];
        int[] h = new int[n];
        Arrays.fill(r, n);

        int max = 0;
        for(char[] row : matrix) {
            int tmp_l = 0, tmp_r = n;
            for(int i = 0; i < n; i++) {
                if(row[i] == '1') {
                    h[i]++;
                    l[i] = Math.max(l[i], tmp_l);
                } else {
                    h[i] = 0;
                    l[i] = 0;
                    tmp_l = i + 1;
                }
            }
            for(int i = n - 1; i >= 0; i--) {
                if(row[i] == '1') {
                    r[i] = Math.min(r[i], tmp_r);
                } else {
                    r[i] = n;
                    tmp_r = i;
                }
            }
            for(int i = 0; i < n; i++) {
                max = Math.max(max, h[i] * (r[i] - l[i]));
            }
        }
        return max;
    }

}
