package amazon;
// Maximum Erasure Value
// Q3
public class Q1695 {
    // O(n) O(n)
    public int maximumUniqueSubarray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sums = new int[n + 1];
        int[] index = new int[10001];
        for(int i = 0; i < n; i++) {

        }
        int max = 0;
        int i = 0, j = 0;
        while(j < n) {
            sums[j + 1] = sums[j] + nums[j];
            i = Math.max(i, index[nums[j]]);
            max = Math.max(max, sums[j + 1] - sums[i]);
            index[nums[j++]] = j;
        }
        return max;
    }
}
