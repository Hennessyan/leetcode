package lyft;

import java.util.Arrays;

//Product of Array Except Self
public class Q238 {

    public static void main(String[] args) {
        Q238 q = new Q238();
        int[] nums = {1,2,3,4};
        int[] res = q.productExceptSelf(nums);  //24,12,8,6
        System.out.println(Arrays.toString(res));
    }

    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for(int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for(int i = len - 1; i > 0; i--) {
            res[i] *= res[0];
            res[0] *= nums[i];
        }
        return res;
    }
}
