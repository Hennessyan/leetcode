package linkedin;
// Pow(x, n)
public class Q50 {
    // O(lgn) O(lgn)
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }
    // O(lgn) O(1)
    public double myPow1(double x, int n) {
        long N = (long) n;
        if(N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1.0, cur = x;
        while(N > 0) {
            if(N % 2 == 1) {
                ans *= cur;
            }
            cur *= cur;
            N /= 2;
        }
        return ans;
    }
}
