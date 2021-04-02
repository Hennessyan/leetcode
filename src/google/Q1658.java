package google;
// Minimum Operations to Reduce X to Zero
public class Q1658 {
    // O(n) O(1)
    // 针对全是POSITIVE NUM的数组可以如此: 最小个数组成X,可以看成最大SUB ARRAY和为TOTAL-X
    // 有可能全部需要,所以METHOD1不能maxi = 0, method2不能min = n.
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int num : nums) { 
            total += num;
        }
        int n = nums.length;
        int maxi = -1;
        int left = 0;
        int current = 0;

        for (int right = 0; right < n; right++) {
            // sum([left ,..., right]) = total - x
            current += nums[right];
            // if larger, move `left` to left
            while (current > total - x && left <= right) {  //必须有边界判断 -> [1,1] 3
                current -= nums[left];
                left += 1;
            }
            // check if equal
            if (current == total - x) {
                maxi = Math.max(maxi, right - left + 1);
            }
        }
        return maxi != -1 ? n - maxi : -1;
    }
    // O(n) O(1)
    public int minOperations1(int[] nums, int x) {
        int n = nums.length, l = 0;
        int sum = 0, min = Integer.MAX_VALUE;   //不能用MIN=N,因为可能全部都需要.
        for(int num : nums) {
            sum += num;
        }
        for(int r = 0; r < n; r++) {
            sum -= nums[r];
            while(sum < x && l <= r) {
                sum += nums[l++];
            }
            if(sum == x) {
                min = Math.min(min, (n - 1 - r) + l);
            }
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }
}
