package amazon;
// Find Minimum in Rotated Sorted Array II
public class Q154 {
    // O(lgn) O(1)
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        if(nums[l] < nums[r]) {
            return nums[l];
        }
        while(l < r) {
            int m = l + (r - l) / 2;
            if(nums[m] < nums[r]) {
                r = m;
            }else if(nums[m] > nums[r]) {
                l = m + 1;
            }else {
                r--;
            }
        }
        return nums[l];
    }
}
