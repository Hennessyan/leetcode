package challenge;
// Minimum Operations to Make Array Equal
public class Q1551 {

    public int minOperations(int n) {
        int l = 0, r = n - 1;
        int total = 0;
        while(l < r) {
            total += (2 * r + 1) - n;
            l++;
            r--;
        }
        return total;
    }

    public int minOperations1(int n) {
        int res = 0;
        // compute the sum:
        // (n - 1) + (n - 3) + (n - 5) + ... + 1 (or 0)
        while (n > 0) {
            res += n - 1;
            n -= 2;
        }
        return res;
    }
}
