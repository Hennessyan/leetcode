package amazon;
// Best Time to Buy and Sell Stock III
public class Q123 {
    //http://blog.csdn.net/linhuanmars/article/details/23236995

    // local[i][j] -  max profit at ith day with j transactions, and last transaction happens at ith day.
    // global[i][j] - max profit at ith day with j transactions
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        //k == 2, then we assign space as 3
        int[] local = new int[3];
        int[] global = new int[3];

        for(int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for(int j = 2; j > 0; j--) {    //we should use[i-1][j-1] 正着来会覆盖掉
                local[j] = Math.max(global[j-1] + Math.max(0, diff), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[2];
    }

    //https://discuss.leetcode.com/topic/39751/my-explanation-for-o-n-solution
    public int maxProfit1(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sale1 = 0, sale2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sale1 = Math.max(sale1, buy1 + price);
            buy2 = Math.max(buy2, sale1 - price);
            sale2 = Math.max(sale2, buy2 + price);
        }
        return sale2;
    }
}
