package amazon;

import java.util.Arrays;

// Distinct Subsequences II
public class Q940 {
    // O(n) O(n)
    // dp[i] - count by using characters from 0 to i-1 - dp[i] = 2 * dp[i - 1] - dp[last[s[c]] - 1]
    public int distinctSubseqII(String s) {
        int modulus = 1_000_000_007;
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            dp[i + 1] = 2 * dp[i] % modulus;
            if(last[c - 'a'] != -1) {
                dp[i + 1] -= dp[last[c - 'a']]; // dp[i + 1] -= dp[last[c - 'a'] + 1 - 1];
            }
            dp[i + 1] %= modulus;
            last[c - 'a'] = i;
        }
        dp[n]--;    // deduct ""
        if (dp[n] < 0) dp[n] += modulus;    // L20 will cause negative value as even raw value is bigger, but result after mod is smaller.
        return dp[n];
    }
    // O(n) O(1)
    public int distinctSubseqII1(String s) {
        int modulus = 1_000_000_007;
        int n = s.length();
        int[] last = new int[26];
        int pre = 1, cur = 1;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cur = 2 * pre % modulus;
            cur -= last[c - 'a'];
            cur = cur < 0 ? cur + modulus : (cur % modulus);
            last[c - 'a'] = pre;
            pre = cur;
        }
        return --cur;
    }
}
