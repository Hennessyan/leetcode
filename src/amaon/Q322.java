package amaon;

import java.util.Arrays;

// Coin Change
public class Q322 {

    public static void main(String[] args) {
        Q322 q = new Q322();
        int[] coins = {1,2,5};
        System.out.println(q.coinChange(coins, 11));    //  3
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }
}
