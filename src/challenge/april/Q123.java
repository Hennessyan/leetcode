package challenge.april;

// Best Time to Buy and Sell Stock III
public class Q123 {

    public static void main(String[] args) {
        Q123 q = new Q123();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(q.maxProfit(prices));    //6
    }

    //https://discuss.leetcode.com/topic/39751/my-explanation-for-o-n-solution
    public int maxProfit(int[] prices) {
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

    //http://blog.csdn.net/linhuanmars/article/details/23236995
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //k == 2, then we assign space as 3
        int[] local = new int[3];
        int[] global = new int[3];

        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = 2; j >= 1; j--) {    //we should use[i-1][j-1] 正着来会覆盖掉
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[2];
    }
}
