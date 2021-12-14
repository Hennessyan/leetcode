package amazon;
// Maximum Length of Subarray With Positive Product
public class Q1567 {
    // DP : O(n) O(1)
    public int getMaxLen(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int ans = 0, pos = 0, neg = 0;
        for(int num : nums) {
            if(num == 0) {
                pos = 0;
                neg = 0;
            } else if(num > 0) {
                pos++;
                neg = neg == 0 ? 0 : neg + 1;
            } else {
                int tmp = pos;
                pos = neg == 0 ? 0 : neg + 1;
                neg = tmp == 0 ? 1 : tmp + 1;   // => neg = tmp + 1;
            }
            ans = Math.max(ans, pos);
        }
        return ans;
    }
}
