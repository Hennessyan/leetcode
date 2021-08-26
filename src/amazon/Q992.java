package amazon;
// Subarrays with K Different Integers
public class Q992 {
    // O(n) O(n)
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }
    private int atMost(int[] A, int k) {
        int max = 0, l = 0, r = 0, n = A.length, count = 0; // max = Integer.MIN_VALUE; - can set min to avoid overflow
        int[] map = new int[n + 1]; // 1 <= A[i] <= A.length
        while(r < n) {
            if(map[A[r++]]++ == 0) {
                count++;
            }
            while(count > k) {
                if(--map[A[l++]] == 0) {
                    count--;
                }
            }
            max += r - l;
        }
        return max;
    }
}
