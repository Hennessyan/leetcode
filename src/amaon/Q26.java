package amaon;
// Remove Duplicates from Sorted Array
public class Q26 {

    public static void main(String[] args) {
        Q26 q = new Q26();
        int[] nums = {1,1,2};
        System.out.println(q.removeDuplicates(nums));   // 2
    }
    // O(n) O(1)
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for(int num : nums) {
            if(num != nums[i]) {
                nums[++i] = num;
            }
        }
        return i + 1;
    }

    public int removeDuplicates1(int[] nums) {
        int i = 0;
        for(int n : nums) {
            if(i == 0 || nums[i - 1] < n) {
                nums[i++] = n;
            }
        }
        return i;
    }
}
