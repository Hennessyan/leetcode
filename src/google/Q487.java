package google;
// Max Consecutive Ones II
public class Q487 {
    // O(n) O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0, zeros = 0, max = 0;
        while(r < n) {
            if(nums[r] == 0) {
                zeros++;
            }
            while(zeros == 2) {
                if(nums[l] == 0) {
                    zeros--;
                }
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
}
