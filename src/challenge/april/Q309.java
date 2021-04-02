package challenge.april;

// Best Time to Buy and Sell Stock with Cooldown
public class Q309 {

    public static void main(String[] args) {
        Q309 q = new Q309();
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(q.maxProfit(prices));
    }

    /*DP*/
    //https://discuss.leetcode.com/topic/31349/7-line-java-only-consider-sell-and-cooldown
//	public int maxProfit(int[] prices) {
//		int profit1 = 0, profit2 = 0;
//		for(int i = 1; i < prices.length; i++){
//			int temp = profit1;
//			profit1 = Math.max(profit1 + prices[i] - prices[i-1], profit2);
//			profit2 = Math.max(temp, profit2);
//		}
//		return Math.max(profit1, profit2);
//	}
    /*three status*/
    //https://discuss.leetcode.com/topic/30680/share-my-dp-solution-by-state-machine-thinking
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] s0 = new int[prices.length];
        int[] s1 = new int[prices.length];
        int[] s2 = new int[prices.length];
        s0[0] = 0;
        s1[0] = -prices[0];                //买入是需要卖的,相当于是负的多少钱
        s2[0] = Integer.MIN_VALUE;        //也可以是0
        for (int i = 1; i < prices.length; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }
        return Math.max(s0[prices.length - 1], s2[prices.length - 1]);    //s1的肯定比s0的小
    }

    public int maxProfit3(int[] prices) {

        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;

        for (int price : prices) {
            int preSold = sold;

            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }

        return Math.max(sold, reset);
    }
}
