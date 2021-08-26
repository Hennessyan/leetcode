package google;
// Jump Game V
// 1345 1696
public class Q1340 {
    // O(n*d) O(n)
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] dp = new Integer[n];
        int max = 0;

        for(int i = 0; i < n; i++) {
            if(dp[i] == null) {
                max = Math.max(max, dfs(arr, dp, i, d));
            }

        }

        return max;
    }
    private int dfs(int[] arr, Integer[] dp, int i, int d) {
        if(dp[i] != null) {
            return dp[i];
        }
        int tmp = 0;
        for(int l = i - 1; l >= i - d && l >= 0; l--) {
            if(arr[l] >= arr[i]) break;
            tmp = Math.max(tmp, dfs(arr, dp, l, d));
        }
        for(int r = i + 1; r <= i + d && r < arr.length; r++) {
            if(arr[r] >= arr[i]) break;
            tmp = Math.max(tmp, dfs(arr, dp, r, d));
        }
        dp[i] = 1 + tmp;
        return dp[i];
    }

//    public int maxJumps(int[] arr, int d) {
//        int n = arr.length;
//        int[] dp = new int[n];
//        int max = 0;
//
//        for(int i = 0; i < n; i++) {
//            if(dp[i] == 0) {
//                max = Math.max(max, dfs(arr, dp, i, d));
//            }
//
//        }
//
//        return max;
//    }
//    private int dfs(int[] arr, int[] dp, int i, int d) {
//        if(dp[i] != 0) {
//            return dp[i];
//        }
//        int tmp = 0;
//        for(int l = i - 1; l >= i - d && l >= 0; l--) {
//            if(arr[l] >= arr[i]) break;
//            tmp = Math.max(tmp, dfs(arr, dp, l, d));
//        }
//        for(int r = i + 1; r <= i + d && r < arr.length; r++) {
//            if(arr[r] >= arr[i]) break;
//            tmp = Math.max(tmp, dfs(arr, dp, r, d));
//        }
//        dp[i] = 1 + tmp;
//        return dp[i];
//    }
}
