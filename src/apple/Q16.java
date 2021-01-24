package apple;

import java.util.Arrays;

// 3Sum Closest
public class Q16 {
    // O(n^2)
    // O(lgn) / O(n) - quick sort is O(lgn)
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) {
            return -1;
        }
        int ans = 0, diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = len - 1;
            while(l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                int absDiff = Math.abs(target - sum);
                if(absDiff < diff) {
                    diff = absDiff;
                    ans = sum;
                }
                if(sum == target) {
                    return ans;
                } else if(sum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ans;
    }
}
