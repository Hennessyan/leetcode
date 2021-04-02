package microsoft;

public class Q10 {
    // O(SP) O(SP)
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for(int j = 1; j <= n; j++) {
            if(p.charAt(j - 1) == '*') {
                //* will never be the first character
                dp[0][j] = dp[0][j - 2];
            }
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j - 1) == '*') {
                    if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i-1][j];  // abcddddd abcd*
                    } else {
                        dp[i][j] = dp[i][j - 2];                // abcd abcde*
                    }
                }
            }
        }
        return dp[m][n];
    }
    // top-down
    Boolean[][] memo;
    public boolean isMatch1(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        memo = new Boolean[m + 1][n + 1];

        return dp(0, 0, s, p);
    }
    private boolean dp(int i, int j, String s, String p) {
        if(memo[i][j] != null) {
            return memo[i][j];
        }
        boolean ans;
        if(j == p.length()) {
            ans = i == s.length();
        } else {
            boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
            if(j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }

        return memo[i][j] = ans;
    }


}
