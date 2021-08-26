package amazon;
// Split Array Largest Sum
public class Q410 {

    //Binary search: O(lg(sum(nums))*n) O(1) => O(n) O(1)
    public int splitArray(int[] nums, int m) {
        int l = max(nums);  // we can use int here based on the num scope
        int r = sum(nums) + 1;

        while(l < r) {
            int limit = l + (r - l) / 2;
            int tmp = group(nums, limit);
            if(tmp > m) {
                l = limit + 1;
            } else {
                r = limit;
            }
        }
        return l;
    }
    private int group(int[] nums, int limit) {
        int count = 1, sum = 0;     // count = 1 !!!
        for(int num : nums) {
            if(sum + num > limit) {
                count++;
                sum = num;
            } else {
                sum += num;
            }

        }
        return count;
    }
    private int max(int[] nums) {
        int max = 0;
        for(int num : nums) {
            if(num > max) {
                max = num;
            }
        }
        return max;
    }
    private int sum(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        return sum;
    }
    // DP : O(mn^2) O(mn)
    // dp[i][j] : minimum largest sum for nums[0]~nums[j] into i groups.
    // dp[1][j] = sum(nums[0]~nums[j])
    // dp[i][j] = min(max(dp[i-1][k], sum(nums[k+1]~nums[j])))  (i-1 <= k < j)
    public int splitArray1(int[] nums, int m) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[m][n];
        int[] sum = new int[n];
        dp[0][0] = sum[0] = nums[0];
        for(int i = 1; i < n; i++) {
            dp[0][i] = sum[i] = nums[i] + sum[i - 1];
        }

        for(int i = 1; i < m; i++) {
            for(int j = i; j < n; j++) {    // needs at least i nums to get i groups
                int min = Integer.MAX_VALUE;
                for(int k = i - 1; k < j; k++) {
                    min = Math.min(min, Math.max(sum[j] - sum[k], dp[i-1][k]));
                }
                dp[i][j] = min;
            }
        }
        return dp[m-1][n-1];
    }
}
