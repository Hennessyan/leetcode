package challenge.april;

import java.util.Stack;

// Backspace String Compare
public class Q844 {

    public static void main(String[] args) {
        Q844 q = new Q844();

    }

    // O(m+n) O(m+n)
    public boolean backspaceCompare1(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c : S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    i--;
                    skipS--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    j--;
                    skipT--;
                } else {
                    break;
                }
            }
            if((i >= 0) != (j >= 0)) {
                return false;
            }
            if(i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {    //"i >= 0 && j >= 0" is important in case i < 0 && j < 0
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
}
