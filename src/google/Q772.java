package google;

import java.util.LinkedList;
import java.util.Queue;

// Basic Calculator III
public class Q772 {

    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        Queue<Character> queue = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if(!Character.isWhitespace(c)) {
                queue.add(c);
            }
        }
        queue.add(' ');
        return helper(queue);
    }
    private int helper(Queue<Character> queue) {
        int res = 0, operand = 0, lastNumber = 0;
        char lastOperation = '+';
        while(!queue.isEmpty()) {
            char c = queue.poll();
            if(Character.isDigit(c)) {
                operand = operand * 10 + c - '0';
            } else if(c == '(') {
                operand = helper(queue);    //注意这里!!!
            } else {
                switch(lastOperation) {
                    case '+':
                        res += lastNumber;
                        lastNumber = operand;
                        break;
                    case '-':
                        res += lastNumber;
                        lastNumber = -operand;
                        break;
                    case '*':
                        lastNumber *= operand;
                        break;
                    case '/':
                        lastNumber /= operand;
                        break;
                }
                if(c == ')') {
                    break;
                }
                operand = 0;
                lastOperation = c;
            }
        }
        return res + lastNumber;
    }
}
