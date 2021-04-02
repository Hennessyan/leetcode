package amazon;

import java.util.Stack;

// Decode String
public class Q394 {
    // O(maxK*n) O(m+n) - maxK is max value of num in string, n is max str length.
    private int i = 0;
    public String decodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while(i < s.length()) {
            char c = s.charAt(i++);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }else if(c == '[') {
                String tmp = decodeString(s);
                for(int j = 0; j < num; j++) {  // while(num-- > 0) 不对,会让num<0
                    sb.append(tmp);
                }
                num = 0;
            }else if(c == ']') {
                break;
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    // O(maxK*n)
    // O(m+n) -> m : number of digits [0-9], n : number of letters [a-z]
    public String decodeString1(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        Stack<Integer> s1 = new Stack<>();
        Stack<StringBuffer> s2 = new Stack<>();
        StringBuffer sb = new StringBuffer();
        int i = 0, num = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if(c == '[') {
                s1.push(num);
                s2.push(sb);
                num = 0;
                sb = new StringBuffer();
            } else if(c == ']') {
                StringBuffer tmp = s2.pop();
                num = s1.pop();
                while(num > 0) {
                    tmp.append(sb);
                    num--;
                }
                sb = tmp;
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }
}
