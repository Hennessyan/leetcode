package google;
// Delete Operation for Two Strings
// Q712(M)
public class Q583 {
    // O(mn) O(mn)
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }

    public int minDistance0(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for(int i = 1; i <= m; i++) {
            int last = 0;
            for(int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = 1 + last;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                last = tmp;
            }
        }
        return m + n - 2 * dp[n];
    }

    // method2
    public int minDistance1(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
    }
    public int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0)
            return 0;
        if (memo[m][n] > 0)
            return memo[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
        else
            memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo));
        return memo[m][n];
    }
    // method3 : without LCS
    public int minDistance2(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
    // O(mn) O(n)
    public int minDistance3(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];

        for(int i = 0; i <= m; i++) {
            int last = dp[0];   // left top corner
            for(int j = 0; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[j] = i + j;
                } else {
                    int tmp = dp[j];
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[j] = last;
                    } else {
                        dp[j] = 1 + Math.min(dp[j-1], dp[j]);
                    }
                    last = tmp;
                }
            }
        }
        return dp[n];
    }
}
