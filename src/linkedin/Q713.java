package linkedin;
// Subarray Product Less Than K
public class Q713 {
    // O(n) O(1)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;    // [1,1,1] 1 => 0
        int left = 0, right = 0, n = nums.length, product = 1, ans = 0;
        while(right < n) {
            product *= nums[right++];
            while(product >= k) product /= nums[left++];
            ans += right - left;
        }
        return ans;
    }
}
