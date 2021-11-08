package google;

import java.util.Arrays;
import java.util.LinkedList;

// Number of Squareful Arrays
// DP is similar to Q943
public class Q996 {
    // backtrack : O(n*n!) O(n)
    int total = 0;
    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;
        Arrays.sort(nums);
        backtrack(nums, new LinkedList<>(), new boolean[n]);
        return total;
    }
    private void backtrack(int[] nums, LinkedList<Integer> list, boolean[] seen) {
        if(list.size() == nums.length) {
            total++;
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(seen[i] || (i > 0 && nums[i] == nums[i - 1] && !seen[i - 1])) continue;
            if(list.size() > 0 && !squareful(list.getLast(), nums[i])) continue;
            list.add(nums[i]);
            seen[i] = true;
            backtrack(nums, list, seen);
            list.removeLast();
            seen[i] = false;
        }
    }
    private boolean squareful(int x, int y) {
        int sqrt = (int) Math.sqrt(x + y);
        return sqrt * sqrt == x + y;
    }
    // DP : O(n^2 * 2^n) O(2^n)
    public int numSquarefulPerms1(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        boolean[][] g = new boolean[n][n];
        int[][] dp = new int[1 << n][n];
        // g[i][j] == true if A[i], A[j] are squareful.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                int sqrt = (int) Math.sqrt(A[i] + A[j]);
                if(sqrt * sqrt == A[i] + A[j]) {
                    g[i][j] = true;
                }
            }
        }
        // dp[s][i] := number of ways to reach state s and ends with node i.
        for(int i = 0; i < n; i++) {
            if(i == 0 || A[i] != A[i - 1]) {
                dp[1 << i][i] = 1;
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] == 0) continue;
                for(int k = 0; k < n; k++) {
                    if(!g[j][k]) continue;
                    if((i & (1 << k)) > 0) continue;
                    if(k > 0 && A[k] == A[k - 1] && !((i & (1 << (k - 1))) > 0)) continue;
                    dp[i | (1 << k)][k] += dp[i][j];
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            ans += dp[(1 << n) - 1][i];
        }
        return ans;
    }
}
