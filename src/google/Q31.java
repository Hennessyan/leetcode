package google;

import java.util.Arrays;

// Next Permutation
public class Q31 {

    public static void main(String[] args) {
        Q31 q = new Q31();
        int[] nums = {-1, -2, -3};
        q.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    // O(n) O(1)
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 1;
        while(i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if(i != 0) {
            swap(nums, i - 1);
        }
        reverse(nums, i);
    }
    private void swap(int[] nums, int i) {
        for(int j = nums.length - 1; j > i; j--) {
            if(nums[j] > nums[i]) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                break;
            }
        }
    }
    private void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while(i < j) {
            int tmp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = tmp;
        }
    }
}
