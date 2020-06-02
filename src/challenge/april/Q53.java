package challenge.april;

// Maximum Subarray
public class Q53 {

    public static void main(String[] args) {
        Q53 q = new Q53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(q.maxSubArray(nums));    //6 => [4,-1,2,1]
    }
    // O(n) O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = sum < 0 ? nums[i] : sum + nums[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    // Divide and Conquer: O(nlgn) O(lgn) - stack
    public int maxSubArray1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private int helper(int[] nums, int l, int r) {
        if(l == r) {return nums[l];}
        int p = l + (r - l) / 2;
        int lmax = helper(nums, l, p);
        int rmax = helper(nums, p + 1, r);
        int mmax = cross(nums, l, r , p);
        return Math.max(mmax, Math.max(lmax, rmax));
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
