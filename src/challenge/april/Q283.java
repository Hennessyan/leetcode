package challenge.april;

import java.util.Arrays;

// Move Zeroes
public class Q283 {

    public static void main(String[] args) {
        Q283 q = new Q283();
        int[] nums = {0,1,0,3,12};
        q.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));  //[1,3,12,0,0]
    }

    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        int index= 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while(index < nums.length) {
            nums[index++] = 0;
        }
    }
}
