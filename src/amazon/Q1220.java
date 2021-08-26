package amazon;

import java.util.Arrays;

// Count Vowels Permutation
public class Q1220 {

    /**
     *  a  0
     *  e  1
     *  i  2
     *  o  3
     *  u  4
     **/
    // O(n) O(1)
    private int[][] nexts = {{1,2,4}, {0,2}, {1,3}, {2}, {2,3}};
    public int countVowelPermutation(int n) {
        long[][] dp = new long[2][5];
        Arrays.fill(dp[0], 1);
        int M = (int) (1e9+7);
        int a = 0;
        for(int i = 1; i < n; i++) {
            int b = a ^ 1;
            Arrays.fill(dp[b], 0);  // don't forget clean up next row !
            for(int j = 0; j < 5; j++) {
                for(int k : nexts[j]) {
                    dp[b][j] = (dp[b][j] + dp[a][k]) % M;
                }
            }
            a = b;
        }
        long total = 0;
        // int a = n - 1;
        total = (dp[a][0] + dp[a][1] + dp[a][2] + dp[a][3] + dp[a][4]) % M;
        return (int) total;
    }

    public int countVowelPermutation1(int n) {
        long aCount = 1, eCount = 1, iCount = 1, oCount = 1, uCount = 1;
        int MOD = 1000000007;

        for (int i = 1; i < n; i++) {
            long aCountNew = (eCount + iCount + uCount) % MOD;
            long eCountNew = (aCount + iCount) % MOD;
            long iCountNew = (eCount + oCount) % MOD;
            long oCountNew = (iCount) % MOD;
            long uCountNew = (iCount + oCount) % MOD;
            aCount = aCountNew;
            eCount = eCountNew;
            iCount = iCountNew;
            oCount = oCountNew;
            uCount = uCountNew;
        }
        long result = (aCount + eCount + iCount + oCount + uCount)  % MOD;
        return (int)result;
    }
}
