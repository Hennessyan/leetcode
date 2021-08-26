package apple;

import java.util.Arrays;

// 3Sum Closest
// next - Q259
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
    // O(n^2lgn) O(lgn)
    public int threeSumClosest1(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, complement = 0;
        for(int i = 0; i < n - 2 && diff != 0; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                complement = target - nums[i] - nums[j];
                int index = Arrays.binarySearch(nums, j + 1, n, complement);
                int hi = index < 0 ? -index - 1 : index, lo = hi - 1;
                if(hi < n && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];       // same as L52
                if(lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];       // we need to calculate res in L55, hence diff can't be Math.abs(complement - nums[lo])
            }
        }
        return target - diff;
    }
}
