package amazon;
// Jump Game II
public class Q45 {

    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int cur = 0, maxJump = 0, n = nums.length, count = 0;
        for(int i = 0; i < n - 1; i++) {
            maxJump = Math.max(maxJump, nums[i] + i);
            if(cur == i) {
                count++;
                if(maxJump >= n - 1) {
                    break;
                }
                cur = maxJump;
            }
        }
        return count;
    }
}
