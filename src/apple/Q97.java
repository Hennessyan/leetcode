package apple;
// Interleaving String
public class Q97 {
    // brute force : O(2^(m+n)) O(m+n) - TLE
    // should TC be O(C-(m+n)-n) ?
    public boolean isInterleave0(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isInterleave(s1, 0, s2, 0, "", s3);
    }
    private boolean isInterleave(String s1, int i, String s2, int j, String res, String s3) {
        if(s3.equals(res) && i == s1.length() && j == s2.length()) {
            return true;
        }
        boolean ans = false;
        if(i < s1.length()) {
            ans |= isInterleave(s1, i + 1,  s2, j, res + s1.charAt(i), s3);
        }
        if(j < s2.length()) {
            ans |= isInterleave(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        }
        return ans;
    }
    // recursion with memo : O(mn) O(mn)
    // each memo is calculated once
    public boolean isInterleave1(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) {
            return false;
        }
        Boolean[][] memo = new Boolean[m][n];
        return isInterleave(s1, 0, s2, 0, s3, 0, memo);
    }
    private boolean isInterleave(String s1, int i, String s2, int j, String s3, int k, Boolean[][] memo) {
        if(i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if(j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if(memo[i][j] != null) {
            return memo[i][j];
        }
        boolean ans = false;
        if(s1.charAt(i) == s3.charAt(k)) {
            ans |= isInterleave(s1, i + 1, s2, j, s3, k + 1, memo);
        }
        if(s2.charAt(j) == s3.charAt(k)) {
            ans |= isInterleave(s1, i, s2, j + 1, s3, k + 1, memo);
        }
        return memo[i][j] = ans;
    }
    // DP : O(mn) O(mn)
    public boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if(i == 0) {
                    dp[i][j] = dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1);
                } else if(j == 0) {
                    dp[i][j] = dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1)) || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }
    // DP : O(mn) O(n)
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        boolean pre = false;
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 && j == 0) {
                    dp[j] = true;
                } else if(i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if(j == 0) {

                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {

                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[n];
    }
}
