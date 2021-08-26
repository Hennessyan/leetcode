package facebook;
// Power of Two
public class Q231 {
    // O(1) O(1)
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (-n)) == n;
    }

    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
