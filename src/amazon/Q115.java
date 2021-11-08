package amazon;

import java.util.HashMap;
import java.util.Map;

// Distinct Subsequences
public class Q115 {
    // O(m*n) O(m*n)
    int m, n;
    Map<Integer, Integer> memo;
    public int numDistinct(String s, String t) {
        m = s.length();
        n = t.length();
        memo = new HashMap<>();
        return numDistinct(s, t, 0, 0);
    }
    private int numDistinct(String s, String t, int i, int j) {
        if(i == m || j == n || m - i < n - j) {
            return j == n ? 1 : 0;
        }
        int key = i * m + j;
        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        char sc = s.charAt(i);
        char tc = t.charAt(j);
        int val = 0;
        if(sc == tc) {
            val = numDistinct(s, t, i + 1, j) + numDistinct(s, t, i + 1, j + 1);
        } else {
            val = numDistinct(s, t, i + 1, j);
        }
        memo.put(key, val);
        return val;
    }

    public int numDistinct1(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];
        for(int r = 0; r <= m; r++) {
            dp[r][n] = 1;
        }
        for(int r = m - 1; r >= 0; r--) {
            for(int c = n - 1; c >= 0; c--) {
                char sc = s.charAt(r);
                char tc = t.charAt(c);
                dp[r][c] = dp[r+1][c];
                if(sc == tc) {
                    dp[r][c] += dp[r+1][c+1];
                }
            }
        }
        return dp[0][0];
    }

    public int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for(int r = m - 1; r >= 0; r--) {
            int pre = dp[n];
            for(int c = n - 1; c >= 0; c--) {
                char sc = s.charAt(r);
                char tc = t.charAt(c);
                int tmp = dp[c];
                if(sc == tc) {
                    dp[c] += pre;
                }
                pre = tmp;
            }
        }
        return dp[0];
    }
}
