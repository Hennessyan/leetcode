package microsoft;
// Partition Array into Disjoint Intervals
public class Q915 {
    // O(n) O(n)
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[n - 1] = nums[n - 1];
        for(int i = 1; i < n; i++) {
            max[i] = Math.max(max[i - 1], nums[i]);
            min[n - 1 - i] = Math.min(min[n - i], nums[n - 1 - i]);
        }

        for(int i = 0; i < n - 1; i++) {
            if(max[i] <= min[i + 1]) return i + 1;
        }
        return n;
    }
    // O(n) O(1) - predict the result as next one, update it if needed.
    public int partitionDisjoint1(int[] nums) {
        int n = nums.length, len = 1;
        int curMax = nums[0], max = nums[0];
        for(int i = 1; i < n; i++) {
            curMax = Math.max(curMax, nums[i]);

            if(nums[i] < max) {
                max = curMax;
                len = i + 1;
            }
        }
        return len;
    }
}
