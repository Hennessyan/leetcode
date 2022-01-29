package amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Partition to K Equal Sum Subsets
// Q1723
public class Q698 {
    // TLE : O(n*n!) O(n)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length < k) return false;
        int sum = 0, n = nums.length;
        for(int num : nums) {
            sum += num;
        }
        if(sum % k != 0) return false;
        int target = sum / k;
        int[] seen = new int[n];
        return backtrack(nums, 0, 0, k, target, seen);
    }
    private boolean backtrack(int[] nums, int sum, int count, int k, int target, int[] seen) {
        int n = nums.length;
        if(count == k - 1) return true;
        if(sum > target) return false;
        if(sum == target) {
            return backtrack(nums, 0, count + 1, k, target, seen);
        }

        for(int i = 0; i < n; i++) {
            if(seen[i] == 0) {
                seen[i] = 1;
                if(backtrack(nums, sum + nums[i], count, k, target, seen)) {
                    return true;
                }
                seen[i] = 0;
            }
        }
        return false;
    }

    // O(n*2^n) - length of key is n => 2^n * n
    // O(2^n)
    public boolean canPartitionKSubsets1(int[] nums, int k) {
        if(nums == null || nums.length < k) return false;
        int sum = 0, target = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % k != 0) return false;
        target = sum / k;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        reverse(sorted);    // increase-order will cause more recursion branches

        Map<Integer, Boolean> memo = new HashMap<>();
        return backtrack(sorted, 0, 0, 0, k, target, 0, memo);
    }
    private boolean backtrack(int[] arr, int index, int sum, int count, int k, int target, int status, Map<Integer, Boolean> memo) {
        int n = arr.length;
        if(count == k - 1) {
            return true;
        }
        if(sum > target) {
            return false;
        }
        if(memo.containsKey(status)) {
            return memo.get(status);
        }
        if(sum == target) {
            boolean ans = backtrack(arr, 0, 0, count + 1, k, target, status, memo);
            memo.put(status, ans);
            return ans;
        }
        for(int i = index; i < n; i++) {
            if((status & (1 << i)) == 0) {
                status ^= 1 << i;
                if(backtrack(arr, i + 1, sum + arr[i], count, k, target, status, memo)) {
                    return true;
                }
                status ^= 1 << i;
            }
        }
        memo.put(status, false);
        return false;
    }

    private void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while(l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
}
