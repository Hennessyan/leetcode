package challenge.june;
// Coin Change 2
public class Q518 {

    public static void main(String[] args) {
        Q518 q = new Q518();
        int[] coins = {1, 2, 5};
        System.out.println(q.change(5, coins));     //  4
    }

    public int change(int amount, int[] coins) {
        int max = amount + 1;
        int[] dp = new int[max];
        dp[0] = 1;
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
