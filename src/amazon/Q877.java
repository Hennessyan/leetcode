package amazon;

import java.util.Arrays;

// Stone Game
// todo : Stone Game II -> VIII
public class Q877 {
    // https://leetcode.com/problems/stone-game/discuss/154610/DP-or-Just-return-true
    // if piles.length is even =>
    // alex can always choose all odd position stones - from tail
    //                           even position stones - from head
    public boolean stoneGame0(int[] piles) {
        return true;
    }
    // dp[i][j] - biggest number of stones you can pick more than opponent
    // if (j - i) is odd, then now Alex pick, dp[i][j] means maximum(alex stone - lee stone)
    // if (j - i) is even, then now Lee pick, dp[i][j] means maximum(lee stone - alex stone)
    // piles[i] - dp[i+1][j] =>
    // if alex picks piles[i], piles[i] + {alex's stones from[i+1,j] - lee's stones from[i+1, j]}
    //                       =>piles[i] - {lee's stones from[i+1, j] - alex's stones from[i+1,j]}
    //                       =>piles[i] - dp[i+1][j]
    // O(n^2) O(n^2)
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) dp[i][i] = piles[i];
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i < n - len; i++) {
                int j = i + len;
                // only use current and next position, so can convert to 1-d array
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
    // O(n^2) O(n)
    public boolean stoneGame1(int[] piles) {
        int[] dp = piles.clone();   //Arrays.copyOf(piles, piles.length);
        for(int l = 1; l <= piles.length; l++) {
            for(int i = 0; i < piles.length - l; i++) {
                dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + l] - dp[i]);
            }
        }
        return dp[0] > 0;
    }

    // wrong answer -> [3, 7, 2, 3]
    public boolean stoneGameXxxx(int[] piles) {
        int alex = 0, lee = 0, l = 0, r = piles.length - 1, i = 0;
        while(l <= r) {
            if(piles[l] >= piles[r]) {
                if(i % 2 == 0) {
                    alex += piles[l];
                } else {
                    lee += piles[l];
                }
                l++;
            } else {
                if(i % 2 == 0) {
                    alex += piles[r];
                } else {
                    lee += piles[r];
                }
                r--;
            }
            i++;
        }
        return alex > lee;
    }
}
