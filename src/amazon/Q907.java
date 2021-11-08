package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

//
public class Q907 {
    // O(n) O(n)
    public int sumSubarrayMins(int[] A) {
        long sum = 0, mod = (long) (1e9 + 7);
        Deque<int[]> stack = new ArrayDeque<>();
        int n = A.length;
        // l[i], the length of contiguous left subarray in which the number is strict bigger than A[i],
        // r[i], the length of contiguous left subarray in which the number is bigger than or equal to A[i],
        int[] l = new int[n];
        int[] r = new int[n];
        for(int i = 0; i < n; i++) {
            int count = 1;
            while(!stack.isEmpty() && stack.peek()[0] > A[i]) {
                count += stack.pop()[1];
            }
            stack.push(new int[]{A[i], count});
            l[i] = count;
        }
        stack.clear();
        for(int i = n - 1; i >= 0; i--) {
            int count = 1;
            while(!stack.isEmpty() && stack.peek()[0] >= A[i]) {
                count += stack.pop()[1];
            }
            stack.push(new int[]{A[i], count});
            r[i] = count;
        }
        for(int i = 0; i < n; i++) {
            sum = (sum + (long) l[i] * r[i] * A[i]) % mod;
        }
        return (int) sum;
    }
}
