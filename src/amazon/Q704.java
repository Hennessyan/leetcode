package amazon;
// Binary Search
public class Q704 {
    // O(lgn) O(1)
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(nums[m] == target) {
                return m;
            } else if(nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}