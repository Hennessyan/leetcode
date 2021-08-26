package challenge.jun21;
// K Inverse Pairs Array
public class Q629 {

    /**
     *
     *  [1,2,3,4] -> K = 0
     *  if we want get k = 1, we can shift one num from right to left with distance k =>
     *  [2,1,3,4] / [1,3,2,4] / [1,2,4,3]
     *  similarly, k = 2 =>
     *  [2,1,4,3] / [1,4,2,3] / [3,1,2,4] ...
     *
     *  count(n, 0) = 1, count(0, k) = 0
     *  count(n, k) = sum{ count(n-1, k-i) } 0 <= i <= Math.min(k, n - 1)
     */

    // MEMO / DP1 : O(nk * min(n, k)) O(nk) - TLE
    class Solution {
        Integer[][] memo = new Integer[1001][1001];
        public int kInversePairs(int n, int k) {
            if (n == 0)
                return 0;
            if (k == 0)
                return 1;
            if (memo[n][k] != null)
                return memo[n][k];
            int inv = 0;
            for (int i = 0; i <= Math.min(k, n - 1); i++)
                inv = (inv + kInversePairs(n - 1, k - i)) % 1000000007;
            memo[n][k] = inv;
            return inv;
        }
    }

    public int kInversePairs0(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                if(j == 0) {
                    dp[i][j] = 1;
                } else {
                    for(int p = 0; p <= Math.min(i - 1, j); p++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
                    }
                }
            }
        }
        return dp[n][k];
    }
    // DP[i][j] = count(i,j) + sum (DP[i][k]) 0 <= k <= j - 1
    public int kInversePairs(int n, int k) {
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                if(j == 0) dp[i][j] = 1;
                else {
                    int val = (dp[i-1][j] - (j >= i ? dp[i-1][j-i] : 0) + mod) % mod;    // add mod to avoid  a-b < 0 -> negative number
                    dp[i][j] = (dp[i][j-1] + val) % mod;
                }
            }
        }
        return (dp[n][k] + mod - (k > 0 ? dp[n][k - 1] : 0)) % mod;
    }

}
