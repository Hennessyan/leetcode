package microsoft;

import java.util.*;

// Reverse Words in a String
public class Q151 {
    // O(n) O(n)

    public String reverseWords0(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int l = 0, r = s.length() - 1;
        while(l <= r && s.charAt(l) == ' ') {
            l++;
        }
        while(l <= r && s.charAt(r) == ' ') {
            r--;
        }
        Deque<String> queue = new ArrayDeque<>();
        while(l <= r) {
            char c = s.charAt(l++);
            if(c != ' ') {
                sb.append(c);
            } else if(sb.length() != 0) {   // necessary
                queue.addFirst(sb.toString());
                sb.setLength(0);
            }
        }
        queue.addFirst(sb.toString());
        return String.join(" ", queue);
    }
    // follow-up use one StringBuilder with two pointers.
    public String reverseWords1(String s) {
        StringBuilder sb = removeSpace(s);
        reverse(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s) {
        StringBuilder sb = new StringBuilder();
        int l = 0, r = s.length() - 1;
        while(l <= r && s.charAt(l) == ' ') {
            l++;
        }
        while(l <= r && s.charAt(r) == ' ') {
            r--;
        }
        while(l <= r) {
            char c = s.charAt(l++);
            if(c != ' ') {
                sb.append(c);
            } else if(sb.charAt(sb.length() - 1) != ' ') {  // } else if(s.charAt(l - 1) != ' ') { is good enough
                sb.append(c);
            }
        }
        return sb;
    }
    private void reverse(StringBuilder sb, int l, int r) {
        while(l < r) {
            char c = sb.charAt(l);
            sb.setCharAt(l++, sb.charAt(r));
            sb.setCharAt(r--, c);
        }
    }
    private void reverseEachWord(StringBuilder sb) {
        int l = 0, r = 0, n = sb.length();
        while(r < n) {
            while(r < n && sb.charAt(r) != ' ') {
                r++;
            }
            reverse(sb, l, r - 1);
            r++;
            l = r;
        }
    }
}
