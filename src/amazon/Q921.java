package amazon;
// Minimum Add to Make Parentheses Valid
// Q1541
public class Q921 {
    // O(n) O(1)
    public int minAddToMakeValid(String s) {
        if(s == null || s.length() == 0) return 0;
        int ans = 0, right = 0;
        for(char c : s.toCharArray()) {
            if(c == ')') {
                if(--right < 0) {
                    ans++;
                    right++;
                }
            } else {
                right++;
            }
        }
        return ans + right;
    }
}
