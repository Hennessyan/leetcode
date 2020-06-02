package challenge.may;

import java.util.LinkedList;

// Remove K Digits
public class Q402 {

    public static void main(String[] args) {
        Q402 q = new Q402();
        System.out.println(q.removeKdigits("1432219", 3));
    }

    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for(char c : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peekLast() > c) {
                stack.removeLast();
                k--;
            }
            stack.addLast(c);   //push operates head
        }
        for(int i = 0; i < k; i++) {
            stack.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for(char c : stack) {
            if(leadingZero && c == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
