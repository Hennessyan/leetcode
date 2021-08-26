package facebook;
// Max Consecutive Ones III
// Q485, Q487
public class Q1004 {

    public int longestOnes1(int[] nums, int k) {
        int n = nums.length, zeros = 0, l = 0, r = 0, max = 0;
        while(r < n) {
            if(nums[r++] == 0) {
                zeros++;
            }
            while(zeros > k) {
                if(nums[l++] == 0) {
                    zeros--;
                }
            }
            max = Math.max(max, r - l);
        }
        return max;
    }
    // concise : https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC++Python-Sliding-Window
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        while(r < nums.length) {
            if(nums[r++] == 0) {
                k--;
            }
            if(k < 0) {
                if(nums[l] == 0) k++;
                l++;
            }
        }
        return r - l;
    }
}
