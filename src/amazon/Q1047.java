package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Remove All Adjacent Duplicates in String
public class Q1047 {
    // O(n) O(n-d) d : duplicates
    // method1 is much slower than method2
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : S.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(); // use sb directly will execute System.arraycopy method!
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
    // method2
    public String removeDuplicates1(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for(char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1))
                sb.deleteCharAt(sbLength-- - 1);
            else {
                sb.append(character);
                sbLength++;
            }
        }
        return sb.toString();
    }
}
