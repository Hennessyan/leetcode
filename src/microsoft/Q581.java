package microsoft;

import java.util.Arrays;
import java.util.Stack;

// Shortest Unsorted Continuous Subarray
public class Q581 {
    // O(nlgn) O(n)
    public int findUnsortedSubarray(int[] nums) {
        int[] s = nums.clone();
        Arrays.sort(s);
        int l = nums.length, r = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != s[i]) {
                l = Math.min(l, i);
                r = Math.max(r, i);
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
    // O(n) O(n)
    public int findUnsortedSubarray2(int[] nums) {
        Stack < Integer > stack = new Stack< Integer >();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
    // O(n) O(1)
    public int findUnsortedSubarray3(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            if(nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i - 1]);
            }
        }
        int l = 0, r = n - 1;
        while(l < n && nums[l] <= min) {
            l++;
        }
        while(r >= 0 && nums[r] >= max) {
            r--;
        }
        return l > r ? 0 : r - l + 1;
    }
    // O(n) O(1)
    public int findUnsortedSubarray0(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int start = 0, end = -1;
        int l = 0, r = nums.length - 1;
        while(l < nums.length) {
            // find valley
            if(nums[l] >= max) {    // >= 不是 >
                max = nums[l];
            } else {
                end = l;
            }

            // find peak
            if(nums[r] <= min) {    // <= 不是 <
                min = nums[r];
            } else {
                start = r;
            }
            l++;
            r--;
        }
        return end - start + 1;
    }
}
