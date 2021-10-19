package amazon;
// House Robber
public class Q198 {
    // O(n) O(1)
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int rob = nums[0], notRob = 0;
        for(int i = 1; i < nums.length; i++) {
            int tmp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(notRob, tmp);
        }
        return Math.max(rob, notRob);
    }

    public int rob1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int a = 0, b = nums[0];
        for(int i = 1; i < n; i++) {
            int cur = Math.max(a + nums[i], b);
            a = b;
            b = cur;
        }
        return b;
    }
}
