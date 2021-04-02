package facebook;
// Find Minimum in Rotated Sorted Array
public class Q153 {
    // O(lgn) O(1)
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        if(nums[0] < nums[r]) {
            return nums[0];
        }
        while(l < r) {
            int m = l + (r - l) / 2;
            if(nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
