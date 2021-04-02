package amazon;

import java.util.ArrayList;
import java.util.List;

// Expression Add Operators
public class Q282 {
    // http://zxi.mytechroad.com/blog/searching/leetcode-282-expression-add-operators/
    // O(n*4^(n-1)) O(n)
    List<String> ans;
    char[] nums;
    int target;
    char[] exp;
    int n;
    public List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();
        this.nums = num.toCharArray();
        this.target = target;
        n = num.length();
        exp = new char[n * 2];          // "" 5 => 2n - 1 will get exception.
        dfs(0, 0, 0, 0);
        return ans;
    }
    private void dfs(int pos, int len, long pre, long cur) {
        if(pos == n) {
            if(cur == this.target) {
                ans.add(new String(exp, 0, len));   // O(n)
                return;
            }
        }

        int s = pos;
        int l = len;    // used for operators
        if(s != 0) {
            ++len;
        }

        long value = 0;
        while(pos < n) {
            if(nums[s] == '0' && pos - s > 0) {
                break;
            }
            value = value * 10 + nums[pos] - '0';
            if(value > Integer.MAX_VALUE) {
                break;
            }
            exp[len++] = nums[pos++];
            if(s == 0) {
                dfs(pos, len, value, value);
                continue;
            }
            exp[l] = '+'; dfs(pos, len, value, cur + value);
            exp[l] = '-'; dfs(pos, len, -value, cur - value);
            exp[l] = '*'; dfs(pos, len, pre * value, cur - pre + pre * value);
        }
    }

    // https://leetcode.com/problems/expression-add-operators/discuss/1036714/JAVA-Easy-to-understand-DFS
}
