package challenge.june;
// Unique Binary Search Trees
public class Q96 {

    public static void main(String[] args) {
        Q96 q = new Q96();
        System.out.println(q.numTrees(3));  // 5
    }
    // O(n^2) O(n)
    // dp[i] - # of combinations for i nodes.
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
