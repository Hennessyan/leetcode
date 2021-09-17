package amazon;
// Consecutive Numbers Sum
public class Q829 {
    // n = (x+1) + (x+2) + ... (x+k) = xk + k(k+1)/2
    // x = n/k - (k+1)/2 >= 0 && x is interger
    // 2n >= k(k+1) => 2n + 1/4 >= (k+0.5)^2
    // => k <= sqrt(2n + 0.25) - 0.5
    // O(sqrt(n)) O(1)
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        int upper_limit = (int) (Math.sqrt(2 * n + 0.25) - 0.5);
        for(int k = 1; k <= upper_limit; k++) {
            if((n - k * (k + 1) / 2) % k == 0) {    // x is integer
                count++;
            }
        }
        return count;
    }
}
