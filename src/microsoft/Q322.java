package microsoft;

import java.util.Arrays;

// Coin Change
public class Q322 {
    // O(amount * n) O(amount)
    public int coinChange0(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // for this question, either order is fine
        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == max? -1 : dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
     int max = amount + 1;
     int[] dp = new int[max];
     Arrays.fill(dp, max);
     dp[0] = 0;
     // for this question, either order is fine
     for(int coin : coins) {
         for(int i = coin; i <= amount; i++) {
             dp[i] = Math.min(dp[i], dp[i - coin] + 1);
         }
     }
     return dp[amount] == max ? -1 : dp[amount];
    }
    // top-down
    private int[] memo;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        return dp(coins, amount);
    }
    private int dp(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        if(amount < 0) {
            return -1;
        }
        if(memo[amount] != 0) {
            return memo[amount];
        }
        int ans = Integer.MAX_VALUE;
        for(int coin : coins) {
            int tmp = dp(coins, amount - coin);
            if(tmp >= 0 && tmp < ans) {
                ans = tmp + 1;
            }
        }
        memo[amount] = ans == Integer.MAX_VALUE ? -1 : ans;
        return memo[amount];
    }
}
