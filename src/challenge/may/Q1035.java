package challenge.may;

// Uncrossed Lines
public class Q1035 {

    public int maxUncrossedLines(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public int maxUncrossedLines1(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }
        int m = A.length, n = B.length;
        int flag = 1;
        int[][] dp = new int[2][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[flag][j] = 1 + dp[flag ^ 1][j - 1];
                } else {
                    dp[flag][j] = Math.max(dp[flag ^ 1][j], dp[flag][j - 1]);
                }
            }
            flag ^= 1;
        }
        return dp[flag ^ 1][n];
    }

}
