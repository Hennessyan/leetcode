package amazon;
// Remove Duplicates from Sorted Array II
public class Q80 {

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for(int num : nums) {
            if(i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }

    public int removeDuplicates1(int[] nums) {
        int k = 2;
        int n = nums.length;
        if(n <= k) {
            return n;
        }
        int count = 1, i = 1, j = 1;
        while(j < n) {
            if(nums[j] != nums[j - 1]) {
                nums[i++] = nums[j++];
                count = 1;
            }else {
                if(count < k) {
                    nums[i++] = nums[j++];
                    count++;
                }else {
                    j++;
                }
            }
        }
        return i;
    }
}
