package google;

import java.util.Arrays;
// similar as Q1231
// Split Array Largest Sum
//http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-410-split-array-largest-sum/
public class Q410 {

    public static void main(String[] args) {
        Q410 q = new Q410();
        int[] nums = {7, 2, 5, 10, 8};
        System.out.println(q.splitArray(nums, 2));
    }

    // DP - 熟悉这种思维方式,常规DP!!!
    /*  dp[i][j] := min of largest sum of splitting nums[0] ~ nums[j] into i groups.
     *  dp[1][j] = sum(nums[0] ~nums[j])
     *  dp[i][j] = min(max(dp[i-1][k], sum(nums[k+1] ~ nums[j]))) 0 <= k < j
     *  O(mn^2) O(mn)
     */
    public int splitArray0(int[] nums, int m) {
        if (nums == null || nums.length < m) {
            return 0;
        }
        int n = nums.length;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for(int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++) {
            dp[0][i] = sums[i];
        }
        for(int i = 1; i < m; i++) {
            for(int j = i; j < n; j++) {    //元素个数必须大于等于分组数,不然不够分.
                int min = Integer.MAX_VALUE;
                for(int k = 0; k < j; k++) {
                    min = Math.min(min, Math.max(dp[i-1][k], sums[j] - sums[k]));
                }
                dp[i][j] = min;
            }
        }
        return dp[m-1][n-1];
    }

    private int[] sums;
    private int[][] memo;
    public int splitArray1(int[] nums, int m) {
        int n = nums.length;
        sums = new int[n];
        sums[0] = nums[0];
        for(int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        memo = new int[m][n];
        for(int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return help(n - 1, m - 1);
    }
    private int help(int j, int i) {
        if(i == 0) {
            return sums[j];
        }
        if(i > j) {
            return Integer.MAX_VALUE;
        }
        if(memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for(int k = 0; k < j; k++) {
            res = Math.min(res, Math.max(help(k, i - 1), sums[j] - sums[k]));
        }
        return memo[i][j] = res;
    }

    //Binary search: O(lg(sum(nums))*n) O(1) => O(n) O(1)
    public int splitArray(int[] nums, int m) {
        long l = max(nums);
        long r = sum(nums) + 1;
        while(l < r) {
            long limit = l + (r - l) / 2;
            int tmp = group(nums, limit);
            if(tmp > m) {
                l = limit + 1;
            }else {
                r = limit;
            }
        }
        return (int) l;
    }
    private long max(int[] nums) {
        long res = 0l;
        for(int n : nums) {
            res = Math.max(res, n);
        }
        return res;
    }
    private long sum(int[] nums) {
        long res = 0l;
        for(int n : nums) {
            res += n;
        }
        return res;
    }
    private int group(int[] nums, long limit) {
        long sum = 0;
        int groups = 1;
        for (int num : nums) {
            if (sum + num > limit) {
                sum = num;
                ++groups;
            } else {
                sum += num;
            }
        }
        return groups;
    }
}
