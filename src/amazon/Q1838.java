package amazon;

import java.util.Arrays;

// Frequency of the Most Frequent Element
// similar problems: https://leetcode.com/problems/frequency-of-the-most-frequent-element/discuss/1175090/JavaC%2B%2BPython-Sliding-Window
public class Q1838 {

    // at most k operations !!! => sum + k >= (long) A[j] * (j - i + 1) is valid !

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 0, l = 0, r = 0, n = nums.length;
        long sum = 0;
        while(r < n) {
            sum += nums[r];
            while(sum + k < (long) nums[r] * (r - l + 1)) {
                sum -= nums[l++];
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

    public int maxFrequency(int[] nums, long k) {
        Arrays.sort(nums);
        int l = 0, r = 0, n = nums.length;
        while(r < n) {
            k += nums[r];
            if(k < (long) nums[r] * (r - l + 1)) {
                k -= nums[l++];
            }
            r++;
        }
        return r - l;
    }
}
