package google;

import java.util.ArrayDeque;
import java.util.Deque;

// Evaluate Reverse Polish Notation
// Q331
public class Q150 {
    // O(n) O(n)
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String token : tokens) {
            if(token.equals("+")) {
                int val = stack.pop() + stack.pop();
                stack.push(val);
            }else if(token.equals("*")) {
                int val = stack.pop() * stack.pop();
                stack.push(val);
            }else if(token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            }else if(token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            }else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public int evalRPN1(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {

            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();

            int result = 0;

            switch (token) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }

            stack.push(result);

        }

        return stack.pop();
    }
}
