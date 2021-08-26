package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Longest Increasing Subsequence
public class Q300 {
    // O(n^2) O(n)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c: dp) {
            longest = Math.max(longest, c);
        }

        return longest;
    }
    // replace the current number (b) with smaller number (a)will open the opportunity to accept the num between [a, b]
    // O(nlgn) O(n)
    public int lengthOfLIS1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            if(nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                int j = binarySearch(list, nums[i]);
                list.set(j, nums[i]);
            }
        }
        return list.size();
    }
    private int binarySearch(List<Integer> list, int val) {
        int l = 0, r = list.size() - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(list.get(m) == val) {
                return m;
            } else if(list.get(m) < val) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0;
        int[] dp = new int[nums.length];
        for(int n : nums) {
            int i = Arrays.binarySearch(dp, 0, len, n);
            if(i < 0) {
                i = - (i + 1);
            }
            System.out.println(i + " " + len);
            dp[i] = n;
            if(i == len) {
                len++;
            }
        }
        return len;
    }
}
