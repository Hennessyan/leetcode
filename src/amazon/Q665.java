package amazon;
// Non-decreasing Array
public class Q665 {
    // O(n) O(1)
    public boolean checkPossibility(int[] nums) {
        boolean canModify = true;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] > nums[i]) {
                if(canModify) {
                    canModify = !canModify;

                    if(i == 1 || nums[i - 2] <= nums[i]) {  // [1,4,1,2]
                        nums[i - 1] = nums[i];

                    } else {
                        nums[i] = nums[i - 1];
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
