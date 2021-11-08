package amazon;
// Single Element in a Sorted Array
public class Q540 {
    // O(lgn) O(1)
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(m % 2 == 1) {
                m--;
            }
            if(nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
    // 0 1 2 3 4
    // m = 2, r = 4 => even = false [2,4] has 3 numbers.
    public int singleNonDuplicate1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            boolean even = (r - m) % 2 == 1;
            if(nums[m] == nums[m + 1]) {
                if(even) {
                    r = m - 1;
                } else {
                    l = m + 2;
                }
            } else if(nums[m] == nums[m - 1]) {
                if(even) {
                    l = m + 1;
                } else {
                    r = m - 2;
                }
            } else {
                return nums[m];
            }
        }
        return nums[l];
    }
}
