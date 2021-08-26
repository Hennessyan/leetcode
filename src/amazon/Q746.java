package amazon;
// Min Cost Climbing Stairs
public class Q746 {
    // DP - bottom up: O(n) O(n)
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for(int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }
    // DP - top down: O(n) O(n)
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        Integer[] memo = new Integer[n + 1];
        return help(cost, n, memo);
    }
    private int help(int[] cost, int n, Integer[] memo) {
        if(n < 2) {
            return 0;
        }
        if(memo[n] != null) {
            return memo[n];
        }

        return memo[n] = Math.min(cost[n-1] + help(cost, n - 1, memo), cost[n-2] + help(cost, n - 2, memo));
    }
    // O(n) O(1)
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length, downOne = 0, downTwo = 0;
        for(int i = 2; i <= n; i++) {
            int tmp = downOne;
            downOne = Math.min(downTwo + cost[i - 2], downOne + cost[i - 1]);
            downTwo = tmp;
        }
        return downOne;
    }
}
