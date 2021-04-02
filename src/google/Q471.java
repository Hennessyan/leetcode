package google;
// Encode String with Shortest Length
public class Q471 {

    //O(n^3) O(n^3) - 算上string长度
    public String encode(String s) { //aabcaabcd 以此为例可以go through一下代码
        int n = s.length();
        String[][] dp = new String[n][n];

        for(int l = 1; l <= n; l++) {
            for(int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                String now = s.substring(i, j + 1);
                dp[i][j] = now;
                for(int k = i; k < j; k++) {
                    if((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
                // "abcdabcd" => "abcdabcdabcdabcd"
                int p = (now + now).indexOf(now, 1);
                if(p < now.length()) {  // repeat exist
                    now = (now.length() / p) + "[" + dp[i][i+p-1] + "]";    //注意这里
                }
                if(now.length() < dp[i][j].length()) {  // "aaa" => "3[a]"
                    dp[i][j] = now;
                }
            }
        }
        return dp[0][n - 1];
    }
}
