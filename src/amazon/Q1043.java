package amazon;
// Partition Array for Maximum Sum
public class Q1043 {

    // dp[i] => max sum for arr[0] ~ arr[i-1]
    // dp[i] = Max{dp[i-k] + Max{arr[i-k+1] ~ arr[i]} * k}
    // method1 O(nk) O(n)
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int tmp_max = Integer.MIN_VALUE;
            for(int j = 1; j <= Math.min(i, k); j++) {
                tmp_max = Math.max(tmp_max, arr[i - j]);
                dp[i] = Math.max(dp[i], dp[i-j] + tmp_max * j);
            }
        }
        return dp[n];
    }
    // better than method1
    public int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int max = arr[0];
        for(int i = 1; i < k; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            dp[i] = max * (i + 1);
        }
        for(int i = k; i < n; i++) {
            max = arr[i];
            for(int j = 0; j < k; j++) {
                max = Math.max(max, arr[i - j]);
                dp[i] = Math.max(dp[i], dp[i - j - 1] + max * (j + 1));
            }
        }
        return dp[n - 1];
    }
}
