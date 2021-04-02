package amazon;
// Best Time to Buy and Sell Stock
public class Q121 {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = 0, minPrice = Integer.MAX_VALUE;
        for(int p : prices) {
            if(p < minPrice) {
                minPrice = p;
            } else {
                maxProfit = Math.max(maxProfit, p - minPrice);
            }
        }
        return maxProfit;
    }
}
