package amazon;

import java.util.*;

// Minimum Remove to Make Valid Parentheses
public class Q1249 {

    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> rs = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c != '(' && c != ')') {
                sb.append(c);
            } else if(c == '(') {
                ls.push(sb.length());   // "))(("
                sb.append(c);
            } else {
                if(ls.size() == rs.size()) {
                    continue;
                } else {
                    sb.append(c);
                    rs.push(i);
                }
            }
        }
        while(ls.size() > rs.size()) {
            sb.deleteCharAt(ls.pop());  // System.arraycopy - expensive
        }
        return sb.toString();
    }
    // O(n) O(n)
    public String minRemoveToMakeValid1(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    // O(n) O(n)
    public String minRemoveToMakeValid2(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int openSeen = 0, balance = 0;
        // remove extra ')'
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                openSeen++;
                balance++;
            } else if(c == ')') {
                if(balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }
        // remove extra '('
        StringBuilder res = new StringBuilder();
        openSeen -= balance;    // number of open parenthesis can keep
        for(int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(c == '(') {
                openSeen--;
                if(openSeen < 0) {
                    continue;
                }
            }
            res.append(c);
        }
        return res.toString();
    }
}
