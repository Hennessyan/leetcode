package amazon;

import java.util.HashSet;
import java.util.Set;

// Bitwise ORs of Subarrays
public class Q898 {
    // O(n^2) O(n^2) - TLE
    public int subarrayBitwiseORs0(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        Set<Integer> seen = new HashSet<>();
        for(int a : arr) {
            seen.add(a);
        }
        for(int l = 1; l <= n; l++) {
            for(int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if(l == 1) {
                    dp[i][j] = arr[j];
                    continue;
                }
                dp[i][j] = dp[i][j - 1] | arr[j];
                seen.add(dp[i][j]);
            }
        }
        return seen.size();
    }
    // O(n^2) O(n) - TLE
    public int subarrayBitwiseORs1(int[] arr) {
        int n = arr.length;
        int[] dp = arr.clone();
        Set<Integer> seen = new HashSet<>();
        for(int a : arr) {
            seen.add(a);
        }
        for(int l = 2; l <= n; l++) {
            for(int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                seen.add(dp[i] = dp[i] | arr[j]);
            }
        }
        return seen.size();
    }
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-898-bitwise-ors-of-subarrays/
    // dp[i] = {a[i], a[i] | a[i-1], a[i] | a[i-1] | a[i-2], ..., a[i] | a[i-1] | ... | a[0]}
    // length of dp[i] at most 32
    // TC & SC : O(n*lg(max(A)) < O(32n)
    // cur is not same as ans, it contains value end with ith value in arr
    // [8, 4, 2, 1]
    // 8
    // 8|4  4
    // 8|4|2  4|2  2
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        for(int a : arr) {
            Set<Integer> next = new HashSet<>();
            next.add(a);
            for(int b : cur) {
                next.add(a | b);
            }
            ans.addAll(next);
            cur = next;
        }
        return ans.size();
    }
}

