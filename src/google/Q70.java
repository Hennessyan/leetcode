package google;
// Climbing Stairs
public class Q70 {

    public static void main(String[] args) {
        Q70 q = new Q70();
        System.out.println(q.climbStairs(15));
    }
    // O(2^n) O(n) - TLE
    public int climbStairs0(int n) {
        if(n < 0) {
            return 0;
        }
        if(n == 0 || n == 1) {
            return 1;
        }
        return climbStairs0(n - 1) + climbStairs0(n - 2);
    }

    public int climbStairs1(int n) {
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1;
        return help(memo, n);
    }
    private int help(int[] memo, int n) {
        if(memo[n] != 0) {
            return memo[n];
        }
        if(n == 0 || n == 1) {
            return 1;
        }
        return memo[n] = help(memo, n - 1) + help(memo, n - 2);
    }

    public int climbStairs2(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
