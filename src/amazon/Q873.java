package amazon;

import java.util.HashMap;
import java.util.Map;

// Length of Longest Fibonacci Subsequence
public class Q873 {
    // DP : O(n^2) O(nlgm) - max value in arr.
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> index = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        Map<Integer, Integer> lenMap = new HashMap<>(); // must record previous comb pair as key.
        int ans = 0;
        for(int k = 2; k < n; k++) {
            for(int j = k - 1; j > 0; j--) {
                int i = index.getOrDefault(arr[k] - arr[j], -1);
                if(i >= 0 && i < j) {
                    int key = i * n + j;
                    int len = lenMap.getOrDefault(key, 2) + 1;
                    ans = Math.max(ans, len);
                    lenMap.put(j * n + k, len);
                }
            }
        }
        return ans;
    }
    // method2: much faster than method1.
    public int lenLongestFibSubseq1(int[] arr) {
        int[][] dp= new int[arr.length][arr.length];

        int maxLen= 0;
        for (int i= 2; i< arr.length; i++){
            int l= 0, r= i-1;
            while(l< r){
                int sum= arr[l]+ arr[r];
                if (sum< arr[i]) l++;
                else if (sum> arr[i]) r--;
                else {
                    dp[r][i]= Math.max(dp[r][i], dp[l][r]+ 1);
                    maxLen= Math.max(maxLen, dp[r][i]);
                    l++;
                    r--;
                    // System.out.println(maxLen);
                }
            }
        }

        return maxLen== 0? 0: maxLen+2;
    }
}
