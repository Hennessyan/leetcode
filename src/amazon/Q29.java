package amazon;
// Divide Two Integers
public class Q29 {
    // O(lgm) O(1) - loop over the bits of our dividend
    public int divide(int dividend, int divisor) {
        long m = Math.abs((long) dividend);
        long n = Math.abs((long) divisor);

        if(m < n || n == 0) {
            return 0;
        }

        boolean negative = (dividend > 0) ^ (divisor > 0);
        long res = 0;
        while(m >= n) {
            long a = n, p = 1;
            while(m >= (a << 1)) {  //  注意这里的实现,并不是 a << p, p <<= 1.
                a <<= 1;
                p <<= 1;
            }
            m -= a;
            res += p;
        }
        if(negative) {
            res = -res;
        }
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }
}
