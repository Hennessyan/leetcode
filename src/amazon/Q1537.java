package amazon;
// Get the Maximum Score
public class Q1537 {
    // O(n) O(n)
    public int maxSum(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length, i = 0, j = 0, m = 1_000_000_007;
        long[] dp1 = new long[n1 + 1];
        long[] dp2 = new long[n2 + 1];
        while(i < n1 && j < n2) {
            if(nums1[i] == nums2[j]) {
                dp1[i+1] = dp2[j+1] = Math.max(dp1[i], dp2[j]) + nums1[i];
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) {
                dp1[i+1] = dp1[i] + nums1[i++];
            } else {
                dp2[j+1] = dp2[j] + nums2[j++];
            }
        }
        while(i < n1) {
            dp1[i+1] = dp1[i] + nums1[i++];
        }
        while(j < n2) {
            dp2[j+1] = dp2[j] + nums2[j++];
        }
        return (int) (Math.max(dp1[n1], dp2[n2]) % m);
    }
    // O(n) O(1)
    public int maxSum1(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length, i = 0, j = 0, m = 1_000_000_007;
        long a = 0, b = 0;
        while(i < n1 || j < n2) {
            if(i < n1 && j < n2 && nums1[i] == nums2[j]) {
                a = b = Math.max(a, b) + nums1[i];
                i++;
                j++;
            } else if(i < n1 && (j == n2 || nums1[i] < nums2[j])) {
                a += nums1[i++];
            } else {
                b += nums2[j++];
            }
        }
        return (int) (Math.max(a,b) % m);
    }
}
