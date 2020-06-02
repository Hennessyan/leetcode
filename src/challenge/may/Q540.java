package challenge.may;
// Single Element in a Sorted Array
public class Q540 {

    public static void main(String[] args) {
        Q540 q = new Q540();
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(q.singleNonDuplicate(nums));
    }

    // O(lgn) O(1)
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            boolean even = (r - m) % 2 == 0;
            if(nums[m] == nums[m + 1]) {
                if(even) {
                    l = m + 2;
                }else {
                    r = m - 1;
                }
            }else if(nums[m] == nums[m - 1]) {
                if(even) {
                    r = m - 2;
                }else {
                    l = m + 1;
                }
            }else {
                return nums[m];
            }
        }
        return nums[l];
    }

    public int singleNonDuplicate1(int[] nums) {
        for(int i = 0; i < nums.length - 1; i += 2) {
            if(nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}
