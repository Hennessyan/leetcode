package google;

import java.util.Stack;

// Score of Parentheses
public class Q856 {
    // O(n) O(n)
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(char c : S.toCharArray()) {
            if(c == '(') {
                stack.push(0);
            } else {
                int cur = stack.pop();
                int preSum = stack.pop();
                stack.push(preSum + Math.max(2 * cur, 1));
            }
        }
        return stack.pop();
    }
    // O(n) O(1)
    public int scoreOfParentheses1(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal;
            }
        }

        return ans;
    }
}
