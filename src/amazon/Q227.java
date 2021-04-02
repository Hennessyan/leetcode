package amazon;

import java.util.Stack;

// Basic Calculator II
public class Q227 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0, operand = 0, sign = 1;
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                operand = operand * 10 + c - '0';
            } else if(c == '+' || c == '-') {
                if(stack.isEmpty()) {
                    res += operand * sign;
                } else {
                    res = sign == 1 ? res * operand : res / operand;
                    res = stack.pop() * res + stack.pop();
                }
                operand = 0;
                sign = c == '+' ? 1 : -1;
            } else if(c == '*' || c == '/') {
                if(stack.isEmpty()) {
                    stack.push(res);
                    stack.push(sign);
                    res = operand;          // 注意这里！
                } else {
                    res = sign == 1 ? res * operand : res / operand;
                }
                operand = 0;
                sign = c == '*' ? 1 : -1;
            }
        }
        if(stack.isEmpty()) {
            res += operand * sign;
        } else {
            res = sign == 1 ? res * operand : res / operand;
            res = stack.pop() * res + stack.pop();
        }
        return res;
    }
    // better than method1 O(n) O(n)
    public int calculate1(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
    // O(n) O(1)
    public int calculate2(String s) {
        int res = 0, operand = 0, n = s.length(), lastNumber = 0;
        char operation = '+';
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                operand = operand * 10 + c - '0';
            }
            if((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == n - 1) {   // 注意这里不能用ELSE IF
                if(operation == '+' || operation == '-') {
                    res += lastNumber;
                    lastNumber = operation == '+' ? operand : -operand;
                } else if(operation == '*') {
                    lastNumber = lastNumber * operand;
                } else if(operation == '/') {
                    lastNumber = lastNumber / operand;
                }
                operation = c;
                operand = 0;
            }
        }
        return res + lastNumber;
    }
}
