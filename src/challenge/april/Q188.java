package challenge.april;

// Best Time to Buy and Sell Stock IV
public class Q188 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q188 q = new Q188();
        int[] prices = {1, 2, 3, 4, 5, 4, 1};
        System.out.println(q.maxProfit(3, prices));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        if (k >= prices.length / 2) {
            int maxPro = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    maxPro += prices[i] - prices[i - 1];
            }
            return maxPro;
        }

        int[] local = new int[k + 1];
        int[] global = new int[k + 1];

        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

    /*method2*/
    //https://discuss.leetcode.com/topic/26169/clean-java-dp-solution-with-comment
    public int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        if (k >= n / 2) {
            int maxprofit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxprofit += prices[i] - prices[i - 1];
                }
            }
            return maxprofit;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }
}
