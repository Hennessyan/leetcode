package challenge.april;
// Check If a Number Is Majority Element in a Sorted Array
// Q1331
public class Q1150 {

    public static void main(String[] args) {
        Q1150 q = new Q1150();
        int[] nums = {2,4,5,5,5,5,5,6,6};
        System.out.println(q.isMajorityElement(nums, 5));   // true
    }

    public boolean isMajorityElement(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int l = search(nums, target);
        int r = l + nums.length / 2;
        return r < nums.length && nums[r] == target;
    }
    private int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(nums[m] >= target) {
                r = m;
            }else {
                l = m + 1;
            }
        }
        return l;
    }
}
