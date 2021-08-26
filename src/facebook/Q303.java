package facebook;
// Range Sum Query 2D - Immutable
public class Q303 {
    // O(1) O(n)
    class NumArray {

        int[] sum;
        // O(n)
        public NumArray(int[] nums) {
            int n = nums.length;
            sum = new int[n + 1];

            for(int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }
        // O(1)
        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }
}
