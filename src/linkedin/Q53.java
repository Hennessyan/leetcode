package linkedin;
// Maximum Subarray
public class Q53 {
    // O(n) O(1)
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE, sum = 0;
        for(int num : nums) {
            sum = sum < 0 ? num : sum + num;
            max = Math.max(max, sum);
        }
        return max;
    }
    // O(n^2) O(1)
    public int maxSubArray1(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }

        return maxSubarray;
    }
}
