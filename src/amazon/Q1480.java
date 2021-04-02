package amazon;
// Running Sum of 1d Array
public class Q1480 {
    // O(n) O(1)
    public int[] runningSum(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
