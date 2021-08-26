package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Longest Valid Parentheses
public class Q32 {
    // DP : O(n) O(n)
    public int longestValidParentheses(String s) {
        int len = s.length();
        int[] dp = new int[len];
        char[] chs = s.toCharArray();
        int max = 0;
        for(int i = 1; i < len; i++) {
            if(chs[i] == ')') {
                if(chs[i-1] == '(') {
                    dp[i] = 2 + (i - 1 > 0 ? dp[i-2] : 0);
                }else if(i - dp[i-1] > 0 && chs[i - dp[i-1] - 1] == '(' ) {
                    dp[i] = 2 + dp[i-1] + (i - dp[i-1] - 1 > 0 ? dp[i - dp[i-1]-2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    // Stack: O(n) O(n)
    public int longestValidParentheses1(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
    // O(n) O(1)
    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
