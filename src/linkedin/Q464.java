package linkedin;

import java.util.HashMap;
import java.util.Map;

// Can I Win
// similar to Q698
public class Q464 {
    // O(2^n) - SC O(2^n)
    Map<Integer, Boolean> memo;
    public boolean canIWin(int a, int b) {
        if(a > b) return true;
        if(a * (a + 1) / 2 < b) return false;
        memo = new HashMap<>();
        return dfs(0, a, b);
    }
    private boolean dfs(int state, int val, int target) {
        if(memo.containsKey(state)) {
            return memo.get(state);
        }
        for(int i = 0; i < val; i++) {
            if((state & (1 << i)) == 0) {
                // current win or next lose => true !!!
                if(target <= i + 1 || !dfs(state | (1 << i), val, target - i - 1)) {
                    memo.put(state, true);
                    return true;
                }
            }
        }
        memo.put(state, false);
        return false;
    }

    public boolean canIWin1(int n, int desiredTotal) {
        if(n > desiredTotal) return true;
        if((1 + n) * n / 2 < desiredTotal) return false;

        return backtrack(0, n, desiredTotal, new Boolean[1 << n]);  // 1 << n
    }
    private boolean backtrack(int state, int n,int target, Boolean[] memo) {
        if(memo[state] != null) return memo[state];
        for(int i = 0; i < n; i++) {    // here i is under[0,n) as size of memo is 1 << n. i == n will OutOfBound.
            if((state & (1 << i)) == 0) {
                if(target <= i + 1 || !backtrack(state | (1 << i), n, target - i - 1, memo)) {
                    return memo[state] = true;
                }
            }
        }
        return memo[state] = false;
    }
}
