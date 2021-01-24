package microsoft;

// Coin Change 2
public class Q518 {
    // 第9跟第10行不换顺序的话是总组合数(不管顺序)：【1，1，1】，【1，2】
    // 换了顺序就是就是考虑组合顺序：【1，1，1】，【1，2】，【2，1】
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
