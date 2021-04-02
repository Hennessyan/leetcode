package facebook;
// Find Peak Element
public class Q162 {
    // O(n) O(1)
    public int findPeakElement0(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
    // recursive
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    private int search(int[] nums, int l, int r) {
        if(l == r) {
            return l;
        }
        int m = l + (r - l) / 2;
        if(nums[m] > nums[m + 1]) {
            return search(nums, l, m);
        }
        return search(nums, m + 1, r);
    }

    // iterative
    public int findPeakElement1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(nums[m] < nums[m + 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public int findPeakElement2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        while(l < r) {
            if(l + 1 == r) {
                return nums[l] < nums[r] ? r : l;
            } else {
                int m = l + (r - l) / 2;
                if(nums[m - 1] < nums[m] && nums[m] > nums[m + 1]) {
                    return m;
                } else if(nums[m - 1] < nums[m] && nums[m] < nums[m + 1]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return l;   // length == 1
    }
}
