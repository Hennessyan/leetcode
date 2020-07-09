package challenge.june;
// Search Insert Position
public class Q35 {

    public static void main(String[] args) {
        Q35 q = new Q35();
        int[] nums = {1,2,3,4,5,6,7};
        System.out.println(q.searchInsert(nums, 3));
    }

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(nums[m] == target) {
                return m;
            } else if(nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
