package microsoft;

import java.util.Arrays;

// Maximum Vacation Days
public class Q568 {
    // memo : O(n^2k) O(nk)
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length, k = days[0].length;
        int[][] memo = new int[n][k];
        for(int[] tmp : memo)
            Arrays.fill(tmp, -1);
        return dfs(flights, days, 0, 0, memo);
    }
    private int dfs(int[][] flights, int[][] days, int f, int d, int[][] memo) {
        if(d == days[0].length) return 0;
        if(memo[f][d] != -1) return memo[f][d];
        int max = 0;
        for(int i = 0; i < flights.length; i++) {
            if(flights[f][i] == 1 || f == i) {  // can stay at original city
                max = Math.max(max, dfs(flights, days, i, d + 1, memo) + days[i][d]);
            }
        }
        return memo[f][d] = max;
    }
    // wrong method, we don't know which city at kth day to choose.
    public int maxVacationDays1(int[][] flights, int[][] days) {
        int n = flights.length, k = days[0].length;
        int[][] dp = new int[n][k + 1];
        for(int d = 0; d < k; d++) {
            for(int cur = 0; cur < n; cur++) {
                for(int prev = 0; prev < n; prev++) {
                    if(flights[prev][cur] == 1 || cur == prev) {
                        dp[cur][d+1] = Math.max(dp[cur][d+1], days[cur][d] + dp[prev][d]);
                    }
                }
            }
        }

        return dp[n-1][k];
    }
    // correct DP: O(n^2k) O(nk)
    public int maxVacationDays2(int[][] flights, int[][] days) {
        if (days.length == 0 || flights.length == 0) return 0;
        int[][] dp = new int[days.length][days[0].length + 1];
        for (int week = days[0].length - 1; week >= 0; week--) {
            for (int cur_city = 0; cur_city < days.length; cur_city++) {
                for (int dest_city = 0; dest_city < days.length; dest_city++) {
                    if (flights[cur_city][dest_city] == 1 || cur_city == dest_city) {
                        dp[cur_city][week] = Math.max(days[dest_city][week] + dp[dest_city][week + 1], dp[cur_city][week]);
                    }
                }
            }
        }
        return dp[0][0];
    }

    public int maxVacationDays3(int[][] flights, int[][] days) {
        int n = flights.length, k = days[0].length;
        int[][] dp = new int[2][n];
        for(int d = k - 1; d >= 0; d--) {
            int a = d & 1, b = 1 - a;
            for(int cur = 0; cur < n; cur++) {
                for(int next = 0; next < n; next++) {
                    if(flights[cur][next] == 1 || cur == next) {
                        dp[a][cur] = Math.max(dp[a][cur], dp[b][next] + days[next][d]);
                    }
                }
            }
        }
        return dp[0 & 1][0];
    }
}
