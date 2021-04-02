package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Basic Calculator
public class Q224 {
    // O(n) O(n)
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0, val = 0, sign = 1;
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                val = val * 10 + c - '0';
            } else if(c == '+') {
                res += val * sign;
                val = 0;
                sign = 1;
            } else if(c == '-') {
                res += val * sign;
                val = 0;
                sign = -1;
            } else if(c == '(') {
                stack.push(res);    // value
                stack.push(sign);   // sign
                res = 0;
                sign = 1;           // 注意这里不能少  -> "-(3+(4+5))
                // val = 0 is not necessary, reinitialized in '+' or '-'.
            } else if(c == ')') {
                val = val * sign + res;
                sign = stack.pop();
                res = stack.pop();
            }
        }
        return res + val * sign;
    }
}
