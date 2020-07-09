package challenge.july;

// Arranging Coins
public class Q441 {

    public static void main(String[] args) {
        Q441 q = new Q441();
        System.out.println(q.arrangeCoins(8));  // 3
    }

    // O(lgn) O(1)
    // without long type
    public int arrangeCoins1(int n) {
        int left = 1;
        int right = n;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (0.5 * mid * (mid + 1) <= n)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
    }
    // with long type
    public int arrangeCoins(int n) {
        long l = 1, r = n;
        long m, k;
        while (l <= r) {
            m = l + (r - l) / 2;
            k = m * (1 + m) / 2;
            if (k == n) {
                return (int) m;
            } else if (k > n) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) r;
    }
}
