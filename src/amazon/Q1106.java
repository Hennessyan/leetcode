package amazon;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Parsing A Boolean Expression
public class Q1106 {
    // O(n) O(n)
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for(char c : expression.toCharArray()) {
            if(c == ',') {
                continue;
            }
            if(c == ')') {
                Set<Character> set = new HashSet<>();
                while(stack.peek() != '(') {
                    set.add(stack.pop());
                }
                stack.pop();
                char operator = stack.pop();
                if(operator == '!') stack.push(opposite(set));
                if(operator == '&') stack.push(and(set));
                if(operator == '|') stack.push(or(set));
            } else {
                stack.push(c);
            }
        }
        return stack.peek() == 't' ? true : false;
    }
    private char opposite(Set<Character> set) {
        char c = set.iterator().next();
        return c == 't' ? 'f' : 't';
    }
    private char and(Set<Character> set) {
        if(set.size() == 2) {
            return 'f';
        } else {
            return set.iterator().next();
        }
    }
    private char or(Set<Character> set) {
        if(set.size() == 2) {
            return 't';
        } else {
            return set.iterator().next();
        }
    }

    class Parser {
        String ex;
        int i;
        public Parser(String str) {
            ex = str;
            i = 0;
        }
        public boolean parse() {
            char c = ex.charAt(i);
            i++;
            if(c == 't') {
                return true;
            }
            if(c == 'f') {
                return false;
            }
            if(c == '!') {
                i++;    // '('
                boolean res = !parse();
                i++;    // ')'
                return res;
            }

            i++;    // after left parenthesis => &(t - t
            boolean res = parse();
            while(ex.charAt(i) != ')') {
                i++;    // ','
                if(c == '&') {
                    res &= parse();
                } else {
                    res |= parse();
                }
            }
            i++;    // ')'
            return res;
        }
    }
    public boolean parseBoolExpr1(String expression) {
        return new Parser(expression).parse();
    }
}
