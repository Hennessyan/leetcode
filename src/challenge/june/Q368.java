package challenge.june;
// Largest Divisible Subset

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q368 {

    public static void main(String[] args) {
        Q368 q = new Q368();
        int[] nums = {1,2,4,8};
        List<Integer> res = q.largestDivisibleSubset(nums);
        System.out.println(res);
    }

    // dp[i] - max size of subset which nums[i] is largest num in this divisible subset
    // dp[i] = max(dp[j] if dp[i] % dp[j] for j in [0, i-1]) + 1;
    // O(n^2) O(n)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int n = nums.length, max_index = 0, max_size = 0;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] != 0 && nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
            if(dp[i] > max_size) {
                max_size = dp[i];
                max_index = i;
            }
        }
        List<Integer> res = new LinkedList<>();
        int pre = nums[max_index];
        int l = max_size;
        for(int j = max_index; j >= 0; j--) {
            if(dp[j] == l && pre % nums[j] == 0) {  //注意这里backtrack的时候需要再MODE, 因为DP[j]==l可能是别的组合的结果.
                res.add(0, nums[j]);
                pre = nums[j];      //可以不用这一步
                l--;
            }
        }
        return res;
    }
    // O(n^2) O(n^2)
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        // Test case with empty set.
        int n = nums.length;
        if (n == 0) return new ArrayList();

        // Container to keep the largest divisible subset
        //   that ends with each of the nums.
        List<ArrayList> EDS = new ArrayList();
        for (int num : nums) EDS.add(new ArrayList());

        /* Sort the original list in ascending order. */
        Arrays.sort(nums);

        /* Calculate all the values of EDS(X_i) */
        for (int i = 0; i < n; ++i) {
            List<Integer> maxSubset = new ArrayList();

            // Find the largest divisible subset of previous elements.
            for (int k = 0; k < i; ++k)
                if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size())
                    maxSubset = EDS.get(k);

            // Extend the found subset with the element itself.
            EDS.get(i).addAll(maxSubset);
            EDS.get(i).add(nums[i]);
        }
        /* Find the largest of EDS values  */
        List<Integer> ret = new ArrayList();
        for (int i = 0; i < n; ++i)
            if (ret.size() < EDS.get(i).size()) ret = EDS.get(i);
        return ret;
    }
}
