package apple;
// Sum of Square Numbers

public class Q633 {

    // need to be long to avoid overflow.

    // O(sqrt(c) * lgc) O(1)
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);    // O(lgc)
            if (b == (int) b)
                return true;
        }
        return false;
    }
    // O(sqrt(c) * lgc) O(lgc) - for stack
    public boolean judgeSquareSum1(int c) {
        for (long a = 0; a * a <= c; a++) {
            int b = c - (int)(a * a);
            if (binary_search(0, b, b))
                return true;
        }
        return false;
    }
    public boolean binary_search(long s, long e, int n) {
        if (s > e)
            return false;
        long mid = s + (e - s) / 2;
        if (mid * mid == n)
            return true;
        if (mid * mid > n)
            return binary_search(s, mid - 1, n);
        return binary_search(mid + 1, e, n);
    }
    // TLE - O(sqrt(c) * lgc) O(1)
    // 1 + 3 + 5 + (2n - 1)
    // 2*1-1 + 2*2-1 + 2*3-1 + ... + 2n-1
    // 2(1+n)*n/2-n => n^2
    public boolean judgeSquareSum2(int c) {
        for (long a = 0; a * a <= c; a++) {
            int b =  c - (int)(a * a);
            int i = 1, sum = 0;
            while (sum < b) {
                sum += i;
                i += 2;
            }
            if (sum == b)
                return true;
        }
        return false;
    }
}
