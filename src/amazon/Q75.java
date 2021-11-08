package amazon;
// Sort Colors
public class Q75 {

    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int i = 0, j = 0, k = n - 1;
        while(j <= k) {
            if(nums[j] == 2) {
                nums[j] = nums[k];
                nums[k--] = 2;
            } else if(nums[j] == 0) {
                nums[j++] = nums[i];
                nums[i++] = 0;
            } else {
                j++;
            }
        }
    }
}
