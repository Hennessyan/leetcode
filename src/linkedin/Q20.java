package linkedin;

import java.util.ArrayDeque;
import java.util.Deque;

// Valid Parentheses
public class Q20 {

    public static void main(String[] args) {
        System.out.println(validate("((asd)ds+1)"));
        System.out.println(validate("())"));
    }
    // only ( ) and other chars
    private static boolean validate(String s) {
        int count = 0;
        for(char c : s.toCharArray()) {
            if(c == '(' || c == ')') {
                count += c == '(' ? 1 : -1;
                if(count < 0) return false;
            }
        }
        return count == 0;
    }
    // method1 : O(n) O(n)
    public boolean isValid1(String s) {
        if(s == null || s.length() < 2) {
            return false;
        }
        int len = s.length();
        char[] arr = new char[len];
        int index = 0;
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                arr[index++] = ')';
            } else if(c == '{') {
                arr[index++] = '}';
            } else if(c == '[') {
                arr[index++] = ']';
            } else if(index == 0 || arr[--index] != c) {
                return false;
            }
        }
        return index == 0;
    }

    // method2 :
    public boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for(char c : s.toCharArray()){
            switch(c){
                case '(':
                case '[':
                case '{':
                    stack[head++] = c;
                    break;
                case ')':
                    if(head == 0 || stack[--head] != '(')
                        return false;
                    break;
                case ']':
                    if(head == 0 || stack[--head] != '[')
                        return false;
                    break;
                case '}':
                    if(head == 0 || stack[--head] != '{')
                        return false;
                    break;
            }
        }
        return head == 0;
    }

    // method3 :
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(')');
            } else if(c == '[') {
                stack.push(']');
            } else if(c == '{') {
                stack.push('}');
            } else if(stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
