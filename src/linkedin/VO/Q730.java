package linkedin.VO;
// Count Different Palindromic Subsequences
public class Q730 {

    // s.charAt(i) == s.charAt(j)
    // "bccb" => f[i][j] = 2 * f[i+1][j-1] + 2
    // 2 => "b", "bb"
    // f[i+1][j-1] => "c" "cc"
    // 2 * f[i+1][j-1] => with / without b => "c" "cc" "bcb" "bccb"

    // s.charAt(i) != s.charAt(j)
    // f[i][j] = f[i+1][j] + f[i][j-1] - f[i+1][j-1] (duplicates)
    // "abccb" => "abcc" + "bccb" - "bcc"

    // base case count(1) = 1,  count(0) = 0

    // above part does not consider duplicate subsequence scenarios

    /*
    1.if one character inside the subarray same as first and last =>
    count("bcbcb") => 2 * count("cbc") + 1 => b, bb, bbb, bcb, bccb, bcbcb  + b, c, cc, cbc
    => b occurs twice, hence + 1 rather than + 2.
    count("cbc") => 2 * count("b") + 2 => b, c, cc, cbc

    2.if two or more characters inside the subarray same as first and last =>
    count("bbcabb") => 2 * count("bcab") - count("ca")
    */
    // O(n^2) O(n^2)
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int mod = 1_000_000_007;
        int[][] dp = new int[n][n];

        for(int l = 1; l <= n; l++) {
            for(int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if(l == 1) {
                    dp[i][j] = 1;
                } else {
                    long ans = 0l;
                    if(s.charAt(i) == s.charAt(j)) {
                        int left = i + 1, right = j - 1;
                        char cur = s.charAt(i);
                        ans = 2 * dp[i+1][j-1];
                        // find duplicates with cur
                        while(left <= right && s.charAt(left) != cur) {
                            left++;
                        }
                        while(left <= right && s.charAt(right) != cur) {
                            right--;
                        }
                        if(left == right) { // one character same as cur
                            ans = ans + 1;
                        } else if(left < right) {   // two / more same as cur
                            ans = ans - dp[left+1][right-1];
                        } else {    // no same as cur
                            ans = ans + 2;
                        }
                    } else {
                        ans = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                    }
                    // we may has negative value after overflow, add mod before % will get correct result
                    dp[i][j] = (int) ((ans + mod) % mod);   // don't forget the outer "()"
                }
            }
        }
        return dp[0][n-1];
    }
}
