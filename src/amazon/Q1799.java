package amazon;
// Maximize Score After N Operations
public class Q1799 {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1799-maximize-score-after-n-operations/
    // if we already know the optional sequence of the first k paris,
    // it will not affect the decision of the k+1 pair.

    // SC - O(n * 2^2n)
    // TC - SC / n * X => O(4n^2 * 2^2n)
    // state (dp) -> numbers, k-th operation
    // each state X - O((2n)^2)

    int l;
    public int maxScore(int[] nums) {
        l = nums.length;
        // dp[mask][k] - left numbers for k-th operation.
        int[][] dp = new int[1 << l][l / 2 + 1];
        return help(nums, dp, (1 << l) - 1, 1);
    }
    private int help(int[] nums, int[][] dp, int mask, int k) {
        if(mask == 0) return 0;
        if(dp[mask][k] != 0) return dp[mask][k];

        int ans = 0;
        for(int i = 0; i < l; i++) {
            for(int j = i + 1; j < l; j++) {
                if((mask & (1 << i)) > 0 && (mask & (1 << j)) > 0) {
                    ans = Math.max(ans, k * gcd(nums[i], nums[j]) + help(nums, dp, mask ^ (1 << i) ^ (1 << j), k + 1));
                }
            }
        }
        return dp[mask][k] = ans;
    }
    // bottom-up
    // O(n^2 * 2^2n) O(2^2n)
    public int maxScore1(int[] nums) {
        int l = nums.length;
        int[] dp = new int[1 << l];
        for(int mask = 0; mask < (1 << l); mask++) {
            int c = Integer.bitCount(mask);
            if(c % 2 == 1) continue;    // invalid case
            int k = c / 2 + 1;

            for(int i = 0; i < l; i++) {
                for(int j = i + 1; j < l; j++) {
                    if((mask & (1 << i)) + (mask & (1 << j)) == 0) {        // !!!
                        int new_mask = mask | (1 << i) | (1 << j);
                        dp[new_mask] = Math.max(dp[new_mask], k * gcd(nums[i], nums[j]) + dp[mask]);
                    }
                }
            }
        }
        return dp[(1 << l) - 1];    // !!!
    }

    private int gcd(int x, int y) {
        if(x == 0) return y;
        if(y == 0) return x;
        return gcd(y, x % y);
    }

}
