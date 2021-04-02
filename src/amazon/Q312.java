package amazon;
// Burst Balloons
public class Q312 {

    public static void main(String[] args) {
        Q312 q = new Q312();
        int[] nums = {3,1,5,8};
        System.out.println(q.maxCoins(nums));   // 167 -> 1, 5, 3, 8
    }
    // O(n^3) O(n^2)
    // cost[i][j] : max-coins of (nums[i] ~ nums[j])
    // ans = cost[0][n-1]
    // cost[i][j] = max(cost[i][k-1] + cost[k+1][j] + nums[i-1] * nums[k] * nums[j+1]) i <= k <= j

    // i-1 | i | i+1 | ... | k-1 | k | k+1 | ... | j-1 | j | j+1
    //       |________________|        |_________________|
    //            cost[i][k-1]              cost[k+1][j]
    // http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-312-burst-balloons/
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] cost = new int[len][len];

        for(int l = 1; l <= len; l++) {
            for(int i = 0; i < len - l + 1; i++) {
                int j = i + l - 1;
                for(int k = i; k <= j; k++) {
                    int c1 = k == i ? 0 : cost[i][k - 1];
                    int c2 = k == j ? 0 : cost[k + 1][j];
                    int a = i - 1 < 0 ? 1 : nums[i - 1];
                    int b = j + 1 == len ? 1 : nums[j + 1];
                    cost[i][j] = Math.max(cost[i][j], c1 + c2 + a * nums[k] * b);
                }
            }
        }
        return cost[0][len - 1];
    }
    // 每次破坏一个气球会导致数组改变,不好追踪记录它. => 因为我们可以看作每次给数组中增加一个气球,倒着计算这个问题.
    // 利用分治的思想,每给数组加入一个气球,那再结合空余位置的结果就好了.
    // DP Top-Down
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for(int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] memo = new int[n + 2][n + 2];

        return dp(memo, arr, 0, n + 1);
    }
    //  0  1  2  3  4  5
    // [1, 3, 1, 5, 8, 1]
    private int dp(int[][] memo, int[] arr, int l, int r) {
        if(l + 1 == r) {
            return 0;
        }
        if(memo[l][r] != 0) {
            return memo[l][r];
        }
        for(int i = l + 1; i < r; i++) {
            memo[l][r] = Math.max(memo[l][r], arr[l] * arr[i] * arr[r] + dp(memo, arr, l, i) + dp(memo, arr, i, r));
        }
        return memo[l][r];
    }
    // DP  Bottom-up
    // 这个逻辑不好理清楚,用前两种
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for(int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];

        for(int l = n; l >= 0; l--) {
            for(int r = l + 2; r < n + 2; r++) {
                for(int k = l + 1; k < r; k++) {
                    dp[l][r] = Math.max(dp[l][r],
                            arr[l] * arr[k] * arr[r] + dp[l][k] + dp[k][r]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
