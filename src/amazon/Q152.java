package amazon;
// Maximum Product Subarray
public class Q152 {

    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = nums[0], min = nums[0], res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int a = nums[i] * max;
            int b = nums[i] * min;
            max = Math.max(nums[i], Math.max(a,b));
            min = Math.min(nums[i], Math.min(a,b));
            res = Math.max(res, max);
        }
        return res;
    }
}
