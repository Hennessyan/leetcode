package challenge.jan21;
// Edit Distance
public class Q72 {

    // O(mn) O(mn)
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m * n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for(int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int top = dp[i-1][j];
                int left = dp[i][j-1];
                int topLeft = dp[i-1][j-1];
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    topLeft -= 1;
                }
                dp[i][j] = Math.min(Math.min(left, top), topLeft) + 1;
            }
        }
        return dp[m][n];
    }
    // O(mn) O(n)
    public int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m * n == 0) {
            return m + n;
        }
        int[][] dp = new int[2][n + 1];
        for(int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        int flag = 1;
        for(int i = 1; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(j == 0) {
                    dp[flag][j] = i;
                }else {
                    int left = dp[flag][j - 1];
                    int top = dp[flag ^ 1][j];
                    int topLeft = dp[flag ^ 1][j - 1];
                    if(word1.charAt(i -1) == word2.charAt(j - 1)) {
                        topLeft -= 1;
                    }
                    dp[flag][j] = Math.min(Math.min(left, top), topLeft) + 1;
                }
            }
            flag ^= 1;
        }
        return dp[flag ^ 1][n];
    }
}
