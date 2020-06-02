package challenge.may;
// Valid Perfect Square
public class Q367 {

    public static void main(String[] args) {

    }

    /**
     * Newton's method
     * [f(x2) - f(x1)] / [x2 - x1] = f'(x1)
     *  f(x2) = 0
     *  x1 - f(x1) / f'(x1) = x2
     *  (x1 + num / x1) / 2 = x2
     *
     *  O(log(n)) O(1)
     */
    public boolean isPerfectSquare(int num) {
        if(num < 2) {
            return true;
        }
        long x = num / 2;
        while(x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }
    // O(log(n)) O(1)
    public boolean isPerfectSquare1(int num) {
        if (num < 2) {
            return true;
        }

        long left = 2, right = num / 2, x, guessSquared;
        while (left <= right) {
            x = left + (right - left) / 2;
            guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            }
            if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }

    public boolean isPerfectSquare2(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2; // to avoid overflow incase (left+right)>2147483647
            int res = num / mid, remain = num % mid;
            if (res == mid && remain == 0) return true; // check if mid * mid == num
            if (res > mid) { // mid is small -> go right to increase mid
                left = mid + 1;
            } else {
                right = mid - 1; // mid is large -> to left to decrease mid
            }
        }
        return false;
    }

    public boolean isPerfectSquare3(int num) {
        int root = 0, bit = 1 << 16;
        while(bit > 0){
            root |= bit;
            if(root > num / root)
                root ^= bit;
            bit >>= 1;
        }
        return root*root == num;
    }
}
