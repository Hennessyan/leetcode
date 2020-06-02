package challenge.april;

// Best Time to Buy and Sell Stock with Transaction Fee
public class Q714 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q714 q = new Q714();
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(q.maxProfit(prices, 2));        //return 8
    }

    //If cash comes from the previous cash, it's fine.
    //If cash is from hold + prices[i] - fee, then in hold = max(hold, cash - prices[i]),
    //cash - prices[i] equals hold + prices[i] - fee - prices[i] which equals hold - fee,
    //which is always smaller than hold, leading to a correct result
    public int maxProfit(int[] prices, int fee) {
        // cash: 手头的现金，即总的赚的金额，同时也是未持股时的现金额
        // hold: 手中有持股时的现金，即总金额减去手中股票的买入价
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {        //应该如备注所示,但因为trick的情况直接写也accept
            //int temp = cash
            // cash是不持有时的现金数,因此原本没有就没有,是cash;原本有就要卖,是hold+prices[i]-fee
            cash = Math.max(cash, hold + prices[i] - fee);
            // hold是持有时的现金数,因此原本持有就持有,是hold;原本没有就要买,是cash-prices[i]
            hold = Math.max(hold, cash - prices[i]);    //cash in this line change to temp
        }
        //最终肯定是没有持有股票时拥有的现金最多,故返回cash
        return cash;
    }
}
