package microsoft;

import java.util.Arrays;

// 2 Keys Keyboard
public class Q650 {
    // O(sqrt(n)) O(1)
    // sum of prime factors
    // 100 = 2 * 2 * 5 * 5
    // 1 + 1 => 2
    // 2 + 2 => 4
    // 4 * 5 => 20
    // 20 * 5 = 100
    public int minSteps0(int n) {
        int ans = 0, d = 2;
        while(n > 1) {
            while(n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
    // https://www.youtube.com/watch?v=t-msCeBTlBY
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[1] = 0;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                if(i % j == 0) {
                    int k = i / j;
                    dp[i] = Math.min(dp[i], dp[k] + 1 + j - 1);
                    // break;       //优化
                }
            }
        }
        return dp[n];
    }

    public int minSteps2(int n) {
        int[] dp = new int [n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            for (int j = 2; j <= h; ++j) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i/j];
                    break;
                }
            }
        }
        return dp[n];

    }
}
