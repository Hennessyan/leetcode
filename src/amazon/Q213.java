package amazon;
// House Robber II
public class Q213 {

    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 1), rob(nums, 1, n));
    }
    private int rob(int[] nums, int l, int r) {
        int rob = nums[l++], notRob = 0;
        while(l < r) {
            int tmp = rob;
            rob = notRob + nums[l++];
            notRob = Math.max(tmp, notRob);
        }
        return Math.max(rob, notRob);
    }
    private int rob1(int[] nums, int l, int r) {
        int b = nums[l++], a = 0;
        while(l < r) {
            int c = Math.max(a + nums[l++], b);
            a = b;
            b = c;
        }
        return b;
    }
}
