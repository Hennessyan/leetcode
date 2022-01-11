package amazon;
// Find Pivot Index
public class Q724 {
    // O(n) O(1)
    public int pivotIndex(int[] nums) {
        int sum = 0, total = 0;
        for(int num : nums) {
            sum += num;
        }
        for(int i = 0; i < nums.length; i++) {
            if(total == sum - nums[i] - total) return i;
            total += nums[i];
        }
        return -1;
    }
}
