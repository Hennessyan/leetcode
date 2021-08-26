package amazon;
// Climbing Stairs
public class Q70 {

    public int climbStairs(int n) {
        if(n < 2) {
            return 1;
        }
        int a = 1, b = 1, c;
        for(int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public int climbStairs1(int n) {
        Integer[] memo = new Integer[n + 1];
        return climbStairs(n, memo);
    }
    private int climbStairs(int n, Integer[] memo) {
        if(n < 2) {
            return 1;
        }
        if(memo[n] != null) {
            return memo[n];
        }
        memo[n] = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
        return memo[n];
    }
}
