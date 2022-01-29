package linkedin;
// Minimum Insertions to Balance a Parentheses String
// Q921
public class Q1541 {
    // O(n) O(1)
    public int minInsertions(String s) {
        int ans = 0;
        int right = 0;     // # of right parenthesis needed.
        for(char c : s.toCharArray()) {
            if(c == ')') {
                if(--right < 0) {
                    ans += 1;   // ")" => "()"
                    right += 2; // add one '(' => we need to + 2
                }
            } else {
                if(right % 2 == 1) {    // unbalance, need to add one ')' => we can get one "())" => right--
                    right--;            // "(()(" => "(())(" => "(("
                    ans++;
                }
                right += 2;
            }
        }
        return ans + right;
    }
}
