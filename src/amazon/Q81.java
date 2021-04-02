package amazon;
// Search in Rotated Sorted Array II
public class Q81 {
    // O(lgn) O(1)
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(nums[m] == target) {
                return true;
            }
            if(nums[m] < nums[r]) {
                if(target > nums[m] && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else if(nums[m] > nums[r]) {
                if(target >= nums[l] && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                r--;    //不能 r = m - 1 !!!
            }
        }
        return false;
    }
}
