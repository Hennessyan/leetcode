package doordash;
// 1359. Count All Valid Pickup and Delivery Options
public class Q1359 {
    //insert ith pair into (i-1) pairs
    // first has 2*i-1 choices
    // second has 2*i choices
    // (2i-1)*2i / 2  as first always before second.
    public int countOrders(int n) {
        long ans = 1l, mod = (long) (1e9 + 7);
        for(int i = 1; i <= n; i++) {
            ans = ans * (2 * i - 1) * i % mod;
        }
        return (int) ans;
    }
}
