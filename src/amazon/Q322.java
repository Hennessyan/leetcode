package amazon;

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
    // top down
    private int[] memo;
    public int coinChange1(int[] coins, int amount) {
        memo = new int[amount + 1];
        return dfs(coins, amount);
    }
    private int dfs(int[] coins, int amount) {
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
            int tmp = dfs(coins, amount - coin);
            if(tmp >= 0 && tmp < ans) {
                ans = tmp + 1;
            }
        }
        return memo[amount] = ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
