package amazon;

import java.util.Arrays;

// Single Number III
public class Q260 {

    public static void main(String[] args) {
        Q260 q = new Q260();
        int[] nums = {1,2,1,3,2,5};
        int[] res = q.singleNumber(nums);
        System.out.println(Arrays.toString(res));
    }
    // O(n) O(1)
    public int[] singleNumber(int[] nums) {
        int combine = 0;
        for(int n : nums) {
            combine ^= n;
        }
        // first bit diff
        int diff = combine & (-combine);
        int a = 0;
        for(int n : nums) {
            if((diff & n) > 0) {
                a ^= n;
            }
        }
        return new int[]{a, a ^ combine};
    }
}
