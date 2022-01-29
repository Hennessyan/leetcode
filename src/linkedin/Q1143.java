package linkedin;
// Longest Common Subsequence
public class Q1143 {

    public int longestCommonSubsequence(String t1, String t2) {
        int l1 = t1.length(), l2 = t2.length();
        int[] dp = new int[l2 + 1];
        for(int i = 0; i < l1; i++) {
            int pre = dp[0];
            for(int j = 0; j < l2; j++) {
                int tmp = dp[j+1];
                if(t1.charAt(i) == t2.charAt(j)) {
                    dp[j+1] = pre + 1;
                } else {
                    dp[j+1] = Math.max(dp[j+1], dp[j]);
                }
                pre = tmp;
            }
        }
        return dp[l2];
    }

    public int longestCommonSubsequence1(String t1, String t2) {
        int l1 = t1.length(), l2 = t2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for(int i = 0; i < l1; i++) {
            for(int j = 0; j < l2; j++) {
                dp[i][j] = -1;
            }
        }
        return help(t1, t2, 0, 0, dp);
    }
    private int help(String t1, String t2, int i, int j, int[][] dp) {
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if(t1.charAt(i) == t2.charAt(j)) {
            ans = 1 + help(t1, t2, i + 1, j + 1, dp);
        } else {
            ans = Math.max(help(t1, t2, i + 1, j, dp), help(t1, t2, i, j + 1, dp));
        }
        return dp[i][j] = ans;
    }
}
