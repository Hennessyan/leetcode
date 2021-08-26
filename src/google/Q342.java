package google;
// Power of Four
public class Q342 {
    // O(log-4-n) O(1)
    public boolean isPowerOfFour(int n) {
        if(n < 1) {
            return false;
        }
        while(n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
    // O(log-4-n) O(log-4-n)
    public boolean isPowerOfFour1(int n) {
        return Integer.toString(n, 4).matches("^10*$");
    }
    // O(lgn) O(1)
    public boolean isPowerOfFour2(int n) {
        return (Math.log10(n) / Math.log10(4)) % 1 == 0;
    }
    // upper bound of int is 2^31 - 1,
    // for 4 => 0 ~ 15
    public boolean isPowerOfFour3(int n) {
        int l = 0, r = 15;
        while(l <= r) {
            int m = l + (r - l) / 2;
            int val = (int) Math.pow(4, m);
            if(val == n) {
                return true;
            } else if(val > n) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
    // O(1) O(1)
    // 2^2k => power of 4 if and only if k is even
    // 4^k % 3 = (3+1)^k % 3 => 1
    public boolean isPowerOfFour4(int n) {
        return n > 0 && (n & (n -1)) == 0 && (n % 3 == 1);
    }
}
