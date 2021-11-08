package amazon;
// Maximum Subarray
public class Q53 {
    // O(n) O(1)
    public int maxSubArray0(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = nums[0], cur = nums[0];
        for(int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            max = Math.max(max, cur);
        }
        return max;
    }
    // O(nlgn) O(lgn)
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private int helper(int[] nums, int l, int r) {
        if(l == r) return nums[l];
        int p = l + (r - l) / 2;
        int lm = helper(nums, l, p);
        int rm = helper(nums, p + 1, r);
        int mm = cross(nums, l, r, p);
        return Math.max(mm, Math.max(lm, rm));
    }
    private int cross(int[] nums, int l, int r, int p) {
        int left = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = p; i >= l; i--) {
            sum += nums[i];
            left = Math.max(left, sum);
        }
        sum = 0;
        int right = Integer.MIN_VALUE;
        for(int i = p + 1; i <= r; i++) {
            sum += nums[i];
            right = Math.max(right, sum);
        }
        return left + right;
    }
}
