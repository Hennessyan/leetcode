package challenge.april;

// Best Time to Buy and Sell Stock
public class Q121 {

    public static void main(String[] args) {
        Q121 q = new Q121();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(q.maxProfit(prices));    //5
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = 0, minValue = Integer.MAX_VALUE;
        for (int p : prices) {
            if (p < minValue) {
                minValue = p;
            } else {
                maxProfit = Math.max(maxProfit, p - minValue);
            }
        }
        return maxProfit;
    }
}
