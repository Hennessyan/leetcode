package challenge.may;
// Maximum Sum Circular Subarray
public class Q918 {

    public static void main(String[] args) {
        Q918 q = new Q918();
        int[] A = {3,-1,2,-1};
        System.out.println(q.maxSubarraySumCircular(A));    // 4
    }

    // method1 - O(n) O(n)
    public int maxSubarraySumCircular1(int[] A) {
        int ans = A[0], cur = A[0];
        int len = A.length;
        // check one interval
        for(int i = 1; i < len; i++) {
            cur = Math.max(0, cur) + A[i];
            ans = Math.max(ans, cur);
        }

        // check two interval - left part + right part
        int[] rsum = new int[len];
        rsum[len - 1] = A[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            rsum[i] = rsum[i+1] + A[i];
        }
        int[] rmax = new int[len];
        rmax[len - 1] = A[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i+1], rsum[i]);
        }
        int left = 0;
        for(int i = 0; i < len - 2; i++) {
            left += A[i];
            ans = Math.max(ans, left + rmax[i+2]);
        }
        return ans;
    }

    // https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
    // local_max[i] = max(A[i], A[i] + local_max[i - 1])
    // O(n) O(1)
    public int maxSubarraySumCircular(int[] A) {
        int sum = 0;
        for(int a : A) {
            sum += a;
        }
        int len = A.length;
        int ans1 = kadane(A, 0, len - 1, 1);
        //有可能会被全选,这时asn2/ans3为0.为了避免这个情况,去掉第一个或者最后一个:
        int ans2 = sum + kadane(A, 0, len - 2, -1);
        int ans3 = sum + kadane(A, 1, len - 1, -1);
        return Math.max(ans1, Math.max(ans2, ans3));
    }
    private int kadane(int[] A, int i, int j, int sign) {
        int cur = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++) {
            cur = Math.max(cur, 0) + A[k] * sign;
            max = Math.max(cur, max);
        }
        return max;
    }
}
