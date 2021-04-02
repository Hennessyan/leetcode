package microsoft;

import java.util.Arrays;

// Unique Paths
public class Q62 {

    // O(m*n) O(n)
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int uniquePaths1(int m, int n) {
        int N = n + m - 2;// how much steps we need to do
        int k = m - 1; // number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for(int i = 1; i <= k; i++){        //别用N - i + 1,结果不对.
            res = res * (N - k + i) / i;	//不能使用res *= (N - k + i) / i;
//        	System.out.println(res);		//当（4-3+2）/2=2.5时,上方法会是res = 4*2 = 8
        }									//而正确方法结果为res = 2.5 * 4 = 10
//        for(int i = 1; i <= k; i++){
//        	res *= N - k + i;
//        	res /= i;
//        }
        return (int)res;
    }
}
