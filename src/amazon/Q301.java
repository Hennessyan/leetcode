package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Remove Invalid Parentheses
public class Q301 {
    // O(2^n) O(n)
    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        int l = 0, r = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') {
                l++;
            } else if(c == ')') {
                if(l > 0) {
                    l--;
                } else {
                    r++;
                }
            }
        }
        backtrack(s, 0, 0, 0, l, r, new StringBuilder(), res);
        return new ArrayList<>(res);
    }
    private void backtrack(String s, int lc, int rc, int i, int l, int r, StringBuilder sb, Set<String> res) {
        if(i == s.length()) {
            if(l == 0 && r == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        if((c == '(' && l > 0) || (c == ')' && r > 0)) {
            backtrack(s, lc, rc, i + 1, c == '(' ? l - 1 : l, c == ')' ? r - 1 : r, sb, res);
        }
        int len = sb.length();
        sb.append(c);
        if(Character.isLetter(c)) {
            backtrack(s, lc, rc, i + 1, l, r, sb, res);
        } else if(c == '(') {
            backtrack(s, lc + 1, rc, i + 1, l, r, sb, res);
        } else if(rc < lc) {
            backtrack(s, lc, rc + 1, i + 1, l, r, sb, res);
        }
        sb.deleteCharAt(len);
    }
}
