package amazon;
// Subarray Sums Divisible by K
public class Q974 {

    public int subarraysDivByK(int[] A, int K) {
        int n = A.length;
        int[] sum = new int[n + 1];
        int[] count = new int[K];
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }

        for(int i = 0; i <= n; i++) {       //需要从0开始,因为如果和为0的只有SUM[0]的1,不影响结果.但是有一个的时候:
            count[(sum[i] % K + K) % K]++;  // 正确情况: 2*(2-1)/2 = 1, 错误情况: 1*(1-1)/2 = 0
        }

        int ans = 0;
        for(int c : count) {
            ans += c * (c - 1) / 2;
        }
        return ans;
    }
}
