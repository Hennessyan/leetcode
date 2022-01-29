package linkedin;
// Minimum Insertion Steps to Make a String Palindrome
// Q5, Q516, Q1682
public class Q1312 {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int l = 1; l <= n; l++) {
            for(int i = 0; i < n - l + 1; i++) {
                if(l == 1) dp[i][i] = 1;
                else {
                    int j = i + l - 1;
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return n - dp[0][n-1];
    }
}
