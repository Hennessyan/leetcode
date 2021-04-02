package amazon;
// Remove Element
public class Q27 {
    // if element move is rare.
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            if(nums[l] == val) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r--] = tmp;
            } else {
                l++;
            }
        }
        return l;
    }
    // [1,2,3,5,4] val = 4
    // [4,1,2,3,5] val = 4
    // order can be changed, then why we need to move / copy multiple times
    public int removeElement1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
