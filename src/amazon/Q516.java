package amazon;
// Longest Palindromic Subsequence
public class Q516 {
    // DP : O(n^2) O(n^2)
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for(int j = i + 1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

    /*method2*/
    public int longestPalindromeSubseq1(String s) {
        int[][] res = new int[s.length()][s.length()];
        return helper(res,s,0,s.length()-1);
    }
    public int helper(int[][] res, String s, int low, int high){
        if(low > high)
            return 0;
        if(res[low][high] != 0)
            return res[low][high];
        if(low == high)
            res[low][high] = 1;
        else if(s.charAt(low) == s.charAt(high))
            res[low][high] = helper(res,s,low+1,high-1) + 2;
        else
            res[low][high] = Math.max(helper(res,s,low+1,high), helper(res,s,low,high-1));
        return res[low][high];
    }
}