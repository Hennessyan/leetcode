package amazon;
// Jump Game
public class Q55 {

    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length, last = n - 1;
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] + i >= last) {
                last = i;
            }
        }
        return last == 0;
    }

    public boolean canJump1(int[] nums) {
        int n = nums.length;

        // max position one could reach
        // starting from index <= i
        int maxPos = nums[0];

        for (int i = 1; i < n; ++i) {
            // if one could't reach this point
            if (maxPos < i) {
                return false;
            }
            maxPos = Math.max(maxPos, nums[i] + i);
        }
        return true;
    }

    public boolean canJump2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length, last = 0;
        for(int i = 0; i <= last; i++) {
            last = Math.max(last, i + nums[i]);
            if(last >= n - 1) {
                return true;
            }

        }
        return false;
    }
}
