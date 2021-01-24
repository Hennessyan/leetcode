package amaon;

import java.util.Arrays;
import java.util.HashMap;

// Combination Sum IV
public class Q377 {
    // ask question before coding!
    // Note that different sequences are counted as different combinations.
    // ask if the array is sorted. Optimization sort and break.

    public static void main(String[] args) {
        Q377 q = new Q377();
        int[] nums = {1, 2, 3};
        System.out.println(q.combinationSum4(nums, 4));
//        7
//        (1, 1, 1, 1)
//        (1, 1, 2)
//        (1, 2, 1)
//        (1, 3)
//        (2, 1, 1)
//        (2, 2)
//        (3, 1)
    }
    // DP - bottom-up : O(n * t) O(t)
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i =  1; i <= target; i++) {
            for(int num : nums) {
                if(i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
    // DP - top-down : O(n * t) O(t)
    // ask if the array is sorted. Optimization sort and break.
    // [3,1,2,4] 4 => 8
    // int[] memo = new int[target+1] will get TLE.
    private HashMap<Integer, Integer> memo;

    public int combinationSum41(int[] nums, int target) {
        // minor optimization
        // Arrays.sort(nums);
        memo = new HashMap<>();
        return combs(nums, target);
    }

    private int combs(int[] nums, int remain) {
        if (remain == 0)
            return 1;
        if (memo.containsKey(remain))
            return memo.get(remain);

        int result = 0;
        for (int num : nums) {
            if (remain - num >= 0)
                result += combs(nums, remain - num);
            // minor optimizaton, early stopping
            // else
            //     break;
        }
        memo.put(remain, result);
        return result;
    }
    // [3, 33, 333]
    // 1000
    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);  //注意这里
        return comb(nums, target, memo);
    }

    private int comb(int[] nums, int target, int[] memo) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (memo[target] != -1) {   // memo[target] != 0 不对，会TLE
            return memo[target];
        }
        int res = 0;
        for (int n: nums) {
            int remaining = target - n;
            res += comb(nums, remaining, memo);
        }
        memo[target] = res;
        return res;
    }


    // below method is wrong : just calculate num of combination => 4
//    public int combinationSum4(int[] nums, int target) {
//        if(nums == null || nums.length == 0) {
//            return 0;
//        }
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        for(int num : nums) {
//            for(int i = num; i <= target; i++) {
//                dp[i] += dp[i - num];
//            }
//        }
//        return dp[target];
//    }
}
