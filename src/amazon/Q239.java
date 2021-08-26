package amazon;

import java.util.Deque;
import java.util.LinkedList;

// Sliding Window Maximum
public class  Q239 {
    // O(n) O(n)
    Deque<Integer> deque;   // ArrayDeque is fine.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) {
            return new int[0];
        }
        if(k == 1) return nums;
        deque = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            shift(nums, i, k);
            deque.addLast(i);
        }
        int[] res = new int[n - k + 1];
        res[0] = nums[deque.getFirst()];
        for(int i = k; i < n; i++) {
            shift(nums, i, k);
            deque.addLast(i);
            res[i - k + 1] = nums[deque.getFirst()];
        }
        return res;
    }
    private void shift(int[] nums, int i, int k) {
        if(!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }
        while(!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
            deque.removeLast();
        }
    }
    // DP : O(n) O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) {
            return new int[0];
        }
        if(k == 1) return nums;
        int[] l = new int[n];
        int[] r = new int[n];
        for(int i = 0; i < n; i++) {
            if(i % k == 0) {
                l[i] = nums[i];
            } else {
                l[i] = Math.max(nums[i], l[i - 1]);
            }
        }
        r[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            if((i + 1) % k == 0) {
                r[i] = nums[i];
            } else {
                r[i] = Math.max(nums[i], r[i + 1]);
            }
        }
        int[] res = new int[n - k + 1];
        for(int i = k - 1; i < n; i++) {
            res[i - k + 1] = Math.max(l[i], r[i - k + 1]);
        }
        return res;
    }
    // more concise.
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }
}
