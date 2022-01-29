package linkedin;

import java.util.Arrays;

// Valid Triangle Number
public class Q611 {

    // if a <= b <= c, just need a + b < c => c already bigger than a and b. hence a + c >= b && b + c >= a always true
    // a + b < c <=> a < c - b, b < c - a

    // O(n^2lgn) O(lgn)
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length < 3) return 0;
        int count = 0, n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < n - 2; i++) {
            int k = i + 2;
            for(int j = i + 1; j < n - 1 && nums[i] != 0; j++) {    // nums[i] != 0 => nums[j], nums[k] != 0
                k = binarySearch(nums, k, n - 1, nums[i] + nums[j]);
                count += k - j - 1;
            }
        }
        return count;
    }
    private int binarySearch(int[] nums, int l, int r, int val) {
        while(l <= r && r < nums.length) {
            int m = l + (r - l) / 2;
            if(nums[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    // linear scan : O(n^2) O(lgn)
    public int triangleNumber1(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int count = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < n - 1 && nums[i] != 0; j++) {
                while(k < n && (nums[i] + nums[j] > nums[k])) {
                    k++;        // does not reset k => amortize to O(n) for L43-L48
                }
                count += k - j - 1;
            }
        }
        return count;
    }
}
