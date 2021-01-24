package amaon;
// Longest Palindromic Subsequence II
public class Q1682 {
    // O(n^2) O(n^2)
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        char[][] last = new char[n][n];
        // good palindromic subsequence has an even length
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                char lastC = s.charAt(j);
                if(s.charAt(i) == lastC) {
                    if(lastC != last[i+1][j-1]) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                    last[i][j] = lastC; // both of them should be lastC
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    if(dp[i+1][j] != dp[i][j-1]) {
                        last[i][j] = dp[i+1][j] > dp[i][j-1] ? last[i+1][j] : last[i][j-1];
                    } else if(last[i+1][j] == last[i][j-1]) {   //不想等时会存在两个长度相同但结尾字符不同的情况,那么我们不设置初始值.
                        last[i][j] = last[i+1][j];              //当之后比较时一定会认为不相同 -> 也符合实际情况,lastC不可能同时等于两种情况.
                    }                                           //这时候ALL LOWER ENGLISH CHARACTERS的条件很重要!!!
                }
            }
        }
        return dp[0][n-1];
    }
    // O(n^2*26) O(n^2*26)
    public int longestPalindromeSubseq1(String s) {
        int n = s.length();
        int[][][] dp = new int[n][n][26];
        int ans = 0;    // must be even length, so not 1.
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                char lastC = s.charAt(j);
                if(len == 2) {
                    if(s.charAt(i) == lastC) {
                        dp[i][j][lastC - 'a'] = 2;
                        ans = 2;
                    }
                } else {
                    for(int k = 0; k < 26; k++) {
                        if(s.charAt(i) == lastC && lastC - 'a' != k) {
                            dp[i][j][lastC - 'a'] = Math.max(dp[i][j][lastC - 'a'], dp[i+1][j-1][k] + 2);
                            ans = Math.max(ans, dp[i][j][lastC - 'a']);
                        } else {
                            dp[i][j][k] = Math.max(dp[i][j][k], Math.max(dp[i+1][j][k], dp[i][j-1][k]));

                        }
                    }
                }
            }
        }
        return ans;
    }

}
