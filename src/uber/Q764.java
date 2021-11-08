package uber;

import java.util.HashSet;
import java.util.Set;

// Largest Plus Sign
public class Q764 {
    // brute-force : O(n^3) O(mines.length)
    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for(int[] m : mines) {
            set.add(m[0] * n + m[1]);
        }
        int max = 0, k = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                k = 0;
                while(r - k >= 0 && r + k < n && c - k >= 0 && c + k < n &&
                        !set.contains((r - k) * n + c) &&
                        !set.contains((r + k) * n + c) &&
                        !set.contains(r * n + c - k) &&
                        !set.contains(r * n + c + k)) {
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }
    // O(n^2) O(n^2)
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for(int[] m : mines) {
            set.add(m[0] * n + m[1]);
        }
        int ans = 0, total = 0;
        int[][] dp = new int[n][n];

        for(int r = 0; r < n; r++) {
            total = 0;
            for(int c = 0; c < n; c++) {
                total = set.contains(r * n + c) ? 0 : total + 1;
                dp[r][c] = total;
            }
            total = 0;
            for(int c = n - 1; c >= 0; c--) {
                total = set.contains(r * n + c) ? 0 : total + 1;
                dp[r][c] = Math.min(dp[r][c], total);
            }
        }

        for(int c = 0; c < n; c++) {
            total = 0;
            for(int r = 0; r < n; r++) {
                total = set.contains(r * n + c) ? 0 : total + 1;
                dp[r][c] = Math.min(dp[r][c], total);
            }
            total = 0;
            for(int r = n - 1; r >= 0; r--) {
                total = set.contains(r * n + c) ? 0 : total + 1;
                dp[r][c] = Math.min(dp[r][c], total);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;
    }
}
